/*
(2)
a) Welche Buchsen des Arduino Mega Boards haben die Funktionalität MOSI, MISO, SCK und SS?
MOSI = PB2 (Digital pin 51)
MISO = PB3 (Digital pin 50)
SCK = PB1 (Digital pin 52)
SS = PB0 (Digital pin 53)

*/

void setup() {
  Serial.begin(9600);

  // TODO

  // data direction: set MOSI==DDB2 and SCK==DDB1 and SS=DDB0 to output p96ff
  DDRB = (1 << DDB2) | (1 << DDB1) | (1 << DDB0);
  SPCR |= (1 << SPE);   // enable SPI
  SPCR |= (1 << MSTR);  // select master SPI
  SPCR |= (1 << SPI2X) | (1 << SPR1) | (1 << SPR0); // select clock rate
  //DDR_SPI = (1<<DD_MOSI)|(1<<DD_SCK);
  


}

// send and receive data
char spi_transceive(char data) {

  // TODO

  // set SS to low, activating slave, synchronization (to be on safe side) 
  PORTB &= ~(1<<DDB0); 
  // start transmission by putting data into buffer 
  SPDR = data;
  // wait until transmission completes 
  while(!(SPSR & (1<<SPIF)));
  // return received data 
  char result = SPDR; 
  // set SS to high, deactivate slave 
  PORTB |= (1<<DDB0); 

return result;
  
}

void loop() {
  char text[] = "Hello Slave!";

  for (int i = 0; i < sizeof(text); i++) {
    char received = spi_transceive(text[i]);
    Serial.println(received);
  }

  delay(2000);
}
