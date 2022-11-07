#include "Vehicle.h"
#include "Car.h"
#include "Boat.h"
#include "AmphibiousCar.h"

int main() {

    // TODO: Call your classes
    int serial = 0;
    int yearOfManufacture = 2000;
    int maxKmh = 50;
    int maxKnots = 3;

    Vehicle vehicle(++serial, ++yearOfManufacture);

    Car car(++serial, ++yearOfManufacture, maxKmh);

    Boat boat(++serial, ++yearOfManufacture, maxKnots);

    AmphibiousCar ac(++serial, ++yearOfManufacture, maxKmh + 10, maxKnots + 10);    

    return 0;
}
