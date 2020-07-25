int microPin = 24;
int ledPin = 21;

typedef enum {
  START, 
  CLAP1_DETECTED,
  CLAP2_PENDING,
} state_t;   

// events
typedef enum {  
  NONE,
  MICRO_PIN_HIGH,
  TIMER100_EXPIRED,
  TIMER300_EXPIRED
} event_t;     

// global variables
state_t state;                 // current state
unsigned long timer100 = 0;    // start time of timer100 
unsigned long timer300 = 0;    // start time of timer300

// transition functions
void idle() {
}
void toClap1Detected() {
  state = CLAP1_DETECTED;
  timer100 = millis();  // start timer for 100 ms
}
void toClap2Pending() {
  state = CLAP2_PENDING;
  timer300 = millis(); // start timer for 300 ms
}
void toStart() {
   state = START; 
}
void toStartToggle() {
   state = START; 
   digitalWrite(ledPin, !digitalRead(ledPin)); // toggle LED 
}

// transition table
void (*state_table[3][4]) (void) = {
  //                  NONE    MICRO_PIN_HIGH    TIMER100_EXPIRED    TIMER300_EXPIRED
  /*START*/          {  ??,   ??,                           ??,               ??},
  /*CLAP1_DETECTED*/ {  ??,   ??,                           ??,               ??},
  /*CLAP2_PENDING*/  {  ??,   ??,                           ??,               ??}
};

void setup() {
  pinMode(microPin, INPUT); 
  pinMode(ledPin, OUTPUT); 
  state = START;   // initial state
}

void loop() {
  state_machine(); 
}

void state_machine() {
  // detect events
  
  // TODO 
}
