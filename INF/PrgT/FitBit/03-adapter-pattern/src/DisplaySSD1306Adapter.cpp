#include "DisplaySSD1306Adapter.h"

DisplaySSD1036Adapter::DisplaySSD1036Adapter(Adafruit_SSD1306 display, int16_t i2c_address, int logo_width) {
    this->display = display;
    this->i2c_address = i2c_address;
    this->logo_width = logo_width;
};

void DisplaySSD1036Adapter::initialze() {
    display.begin(SSD1306_SWITCHCAPVCC, i2c_address);
    display.clearDisplay();
    display.setTextSize(2);
    display.setTextColor(SSD1306_WHITE);
    display.println("Display");
    display.println("booted");
    display.display();
};

void DisplaySSD1036Adapter::print(const unsigned char* logo, unsigned char* data, int data_length) {
    display.clearDisplay();
    display.drawBitmap(0, 0, logo, logo_width, logo_width, SSD1306_WHITE);
    display.setTextSize(2);
    display.setTextColor(SSD1306_WHITE);
    display.setCursor(50, 0);
    for (int i = 0; i < data_length; i++)
    {
        display.print((char) data[i]);
    }
    display.display();
};

