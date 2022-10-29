# Classes
In this exercise we are dedicated to the classes of object-oriented programming realized in C++.
For this purpose you will implement several classes and their concepts.
Learning Objectives:
* Correct class declarations
* Correct class implementation
* Correct #include-statements
* Visibility (Private and public)
* Object creation and
* the use of object methods

## Dices and Players

In this exercise you will implement a die game in which two players roll a die against each other.
Therefore, you'll implement two classes: Player and Dice.
Implement your classes in split declaration


### Implement the Dice class

```plantuml
class Dice {
-unsigned int minimum
-unsigned int maximum
-std::random_device randomDevice

+Dice(unsigned int min, unsigned int max)
+unsigned int getRandomNumber()
}
```
**Requirements:**
* Initialize your dice during the constructor call:
  * Set minimum value
  * Set maximum value
* Use `std::random_device` to avoid predicting the generated values.
* Implement `getRandomNumber` by using minimum and maximum
  to determine the interval of the generated numbers


### Implement the Player class
```plantuml
class Player {
-unsigned int score
+unsigned int rollDice(Dice dice)
+unsigned int getScore()
}
```

**Requirements:**
* The function `rollDice()` rolls the dice,
  * adds the rolled score to the player score and
  * returns the rolled score.
* The function `getScore()` returns the cumulative score of a player

### Create Players and Let Them Roll the Dice

Let each player roll the dice one or multiple times,
print out the current score of a die roll and the cumulative score at the end.


## Cars and Car Exchanges

### Implement the Car Class
```plantuml
class Car {
-std::string brand
-std::string owner
-std::string licensePlate

+Car()
+Car(std::string brand, std::string owner, std::string licensePlate)

+~Car()

+std::string getOwner()
+std::string getBrand()
+std::string getLicensePlate()

+void setOwner(std::string owner)
+void setBrand(std::string brand)
+void setLicensePlate(std::string licensePlate)

+std::string show()
}
```

**Requirements**
* When calling the default constructor `Car()`,
  initialize the fields with the string `n.a.`
* Display the creation of a car on the console using the Constructors
* Display the destruction of the car object on the console
  using the destructor `~Car()`
* Implement the method `show()` to return a string form of the car class Car
  values
* Call the method `show`and output the returned string to the console


### Car Exchange

Define and implement a function `void exchangeCars(Car c1, Car c2)` that
exchanges the two given cars. The brand remains the same, while owner and
license plate get exchanged.


### Main Program

**Requirements**
* Create three Car objects with
  * I. no input,
  * II. brand and owner as input and
  * III. brand, owner and license plate as input
* Display all three cars
* Change the information for the first Car object using the setters
* Display that information to verify the change

### Questions

*Edit the answer blocks using markdown and commit your answers.
Tags indicate the preferred answer format.*

1. What is a possible output on the console,
   in case `exchangeCars(...)` has been realized by call-by-reference?
   >
2. What is a possible output on the console,
   in case `exchangeCars(...)` has been realized by call-by-value?
   >
3. What changes when constructors/destructors are missing?
   >
4. How would constructor delegation look like? (Code example)
   ```cpp
    
   ```


## Copy Constructor

`A` is any C++ class with standard and copy constructor.
Which of the following functions are correct?

If the function is correct,
specify the number of calls of the default, parameter constructor or
copy constructor.
If the function is not correct, explain the error.

1. ```cpp
   A& f1() { A a; return a; }
   ```
   >
2. ```cpp
   A* f2(A a) { A b(a); return new A(b); }
   ```
   >
3. ```cpp
   A* f3(A* a) { return a; }
   ```
   >
4. ```cpp
   A& f4(A& a) { A b(a); return a; }
   ```
   >
5. ```cpp
   A f5(A& a) { A* b = new A(a); return *b; }
   ```
   >

## Structs

Define a struct `Coordinate` as specified by the UML diagramm below:

```plantuml
hide circle

class Coordinate {
double x
double y
}
```

Write a main function that declares two Coordinate structs and initialize them.
Print the Coordinates on screen, similar to this:
```
P1 = (P12.3, 14.5)
P2 = (0.2, 10.3)
```

Calculate the linear equation defined by:
```math
y = m*x + c \\
m = dy/dx \\
dx = x_2 - x_1 \\
dy = y_2 - y_1 \\
```

Use structured binding to extract the x and y information from each point.
Display the solution on screen.
Avoid divisions by zero `(dx == 0)`.
In that case, P1 and P2 are along a vertical.


## Unions


Define a union according the following UML diagram:

```plantuml
hide circle

class BankAccount {
int accountNumber
char owner[30]
double balance
}
```

Write a main function that creates an object of the union BankAccount.
Initialize the bank account in two different ways:

1. Ask for input from the command line for bank account number, owner and 
   account balance. 
   Use each element at a time by storing it into the bank account object and 
   printout the initialized value to the command line.

2. Ask for input from the command line for bank account number, owner and 
   bank account balance. 
   Read in parameter by parameter and initialize the appropriate union variable.

Add three `std::cout` statements at the end and describe what's happening:
>
