#include <cstdlib>    //EXIT_SUCCESS
#include <iostream>   //std::cout
#include "decimal_for_cpp/include/decimal.h"

int main(void)
{
    //TODO: declare a decimal fixed point variable with a precision of 1 digit (after the point)
    //      Hint:    dec::decimal<precision> val(initial_value);
    //      Example: dec::decimal<2> val(0.0); //decimal fixed point with 2 digits after the point
    //      Details: https://github.com/vpiotr/decimal_for_cpp
    

    for(int i = 1; i <= 500; ++i){
        //TODO: add 0.8 to your decimal point variable
        //      Hint: you may declare a inc variable (of type: decimal fixed point) to add
    }

    // TODO: print the result to std out using the output-stream
    //       Hint:    val is propably your variable
    //       Example: std::cout << "the value is: " << val << std::endl;


    return EXIT_SUCCESS;
}