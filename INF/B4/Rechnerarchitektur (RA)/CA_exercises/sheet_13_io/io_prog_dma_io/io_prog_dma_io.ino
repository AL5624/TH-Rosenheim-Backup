// This exercise is based on the simple ZeroDMA example by Adafruit
// Because we use DMA, unlike memcpy(), our code could do other
// things simultaneously while the copy operation runs.

// You may need to checkout the header to fill in the Todos
// https://github.com/adafruit/Adafruit_ZeroDMA/blob/master/Adafruit_ZeroDMA.h

#include <Adafruit_ZeroDMA.h>  // lib Version 1.04
#include "utility/dma.h"

Adafruit_ZeroDMA myDMA;
ZeroDMAstatus    stat; // DMA status codes returned by some functions

// The amount of memory we'll be moving:
#define DATA_LENGTH 1024

//Arrays for source and destination
uint8_t source_memory[DATA_LENGTH];
uint8_t destination_memory[DATA_LENGTH];

volatile bool is_transfer_done = false; 

//TODO: write a callback for the end-of-DMA-transfer and set 
//      the is_transfer_done value to true


void setup() {
  uint32_t t;
  
  //Establish a connection to the serial monitor
  Serial.begin(9600);
  while (!Serial); //wait until a serial port is connected

  Serial.println("DMA test: memory copy");
  Serial.print("Allocating DMA channel...");

  stat =  //TODO: allocate a DMA channel
  myDMA.printStatus(stat);

  Serial.println("Setting up transfer");
  //TODO: set src, dest, count
  myDMA.addDescriptor(/*TODO: src, dest, count*/);

  //TODO: set your callback, use the default type
  myDMA.setCallback(/*TODO: use your callback from above here */);

  // Fill the source buffer with incrementing bytes, dest buf with 0's
  for (uint32_t i = 0; i < DATA_LENGTH; i++) {
    source_memory[i] = i;
  }
  memset(destination_memory, 0, DATA_LENGTH); //clear destination memory

  // Show the destination buffer is empty before transfer
  Serial.println("Destination buffer before transfer:");
  dump();

  Serial.println("Starting transfer job");
  //TODO: start the DMA transfer job

  myDMA.printStatus(stat);

  Serial.println("Triggering DMA transfer...");
  t = micros();
  myDMA.trigger();

  // Your code could do other things here while copy happens!
  int32_t x = 0;
  while (!is_transfer_done) {
    ++x; // Chill until DMA transfer completes
  }

  t = micros() - t; // Elapsed time

  Serial.print("Done! ");
  Serial.print(t);
  Serial.println(" microseconds");

  Serial.print("Did ");
  Serial.print(x);
  Serial.println(" loops while waiting until DMA has completed");

  myDMA.free();

  Serial.println("Destination buffer after transfer:");
  dump();

  copy_data_manually();
}

// Show contents of destination_memory[] array
void dump() {
  for (uint32_t i = 0; i < DATA_LENGTH; i++) {
    Serial.print(destination_memory[i], HEX); Serial.print(' ');
    if ((i & 15) == 15) Serial.println();
  }
}

// Repeat the same operation "manually" without DMA
void copy_data_manually() {
  uint32_t t = micros();
  
  //TODO: copy the memory "manually" without DMA in one line
  
  t = micros() - t; // Elapsed time
  
  Serial.print("Same operation without DMA: ");
  Serial.print(t);
  Serial.println(" microseconds");
}


void loop() { }
