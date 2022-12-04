# Adapter Pattern
The main goal of this Work Sheet is to:
* Learn & practice the adapter pattern
* Apply the adapter pattern to a theoretical example
* Model the Fitness Tracker based on the adapter pattern

## Real World Adapters
Look at the following schematic of a classical adapter,
which converts a german plug to an irish plug:


Which other real world examples of adapters come to your mind?
Is there any experience in implementing the adapter pattern already?

## Real World Adapters in UML
Take your adapter example from FitBit 3.1 and model it within a UML class diagram.
Consider the basic idea of the adapter pattern:

* Adapter patterns are typically used to enable the communication of classes
  that are similar within a specific context,
  but which are incompatible in terms of their specification.
* Assume a class `Client`.
  This class defines a certain specification,
  meaning a set of methods to achieve a certain goal.
* At the same time there exists a class `Adaptee`,
  which offers this functionality "content wise",
  but has a different specification, i.e. different methods,
  than expected from the class Client.
* The `Adapter` is understood as an intermediate class with the goal of making
  the classes `Client` and `Adaptee` compatible with each other.
* Therefore, the `Adapter` delegates the requested tasks to the corresponding
  functions of the `Adaptee`.
* For this purpose, the `Adapter` must contain an instance of the `Adaptee`
  object.
* This instance is passed to the `Adapter` via the constructor call within the
  `Client` class.
* This instance of the `Adaptee` is used to call the corresponding `Adaptee`
  methods.

Use a UML tool of your choice.
Be prepared to present your solution.

## Develop the Software Design of the Fitness Tracker via the Adapter Pattern

Given are the following modules:
- Display
- Heart rate sensor
- Blood oxygen sensor
- Pedometer
- Wireless module
- Buttons for control

Apply the adapter pattern to model the SW Design for the fitness tracker
so that the tracker is designed in a modular and exchangeable way.

## Implementation of the Display Adapter

Given is the following adapter pattern:
```plantuml
entity main.cpp {
**void** setup()
**void** loop()
}
note top of "main.cpp" {
Initialize two objects:
- **Adafruit_SSD1306***
- **Display***
Setup the display by calling the method initialize.
Loop over pby printing a bitmap andtext of your choice on to the screen.
}

abstract class Display {
+**void** initialize()
+**void** print()
}

class DisplaySSD1306Adapter {

-**Adafruit_SSD1306** display
-**int16_t** i2c_address
-**int** logo_width

+**void** initialize()
+**void** print(unsigned char* logo, unsigned char* data, int data_length)
}
note left of DisplaySSD1306Adapter {
Inherits from the Display class and
implements initialize() and print()
such that the tasks are delegated to
the proper library function of the
Adafruit GFX and Adafruit SSD1306 library.
}

"main.cpp" -> Display
Display <|-- DisplaySSD1306Adapter
```

Add the SSD1306 display to your breadboard setup:
* Arduino Nano:
![Arduino Nano Wiring](media/Arduino%20Nano%20Button%20+%20Display%20Steckplatine.png)

* Arduino Mega:
![Arduino Mega Wiring](media/Arduino%20Mega%20Button%20+%20Display%20Steckplatine.png)

Although we neglect the button for the moment.
Implement the given UML scenario by transferring the procedural approach
into an OOP oriented approach as given inside the UML diagram.
