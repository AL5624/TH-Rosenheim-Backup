//PIN configuration
const int BUTTON_PIN = 2; //TODO: Specify the digital input according to your wiring
const int LED_PIN    = LED_BUILTIN; //Internal built-in LED

//Global variables
volatile bool led_state = false;               
volatile unsigned long time_prev_rising_edge = 0;

/*!
 * setup() is called once on startup/reset of the Arduino
 */
void setup(){
  //Configure serial connection
  Serial.begin(9600);

  //configure PINs
  pinMode(BUTTON_PIN, INPUT/*TODO*/); //TODO: configure as INPUT or OUTPUT
  pinMode(LED_PIN,    OUTPUT/*TODO*/); //TODO: configure as INPUT or OUTPUT
  
  //Switch LED initally off
  digitalWrite(LED_PIN, false); 

  //Configure interrupt
  attachInterrupt(digitalPinToInterrupt(BUTTON_PIN), isr_button_pressed/*TODO*/, RISING); //TODO: specify the ISR
}

/*!    
 * loop() is called as fast as possbile.
 * 
 * As you can see, there is no call to a function 
 * changing the LED state in the main-loop
*/
void loop(){
  Serial.print("LED state is: ");
  if(led_state) {
    Serial.println("ON");
  } else {
    Serial.println("OFF");
  }
  delay(1000); //wait for 1 sec
}

/*!
 * isr_button_pressed() = Interrupt service routine
 * Change here the state of the LED
 */
void isr_button_pressed(){
  if (millis() - time_prev_rising_edge > 250) { //only react on rising edges every 250 ms
    //TODO: Toggle led_state
      led_state = !led_state;
    //TODO: Turn LED on/off according to state
    digitalWrite(LED_PIN, led_state);
    //TODO: Print a message to the serial port about the change
    if(led_state) {
    Serial.println("change ON");
    } else {
      Serial.println("change OFF");
    }
    time_prev_rising_edge = millis();  
  }
}
