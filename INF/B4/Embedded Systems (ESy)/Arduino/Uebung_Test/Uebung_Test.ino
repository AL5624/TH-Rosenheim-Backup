
void setup() {
  // put your setup code here, to run once:
  DDRB |= (1 << 7);
  PORTA |= (1 << 2);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(!(PINA & (1 << 2))) {
    PORTB ^= (1 << 7);
    delay(200);
  }
    
  
  
}
