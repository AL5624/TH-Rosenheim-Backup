#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

int counter = 0;

void* work() {
    sleep(20); //simulates the "heavy" work!!

    //TODO: Add code for created threads here
    

    return NULL;
}

int main(int argc, char** argv){
    int N = 0; //N contains the number of threads to create

    //determine the number of threads as a program argument
    if(argc == 2){
        N = atoi(argv[1]); //first program argument
    } else {
        printf("Usage: %s N \n", argv[0]);
        exit(EXIT_FAILURE);
    }

    //TODO: Add code for the main thread here
    pthread_t thread_ids[N];

    return EXIT_SUCCESS;
}
