int microPin = 24;
int ledPin = 21;

void setup() {
  /* add setup code here */
  pinMode(microPin, INPUT);
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW);
}

void loop() {
  int val = digitalRead(microPin);// 
  if (val == HIGH) {
    digitalWrite (ledPin, HIGH); 
  } else {
    digitalWrite (ledPin, LOW);
  }
}


