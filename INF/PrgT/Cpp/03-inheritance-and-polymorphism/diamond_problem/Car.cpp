#include "Car.h"
#include <iostream>


// TODO: Implement the Car class

Car::Car(int serial, int yearOfManufacture, int maxKmh) : Vehicle(serial, yearOfManufacture) {
	this->maxKmh = maxKmh;
	std::cout << "Car Constructor\n";
}

Car::~Car() {
	std::cout << "Car Deconstructor\n";
}