int microPin = 24;
int ledPin = 13;

typedef enum {
  START,
  CLAP1_DETECTED,
  CLAP2_PENDING
} state_t;

state_t state;                 // keeps current state
unsigned long timer100 = 0;    // start time of timer100
unsigned long timer300 = 0;    // start time of timer300

void setup() {
  Serial.begin(9600);
  Serial.println("Start");
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
       start();
       break;
    case CLAP1_DETECTED:
       clapOne();
       break;
    case CLAP2_PENDING:
      clapTwo();
      break;
  }
}

  void start() {
      if(digitalRead(microPin) == HIGH) {
        timer100 = millis();
        state = CLAP1_DETECTED;
      }      
  }

  void clapOne() {
      if(millis() - timer100 >= 100) {
      state = CLAP2_PENDING;
      timer300 = millis();
      }
  }

  void clapTwo() {
    if(millis()- timer300 > 300) {
      Serial.println("timer300 expired");
      state = START;
    } else if(digitalRead(microPin) == HIGH) {
      Serial.println("Second Clap detected");
      digitalWrite(ledPin, !digitalRead(ledPin));
      delay(100);
      state = START;
    }
  }
