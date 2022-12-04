#include <gtest/gtest.h>
#include "Shape.h"
#include "Circle.h"

TEST(CircleTestSuite, DefaultConstructorTests)
{
    Circle c;

    auto pos1 = c.getPosition();

    EXPECT_EQ(pos1.x, 0);
    EXPECT_EQ(pos1.y, 0);
    EXPECT_EQ(c.getName(), "no name");
    EXPECT_TRUE(c.getVisible());

    EXPECT_EQ(c.getRadius(), 1);
}


TEST(CircleTestSuite, ValueConstructorTests)
{
    Circle c1("Shape One", 1, 1, true, 2);
    Circle c2("Shape Two", 123, 456, false, 5);

    auto pos1 = c1.getPosition();

    EXPECT_EQ(pos1.x, 1);
    EXPECT_EQ(pos1.y, 1);
    EXPECT_EQ(c1.getName(), "Shape One");
    EXPECT_TRUE(c1.getVisible());
    EXPECT_EQ(c1.getRadius(), 2);

    auto pos2 = c2.getPosition();

    EXPECT_EQ(pos2.x, 123);
    EXPECT_EQ(pos2.y, 456);
    EXPECT_EQ(c2.getName(), "Shape Two");
    EXPECT_FALSE(c2.getVisible());
    EXPECT_EQ(c2.getRadius(), 5);
}

TEST(CirleTestSuite, NameSetterTests)
{
    Circle c;

    std::string newName("New Name");
    c.setName(newName);

    EXPECT_EQ(c.getName(), newName);
}

TEST(CircleTestSuite, PositionSetterTests)
{
    Circle c;
    int newX = 987;
    int newY = 654;

    c.setPosition(987, 654);

    auto newPos = c.getPosition();

    EXPECT_EQ(newPos.x, newX);
    EXPECT_EQ(newPos.y, newY);
}

TEST(CircleTestSuite, VisibilitySetterTests)
{
    Circle c;

    EXPECT_TRUE(c.getVisible());
    c.setVisible(false);
    EXPECT_FALSE(c.getVisible());
    c.setVisible(true);
    EXPECT_TRUE(c.getVisible());
}

TEST(CircleTestSuite, RadiusSetterTests)
{
    Circle c;

    EXPECT_EQ(c.getRadius(), 1);

    int newRadius = 7;

    c.setRadius(newRadius);

    EXPECT_EQ(c.getRadius(), newRadius);
}