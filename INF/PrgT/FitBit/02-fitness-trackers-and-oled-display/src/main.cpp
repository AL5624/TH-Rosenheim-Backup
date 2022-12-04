#include <arduino.h>

const int button = PIND2;
const int debounceMs = 100;

volatile unsigned long millisLastInterrupt;
volatile bool buttonPressed;

/*
 * Keep interrupt service routines as fast and short as possible.
 * While servicing an interrupt, the CPU can't service other interrupts.
 * The flag buttonPressed is used to signal that the button was pressed.
 * This avoids having the usually slow logging in an ISR.
 * It is instead handled asynchronously in the loop function.
 */
void buttonPressedISR() {
    unsigned long interruptTime = millis();

    if (interruptTime - millisLastInterrupt > debounceMs) {
        buttonPressed = true;
    }
    millisLastInterrupt = interruptTime;
}

/*
 * Everything is set up and set to a known state.
 * The LED pin is set to LOW to ensure that the LED always starts powered off.
 * Rising edge triggering is used in this example to determine, i.e. trigger at,
 * the end of a button press.
 * Falling edge triggering could be used instead to determine, i.e. trigger at,
 * the beginning of a button press.
 */
void setup() {
    // put your setup code here, to run once:
    Serial.begin(9600);

    pinMode(button, INPUT_PULLUP);
    buttonPressed = false;

    millisLastInterrupt = 0;
    attachInterrupt(digitalPinToInterrupt(button),
                    buttonPressedISR,
                    RISING);
}

void loop() {
    // put your main code here, to run repeatedly:
    if (buttonPressed) {
        Serial.println("Button was pressed.");
        buttonPressed = false;
    }
}