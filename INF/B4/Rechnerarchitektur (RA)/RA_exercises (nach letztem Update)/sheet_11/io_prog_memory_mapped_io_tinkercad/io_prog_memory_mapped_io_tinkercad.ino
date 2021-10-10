//".equ" is nearly similar to the C-preprocessor statement "#define" 
asm (".equ ON,  0b00100000"); //value for enable (switch on)
asm (".equ OFF, 0b00000000"); //value for disable (switch off)

void setup() {
  //The built-in LED is connected to digital PIN 13, so we set it as an output
  pinMode(13, OUTPUT);
}

void loop() {
  //Switch on
  asm("ldi r16, ON"); //load the immediate value ON into r16

  //TODO: check out the AVR Assembler Instructions for an instruction to store data into memory
  //TODO: check out the ATmega328 Datasheet for the memory address of the register
  asm(" , r16");
  delay(1000); //wait a second

  //Switch off
  //TODO: switch of the LED using your assembly code from above

  
  delay(1000); //wait a second
}
