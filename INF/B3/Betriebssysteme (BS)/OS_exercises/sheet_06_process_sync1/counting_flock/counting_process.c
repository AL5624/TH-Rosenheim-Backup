#include <stdio.h>     //printf, perror
#include <stdlib.h>    //EXIT_FAILURE, EXIT_SUCCESS
#include <string.h>    //sprintf
#include <unistd.h>    //open, close, read, write
#include <fcntl.h>     //flags: O_CREAT, O_EXCL
#include <errno.h>     //errno
#include <sys/file.h>  //flock
#include <sys/stat.h>  //umask

int main () {
    //Main task: Loop 100000 times and add 1 to the counter inside the loop
    for (int i = 0; i < 100000; ++i) {
        //TODO: You have to place the P/V operations here...
        //      use flock() to perform P/V


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

    printf("Finished!\n");

    return EXIT_SUCCESS;
}
