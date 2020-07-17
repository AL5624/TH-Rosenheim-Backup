#define BUTTON 22   // Digital Pin 22
#define LED 21      // Digital Pin 21 

typedef enum {
  LIGHT_OFF, 
  LIGHT_ON
}  state_t;               // enum datatype for all states

state_t state;            // keep current state
unsigned long timer = 0;  // start time of timer

void setup() {
  state = LIGHT_OFF;         // set initial state
  pinMode(LED, OUTPUT); 
  digitalWrite(LED, LOW);
  digitalWrite(BUTTON, HIGH); //activate Pull-up
}
 
void loop() {
   state_machine(); 
}

void state_machine() {
   switch (state) {
    case LIGHT_OFF:
      if (digitalRead(BUTTON) == LOW) { 
        digitalWrite(LED, HIGH);
        timer = millis();   // start timer
        state = LIGHT_ON;
      }
      break;
    case LIGHT_ON:
      if (millis() - timer > 5000) {  
        digitalWrite(LED, LOW);
        state = LIGHT_OFF;
        } 
      else if (digitalRead(BUTTON) == LOW) {
        timer = millis(); // start timer
      }
      break;  
   }
}
