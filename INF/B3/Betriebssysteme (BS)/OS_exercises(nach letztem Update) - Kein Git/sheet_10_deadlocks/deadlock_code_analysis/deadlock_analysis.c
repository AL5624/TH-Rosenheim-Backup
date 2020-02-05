#include <stdio.h>     //printf, perror
#include <stdlib.h>    //EXIT_FAILURE, EXIT_SUCCESS
#include <string.h>    //sprintf
#include <unistd.h>    //open, close, read, write
#include <pthread.h>   //pthread_*
#include <fcntl.h>     //flags: O_CREAT, O_EXCL
#include <semaphore.h> //sem_open, sem_wait, sem_post, sem_close
#include <errno.h>     //errno

#define SEMAPHORE1_NAME "/sem1"          //name of semaphore
#define SEMAPHORE2_NAME "/sem2"          //name of semaphore
sem_t*   semaphore1 = NULL;              //pointer to semaphore
sem_t*   semaphore2 = NULL;              //pointer to semaphore
const int     PERM  = 0600;               //permission to the semaphore (read + write)

void create_semaphore() {
    semaphore1 = sem_open(SEMAPHORE1_NAME, O_CREAT, PERM, 1);
    if(semaphore1 == SEM_FAILED) {
        perror("Error when creating the semaphore ...\n");
        exit(EXIT_FAILURE);
    }
    semaphore2 = sem_open(SEMAPHORE2_NAME, O_CREAT, PERM, 1);
    if(semaphore1 == SEM_FAILED) {
        perror("Error when creating the semaphore ...\n");
        exit(EXIT_FAILURE);
    }
}

void close_semaphore() {
    if(sem_close(semaphore1) == -1) {
        perror("Error can't close semaphore ...\n");
        exit(EXIT_FAILURE);
    }
    if(sem_close(semaphore2) == -1) {
        perror("Error can't close semaphore ...\n");
        exit(EXIT_FAILURE);
    }
}

void delete_semaphore() {
    if(sem_unlink(SEMAPHORE1_NAME) == -1) {
        switch(errno)
        {
        case EACCES:       //fall through
        case ENAMETOOLONG:
            perror("Error can't delete (unlink) semaphore ...\n");
            exit(EXIT_FAILURE);
            break;
        case ENOENT: //semaphore already deleted, no error should be printed!
            break;
        }
    }
    if(sem_unlink(SEMAPHORE2_NAME) == -1) {
        switch(errno)
        {
        case EACCES:       //fall through
        case ENAMETOOLONG:
            perror("Error can't delete (unlink) semaphore ...\n");
            exit(EXIT_FAILURE);
            break;
        case ENOENT: //semaphore already deleted, no error should be printed!
            break;
        }
    }
}

void* worker1() {
    printf("w1 started\n");

    for(int i = 0; i < 5; ++i){
        sem_wait(semaphore2); usleep(1);
        sem_wait(semaphore1);
            printf("w1 in critical area: working...\n");
            sleep(1);
        sem_post(semaphore1);
        sem_post(semaphore2);
    }

    printf("w1 ends\n");
    return NULL;
}

void* worker2() {
    printf("w2 started\n");

    for(int i = 0; i < 5; ++i){
        sem_wait(semaphore1); usleep(1);
        sem_wait(semaphore2);
            printf("w2 in critical area: working...\n");
            sleep(1);
        sem_post(semaphore2);
        sem_post(semaphore1);
    }

    printf("w2 ends\n");
    return NULL;
}

int main(int argc, char** argv){
    //create semaphores
    delete_semaphore();
    create_semaphore();

    //start worker thread
    pthread_t thread_w1;
    pthread_t thread_w2;
    pthread_t thread_w3;

    int thread_create_state = -1;
    thread_create_state = pthread_create(&thread_w1, NULL, &worker1, NULL);
    if(thread_create_state != 0) {
        printf("Failed creating thread\n");
        exit(EXIT_FAILURE);
    }
    thread_create_state = pthread_create(&thread_w2, NULL, &worker2, NULL);
    if(thread_create_state != 0) {
        printf("Failed creating thread\n");
        exit(EXIT_FAILURE);
    }


    //Wait for the termination of all threads
    pthread_join(thread_w1, NULL);
    pthread_join(thread_w2, NULL);

    //close & delete semaphores
    close_semaphore();
    delete_semaphore();
    
    return EXIT_SUCCESS;
}
