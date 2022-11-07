#include "Circle.h"
#include <iostream>

// TODO: Implement your Circle class

// (de)constructors

Circle::Circle() : Shape() {
    this->setRadius(0);

    this->printOut();
}

Circle::Circle(std::string name, int x, int y, bool isVisible, int r) : Shape(name, x, y, isVisible) {
    this->setRadius(r);

    this->printOut();
}

Circle::~Circle() {
    Shape::~Shape();
}

// setter

void Circle::setRadius(int value) {
    this->radius = value;
}

// getter

int Circle::getRadius() {
    return this->radius;
}

// methods

void Circle::printOut() {
    std::cout << "Circle {";
    std::cout << "  Name: " << this->getName() << ",\n";
    Position p = this->getPosition();
    std::cout << "  Position: x = " << p.x << ", y = "<< p.y << ",\n";
    std::cout << "  is visible: " << this->getVisible() << ",\n";
    std::cout << "  Radius: " << this->getRadius() << '\n';
    std::cout << "}\n";
}