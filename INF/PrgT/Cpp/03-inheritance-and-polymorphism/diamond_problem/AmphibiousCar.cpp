#include "AmphibiousCar.h"
#include <iostream>

// TODO: Implement your AmphibiousCar class

AmphibiousCar::AmphibiousCar(int serial, int yearOfManufacture, int maxKmh, int maxKnots) : Car(serial, yearOfManufacture, maxKmh), Boat(serial, yearOfManufacture, maxKnots) {
	std::cout << "AmphibiousCar Constructor\n";
}

AmphibiousCar::~AmphibiousCar() {
	std::cout << "AmphibiousCar Deconstructor\n";
}