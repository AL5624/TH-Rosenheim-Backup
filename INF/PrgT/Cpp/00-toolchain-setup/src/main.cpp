#include <iostream>
#include "multiply.h"

int main() {

    std::cout << "Hello, World!" << std::endl;

    /*
     * Using the end of line operator for large amounts of text output to console can lead to performance issues,
     * as the buffer is flushed after each line.
     *
     * The final flush is performed separately after all sums have been calculated.
    */
    std::cout << "The results of the first twenty integer squares are:" << "\n";

    for (int i = 1; i <= 20; ++i) {
        std::cout << i << " * " << i << ": " << multiply(i, i) << "\n";
    }
    std::cout << std::flush; // Flush for text to appear

    system.p

    return 0;
}
