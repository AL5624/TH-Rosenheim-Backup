/**********************************/
/* mycat - ein cat fuer den scull */
/* Erstellt von Korbinian Hammer  */
/**********************************/

#include <stdlib.h>
#include <stdio.h>

#define MAX 8182

int main(int argc, char* argv[]) {
    if(argc != 2) {
        printf("Usage: mycat <file>\n<file> has to be exactly ONE argument!\n");
        return EXIT_FAILURE;
    }
    
    FILE* file = fopen(argv[1], "r");
    if(file == NULL) {
        printf("Can't open file!\n");
        return EXIT_FAILURE;
    }

    char buffer[MAX];
    size_t count = fread(buffer, sizeof(char), MAX, file);

    for(size_t i = 0; i < count; i++) {
        printf("%c", buffer[i]);
    }

    fclose(file);

    return EXIT_FAILURE;
}
