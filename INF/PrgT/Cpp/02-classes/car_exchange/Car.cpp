#include "Car.h"
#include <iostream>

// TODO 2.3: Implement the Car class

const std::string d = "n.a.";

Car::Car() : brand(d), owner(d), licensePlate(d) {};

Car::Car(std::string b, std::string o, std::string lp) : brand(b), owner(o), licensePlate(lp) {
	std::cout << "Car created;\n";
};

Car::~Car() {
	std::cout << "Car destroyed;\n";
};

std::string Car::getBrand() const {
	return brand;
}

std::string Car::getOwner() const {
	return owner;
}

std::string Car::getLicensePlate() const {
	return licensePlate;
}

void Car::setOwner(std::string value) {
	owner = value;
}

void Car::setBrand(std::string value) {
	brand = value;
}

void Car::setLicensePlate(std::string value) {
	licensePlate = value;
}

std::string Car::show() {
	std::cout << "Brand: " << brand << "Owner: " << owner << "License Plate: " << licensePlate << ";\n";
};

