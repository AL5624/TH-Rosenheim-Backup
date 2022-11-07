#include "Boat.h"
#include <iostream> 

// TODO: Implement the Boat class

Boat::Boat(int serial, int yearOfManufacture, int maxKnots) : Vehicle(serial, yearOfManufacture) {
	this->maxKnots = maxKnots;
	std::cout << "Boat Constructor\n";
}

Boat::~Boat() {
	std::cout << "Boat Deconstructor\n";
}