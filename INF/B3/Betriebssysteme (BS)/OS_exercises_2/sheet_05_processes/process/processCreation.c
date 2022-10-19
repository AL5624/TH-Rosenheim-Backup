#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int counter = 0;

void work() {
    sleep(20); //simulates the "heavy" work!!

    //TODO: Add code for created processes here
    
}

int main(int argc, char** argv){
    int N = 0; //N contains the number of processes to create

    //determine the number of processes as a program argument
    if(argc == 2){
        N = atoi(argv[1]); //first program argument
    } else {
        printf("Usage: %s N\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    //TODO: Write your code here
    pid_t child_pids[N];

    return EXIT_SUCCESS;
}
