#include <stdio.h>      //printf
#include <stdlib.h>     //EXIT_SUCCESS, EXIT_FAILURE
#include <string.h>     //strcmp
#include <stdbool.h>    //true, false
#include <sys/socket.h> //socket, bind, listen, accept, recv, send
#include <netinet/in.h> //struct sockaddr_in
#include <unistd.h>     //close

const int BUFFER_SIZE = 1024;
const int PORT        = 50000;

int main(void)
{
    //create socket for incoming connections
    int network_socket = socket(AF_INET, SOCK_STREAM, 0);
    if(network_socket < 0){
        printf("Error: can't create socket!\n");
        exit(EXIT_FAILURE);
    }

    //bind socket to port
    struct sockaddr_in address;
    address.sin_family      = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port        = htons(PORT); //convert values between host and network byte order 

    int bind_result = bind(network_socket, (struct sockaddr*) &address, sizeof(address));
    if(bind_result != 0) {
        printf("Error: can't bind socket\n");
        exit(EXIT_FAILURE);
    }

    //create a queue for connections
    const int MAX_PENDING_CONNECTIONS = 5;
    int listen_result = listen(network_socket, MAX_PENDING_CONNECTIONS);
    if(listen_result != 0) {
        printf("Error: can't create listen queue\n");
        exit(EXIT_FAILURE);
    }

    //server endless loop
    while(1) {
        //wait for new connections
        socklen_t addrlen = sizeof(struct sockaddr_in);
        int client_socket = accept(network_socket, (struct sockaddr*) &address, &addrlen);
        
        if (client_socket >= 0) {
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
    close(network_socket);

    return EXIT_SUCCESS;
}