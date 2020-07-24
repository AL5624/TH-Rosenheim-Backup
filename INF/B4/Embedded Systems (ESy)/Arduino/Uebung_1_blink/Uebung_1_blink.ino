int ledPin = 26;

String string;

void setup() {
  // put your setup code here, to run once:

  Serial.begin(9600);

  pinMode(ledPin, OUTPUT);

}

void loop() {
  // put your main code here, to run repeatedly:

  while(Serial.available() < 4)
  {
    digitalWrite(ledPin, HIGH);

    delay(500);

    digitalWrite(ledPin, LOW);

    delay(500);
  }

  string = Serial.readString();

  Serial.println("Echo: " + string);

  delay(5000);


}
