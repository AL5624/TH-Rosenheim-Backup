#ifndef INC_03_INHERITANCE_AND_POLYMORPHISM_CIRCLE_H
#define INC_03_INHERITANCE_AND_POLYMORPHISM_CIRCLE_H

#include <string>
#include "Shape.h"

// TODO: Define your Circle class

class Circle : public Shape {
private:

    int radius;

public:

    Circle();
    Circle(std::string name, int x, int y, bool isVisible, int r);
    ~Circle();

    void setRadius(int r);

    int getRadius();

    void printOut() override;
};


#endif //INC_03_INHERITANCE_AND_POLYMORPHISM_CIRCLE_H
