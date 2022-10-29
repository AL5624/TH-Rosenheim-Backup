#include <gtest/gtest.h>
#include "Car.h"

TEST(CarTestSuite, DefaultConstructor)
{
    Car car;
    std::string na_string("n.a.");
    EXPECT_EQ(car.getBrand(), "n.a.");
    EXPECT_EQ(car.getOwner(), "n.a.");
    EXPECT_EQ(car.getLicensePlate(), "n.a.");
}


Car car("BMW", "Klaus", "RO-TH-1234");

TEST(CarTestSuite, BrandGetterSetter){

    EXPECT_EQ(car.getBrand(), "BMW");

    std::string newBrand = "VW";
    car.setBrand(newBrand);
    EXPECT_EQ(car.getBrand(), newBrand);
}

TEST(CarTestSuite, OwnerGetterSetter)
{
    EXPECT_EQ(car.getOwner(), "Klaus");

    std::string newOwner = "Hans";
    car.setOwner(newOwner);
    EXPECT_EQ(car.getOwner(), newOwner);
}

TEST(CarTestSuite, LicensePlateGetterSetter)
{
    EXPECT_EQ(car.getLicensePlate(), "RO-TH-1234");

    std::string newLicensePlate = "RO-TH-9999";
    car.setLicensePlate(newLicensePlate);
    EXPECT_EQ(car.getLicensePlate(), newLicensePlate);
}
