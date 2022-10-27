#include <cstdlib>    //EXIT_SUCCESS
#include <iostream>   //std::cout
#include <cinttypes>  //int32_t
#include "cnl/include/cnl/all.h"

int main()
{
    //TODO: declare a binary fixed point variable with a precision of 7 digits (after the point)
    //      Hint:    cnl::fixed_point<int32_t, -precision> val{initial_value};
    //      Example: cnl::fixed_point<int32_t, -2> val{0}; //binary fixed point with 2 digits after the point
    //      Details: https://johnmcfarlane.github.io/cnl/

    for(int i = 1; i <= 500; ++i){
        //TODO: add 0.8 to your decimal point variable
        //      Hint: you may declare a inc variable (of type binary fixed point) to add
    }

    // TODO: print the result to std out using the output-stream
    //       Hint:    val is propably your variable
    //       Example: std::cout << "the value is: " << val << std::endl;

    return EXIT_SUCCESS;
}


