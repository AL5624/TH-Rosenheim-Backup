volatile int counter = -3;
volatile int polling = 0;

void setup() {
  // put your setup code here, to run once:
  //DDRD &= ~ (1 << 0);
  //SREG |= (1 << 7);   // oder sei(), globales Aktivieren der INterrupts
  EIMSK |= (1 << INT0); //gezielt INTerrupt 0 aktivieren
  EICRA |= (1 << ISC00) | (1 << ISC01); // bei steigender Flanke? 
  sei();
  // polling = millis();
  // delay(250);
  //attachInterrupt(digitalPinToInterrupt(21), isr, RISING);
  Serial.begin(9600);
  Serial.println(counter);

}

void loop() {
  // put your main code here, to run repeatedly:
  //if(PIND & (1 << PD0)) {
  //  counter++;
  //}
  Serial.println(counter);
  delay(1000);

}
//1:
void isr () {
  if(millis() - polling > 250) {
   counter++; 
  }
  polling = millis();
}

//2:
ISR (INT0_vect) {
  counter++;
}
