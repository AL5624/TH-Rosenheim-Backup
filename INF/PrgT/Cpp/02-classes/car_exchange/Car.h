#pragma once

#ifndef INC_02_CLASSES_CAR_H
#define INC_02_CLASSES_CAR_H
#include <string>

// TODO 2.3: Define the Car class

class Car {
private:
	std::string brand;
	std::string owner;
	std::string licensePlate;

public:
	Car();

	Car(std::string, std::string, std::string);

	~Car();

	std::string getOwner() const;

	std::string getBrand() const;

	std::string getLicensePlate() const;

	void setOwner(std::string);

	void setBrand(std::string);

	void setLicensePlate(std::string);

	void show();
};

#endif //INC_02_CLASSES_CAR_H
