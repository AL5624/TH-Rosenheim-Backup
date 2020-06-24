// Demo: Timer3 hochzählen lassen und regelmäßig den Timer auslesen
// zusätzlich: Bei jedem Overflow einen Interrupt


void setup() {
  Serial.begin(9600);
  TCNT3 = 0x00;   
  TCCR3B |= (1 << CS30) | (1 << CS32); // p. 156, timer gestartet, prescaler 1024 aktiv
  TIMSK3 |= (1 << TOIE3); // Aktivieren des Timer Overflow Interrupts, p161 
  sei();                  

}

void loop() {
    unsigned int timer =  TCNT3;    // p. 137, Timer auslesen
    Serial.println(timer); 
    delay(1000); 
}

ISR (TIMER3_OVF_vect) {    // interrupt service routine, siehe https://www.nongnu.org/avr-libc/user-manual/group__avr__interrupts.html
    //
}



  
