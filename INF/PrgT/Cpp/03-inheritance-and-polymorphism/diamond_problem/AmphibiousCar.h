#ifndef INC_03_INHERITANCE_AND_POLYMORPHISM_AMPHIBIOUSCAR_H
#define INC_03_INHERITANCE_AND_POLYMORPHISM_AMPHIBIOUSCAR_H

#include "Car.h"
#include "Boat.h"

// TODO: Define the AmphibiousCar class

class AmphibiousCar : Boat, Car {
public:

	AmphibiousCar(int serial, int yearOfManufacture, int maxKmh, int maxKnots);
	~AmphibiousCar();
};


#endif //INC_03_INHERITANCE_AND_POLYMORPHISM_AMPHIBIOUSCAR_H
