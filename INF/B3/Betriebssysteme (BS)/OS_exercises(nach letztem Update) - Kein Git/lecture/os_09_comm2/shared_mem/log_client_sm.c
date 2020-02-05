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
#define SEMAPHORE_READY_TO_WRITE_NAME "/chat_rw" //name of semaphore
#define SEMAPHORE_READY_TO_READ_NAME  "/chat_rr" //name of semaphore
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
    //fetch the semaphores
    fetch_semaphore();

    //get existing shared memory
    int shared_mem_id = shmget(SHM_KEY, 0, IPC_PRIVATE);
    if(shared_mem_id < 0) {
        perror("Error: can't get shared memory!\n");
        exit(EXIT_FAILURE);
    }
    
    //attach the shared memory
    void* shared_mem_address = shmat(shared_mem_id, NULL, 0);
    if(shared_mem_address == (void*)-1) {
        perror("Error: can't attach shared memory!\n");
        exit(EXIT_FAILURE);
    }

    //let buffer point the shared mem address as a char pointer
    char* buffer = (char*)shared_mem_address;
    
    //client endless loop
    while(true) {
        char message[MAX_SHM_LEN];
        fgets(message, MAX_SHM_LEN, stdin);

        if(strcmp("\\quit\n", message) == 0) { //quit if user types: \quit
            break;
        }

        //wait until the shared memory is free
        sem_wait(ready_to_write_sem);

            //write the message into the shared memory
            strcpy(buffer, message);

        //signal the server that it can now read the shared memory
        sem_post(ready_to_read_sem);
    }

    //detach shared memory
    shmdt(shared_mem_address);
    buffer = NULL;
    shared_mem_address = NULL;

    //close semaphore
    close_semaphore();
    
    return EXIT_SUCCESS;
}
