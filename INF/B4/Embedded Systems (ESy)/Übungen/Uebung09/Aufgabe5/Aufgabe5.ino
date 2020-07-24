#include <Arduino_FreeRTOS.h>
#include "queue.h"

// definition of tasks
void vUARTTask(void *pvParameters);

QueueHandle_t rxQueue; 

void setup() {
  // configure UART, set up UART Receive interrupt
  UBRR0L = (unsigned char) 103;    // set buadrate 
  UBRR0H = (unsigned char) (103 >> 8);

  // Enable receiver and transmitter p220
  UCSR0B |= (1<<TXEN0) | (1<<RXEN0); 

  // Set frame format: 8 data, 1 stop bit, p221
  UCSR0C |=  (1<<UCSZ01) | (1<<UCSZ00);

  // activate RX interrupt
  UCSR0B |= (1<<RXCIE0); 
  sei(); 
  
  xTaskCreate(   // set up the button task 
    vUARTTask
    ,  "UART Task"           
    ,  128  // Stack size
    ,  NULL
    ,  2                          // priority
    ,  NULL );
  
}

void loop() {                     
}
  
// implementation of "UART Task"  
void vUARTTask(void *pvParameters)  {
  rxQueue = xQueueCreate(128,1);   

  char c;
  
  for (;;) {
   // TODO: if-Abfrage ob etwas in Queue ist und ggfs. rausnehmen
   if (...) {
   
      while (!(UCSR0A & (1 << UDRE0)));   // echo back 
      UDR0 = c;
   }
  }
}

//ISR when UART character has been received
ISR(USART0_RX_vect) {
  unsigned char ch = UDR0; 
  xQueueSendFromISR(rxQueue, &ch, NULL);
}
