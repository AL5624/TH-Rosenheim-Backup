int microPin = 24;
int ledPin = 13;
int counter = 0;

typedef enum {
  START,
  CLAP1_DETECTED,
  CLAP2_PENDING,
  LED
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
    case LED:
      led();
      break;
  }
}

  void start() {
    counter = 0;
      if(digitalRead(microPin) == HIGH) {
        counter++;
        Serial.print("clap ");
        Serial.println(counter);
        state = CLAP1_DETECTED;
        timer100 = millis();
        timer300 = millis();
      }      
  }

  void clapOne() {
    
    if(millis()- timer300 > 300) {
      state = LED;
    } else if(millis() - timer100 >= 100 && digitalRead(microPin) == HIGH) {
      //state = CLAP2_PENDING;
      if(counter >= 2) {
        state = LED;
      } else {
        
      
      counter++;
      Serial.print("clap ");
      Serial.println(counter);
      timer300 = millis();
      timer100 = millis();
      }
      }
  }

  void clapTwo() {
    
if(millis() - timer100 >= 100) {
      counter++;
      Serial.print("clap ");
      Serial.println(counter);
      state = CLAP1_DETECTED;
      timer300 = millis();
    }
  }

  void led()  {
    for(int i = 0; i < counter * 2; i++) {
      digitalWrite(ledPin, !digitalRead(ledPin));
      delay(500);
    }
    state = START;
  }
