# Inheritance and Polymorphism

The main goal of this work sheet is to practice:
* realizing inheritance
* working with virtual methods
* handling polymorphism
* multiple inheritance and
* virtual inheritance

in C++.

Each exercise is a standalone console application.


## Inheritance

### Implementation
Implement the following UML class diagram with inheritance:

```plantuml
class Position {
+int x
+int y
}


class Shape {
-std::string name = "no name"
-bool visible = true
__
+Shape()
+Shape(std::string name, int x, int y, bool isVisible)
+Shape()
..
+void setName(std::string name)
+void setPosition(int x, int y)
+void setVisible(bool vis)
..
+std::string getName()
+Position getPosition()
+bool getVisible()
..
+void printOut()
}

Shape o- Position


class Circle {
-int radius = 1
__
+Circle()
+Circle(std::string name, int x, int y, bool isVisible, int r)
+Circle()
..
+void setRadius(int r)
..
+int getRadius()
..
+void printOut()
}

Shape <|-- Circle
```
**Requirements:**
* For easier visualization, customize constructors and destructors by adding
  `std::cout` statements to them
* Implement the `printOut()` function to display the basic parameters of the
  parent class `Shape`, and the additional parameters of the subclass `Circle`

### Experimentation  
Research the difference between constructor and initializer list initialization
and experiment with the different initialization methods.
<!-- TODO: Getter Initialization -->

After implementing the classes, experiment further by:
* creating objects,
* changing the object's values with the setters,
* verifying your changes on the console using `printOut`
* trying to access the base class members and methods by using
  `circName.Circle::printOut()`, for example
* using pointers to access objects and its members and functions
* explicitly destroying objects by calling their constructor


## Virtual Methods

Start situation:
* Vehicle classes that can `drive()` and
  each makes (returns) a different sound when driving:
  * Car: "honk honk"
  * Tricycle: "step step"
  * Motorcycle: "nee nee"
  * Bicycle: "ring ring"

1. Design a UML class diagram that captures the situation using PlantUML.
   Use the exact names and sound imitations.
2. Implement your class diagram in C++
   using virtual interfaces and virtual methods.
3. Test your implementation by experimenting with it in the main function.
4. Test your implementation against the test drivers.

Your class diagram:
```plantuml
hide empty description

```


## Polymorphism
Create an array of length 10 in which you store pointers to different Vehicles.
Store any kind of vehicles from the virtual methods exercise into the array.

Write a function `trafficJam(...)` that takes aforementioned array as parameter.
The function calls the `drive()`-function of each element it contains.


## Diamond Problem

Implement the following multiple inheritance situation without considering solving the diamond problem yet:

```plantuml
class Vehicle {
+int serial
+int yearOfManufacture

+Vehicle(int serial int yearOfManufacture)
+~Vehicle()
}

class Car {
+int maxKmh
+Car(int serial, int yearOfManufacture, int maxKmh)
+~Car()
}

class Boat {
+int maxKnots
+Boat(int serial, int yearOfManufacture, int maxKnots)
+~Boat()
}

class AmphibiousCar {
+AmphibiousCar(int serial, int yearOfManufacture, int maxKmh, int maxKnots)
+~AmphibiousCar()
}

Vehicle <|-- Car
Vehicle <|-- Boat

Car <|-- AmphibiousCar
Boat <|-- AmphibiousCar
```

**Requirements**  
Each of the classes has a customized constructor and destructor (`std::cout` inside these methods).

Now realize virtual inheritance and rerun the program. What happens?
