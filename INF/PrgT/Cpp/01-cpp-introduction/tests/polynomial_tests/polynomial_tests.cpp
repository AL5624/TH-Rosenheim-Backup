#include "gtest/gtest.h"
#include "polynomial.h"

TEST(PolynomialTestSuite, LinearPolynomial){

ASSERT_NEAR(-34.34, polynomial(6.6, 1.3, -5.4), 0.01);
}

TEST(PolynomialTestSuite, SquarePolynomial){
    ASSERT_NEAR(87.628, polynomial(6.6, 1.3, -5.4, 2.8), 0.01);
}

TEST(PolynomialTestSuite, CubicPolynomial){
    ASSERT_NEAR(116.378, polynomial(6.6, 1.3, -5.4, 2.8, 0.1), 0.01);
}
