#include "Shape.h"
#include "Circle.h"

int main() {

    // TODO: Experiment with inheritance

    Shape shape("Shape", 1, 2, true);
    Circle circle("Circle", 4, 5, false, 10);

    circle.setVisible(true);

    shape.setName("Other Shape");

    circle.printOut();
    shape.printOut();

    circle.~Circle();

    return 0;
}