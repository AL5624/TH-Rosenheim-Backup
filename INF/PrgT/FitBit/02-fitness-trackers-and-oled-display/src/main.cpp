#include <Adafruit_MPU6050.h>
#include <Adafruit_SSD1306.h>
#include <Adafruit_Sensor.h>
#include <arduino.h>
#include <Adafruit_GFX.h>
#include <Wire.h>

#include "I2Cdev.h"
#include "MPU6050.h"
#include <Adafruit_BMP085.h>
#include <HMC5883L_Simple.h>
extern "C" {
#include "count_steps.h"
}
#include "helper_3dmath.h"
#include "math.h"


Adafruit_MPU6050 mpu;
Adafruit_SSD1306 display = Adafruit_SSD1306(128, 32, &Wire);

MPU6050 accelgyro;
Adafruit_BMP085 bmp;
HMC5883L_Simple Compass;

struct SensorData
{
  float temperature;
  float pressure;
  float altitude;
};

const int BUTTON_PIN = 2;

const int debounceMs = 50;
volatile unsigned long millisLastInterrupt;
const unsigned short int num_of_states = 5;
volatile unsigned short int current_state = 0;

void print_current_state() {
    Serial.print("Current State: ");
    Serial.println(current_state);
}

void buttonPressedISR() {
    unsigned long interruptTime = millis();

    if (interruptTime - millisLastInterrupt > debounceMs) {
        if (current_state + 1 >= num_of_states) {
            current_state = 0;
        }
        else {
            ++current_state;
        }

        print_current_state();
    }
    millisLastInterrupt = interruptTime;
}

int16_t ax, ay, az;
int16_t gx, gy, gz;

//Count Steps
uint8_t steps = 0;

void setup() {
    Serial.begin(9600);
    pinMode(BUTTON_PIN, INPUT_PULLUP);

    millisLastInterrupt = 0;
    attachInterrupt(digitalPinToInterrupt(BUTTON_PIN), buttonPressedISR, RISING);
    
    if (!mpu.begin(104U, &Wire, 0)) {
        Serial.println("Sensor init failed");
    }

    if (!bmp.begin()) {
        Serial.println("BMP180 failed");
    }


    if (!display.begin(SSD1306_SWITCHCAPVCC, 0x3C)) { 
        Serial.println("SSD1306 allocation failed");
    }

    Wire.begin();

    // initialize mpu6050
    accelgyro.initialize();

    if (!accelgyro.testConnection()) {
        Serial.println("MPU6050 connection failed");
    }

    accelgyro.setI2CBypassEnabled(true); // set bypass mode for gateway to hmc5883L

    // initialize hmc5883l
    Compass.SetDeclination(23, 35, 'E');
    Compass.SetSamplingMode(COMPASS_SINGLE);
    Compass.SetScale(COMPASS_SCALE_130);
    Compass.SetOrientation(COMPASS_HORIZONTAL_X_NORTH);

    print_current_state();

    display.clearDisplay();
    display.setTextSize(1);
    display.setCursor(50, 0);
    display.setTextColor(SSD1306_WHITE);
    display.setRotation(0);
    display.display();
}

void state_0(const sensors_event_t &accelerometer) {
    display.println("Accelerometer: ");
    display.println("");
    display.print("x=");
    display.print(accelerometer.acceleration.x, 1);
    display.print(" y=");
    display.print(accelerometer.acceleration.y, 1);
    display.print(" z=");
    display.print(accelerometer.acceleration.z, 1);
    display.println("");
}

void state_1(const sensors_event_t &gyroscope) {
    display.println("Gyroscope: ");
    display.println("");
    display.print("x=");
    display.print(gyroscope.gyro.x, 1);
    display.print(" y=");
    display.print(gyroscope.gyro.y, 1);
    display.print(" z=");
    display.print(gyroscope.gyro.z, 1);
    display.println("");
}

void state_2(const sensors_event_t &temperature) {
    display.println("Temperature: ");
    display.println("");
    display.print(temperature.temperature);
    display.print(" Celsius ");
    display.println("");
}

void state_3() {
    display.setTextSize(2);
    display.setTextColor(SSD1306_WHITE);
    // display.setCursor(50, 0);
    display.print("Steps: ");
    display.println(steps);
    display.display();

    int8_t data[NUM_TUPLES*3] = {0};
    float  scale_f = 55.3293*3;


    for(int i = 0; i < 80*3; i=i+3) {
        if (current_state != 3) {
            break;
        }
        // read raw accel/gyro measurements from device
        accelgyro.getMotion6(&ax, &ay, &az, &gx, &gy, &gz);

        //Normalizing
        VectorFloat vectorFloat(ax, ay, az);
        VectorFloat r = vectorFloat.getNormalized();

        data[i] = (int8_t)roundf(r.x*scale_f);
        data[i+1] = (int8_t)roundf(r.y*scale_f);
        data[i+2] = (int8_t)roundf(r.z*scale_f);

        //Delay to stop fast reading of data 4s = 4000 ms -> 4000ms/80 reads = 50 ms
        delay(30);
    }
    
    steps += count_steps(data);
}

void state_4() {
    SensorData data;

    // Read all data from the BMP sensor in a single function call
    data.temperature = bmp.readTemperature();
    data.pressure = bmp.readPressure();
    data.altitude = bmp.readAltitude();

    // Check for errors in the BMP sensor data
    if (isnan(data.temperature) || isnan(data.pressure) || isnan(data.altitude))
    {
        Serial.println("Error reading data from BMP sensor");
    }

    display.println("Temperature = " + String(data.temperature, 1) + " *C");

    display.setCursor(0, 8);
    display.println("Pressure = " + String(data.pressure, 1) + " Pa");

    display.setCursor(0, 8 * 2);
    display.println("Altitude = " + String(data.altitude, 1) + " m");
}

void loop() {  
  sensors_event_t a, g, tmp;
  mpu.getEvent(&a, &g, &tmp);

  display.clearDisplay();
  display.setCursor(0, 0);
  display.setTextSize(1);

  switch (current_state)
  {
    case 0:
        state_0(a);
    break;
    case 1:
        state_1(g);
    break;
    case 2:
        state_2(tmp);
    break;
    case 3:
        state_3();
    break;
    case 4:
        state_4();
    break; 
  default:
    break;
  }

  display.display();
  delay(100);
}
