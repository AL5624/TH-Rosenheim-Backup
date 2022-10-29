#include <gtest/gtest.h>
#include "CarExchange.h"


TEST(CarExchangeTestSuite, ExchangeCars)
{
    Car c1("BMW", "Klaus", "RO-TH-1234");
    Car c2("Mercedes", "Hans", "RO-TH-9999");

    std::string brand_c1 = c1.getBrand();
    std::string brand_c2 = c2.getBrand();

    std::string owner_c1_old = c1.getOwner();
    std::string licensePlate_c1_old = c1.getLicensePlate();

    std::string owner_c2_old = c2.getOwner();
    std::string licensePlate_c2_old = c2.getLicensePlate();


    exchangeCars(c1, c2);

    EXPECT_EQ(c1.getOwner(), owner_c2_old);
    EXPECT_EQ(c1.getLicensePlate(), licensePlate_c2_old);

    EXPECT_EQ(c2.getOwner(), owner_c1_old);
    EXPECT_EQ(c2.getLicensePlate(), licensePlate_c1_old);

    EXPECT_EQ(c1.getBrand(), brand_c1);
    EXPECT_EQ(c2.getBrand(), brand_c2);
}
