
int count = 0; 

// externer Interrupt 5 bei steigender Flanke, Port E / Pin 5, Digital Pin 3
void setup() {
  //pinMode(3, INPUT) oder DDRE &= ~(1 << 5); 
  SREG |= (1 << 7);   // oder sei(), globales Aktivieren der INterrupts
  EIMSK |= (1 << 5);  // oder EIMSK |= (1 << INT5), gezielt INterrupt 5 aktivieren
  EICRB |= (1 << ISC50) | (1 << ISC51); // bei steigender Flanke? 
  Serial.begin(9600); 
  Serial.println(count); 
}

void loop() {
  Serial.println(count);
}


// wird nie aufgerufen 
ISR (INT5_vect) {    
  count++; 
}
