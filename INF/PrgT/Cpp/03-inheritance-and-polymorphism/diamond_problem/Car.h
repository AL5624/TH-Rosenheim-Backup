#ifndef INC_03_INHERITANCE_AND_POLYMORPHISM_CAR_H
#define INC_03_INHERITANCE_AND_POLYMORPHISM_CAR_H

#include "Vehicle.h"

// TODO: Define the Car class

class Car : Vehicle {
public:

	int maxKmh;

	Car(int serial, int yearOfManufacture, int maxKmh);
	~Car();
};


#endif //INC_03_INHERITANCE_AND_POLYMORPHISM_CAR_H
