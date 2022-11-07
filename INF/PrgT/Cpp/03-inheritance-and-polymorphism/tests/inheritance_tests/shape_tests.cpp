#include <gtest/gtest.h>
#include "Shape.h"

TEST(ShapeTestSuite, DefaultConstructorTests)
{
    Shape s;

    auto pos1 = s.getPosition();

    EXPECT_EQ(pos1.x, 0);
    EXPECT_EQ(pos1.y, 0);
    EXPECT_EQ(s.getName(), "no name");
    EXPECT_TRUE(s.getVisible());
}

TEST(ShapeTestSuite, ValueConstructorTests)
{
    Shape s1("Shape One", 1, 1, true);
    Shape s2("Shape Two", 123, 456, false);

    auto pos1 = s1.getPosition();

    EXPECT_EQ(pos1.x, 1);
    EXPECT_EQ(pos1.y, 1);
    EXPECT_EQ(s1.getName(), "Shape One");
    EXPECT_TRUE(s1.getVisible());

    auto pos2 = s2.getPosition();

    EXPECT_EQ(pos2.x, 123);
    EXPECT_EQ(pos2.y, 456);
    EXPECT_EQ(s2.getName(), "Shape Two");
    EXPECT_FALSE(s2.getVisible());
}

TEST(ShapeTestSuite, NameSetterTests)
{
    Shape s;

    std::string newName("New Name");
    s.setName(newName);

    EXPECT_EQ(s.getName(), newName);
}

TEST(ShapeTestSuite, PositionSetterTests)
{
    Shape s;
    int newX = 987;
    int newY = 654;

    s.setPosition(987, 654);

    auto newPos = s.getPosition();

    EXPECT_EQ(newPos.x, newX);
    EXPECT_EQ(newPos.y, newY);
}

TEST(ShapeTestSuite, VisibilitySetterTests)
{
    Shape s;

    EXPECT_TRUE(s.getVisible());
    s.setVisible(false);
    EXPECT_FALSE(s.getVisible());
    s.setVisible(true);
    EXPECT_TRUE(s.getVisible());
}