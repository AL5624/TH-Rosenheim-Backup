#include <stdio.h>     //printf, perror
#include <stdlib.h>    //EXIT_FAILURE, EXIT_SUCCESS
#include <stdbool.h>   //bool
#include <sys/msg.h>   //msgget, msgrcv, msgsnd, msgctl

#include "message_queue_def.h"

const int PERM      = 0666;   //give the created message queue all rights


int main() {
    //create the message queue
    int message_queue_id = msgget(MESSAGE_QUEUE_KEY, PERM | IPC_CREAT);
    if(message_queue_id < 0) {
        printf("Error: can't create message queue!\n");
        exit(EXIT_FAILURE);
    }

    //server endless loop
    while(true) {
        //receive message
        message_t message;
        const long PRIO_FETCH_FLAG = 0;
        ssize_t size = msgrcv(message_queue_id, &message, MAX_MESSAGE_LEN-1, PRIO_FETCH_FLAG, 0);
        if(size < 0) {
            printf("Error: can't receive message;");
            exit(EXIT_FAILURE);
        }

        //print received message on console
        message.message[size] = '\0';
        printf("message (prio: %ld): %s\n", message.priority, message.message);
    } //should never be left

    //remove message queue
    msgctl(message_queue_id, IPC_RMID, NULL);

    return EXIT_SUCCESS; //should never be reached
}
