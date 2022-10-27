#include <stdio.h> //printf
#include <stdlib.h> //EXIT_SUCCESS
#include <stdint.h> //uint64_t, ..

int main(int argc, char* argv[]) {

    uint64_t N = 1e9;
    if(argc == 2){
        N = (uint64_t)atol(argv[1]);
    }

    uint64_t sum = 0;
    for(uint64_t i = 0; i < N; ++i){
        sum += i;
    }

    printf("The sum is: %lu", sum);

    return EXIT_SUCCESS;
}