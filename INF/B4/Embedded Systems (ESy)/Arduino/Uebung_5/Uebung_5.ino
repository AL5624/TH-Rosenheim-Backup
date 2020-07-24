#include <Servo.h>
Servo servo;

void setup() {
  Serial.begin(9600);
  //servo.attach(8);
  DDRH |= (1 << DDH5);
  TCCR4A = 0x00;
  TCCR4B = 0x00;
  
  
  TCCR4B |= (1 << WGM43) | (1 << WGM42);
  TCCR4A |= (1 << WGM41);
  TCCR4B |= (1 << CS41); //rpescaler 8 bit

  TCCR4A |= (1 << COM4C1);

  ICR4 = 40000;
  OCR4C = 2500;
  delay(5000);
}

void loop() {
  OCR4C = 1088;
  delay(5000);
  
  OCR4C = 4800;
  delay(5000);
  
  //servo.write(0);
  //delay(3000);

  //servo.write(90);
  //delay(3000);

  //servo.write(180);
  //delay(3000);
}
