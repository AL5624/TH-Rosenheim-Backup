#include "polynomial.h"

double polynomial(double x, double a0, double a1)
{
    // TODO 1.1 a): Implement your function
    return a1 * x + a0;
}

// TODO 1.1 b): Implement your Overload for square polynomials

double polynomial(double x, double a0, double a1, double a2)
{
    return polynomial(x, a1 * x + a0, a2 * x);
}

// TODO 1.1 c): Implement your Overload for cubic polynomials

double polynomial(double x, double a0, double a1, double a2, double a3)
{
    return polynomial(x, a1 * x + a0, a2 * x, a3 * x);
}