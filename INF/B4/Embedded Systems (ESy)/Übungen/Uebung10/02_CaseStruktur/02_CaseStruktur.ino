int microPin = 24;
int ledPin = 21;

typedef enum {
  START,
  CLAP1_DETECTED,
  CLAP2_PENDING
} state_t;

state_t state;                 // keeps current state
unsigned long timer100 = 0;    // start time of timer100
unsigned long timer300 = 0;    // start time of timer300

void setup() {
  pinMode(microPin, INPUT);
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, HIGH); 
  state = START;   // initial state
}

void loop() {
  state_machine();
}

void state_machine() {
  switch (state) {
    case START:
       // TODO
    case CLAP1_DETECTED:
       // TODO 
    case CLAP2_PENDING:
       // TODO 
  }
}
