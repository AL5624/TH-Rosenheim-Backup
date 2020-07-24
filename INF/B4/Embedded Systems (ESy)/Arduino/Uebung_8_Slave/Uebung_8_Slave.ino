void setup() {
  Serial.begin(9600);

  // TODO

  SPCR |= (1 << SPE);   // enable SPI
  SPCR |= (0 << MSTR);  // select slave SPI
  //DDR_SPI = (1<<DD_MOSI)|(1<<DD_SCK);
  // data direction: set MIS==DDB3 to output (p96ff), all other input 
  DDRB = (1<<DDB3);
}

// send and receive data
char spi_transceive(char data) {

  // TODO

  // start transmission by putting data into buffer 
  SPDR = data;
  // wait until transmission completes
  while(!(SPSR & (1<<SPIF)));
  // return received data 
  return (SPDR);
}


void loop() {
    char text[] = "Hello Master!";

  for (int i = 0; i < sizeof(text); i++) {
    char received = spi_transceive(text[i]);
    Serial.println(received);
  }

  delay(2000);

}
