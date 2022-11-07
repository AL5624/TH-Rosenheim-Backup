#include "Vehicle.h"
#include <iostream>


// TODO: Implement the Vehicle class

Vehicle::Vehicle(int serial, int yearOfManufacture) {
	this->serial = serial;
	this->yearOfManufacture = yearOfManufacture;
	std::cout << "Vehicle Constructor\n";
}

Vehicle::~Vehicle() {
	std::cout << "Vehicle Deconstructor\n";
}