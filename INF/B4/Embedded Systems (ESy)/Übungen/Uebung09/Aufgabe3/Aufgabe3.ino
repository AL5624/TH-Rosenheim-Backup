#include <Arduino_FreeRTOS.h>

// definition of tasks 
void vBlinkLedTask(void *pvParameters);
void vBusyTask(void *pvParameters);
 int blinkTimer = millis();
 int busyTimer = millis();
void setup() {

  Serial.begin(9600);

  xTaskCreate(   // set up the blink task 
    vBlinkLedTask
    ,  "Blinking Task"             
    ,  128  // Stack size
    ,  NULL
    ,  3                        // higher number == higher prio
    ,  NULL );

  xTaskCreate(    // set up the busy task 
    vBusyTask
    ,  "Busy Task "             
    ,  128  // Stack size
    ,  NULL
    ,  2                        // higher number == higher prio
    ,  NULL );

  // Now the task scheduler, which takes over control of scheduling individual tasks, is automatically started.
}

void loop() {                     
}
  
// implementation of "Blinking task" 
void vBlinkLedTask(void *pvParameters)  {
  DDRB |= (1 << 7);      // internal LED == PB 7 | D13
  
  for (;;)  {          
    Serial.println("Blinking Task");
    PORTB ^= (1 << 7);                         // toggle LED  
    vTaskDelay(pdMS_TO_TICKS(1000));           // block / wait for 1 second
    //delay(1000);
  }
}

// implementation of "Busy task" 
void vBusyTask(void *pvParameters)  {
  for (;;) {
    Serial.println("Busy Task ");
     //vTaskDelay(pdMS_TO_TICKS(3000));
     delay(3000);
  }
}
