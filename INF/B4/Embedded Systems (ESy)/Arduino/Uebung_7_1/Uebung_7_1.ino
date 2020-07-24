#include <avr/wdt.h>

void setup() {
  Serial.begin(9600);
  Serial.println("System restart");

  // TODO: set watchdog interval to 4s and start watchdog timer
  cli();                  // globally deactivate interrupts
  wdt_reset();
  /* Clear WDRF in MCUSR */
  /* Watchdog Reset Flag */
  // MCUSR &= ~(1 << WDRF);
  /* Write logical one to WDCE and WDE */
  /* Keep old prescaler setting to prevent unintentional time-out
  */
  WDTCSR |= (1 << WDCE) | (1 << WDE);
  /* Turn off WDT */
  // WDTCSR = 0x00;

  /* Set new prescaler(time-out) value = 512K cycles (~4.0 s) */
  WDTCSR = (1 << WDE) | (1 << WDP3);

  // configure external interrupt on pin PD0
  DDRD &= ~(1 << DDD0);   // configure PD0 as input
  PORTD |= (1 << PORTD0); // pull up, write to PORT when in INPUT mode, p68
  EIMSK |= (1 << INT0);   // turn on INT0
  EICRA |= (1 << ISC01);  // set INT0 to trigger on falling edge
  sei();                  // globally activate interrupts
}

void loop() {
  // empty
}

ISR (INT0_vect) {
  // TODO: reset watchdog within ISR
  wdt_reset();
  
  Serial.println("ResetWDT");
}
