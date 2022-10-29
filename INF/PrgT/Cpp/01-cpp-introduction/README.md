# C++ Introduction
The goal of this Work Sheet is to solve small problem statements in a procedural
manner:
- practice C++ fundamentals such as conditionals, loops, switch statement
- realize function overloading
- practice call-by-reference and understand its usage
- work with 2D arrays
- investigate dynamic memory allocation
  
Each exercise is a standalone console application.

## Function Overloading
Given is the following method signature (prototype) which is used to calculate
polynomials:
```cpp
double polynomial(double x, double a0, double a1);
```

### Implement a linear polynomial calculation
Polynomial format: $`a_1 * x + a_0`$

### Overload `polynomial()` to calculate a square polynomial
Polynomial format: $`a_2 * x * x + a_1 * x + a_0`$  
(Hint: Call `polynomial()` from the previous task.)

### Overload `polynomial()` to calculate a cubic polynomial
Polynomial format: $`a_3 * x * x * x + a_2 * x * x + a_1 * x + a_0`$  
(Hint: Call `polynomial()` from the previous task.)

### Test your methods with input values
Example:
```
a0 = 1.3
a1 = -5.4
a2 = 2.8
a3 = 0.1
x = 6.6
```

### Create a formatted text output
Example:
```
For x = 6.6 we get:
Linear Polynomial: -34.34
Square Polynomial: 87.628
Cubic Polynomial: 116.378
```

## Pointers and References
* Implement a function `rotate()` that takes three int-values as input and 
  rotates them by assigning `a` to `b`, `b` to `c` and `c` to `a`.
* Let the user input three (different) integer values.
* Implement two versions of `rotate()`:
  * one that is taking pointers (c-style) and
  * one that is taking references (c++-style).
* Test your functions within your `main()`-method taking the values 5, 2, 9 and
  produce an output similar to this one:
```
Input values before rotate(): a = 5 b = 2 c = 9
Rotated values using pointers: a = 9 b = 5 c = 2
Rotated values using references: a = 2 b = 9 c = 5
```

## 2D Arrays and Dynamic Memory Allocation
In this exercise you work with 2D arrays as well as dynamic memory allocation.
Hint for the next two exercises:
You can implement everything in the `main()`-method or the functionality separate into multiple methods.
These exercises are untested. Feel free to experiment to your liking.

### Implement a program that creates a square output
```
& & & & & &
&         &
&         &
&         &
&         &
& & & & & &
```
To achieve this, you work with a 2D char array that is filled at the appropriate positions.
* The 2D char array is of the size `n * n` with `n = 6`.
* Initialize the array with `char c = ' ';`
* Fill the array with `char s= '&';` such that the output of the array content gives a square (as shown above).
* Use `std::cout` to display the array content on screen.

### Use dynamic memory allocation to achieve the same task
```
Allocation successful!
& & & & & &
&         &
&         &
&         &
&         &
& & & & & &
De-allocation successful!
```
* The 2D character array is of the size `n x n` with `n = 6`.
* Dynamically allocate memory using the `new` operator
* Initialize the array with `char c = ' ';`
* Fill the array with `char s = '&';` such that the output of the array content
  gives a square (as shown above).
* Use `std::cout` to display the array content on screen.
* Deallocate memory using the `delete` operator.

Reminder: Always use the correct operator pair for dynamic memory.
```cpp
/* Single-type/-object operators */
new
delete

/* Array operators */
new[]
delete[]
```
Any other combinations are undefined behaviour.

### What is the main difference between the two versions?
