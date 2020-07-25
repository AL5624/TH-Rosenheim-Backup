int microPin = 24;
int ledPin = 21;

typedef enum {
  START,
  CLAP1_DETECTED,
  CLAP2_PENDING
} state_t;

state_t state;                 // current state
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
      if (digitalRead(microPin) == HIGH) {
        state = CLAP1_DETECTED;
        timer100 = millis();  // start timer for 100 ms
      }
      break;
    case CLAP1_DETECTED:
        if (millis() - timer100 > 100) {     // timer 100 ms expired
          state = CLAP2_PENDING;
          timer300 = millis(); // start timer for 300 ms
        }
      break;
    case CLAP2_PENDING:
        if (millis() - timer300 > 300) {  // timer 300 ms expired
          state = START;
        }
        else if (digitalRead(microPin) == HIGH) {
          digitalWrite(ledPin, !digitalRead(ledPin)); // toggle 
          state = START;
        }
      break;
  }
}
