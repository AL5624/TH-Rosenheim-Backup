/*
Aufgabe 1a)
Welcher Wert muss im UBRRn Register gesetzt werden, damit sich eine Baudrate von 9600 Baud bei einem Systemtakt von 𝑓𝑜𝑠𝑐=16 𝑀𝐻𝑧 ergibt?
Hinweis: Datenblatt Kapitel 22.3.1, S. 202/203, Abschnitt 22.3.1 und Abbildung 22-2.

Asynchronous Normal mode: UBRRn = f(OSC)/16BAUD - 1; BAUD = f(OSC)/(16*(UBRRn + 1));
UBRRn = (16*10^6)/(16*9600) - 1 = 103,1/6 ~ 103;
Error[%] = (UBRRn(closest match)/UBRRn - 1) * 100%
Error[%] = (103/103,1/6 - 1) * 100% = -0,1615508885 ~ -0,16%;
*/
#define FOSC  16000000
#define MYUBRR  103

void setup() {
  // TODO
  UBRR0L = (unsigned int) MYUBRR;
  UBRR0H = (unsigned int) (MYUBRR >> 8);
  //UBRR0 = 103;              // set baud rate
  UCSR0B |= (1 << TXEN0);   // enable transmitter
  UCSR0C |= (0 << USBS0);   // set 1 stop bit
  UCSR0C |= (1 << UCSZ01) | (1 << UCSZ00);  //set frame with 8 data bits
}

void uart_putchar(char c) {
  // TODO
  while ( !( UCSR0A & (1 << UDRE0)) );

  UDR0 = c;

  
}

void loop() {
  char text[] = "Hallo";
  for(int i = 0; i < sizeof(text); i++) {
    uart_putchar(text[i]);
    delay(100);
  }
  delay(2000);
}
