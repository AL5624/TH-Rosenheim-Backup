#ifndef MESSAGE_QUEUE_DEF_H
#define MESSAGE_QUEUE_DEF_H

#include <sys/ipc.h>

key_t MESSAGE_QUEUE_KEY   = 0x4242; //key of the message queue
#define MAX_MESSAGE_LEN     (1024)  //max length of messages

//priority for messages
typedef enum {
    HIGH   = 1
   ,NORMAL = 2
   ,LOW    = 3
} priority_t;

//structure for messages
typedef struct {
    long priority;
    char message[MAX_MESSAGE_LEN];
} message_t;

#endif