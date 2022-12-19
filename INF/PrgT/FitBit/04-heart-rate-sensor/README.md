# Heart Rate Sensor

The main goal of this Work Sheet is to:
* Add the MAX30102 into the fitness tracker.
* Get to know the module and where to find relevant information.
* Connect the module in procedural manner.
* Date process of the received signal to represent beats per minutes in seconds.
* Transfer into the fitness tracker model by implementing the adapter.


## Theoretical Background and Online Documentation
We have already heard about and worked with the Nano V3.0 and
the Adafruit OLED Monochrome Display.
You have implemented your module with adapter into your fitness tracker project.
Today we start to include the MAX30102 module, which is a sensor pulse oximeter
and heart-rate sensor to be used for wearable health devices.
We start to extract the heart rate first.

The heart rate is a measure for the heart function and is listed in beats per
minute (bpm).
The human heart in resting state is in the range of 70 to 100 bpm varying
between individuals (competitive athletes) and states, i.e., doing sports.
The goal of the pulsation is to transport blood through the human body
characterized by the stroke volume (Schlagvolumen).
The beating heart transports the stroke volume by a dedicated rhythm
characterized by bpm.

The Max30102 uses an infrared (IR) LED together with a photodetector to measure
reflected infrared light.
Therefore, a finger is placed close to an IR LED.
The finger absorbs the IR light and reflects part of it dependent on the
blood pulsation.
This absorption/reflection can be measured by a detector in the form of a
photo diode.

The Photodetector “sees” the pulsation by varying reflected signal intensity.
The received signal is converted into beats per minute.
The processed data is shown on the display.

Technical Setup:
The MAX30102 is operated with a single 1.8V power supply and a
separate 3.3V power supply for the internal LEDs.
The communication is via a standard I2C compatible interface.
The module can be shut down via the software with zero standby current,
whereby the conductor rails remain supplied with power at all times.
It has:
- IR LED
- RED LED
- Photodetector

Summary of technical parameters:
LED peak wavelength: 660 nm / 880 nm
LED supply voltage: 3.3V - 5V
Detection signal type: Optical reflection signal (PPG)
Output signal interface: I²C interface
Communication interface voltage: 1.8V - 5V
Interface:
* VIN: Main power supply, 1.8V - 2.5V
* 3-Bit-Pad:
  The I2C-bus is pulled up;
  you can choose between 1.8V and 3.3V depending on the pin master voltage.
* SCL: I2C bus clock
* SDA: Connected to the I2C bus data
* GND: Ground wire

More details can be found here:
https://www.maximintegrated.com/en/products/interface/sensor-interface/MAX30102.html

The MAX3010x library is provided here:
https://github.com/sparkfun/SparkFun_MAX3010x_Sensor_Library


## Install Max3010x Library and Measure the Heartbeat

We will start to connect the Max30102 and experiment with it by following
the SparkFun Tutorial:
https://learn.sparkfun.com/tutorials/max30105-particle-and-pulse-ox-sensor-hookup-guide/all
1. Follow the instructions on how to install the library under the section
   “Using the SparkFun MAX30105 Arduino Library”
2. Wire the following setup:  
   ![Arduino Mega with Maxim MAX30102 Breadboard](media/Arduino%20Mega%20with%20Maxim%20MAX30102%20Breadboard.svg)
3. Follow the instructions for **SparkFun Example 4**.
4. How does your heart beat look like?
   You can play with the parameters inside the sketch:
```cpp
byte sampleAverage = 32; //Options: 1, 2, 4, 8, 16, 32
int sampleRate = 1600; //Options: 50, 100, 200, 400, 800, 1000, 1600, 3200
int pulseWidth = 411; //Options: 69, 118, 215, 411
int adcRange = 4096; //Options: 2048, 4096, 8192, 16384
```
4. Follow the instructions for **SparkFun Example 5**.
   Try to understand the heart beat calculations.
   Summarize the calculations within a flow chart.
   What happens step by step?


## Technical Work with MAX30102
Adapt your setup to display the BPM data on screen.

1. Draw a Fritzing illustrating the wiring.
2. Wire your setup.
3. Apply the changes to the Sketch so that a heart bitmap,
   a text stating “BPM” and the calculated BPM value is plotted on the
   monochrome display.
   Guide the user within the serial monitor.


## Include the Module into the Fitness Tracker Design
The goal of this exercise is to include the MAX30102 into the fitness tracker project.
1. Define a class `FitnessTracker`, which represents the Fitness Tracker
   (so to speak, the main object of a fitness tracker library).
   The Fitness Tracker controls the modules used and
   processes the input via the button pushes.
   ```cpp
   FitnessTracker(...); // In the constructor an array of modules will be passed
   initialize();  // Calls all initialize methods of the modules and the display
   run(); // Calls the run methods of the modules
   bool OnButtonPressed; // Action in case the button is pressed
   modules; // List of all modules
   currentView; // Pointer to the currently active module
   millisLastInterrupt // To buffer the button input
   ```
2. Define an abstract class `Module` (See UML).
3. Define an adapter class `BpmMAX3010x`, which inherits from `Module` and
   delivers the according functionality from the Max30105 library,
   basically transferring the tasks from the procedural approach into the four
   functions of `Module` (see UML).


```plantuml
class Module {
+void initialize()
+void startUp()
+void run()
+void end()
}

note right of Module::initialize()
  Called in the setup method.
end note

note right of Module::startUp()
  Called when this module is selected (via buttons).
end note

note right of Module::run()
  Is called continuously in the loop method.
end note

note right of Module::end()
  Called when another module is selected (via buttons).
end note

class Display {
+void print()
}

class DisplaySSD1306 {
-AdafruitSSD1306_Display* display
-int logo_width
-uint16_t i2c_address
+DisplaySSD1306()
}

class BpmMAX3010x {
-beatAvg
-display
-heartRate
-lastBeat
-rates
-rateSpot
-sensor
-startupTimer
+void BpmMAX3010x()
}

class FitnessTracker {
-Display* display
-Modules** modules
-Module* currentView
-int currentViewIndex
-int moduleCount
-int millisLastInterrupt
-bool switchModule
+FitnessTracker()
+initialize()
+OnButtonPressed()
+run()
}

Module <|-- Display
Display <|-- DisplaySSD1306
Module <|--- BpmMAX3010x

FitnessTracker *---- DisplaySSD1306
FitnessTracker *---- BpmMAX3010x
```
