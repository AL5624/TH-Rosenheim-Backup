#include "I2Cdev.h"
#include "MPU6050.h"
#include <Adafruit_BMP085.h>
#include <HMC5883L_Simple.h>
#include <Adafruit_SSD1306.h>

#define LCD_WIDTH 128
#define LCD_HEIGHT 32
#define OLED_RESET -1
#define DISPLAY_ADDRESS 0x3C

MPU6050 accelgyro;
Adafruit_BMP085 bmp;
HMC5883L_Simple Compass;


#define LED_PIN 13
bool blinkState = false;

//Count Steps
uint8_t steps = 0;

//Display Variable
Adafruit_SSD1306 display(LCD_WIDTH, LCD_HEIGHT, &Wire, OLED_RESET);

void setup() {
  Serial.begin(9600);
  Wire.begin();

  // initialize devices
  Serial.println("Initializing I2C devices...");

  // initialize mpu6050
  accelgyro.initialize();
  accelgyro.setI2CBypassEnabled(true); // set bypass mode for gateway to hmc5883L


  // initialize hmc5883l
  Compass.SetDeclination(23, 35, 'E');
  Compass.SetSamplingMode(COMPASS_SINGLE);
  Compass.SetScale(COMPASS_SCALE_130);
  Compass.SetOrientation(COMPASS_HORIZONTAL_X_NORTH);


  // configure Arduino LED for checking activity
  pinMode(LED_PIN, OUTPUT);
}

void loop() {
  display.clearDisplay();
  display.setTextSize(2);
  display.setTextColor(SSD1306_WHITE);
  display.setCursor(50, 0);
  display.println(steps);
  display.display();

  float heading = Compass.GetHeadingDegrees();
  Serial.print("Heading: \t");
  Serial.println( heading );


  // blink LED to indicate activity
  blinkState = !blinkState;
  digitalWrite(LED_PIN, blinkState);

  delay(500);
}
