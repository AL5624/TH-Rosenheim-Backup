#include <stdlib.h>
#include <inttypes.h>
#include <stdbool.h> //bool

typedef volatile struct { //FSI interface
    uint16_t CSR;  //control and status register
    uint16_t TBUF; //transmit buffer register
    uint16_t RBUF; //receive buffer register
    uint16_t CFR;  //configuration register
} FsiStruct;

//FSI: FsiStruct is mapped to the memory position 0xFF000000
#define FSI  (*((FsiStruct*)(0xFF000000)))

#define TRDY (0B1000000000000000) //Mask for TRDY (or: 0x8000)
#define TIE  (0B0100000000000000) //Mask for TIE  (or: 0x4000)
#define RRDY (0B0000000010000000) //Mask for RRDY (or: 0x0080)
#define RIE  (0B0000000001000000) //Mask for RIE  (or: 0x0040)
//more defines...

#define BUFFER_SIZE (16)
uint8_t buffer[BUFFER_SIZE] = {0}; //initialise all elements with 0

//TODO: write the transfer with the busy wait approach
int main(void) {
    
    
    
    
    
    
    
    
    
    
    
    
    return EXIT_SUCCESS;
}
