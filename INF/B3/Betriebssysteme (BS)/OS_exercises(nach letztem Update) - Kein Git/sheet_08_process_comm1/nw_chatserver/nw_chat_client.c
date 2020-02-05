#include <stdio.h>      //printf
#include <stdlib.h>     //EXIT_SUCCESS, EXIT_FAILURE
#include <string.h>     //strcmp
#include <stdbool.h>    //true, false
#include <sys/socket.h> //socket, bind, listen, accept, recv, send
#include <netinet/in.h> //struct sockaddr_in
#include <unistd.h>     //close
#include <arpa/inet.h>  //inet_aton
#include <pthread.h>    //pthread_*
/*
 * nw_chat_client.c
 * The client for a simple chat server
 */

const int MAX_MESSAGE_LEN = 1024;  //Max length of messages
const int PORT            = 15000; //Network port

int network_socket = -1;

//this function receives all incoming messages, it should run inside a second thread
void* receiver_thread() {
    //endless loop to receive messages from the server
    while(true) {
        char received_message[MAX_MESSAGE_LEN];
        ssize_t size = -1;
        //TODO: receive messages from the server
        
        if(size <= 0) {
            break; //no data received or connection closed
        } else {
            //the message has to be properly 0-terminated
            received_message[size] = '\0';
            printf("Received: %s", received_message);
        }
    }
    return NULL;
}

int main(int argc, char** argv) {
    //check if a parameter for the IP address exists
    char* server_ip = NULL;
    if(argc < 2) {
        printf("Usage: %s <serveraddress>\n", *argv);
        exit(EXIT_FAILURE);
    } else {
        server_ip = argv[1];
    }

    //TODO: Create the socket


    //TODO: Set the IP address and the port of the server (The server listens on port 15000)
    //Hint:
    // - use "inet_aton(...)" to convert internet host address to binary form
    // - use "htons(...)" convert values between host and network byte order
    struct sockaddr_in address;

    //TODO: Connect to server


    //start the thread to receive messages from the server
    pthread_t thread_id = -1;
    //TODO: start the thread that receives messages: Hint: the receiver_thread contains the code for the threads.

    //send input from stdin as a message
    char message[MAX_MESSAGE_LEN];
    while(true) {
        //fetch user input from console (stdin)
        fgets(message, MAX_MESSAGE_LEN, stdin);
        
        if(strcmp(message, "\\quit\n") == 0) {
            //close the network socket:
            // - similar to close(network_socket)
            // - but the recv() in the receiver_thread exits with: size == 0
            shutdown(network_socket, SHUT_RDWR);
            break;
        }

        //TODO: send message to the server
        
    }

    //wait until the receive thread exits
    pthread_join(thread_id, NULL);

    //TODO: close socket


    return EXIT_SUCCESS;
}
