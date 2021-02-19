#include <Arduino_FreeRTOS.h>
#include "semphr.h"

// definition of tasks 
void vBlinkLedTask(void *pvParameters);
void vButtonTask(void *pvParameters);

SemaphoreHandle_t xButtonSemaphore;    // stores handle to semaphore

void setup() {

  Serial.begin(9600);

  xTaskCreate(                    // set up Blinking task 
    vToggleLedTask
    ,  "Toggle LED Task"             
    ,  128  // Stack size
    ,  NULL
    ,  2                          
    ,  NULL );

  xTaskCreate(                    // set up Button task 
    vButtonTask
    ,  "Button Task"             
    ,  128  // Stack size
    ,  NULL
    ,  2                          
    ,  NULL );
}

void loop() {                       
}
  
// implementation of "Blinking task" 
void vToggleLedTask(void *pvParameters)  {
  int c;
  DDRB |= (1 << 7);      // internal LED output -> digital pin 13 / PB 7
  for (;;)  {            
    // TODO: versuchen Semaphor zu belegen -> if Statement
    if(xButtonSemaphore != NULL && xSemaphoreTake(xButtonSemaphore, 10)) {
        PORTB ^= (1 << 7); // toggle       
      
        
      }    
      vTaskDelay(pdMS_TO_TICKS(1000)); 
    }
  }


void vButtonTask(void *pvParameters)  {

  DDRA &= ~(1 << 2);     // PA2 == Digital PIn 24 as input
  PORTA |= (1 << 2);    // activate internal pull-up -> p68 of manual

  xButtonSemaphore = xSemaphoreCreateBinary();      // create semaphore
  int b = 1;
  for (;;) {
     if (!(PINA & (1 << 2))) { // button pressed
         // TODO: Semaphor freigeben 
         xSemaphoreGive(xButtonSemaphore);
     }
     vTaskDelay(pdMS_TO_TICKS(100));               // check every 100 ms
  }
}
