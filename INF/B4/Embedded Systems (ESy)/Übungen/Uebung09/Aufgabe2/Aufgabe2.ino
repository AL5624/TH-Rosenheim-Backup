#include <Arduino_FreeRTOS.h>

// definition of tasks, prototypes 
void vBlinkLedTask(void *pvParameters );
  
void setup() {

  xTaskCreate(                    // set up the blink task 
    vBlinkLedTask
    ,  "Blinking LED Task"        // human-readable name
    ,  128                        // stack size
    ,  NULL
    ,  2                          // priority
    ,  NULL );

  // Now the task scheduler, which takes over control of scheduling individual tasks, is automatically started.
}

void loop() {                     // Empty: Things are done in tasks   
}
  
// implementation of tasks 
void vBlinkLedTask(void *pvParameters)  {
  // TODO: DDR? to output
 
  for (;;)  {                                  // a task does not return or exit
    // TODO: toggle LED  
    vTaskDelay(pdMS_TO_TICKS(1000));           // block / wait for 1 second
  }
}
