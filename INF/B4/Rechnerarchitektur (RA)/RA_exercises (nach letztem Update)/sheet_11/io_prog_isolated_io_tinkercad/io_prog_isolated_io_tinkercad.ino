//TODO: set a "1" for ON and a "0" for OFF for the right bit into the register.
//use an appropriate number representation for that.
//".equ" is nearly similar to the C-preprocessor statement "#define" 
asm (".equ ON,  "); //value for enable (switch on)
asm (".equ OFF, 0b00000000"); //value for disable (switch off)

void setup() {
  //The built-in LED is connected to digital PIN 13, so we set it as an output
  pinMode(13, OUTPUT);
}

void loop() {
  //Switch LED on
  asm("ldi r16, ON"); //load the immediate value ON into r16

  //TODO: check out the AVR Assembler Instructions for an instruction to directly write to I/O ports
  //TODO: check out the ATmega328 Datasheet for the separate IO memory space
  asm("out ,r16");
  delay(1000); //wait a second

  //Switch LED off
  //TODO: switch of the LED using and modifying your assembly code from above


  delay(1000); //wait a second
}
