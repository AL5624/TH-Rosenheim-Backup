#ifndef INC_03_INHERITANCE_AND_POLYMORPHISM_BOAT_H
#define INC_03_INHERITANCE_AND_POLYMORPHISM_BOAT_H
#include "Vehicle.h"

// TODO: Define the Boat class

class Boat : Vehicle {
public:

	int maxKnots;

	Boat(int serial, int yearOfManufacture, int maxKnots);
	~Boat();
};


#endif //INC_03_INHERITANCE_AND_POLYMORPHISM_BOAT_H
