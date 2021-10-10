#include <stdio.h>  //fopen, ...
#include <stdlib.h> //EXIT_SUCCESS
#include <stdint.h> //uint8_t, uint16_t

uint16_t swap_bytes(uint16_t value_to_swap); //prototype

int main(void) {
    uint16_t value = 0;

    FILE* file = NULL;
    file = fopen("../output.txt", "rb");
        
    if (file == NULL) {
        printf("Error opening output.txt\n");
        return EXIT_FAILURE;
    }

    fread(&value, sizeof(uint16_t), 1, file);
    fclose(file);

    printf("Read from output.txt -> : %2x\n", value);

    //TODO: fix the byte order (you may call the swap_bytes() function for that)


    //TODO: print the fixed value

    
    return EXIT_SUCCESS;
}

uint16_t swap_bytes(uint16_t value_to_swap) {
    union {
        uint16_t value;
        uint8_t byte[2];
    } SWAP_TYPE;

    //TODO: fix the byte order (you may use the union for that)


    return SWAP_TYPE.value;
}
