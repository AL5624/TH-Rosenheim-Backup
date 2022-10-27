#include <stdio.h>     //printf, perror
#include <stdlib.h>    //EXIT_FAILURE, EXIT_SUCCESS
#include <stdbool.h>   //bool
#include <string.h>    //strcmp
#include <sys/msg.h>   //msgget, msgrcv, msgsnd, msgctl

key_t MESSAGE_QUEUE_KEY   = 0x4242; //key of the message queue
#define MAX_MESSAGE_LEN     (1024)  //max length of messages

//structure for messages
//TODO: define message struct


int main() {
    //open the message queue
    int message_queue_id = msgget(MESSAGE_QUEUE_KEY, IPC_PRIVATE);
    if(message_queue_id < 0) {
        printf("Error: can't open message queue!\n");
        exit(EXIT_FAILURE);
    }

    //client endless loop
    while(true) {
        //fetch user input from console (stdin)
        char buffer[MAX_MESSAGE_LEN];
        fgets(buffer, MAX_MESSAGE_LEN-1, stdin);

        if(strcmp("\\quit\n", buffer) == 0) { //quit if user types: \quit
            break;
        }

        //TODO: prepare message

        //TODO: send message to message queue
        
    }

    return EXIT_SUCCESS;
}
