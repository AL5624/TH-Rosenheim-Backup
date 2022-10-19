#include <stdio.h>     //printf, perror
#include <stdlib.h>    //EXIT_FAILURE, EXIT_SUCCESS
#include <stdbool.h>   //bool
#include <sys/msg.h>   //msgget, msgrcv, msgsnd, msgctl

#include "message_queue_def.h"

int main() {
    //open the existing message queue
    int message_queue_id = msgget(MESSAGE_QUEUE_KEY, IPC_PRIVATE);
    if(message_queue_id < 0) {
        printf("Error at opening the message queue!\n");
        exit(EXIT_FAILURE);
    }

    //prepare some message prios
    int priorities[] = {LOW, LOW, LOW, NORMAL, NORMAL, NORMAL, HIGH, HIGH, HIGH};
    int num_priorities = sizeof(priorities)/sizeof(int);

    for(int i = 0; i < num_priorities; ++i) {
        //prepare message
        message_t message;
        message.priority = priorities[i];
        sprintf(message.message, "message with prio %ld", message.priority);

        //send message to message queue 
        int size = msgsnd(message_queue_id, &message, MAX_MESSAGE_LEN-1, 0);
        if(size < 0){
            printf("Error at writing message to message queue!\n");
            exit(EXIT_FAILURE);
        }
    }

    return EXIT_SUCCESS;
}
