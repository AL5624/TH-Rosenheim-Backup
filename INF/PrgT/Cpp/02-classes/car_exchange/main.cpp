#include "Car.h";

void exchangeCars(Car c1, Car c2) {
    Car tmp = c2;
    c2.setOwner(c1.getOwner());
    c2.setLicensePlate(c1.getLicensePlate());

    c1.setOwner(tmp.getOwner());
    c1.setLicensePlate(tmp.getLicensePlate());
};

int main() {

    // TODO implement the main function for the car exchange
    Car c1;
    Car c2("Porsche", "Anton", "AB 2020");



    return 0;
}