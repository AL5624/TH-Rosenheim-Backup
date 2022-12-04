#include "Shape.h"
#include <iostream>


// TODO: Implement your Shape class

// (de)constructors

Shape::Shape() : Shape("no name", 0, 0, true) {}

Shape::Shape(std::string name, int x, int y, bool isVisible) {
    this->setName(name);
    this->setPosition(x, y);
    this->setVisible(isVisible);
    this->printOut();
}

Shape::~Shape() {
    std::cout << "Shape deconstructed\n";
}

// setter

void Shape::setName(std::string value) {
    this->name = value;
}

void Shape::setPosition(int x, int y) {
    this->position.x = x;
    this->position.y = y;
}

void Shape::setVisible(bool value) {
    this->visible = value;
}

// getter

std::string Shape::getName() {
    return this->name;
}

Position Shape::getPosition() {
    return this->position;
}

bool Shape::getVisible() {
    return this->visible;
}

// methods

void Shape::printOut() {
    std::cout << "Shape: {\n";
    std::cout << "  Name: " << this->getName() << ",\n";
    Position p = this->getPosition();
    std::cout << "  Position: x = " << p.x << ", y = " << p.y << ",\n";
    std::cout << "  is visible: " << this->getVisible() << '\n';
    std::cout << "}\n";
}