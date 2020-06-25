// up-down counter (phase-and-frequency correct mode):  0 bis 64000, dann runter
// toggle interne LED an OC3B (an bei Überschreiten, aus bei Unterschreiten) 
// Schaltung bauen 

void setup() {
  // LED at OC3B
  DDRE |= (1 << DDE4);  
  
  Serial.begin(9600);
  TCNT3 = 0x00; 

  // vorsichtshalber mit 0 initialisieren 
  TCCR3A = 0x00;
  TCCR3B = 0x00;

  // Mode 8: up-down counter or "Phase and Frequency Correct Mode", p145, p151
  TCCR3B |= (1 << WGM33); 

  // select prescaler, start timer, p157
  TCCR3B |= (1 << CS30) | (1 << CS32); 

  // Output Compare, set to 1 when upcounting, set to 0 when downcounting p155
  TCCR3A |= (1 << COM3B0) | (1<<COM3B1);

  // definiere "Umkehrwert"
  ICR3B = 64000; 

  // define Output Compare / CMP, point where action at PWM output is triggered
  OCR3B = 32000


}

void loop() {
    unsigned int timer = TCNT3;
    Serial.println(timer); 
    delay(500); 

    OCR3B = 20000; 
}



  
