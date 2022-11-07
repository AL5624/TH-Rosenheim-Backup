#ifndef INC_03_INHERITANCE_AND_POLYMORPHISM_SHAPE_H
#define INC_03_INHERITANCE_AND_POLYMORPHISM_SHAPE_H

#include <string>
#include "Position.h"


// TODO: Define your Shape class

class Shape {
private:

    std::string name;
    bool visible;
    Position position;

public:

    Shape();
    Shape(std::string name, int x, int y, bool isVisible);
    virtual ~Shape();

    void setName(std::string name);
    void setPosition(int x, int y);
    void setVisible(bool vis);

    std::string getName();
    Position getPosition();
    bool getVisible();

    virtual void printOut();    
};


#endif //INC_03_INHERITANCE_AND_POLYMORPHISM_SHAPE_H
