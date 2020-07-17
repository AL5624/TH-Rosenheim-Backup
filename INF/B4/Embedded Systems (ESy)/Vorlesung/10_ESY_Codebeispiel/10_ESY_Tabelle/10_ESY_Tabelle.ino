#define BUTTON 22  // Digital Pin 22
#define LED 21     // Digital Pin 21 

typedef enum {
  LIGHT_OFF, 
  LIGHT_ON
}  state_t;     // enum datatype for states

// events
typedef enum {
  NONE,
  TIMER_EXPIRED,
  BUTTON_PRESSED
} event_t;      // enum datatype for events

state_t state;      // keep current state

unsigned long timer = 0;  // start time of timer

void startTimer (void) {
    digitalWrite(LED, HIGH);
    state = LIGHT_ON; // set new state
    timer = millis(); // start timer
}
void resetTimer (void) {
    timer = millis(); 
}
void timerExpires (void) {
    digitalWrite(LED, LOW);
    state = LIGHT_OFF; // set new state
}
void idle(void) {
}


void setup() {
  pinMode(LED, OUTPUT); 
  digitalWrite(LED, LOW);
  digitalWrite(BUTTON, HIGH); // Pull-UP
  state = LIGHT_OFF;              // initial state
}

// state/event lookup table with function pointers
void (*state_table[2][3]) (void) = {
  //             NO_EVENT   TIMER_EXPIRED   BUTTON_PRESSED
  /* LIGHT_OFF*/ {idle,   idle,           startTimer      }, 
  /* LIGHT_ON*/  {idle,   timerExpires,   resetTimer  }  
};

void loop() {
  state_machine(); 
}

void state_machine() {
  // check for new events
  event_t event = NONE;   // default: no event
  if (timer && millis() - timer > 5000) {
    event = TIMER_EXPIRED;
    timer = 0; 
  } else if (digitalRead(BUTTON) == LOW) {
    event = BUTTON_PRESSED;
  }
  // go to next state
  state_table[state][event]();
}
