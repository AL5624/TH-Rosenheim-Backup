#include <Arduino.h>
#include <Adafruit_ZeroTimer.h>

//PIN configuration
const int LED_PIN = LED_BUILTIN; //Internal built-in LED

//Global variables
volatile bool led_state = false;

//Defines a timer on timer 3 (16 bit timer)
Adafruit_ZeroTimer timer = Adafruit_ZeroTimer(3);

/*!
 * TC3_Handler() is the ISR for timer 3.
 * We have to forward the interrupt to the Adafruit_ZeroTimer library.
 */
void TC3_Handler() {
  Adafruit_ZeroTimer::timerHandler(3);
}

/*!
 * setup() is called once on startup/reset of the Arduino
 */
void setup() {
  //Configure serial connection
  Serial.begin(9600);

  //configure PINS
  pinMode(LED_PIN, OUTPUT);
  
  //Switch LED initally off
  digitalWrite(LED_PIN, false);

  timer.configure(/*TODO: set prescaler here*/,  // prescaler
                  TC_COUNTER_SIZE_16BIT,         // bit width of timer/counter
                  TC_WAVE_GENERATION_NORMAL_FREQ // frequency or PWM mode
               );
  timer.setCompare(0, 0xFFFF); //if the internal counter=0xFFFF 
                               //then it calls the timer3_callback
  timer.setCallback(true, TC_CALLBACK_CC_CHANNEL0, /*TODO: set timer callback function pointer here*/);
  //TODO: start the timer
}

/*!
 * loop() is called as fast as possbile.
 */
void loop() {
  Serial.print("LED state is: ");
  if(led_state) {
    Serial.println("ON");
  } else {
    Serial.println("OFF");
  }
  delay(1000); //wait for 1000 ms -> 1 sec
}

/*!
 * This is the callback from timer3. It is called from the 
 * Adafruit_ZeroTimer timer library when the counter reached
 * the defined comparison value.
 */
void timer3_callback()
{
  //TODO: Toggle led_state
  //TODO: Turn LED on/off according to state
  
  //TODO: Print a message to the serial port about the change
}
