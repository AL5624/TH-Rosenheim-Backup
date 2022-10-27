#include <stdio.h>      //printf
#include <stdlib.h>     //EXIT_SUCCESS, EXIT_FAILURE
#include <string.h>     //strcmp
#include <stdbool.h>    //true, false
#include <sys/socket.h> //socket, bind, listen, accept, recv, send
#include <netinet/in.h> //struct sockaddr_in
#include <unistd.h>     //close
#include <arpa/inet.h>  //inet_aton

const int BUFFER_SIZE = 1024;
const int PORT        = 50000;

int main(void)
{
    //create socket for outgoing connection
    int network_socket = socket(AF_INET, SOCK_STREAM, 0);
    if(network_socket < 0){
        printf("Error: can't create socket!\n");
        exit(EXIT_FAILURE);
    }

    //connect to address
    struct sockaddr_in address;
    address.sin_family      = AF_INET;
    inet_aton("127.0.0.1", &address.sin_addr); //convert internet host address to binary form
    address.sin_port        = htons(PORT); //convert values between host and network byte order 

    int connection_result = connect(network_socket, (struct sockaddr*) &address, (sizeof address));
    if(connection_result != 0){
        printf("Error: can't connect to address\n");
        exit(EXIT_FAILURE);
    }

    //buffer for messages
    char buffer[BUFFER_SIZE];

    //send hello world
    sprintf(buffer, "hello from network socket client");
    ssize_t size = send(network_socket, buffer, strlen(buffer), 0);
    if(size == -1){
        printf("Error: Send data failed\n");
        exit(EXIT_FAILURE);
    }

    //close socket
    close(network_socket);

    return EXIT_SUCCESS;
}