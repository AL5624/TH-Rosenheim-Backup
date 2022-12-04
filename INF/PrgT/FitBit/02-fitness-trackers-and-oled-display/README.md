# Fitness Trackers and OLED Display

The main goal of this Work Sheet is to:
*	evaluate relevant concepts implemented in today Fitness Tracker
*	get an introduction on suggested fitness tracker modules

## Current status of today’s Fitness Tracker

The Fitness Tracker which we will build in this lecture,
uses an Arduino Mega micro-controller board.
You got to know it theoretically in the first exercise FitBit 1.1 and
then also practically in the exercise FitBit 1.3.
Further information can be found in the Learning Campus (Arduino Fundgrube)
and also in the summary presentation worked out in teams of the FitBit 1.1.

This exercise is designed to help you make decisions
regarding the design of the software architecture,
as well as identify core requirements for your fitness tracker.
Given is the following web link:
https://www.chip.de/bestenlisten/Bestenliste-Fitness-Tracker--index/index/id/1171/

We divide into groups to watch the top 15 fit bits (according to chip.de):
* Group 1: Fitness Tracker # 1-3
* Group 2: Fitness Tracker # 4-6
* Group 3: Fitness Tracker # 7-9
* Group 4: Fitness Tracker # 10-12
* Group 5: Fitness Tracker # 13-15

Try to find answers to the following questions and summarize them in bullet points:

* Which sensors and their core functionality are installed?
* How flexible are the listed fitness tracker in terms of
  configuring the modules into the fit bit individually?
* How does our Fitness Tracker compare in price to the of-the-shelf FitBits?
  Our raw costs are currently about 48.30 €.
* Make a statement as to why a Fitness Tracker is important to you
  as a group and/or individually.
  What will you use it for?
* What are your must-have components of a Fitness Tracker?
* What steps do you think are needed to move the PrgT FitBit towards a
  wristwatch-based fitness tracker that could be sold commercially?

You have 30 minutes time for this,
we will discuss your results in the main group.
Please upload your summaries into the learning campus.


## Getting the OLED Display (Monochrome 128x32 I2C OLED) operational

### Instructions
1. Build the following setup:
   * Wiring:
     * Button input to Pin D2
     * Button output to GND
     * OLED SDA to the microcontroller SDA (data signal)
     * OLED SCK to the microcontroller SCL (clock signal)
     * OLED VCC to the microcontroller 5V line
     * OLED GND to the microcontroller GND
   * Arduino Mega:
     ![Arduino Mega with Button and Display](media/Arduino%20Mega%20Button%20+%20Display%20Breadboard.png)
   * Arduino Nano:
     ![Arduino Nano with Button and Display](media/Arduino%20Nano%20Button%20+%20Display%20Breadboard.png)
2. The needed libraries are already configured as dependency and will be
   supplied by PlatformIO automatically.
   Have a look at this project's `platformio.ini` to get an idea of this.
   * Go to Arduino ide/Sketch/Libraries/Manage libraries/
     *	search for gfx and select Adafruit GFX library by adafruit and install it
     *	search for Adafruit_ZeroDMA and install it
     *	search for ssd1306 and install it
3. Understand the example program listed here:
   https://github.com/adafruit/Adafruit_SSD1306/blob/master/examples/ssd1306_128x32_i2c/ssd1306_128x32_i2c.ino
4. Connect to the display:
   * Declare a display object
   * Output a "Hello World" on the display
   * Draw a small bitmap on the display
   * Combine both, a bitmap and a small text element.
     Pay attention to the positioning on the display.
   * Change the content of the display by pushing the button.

**Helpful links:**
* OLED Data Sheet:
  https://www.az-delivery.de/products/0-91-zoll-i2c-oled-display
* Online description of the core graphics library Adafruit GFX:
  https://learn.adafruit.com/adafruit-gfx-graphics-library
* Adafruit GFX Library on GitHub:
  https://github.com/adafruit/Adafruit-GFX-Library
* Adafruit SSD1306 (SSD1306 monochrome OLED driver library):
  https://github.com/adafruit/Adafruit_SSD1306

Further details regarding function can be found in `Adafruit_SSD1306.h`.
