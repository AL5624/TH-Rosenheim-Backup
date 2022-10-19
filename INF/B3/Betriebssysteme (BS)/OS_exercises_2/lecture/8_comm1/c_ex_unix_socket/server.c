#include <stdio.h>      //printf
#include <stdlib.h>     //EXIT_SUCCESS, EXIT_FAILURE
#include <string.h>     //strcmp
#include <stdbool.h>    //true, false
#include <sys/socket.h> //socket, bind, listen, accept, recv, send
#include <sys/un.h>     //struct sockaddr_un
#include <unistd.h>     //unlink, close

const int   BUFFER_SIZE           = 1024;
const char* UNIX_SOCKET_FILE_NAME = "server.socket";

int main(void)
{
    //remove socket file: just to make sure it doesn't exist
    unlink(UNIX_SOCKET_FILE_NAME);

    //create socket for incoming connections
    int unix_socket = socket(AF_LOCAL, SOCK_STREAM, 0);
    if(unix_socket < 0){
        printf("Error: can't create socket!\n");
        exit(EXIT_FAILURE);
    }

    //bind socket to unix socket file
    struct sockaddr_un address;
    address.sun_family = AF_LOCAL;
    strcpy(address.sun_path, UNIX_SOCKET_FILE_NAME);

    int bind_result = bind(unix_socket, (struct sockaddr*) &address, sizeof(address));
    if(bind_result != 0) {
        printf("Error: can't bind socket\n");
        exit(EXIT_FAILURE);
    }

    //create a queue for connections
    const int MAX_PENDING_CONNECTIONS = 5;
    int listen_result = listen(unix_socket, MAX_PENDING_CONNECTIONS);
    if(listen_result != 0) {
        printf("Error: can't create listen queue\n");
        exit(EXIT_FAILURE);
    }

    //server endless loop
    while(true) {
        //wait for new connections
        socklen_t addrlen = sizeof(struct sockaddr_un);
        int client_socket = accept(unix_socket, (struct sockaddr*) &address, &addrlen);
        
        if(client_socket >= 0) {
            char buffer[BUFFER_SIZE];
            ssize_t size = 0; //received data size in bytes

            do {
                //receive data
                size = recv(client_socket, buffer, BUFFER_SIZE-1, 0);
                if(size > 0){
                    buffer[size] = '\0';
                    printf("Message received: %s\n", buffer);
                }
            } while(size > 0);

            //close client socket
            close(client_socket);
        }
    }

    //close socket for incoming connections
    close(unix_socket);

    //remove socket file: just to make sure it doesn't exist
    unlink(UNIX_SOCKET_FILE_NAME);

    return EXIT_SUCCESS;
}