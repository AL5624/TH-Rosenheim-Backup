volatile unsigned int overflow_counter = 0;
void setup() {
  // put your setup code here, to run once:
    Serial.begin(9600);
  //pinMode(24, OUTPUT);
  DDRA |= (1 << DDA2);
  TCCR4A = 0x00;
  TCCR4B = 0x00;
  TCNT4 = 0;
  OCR4A = 62500;
  TCCR4B |= (1 << WGM42);
  //p. 157
  //TCCR4B |= (1 << CS40) | (1 << CS42); //1024
  TCCR4B |= (1 << CS42); //256
  //TCCR4B |= (1 << CS40) | (1 << CS41); //64
  //TCCR4B |= (1 << CS41); //8
  //TCCR4B |= (1 << CS40); //no prescaler
  //TIMSK4 |= (1 << TOIE4); // Aktivieren des Timer Overflow Interrupts, p161
  TIMSK4 |= (1 << OCIE4A);  
  sei();

}

void loop() {
  // put your main code here, to run repeatedly:
 
}

ISR(TIMER4_OVF_vect) {
  if(overflow_counter == 244) {
    overflow_counter = 0;
    PORTA ^= (1 << PA2);  
  }
  overflow_counter++;
}

ISR(TIMER4_COMPA_vect){
  PORTA ^= (1 << PA2);
}
