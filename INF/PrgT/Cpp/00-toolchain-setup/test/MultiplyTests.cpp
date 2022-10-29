#include "gtest/gtest.h"
#include "multiply.h"


TEST(SquareTests, 1Squared)
{
    ASSERT_EQ(1, multiply(1, 1));
}

TEST(SquareTests, 2Squared)
{
    ASSERT_EQ(4, multiply(2, 2));
}

TEST(SquareTests, 3Squared)
{
    ASSERT_EQ(9, multiply(3, 3));
}

TEST(SquareTests, 4Squared)
{
    ASSERT_EQ(16, multiply(4, 4));
}


TEST(GuaranteedIntMaxTests, 1Times32767)
{
    ASSERT_EQ(32767, multiply(1, 32767));
}

TEST(GuaranteedIntMaxTests, 7Times4681)
{
    ASSERT_EQ(32767, multiply(7, 4681));
}

TEST(GuaranteedIntMaxTests, 31Times1057)
{
    ASSERT_EQ(32767, multiply(31, 1057));
}

TEST(GuaranteedIntMaxTests, 151Times217)
{
    ASSERT_EQ(32767, multiply(151, 217));
}


TEST(GuaranteedNegativeIntMaxTests, 1TimesNegative32768)
{
    ASSERT_EQ(-32768, multiply(1, -32768));
}

TEST(GuaranteedNegativeIntMaxTests, Negative1Times32768)
{
    ASSERT_EQ(-32768, multiply(-1, 32768));
}

TEST(GuaranteedNegativeIntMaxTests, 8TimesNegative4096)
{
    ASSERT_EQ(-32768, multiply(8, -4096));
}

TEST(GuaranteedNegativeIntMaxTests, Negative8Times4096)
{
    ASSERT_EQ(-32768, multiply(-8, 4096));
}
