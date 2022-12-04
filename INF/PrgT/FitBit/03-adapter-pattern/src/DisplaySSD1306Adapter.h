#include "Display.h"
#include <inttypes.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <Wire.h>

class DisplaySSD1036Adapter : public Display {
private:
    Adafruit_SSD1306 display;
    int16_t i2c_address;
    int logo_width;

public:

    DisplaySSD1036Adapter(Adafruit_SSD1306 display, int16_t i2c_address, int logo_width);

    void initialze() override;
    void print(const unsigned char* logo,unsigned char* data, int data_length) override;
};