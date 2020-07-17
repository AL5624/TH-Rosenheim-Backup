// -------------------- Demo I2C ---------------------
// Connections
// UNO     Mega
// A4      20
// A5      21
// GND     GND 
// Receiver, I2C Master, ARduino Mega
#include <Wire.h>

void setup() {
  Wire.begin();        // join i2c bus (address optional for master)
  Serial.begin(9600);  // start serial for output
}

void loop() {
  Wire.requestFrom(8, 21);    // request 21 bytes from slave device #8

  while (Wire.available()) { // slave may send less than requested
    char c = Wire.read(); // receive a byte as character
    Serial.print(c);         // print the character
  }

  delay(500);
}




// Sender, I2C Slave, Arduino Uno

#include <Wire.h>

void setup() {
  Wire.begin(8);                // slave address
  Wire.onRequest(requestEvent); // register callback 
}

void loop() {
  delay(100);
}

void requestEvent() {  // callback when master asks for slave data
  Wire.write("Greetings from slave "); 
}
