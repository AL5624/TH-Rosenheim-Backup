#include <stdlib.h>
#include <stdbool.h>
#include <inttypes.h>

typedef volatile struct { //FDD interface
    uint32_t CSR;  //control and status register
    uint32_t DARH; //disk address register HI
    uint32_t DARL; //disk address register LO
    uint32_t BAR;  //bus address register
    uint32_t BCR;  //byte count register
} FddStruct;

//FDD: FddStruct is mapped to the memory position 0xFF000010
#define FDD  (*((FddStruct*)(0xFF000010)))

#define GO    (0x01) //Mask for GO
#define IE    (0x40) //Mask for IE
#define WRITE (0x02) //Mask for WRITE
//more defines...

typedef void (*ISR_t)(void); //Function pointer for an ISR
//INTVECTOR: ISR_t is mapped to the memory position 0x00000108
#define INTVECTOR (*((ISR_t*)(0x00000108)))

void ISR() { //interrupt service routine for the end of the transfer
    //notify application that everything is transferred
}

int main(void) {
    //TODO: set ISR
    

    //TODO: Configure DMA interface
    //In principle: {source, destination, how much, IE | R/W | GO}
    
    
    
    
    

    //DMA transmits data now without CPU.
    //At the end there is an interrupt!

    return EXIT_SUCCESS;
}