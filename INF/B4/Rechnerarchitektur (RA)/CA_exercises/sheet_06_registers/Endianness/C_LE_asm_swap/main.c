#include <stdio.h>  //fopen, ...
#include <stdlib.h> //EXIT_SUCCESS
#include <stdint.h> //uint8_t, uint16_t

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

    //Hint:
    // Syntax (Intel): Op-code dst, src;
    // Example (Intel): MOV EAX, 1; //moves a 1 into the EAX register
    //- with XCHG instruction you can exchange bytes within a register:
    //- with AL you can use the AL register part (byte 0) of the (E)AX register
    //- with AH you can use the AH register part (byte 1) of the (E)AX register
    //  - https://www.felixcloutier.com/x86/xchg
    //  - infos: AMD64 Architecture Programmer’s Manual Volume 3: 
    //           General Purpose and System Instructions: Page 396 (356)
    //           http://support.amd.com/TechDocs/24594.pdf
    //  - https://c9x.me/x86/html/file_module_x86_id_328.html
    //  - https://www.utd.hs-rm.de/infobuch2/Buch_Webseite/kap03/Assemblerbefehle.pdf
    //  - https://www.ibiblio.org/gferg/ldp/GCC-Inline-Assembly-HOWTO.html
    // volatile: let the compiler don't move the the ASM instructions around
    __asm__ volatile (
        ""    // TODO: do the swap with a single ASM instruction here
        : "=a" (value)    // output: saves the AX register into the value 
                          // can be considered as: MOV value
        : "a"  (value)    // input: loads the value into the AX register 
                          // can be considered as: MOV AX, value
    );

    //print the fixed value
    printf("Converted to LE -> : %2x\n", value);
    
    return EXIT_SUCCESS;
}
