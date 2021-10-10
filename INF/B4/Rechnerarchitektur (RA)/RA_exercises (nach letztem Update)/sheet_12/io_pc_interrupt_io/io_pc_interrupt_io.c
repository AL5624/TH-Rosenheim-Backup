#include <stdlib.h>
#include <inttypes.h>

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

typedef void (*ISR_t)(void); //Function pointer for an ISR
//INTVECTOR: ISR_t is mapped to the memory position 0x000000C8
#define INTVECTOR (*((ISR_t*)(0x000000C8)))

//TODO: initialise BUFFER_SIZE
#define BUFFER_SIZE (  )
volatile uint8_t counter = 0;
volatile uint8_t buffer[BUFFER_SIZE] = {0}; //initialise all elements with 0

void ISR_serial_read(); //prototype

int main(void) {
    //TODO: Start transfer
    
    

    //transfer is in progress...

    return EXIT_SUCCESS;
}

//TODO: write an ISR that is triggered if
//the hardware has provided a new character
//TODO: after all characters are transferred
//stop the transfer by disabling the interrupt








