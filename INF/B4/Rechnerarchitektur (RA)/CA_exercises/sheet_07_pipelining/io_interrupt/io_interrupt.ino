//PIN configuration
const int BUTTON_PIN = ; //TODO: Specify the digital input according to your wiring

//Global variables
uint8_t counter = 0;


/*!
 * setup() is called once on startup/reset of the Arduino
 */
void setup(){
  //Configure serial connection
  Serial.begin(9600);

  //configure PINs
  pinMode(BUTTON_PIN, /*TODO*/); //TODO: configure as INPUT_PULLUP or OUTPUT
  
  //Configure interrupt
  attachInterrupt(digitalPinToInterrupt(BUTTON_PIN), /*TODO*/, RISING); //TODO: specify the ISR
}

/*!
 * loop() is called as fast as possible.
 * 
 * As you can see, there is no call to a function 
 * changing the LED state in the main-loop
*/
void loop(){
  //TODO: transmit the counter value with the serial connection
  Serial.print("The value is: ");
  Serial.println(0);
  
  delay(1000); //wait for 1000 ms -> 1 sec
}

/*!
 * isr_button_pressed() = Interrupt service routine
 * Change here the state of the LED
 */
void isr_button_pressed(){
  static unsigned long time_prev_rising_edge = 0;
  
  if (millis() - time_prev_rising_edge > 250) { //only react on rising edges every 250 ms
    //TODO: increment counter
    
    time_prev_rising_edge = millis();
  }
}
