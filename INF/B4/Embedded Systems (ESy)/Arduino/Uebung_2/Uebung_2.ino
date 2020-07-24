int led = 26;
int button = 22;

void setup() {
  DDRA |= (1 << DDA4);
  //pinMode(led, OUTPUT);
  DDRA &= ~ (1 << DDA0);
  //pinMode(button, INPUT);

}

void loop() {
  //if(digitalRead(button) == HIGH){
  //  digitalWrite(led, !digitalRead(led));

  if(PINA & (1 << PA0)){
    PORTA ^= (1 << PA4);    
    delay(200);
  }

}
