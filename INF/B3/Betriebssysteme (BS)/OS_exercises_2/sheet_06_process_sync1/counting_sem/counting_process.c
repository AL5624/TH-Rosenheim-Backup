#include <stdio.h>     //printf, perror
#include <stdlib.h>    //EXIT_FAILURE, EXIT_SUCCESS
#include <string.h>    //sprintf
#include <unistd.h>    //open, close, read, write
#include <fcntl.h>     //flags: O_CREAT, O_EXCL
#include <semaphore.h> //sem_open, sem_wait, sem_post, sem_close, sem_unlink
#include <errno.h>     //errno

int main () {
    //TODO: Create or use the existing semaphore.

    //Main task: Loop 100000 times and add 1 to the counter inside the loop
    for (int i = 0; i < 100000; ++i) {
        //TODO: You have to place the P/V operations here...
        //      But remember, the Linux API does not provide P/V directly.


        //Open the file
        int file = open("counter", O_RDWR);
        if (file == -1) {
            printf("Could not open file, exiting!\n");
            exit(EXIT_FAILURE);
        }

        //Read the number
        const int MAX_LEN = 64;
        char number[MAX_LEN];
        read(file, &number, sizeof(number));
        
        //Convert the string into an integer
        int counter = atoi(number);
        counter++;

        //Write the new number into the counter
        sprintf(number, "%d\n", counter);
        lseek(file, 0, 0);
        write(file, &number, strlen(number) + 1);
        
        //Close the file
        close (file);
    }

    //TODO: Close and delete the semaphore

    printf("Finished!\n");

    return EXIT_SUCCESS;
}
