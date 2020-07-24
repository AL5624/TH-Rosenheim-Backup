int analogPin = A2;
int val = 0;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);

  ADMUX |= (1 << MUX1);
  ADMUX |= (1 << REFS0);

  ADCSRA |= (1 << ADPS2) | (1 << ADPS1) | (1 << ADPS0);
  ADCSRA |= (1 << ADEN);
  ADCSRA |= (1 << ADATE);
  ADCSRA |= (1 << ADSC);
}

void loop() {
  // put your main code here, to run repeatedly:
  //val = analogRead(analogPin)/pow(2, 10)*5;
  //Serial.println(val);

//  while(ADCSRA & (1 << ADSC)) {
//  }

  unsigned int read = ADCL + 256 * ADCH;

  double result = 5.0 * read / 1024.0;

  Serial.print(result);
  Serial.println(" Volt");
}
