#include <gtest/gtest.h>
#include "Car.h"
#include "Bicycle.h"
#include "Tricycle.h"
#include "Motorcycle.h"
#include "Vehicle.h"


TEST(VehicleSoundTestSuite, CarSoundTest)
{
    Car car;
    EXPECT_EQ(car.drive(), "honk honk");
}

TEST(VehicleSoundTestSuite, BicycleSoundTest)
{
    Bicycle bicycle;
    EXPECT_EQ(bicycle.drive(), "ring ring");
}

TEST(VehicleSoundTestSuite, MotorcycleSoundTest)
{
    Motorcycle motorcycle;
    EXPECT_EQ(motorcycle.drive(), "nee nee");
}

TEST(VehicleSoundTestSuite, TricycleSoundTest)
{
    Tricycle tricycle;
    EXPECT_EQ(tricycle.drive(), "step step");
}


TEST(VehiclePolymorphismTestSuite, CarPolymorphismTest)
{
    Car car;
    Vehicle* car_vehicle = &car;

    EXPECT_EQ(car_vehicle->drive(), "honk honk");
}

TEST(VehiclePolymorphismTestSuite, BicyclePolymorphismTest)
{
    Bicycle bicycle;
    Vehicle* bicycle_vehicle = &bicycle;

    EXPECT_EQ(bicycle_vehicle->drive(), "ring ring");
}

TEST(VehiclePolymorphismTestSuite, MotorcyclePolymorphismTest)
{
    Motorcycle motorcycle;
    Vehicle* motorcycle_vehicle = &motorcycle;

    EXPECT_EQ(motorcycle_vehicle->drive(), "nee nee");
}

TEST(VehiclePolymorphismTestSuite, TricyclePolymorphismTest)
{
    Tricycle tricycle;
    Vehicle* tricycle_vehicle = &tricycle;

    EXPECT_EQ(tricycle_vehicle->drive(), "step step");
}