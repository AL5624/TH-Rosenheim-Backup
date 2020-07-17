#include <Arduino_FreeRTOS.h>
#include "semphr.h"

// definition of tasks 
void vTask1(void *pvParameters);
void vTask2(void *pvParameters);

SemaphoreHandle_t xSemaphore = xSemaphoreCreateBinary(); 

void setup() {

  Serial.begin(115200); 
  
  xTaskCreate(   // set up the blink task 
    vTask1
    ,  "Task1"             
    ,  128  // Stack size
    ,  NULL
    ,  2                        // higher number == higher prio
    ,  NULL );

  xTaskCreate(    // set up the busy task 
    vTask2
    ,  "Task 2"             
    ,  128  // Stack size
    ,  NULL
    ,  2                        // higher number == higher prio
    ,  NULL );

  // Now the task scheduler, which takes over control of scheduling individual tasks, is automatically started.
}

void loop() {                     
}
  
void vTask1(void *pvParameters)  {
 
  for (;;) {
    Serial.println("Ich bin Task1");
    xSemaphoreGive(xSemaphore); 
    vTaskDelay(pdMS_TO_TICKS(1000));
  }
}

void vTask2(void *pvParameters)  {
  for (;;) {
    while (!xSemaphoreTake(xSemaphore, pdMS_TO_TICKS(1000)));  // sonst block
    Serial.println("Ich bin Task2");
    vTaskDelay(pdMS_TO_TICKS(500));
  }
}
