#include <avr/sleep.h>

void setup() {
  Serial.begin(9600);
  Serial.println("System restart");
  
  // configure external interrupt on pin PD0
  DDRD &= ~(1 << DDD0);   // configure PD0 as input
  PORTD |= (1 << PORTD0); // pull up, write to PORT when in INPUT mode, p68
  EIMSK |= (1 << INT0);   // turn on INT0
  EICRA |= (1 << ISC01);  // set INT0 to trigger on falling edge
  sei();                  // globally activate interrupts
  SMCR |= (1 << SM1);
}

void loop() {
  Serial.println("Enter sleep mode");
  delay(1000);

  sleep_mode();

  Serial.println("Leave sleep mode");
  delay(2000);

}

ISR (INT0_vect) {
}
