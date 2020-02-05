#include <stdio.h>     //printf, perror
#include <stdlib.h>    //EXIT_FAILURE, EXIT_SUCCESS
#include <stdbool.h>   //bool
#include <string.h>    //strcmp, strcpy
#include <sys/shm.h>   //shm*
#include <fcntl.h>     //flags: O_CREAT, O_EXCL
#include <semaphore.h> //sem_open, sem_wait, sem_post, sem_close

const key_t SHM_KEY  = 0x424242;   //Key of the shared memory segment
#define MAX_SHM_LEN        (1024)    // length of shared memory

/*
 * The log server consists of two semaphores
 * - ready_to_write_sem - Initialized with 1
 * - ready_to_read_sem  - Initialized with 0
 */
#define SEMAPHORE_READY_TO_WRITE_NAME "/log_rw" //name of semaphore
#define SEMAPHORE_READY_TO_READ_NAME  "/log_rr" //name of semaphore
sem_t* ready_to_write_sem = NULL;
sem_t* ready_to_read_sem  = NULL;
const int PERM            = 0600;    //Permission to the semaphore and shared memory

void fetch_semaphore() {
    ready_to_write_sem = sem_open(SEMAPHORE_READY_TO_WRITE_NAME, O_EXCL);
    if(ready_to_write_sem == SEM_FAILED){
        perror("Error when opening the semaphore ...\n");
        exit(EXIT_FAILURE);
    }

    ready_to_read_sem = sem_open(SEMAPHORE_READY_TO_READ_NAME, O_EXCL);
    if(ready_to_read_sem == SEM_FAILED){
        perror("Error when opening the semaphore ...\n");
        exit(EXIT_FAILURE);
    }
}

void close_semaphore() {
    if(sem_close(ready_to_write_sem) == -1){
        perror("Error can't close semaphore ...\n");
        exit(EXIT_FAILURE);
    }

    if(sem_close(ready_to_read_sem) == -1){
        perror("Error can't close semaphore ...\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    //TODO: fetch the semaphores


    //TODO: get existing shared memory
    
    
    //TODO: attach the shared memory
    

    //TODO: let buffer point the shared mem address as a char pointer
    char* buffer = NULL;
    
    //client endless loop
    while(true) {
        char message[MAX_SHM_LEN];
        fgets(message, MAX_SHM_LEN, stdin);

        if(strcmp("\\quit\n", message) == 0) { //quit if user types: \quit
            break;
        }

        //TODO: use P/V and copy the message into the shared memory
        //Hint: For copy use strcpy()
        
    }

    //TODO detach shared memory
    

    //TODO close semaphore


    return EXIT_SUCCESS;
}
