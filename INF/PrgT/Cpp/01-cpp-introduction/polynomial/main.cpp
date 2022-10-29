#include <iostream>
#include "polynomial.h"

int main() {

    auto a0 = 1.3;
    auto a1 = -5.4;
    auto a2 = 2.8;
    auto a3 = 0.1;
    auto x = 6.6;

    std::cout << "Linear Polynomial:" << polynomial(x, a0, a1) << "\n";
    std::cout << "Square Polynomial:" << polynomial(x, a0, a1, a2) << "\n";
    std::cout << "Cubic Polynomial:" << polynomial(x, a0, a1, a2, a3) << "\n";

    return 0;
}
