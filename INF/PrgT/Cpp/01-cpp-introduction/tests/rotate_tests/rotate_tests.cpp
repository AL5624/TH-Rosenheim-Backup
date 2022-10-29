#include "gtest/gtest.h"
#include "rotate.h"

TEST(RotateTestSuite, PointerRotateTests){

    int a = 1;
    int b = 2;
    int c = 3;

    int initial_a = a;
    int initial_b = b;
    int initial_c = c;

    rotate(&a, &b, &c);

    ASSERT_EQ(initial_a, b);
    ASSERT_EQ(initial_b, c);
    ASSERT_EQ(initial_c, a);
}

TEST(RotateTestSuite, ReferenceRotateTests){

    int a = 1;
    int b = 2;
    int c = 3;

    int initial_a = a;
    int initial_b = b;
    int initial_c = c;

    rotate(a, b, c);

    ASSERT_EQ(initial_a, b);
    ASSERT_EQ(initial_b, c);
    ASSERT_EQ(initial_c, a);
}
