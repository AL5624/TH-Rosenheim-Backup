void setup() {
 Serial.begin(9600);
  ADCSRA |= (1 << ADEN);                                    // enable ADC
  ADCSRA |= (1 << ADPS2) | (1 << ADPS1) | (1 << ADPS0);     // ADC prescaler, p271 
  ADCSRA |= (1 << ADATE)|(1 << ADSC);                       // auto-trigger
  ADCSRB &= ~((1 << ADTS2) | (1 << ADTS1) | (1 << ADTS0));  //free-running, p287
  ADMUX |= (1 << MUX1);                                     // select ADC2 as input pin for ADC 
  // TODO: set “good” ADC reference voltage, p281
  ADMUX |= (1 << REFS0) | (1 << REFS1);
}

void loop() {
  // note: conversion is continuously triggered in free running mode
  // read analog value, first LOW then HIGH register 
  unsigned int read = ADCL + 256 * ADCH; // TODO get converted value from ADC
  double temperature = 0.25 * read - 50; // TODO convert integer value into temperature 
  Serial.println(temperature);
  delay(1000);

}
