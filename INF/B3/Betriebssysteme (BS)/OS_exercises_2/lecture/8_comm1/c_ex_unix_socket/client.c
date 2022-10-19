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
    //create socket for outgoing connection
    int unix_socket = socket(AF_LOCAL, SOCK_STREAM, 0);
    if(unix_socket < 0){
        printf("Error: can't create socket!\n");
        exit(EXIT_FAILURE);
    }

    //connect to unix socket file
    struct sockaddr_un address;
    address.sun_family = AF_LOCAL;
    strcpy(address.sun_path, UNIX_SOCKET_FILE_NAME);

    int connection_result = connect(unix_socket, (struct sockaddr*) &address, sizeof(address));
    if(connection_result != 0){
        printf("Error: can't connect to address\n");
        exit(EXIT_FAILURE);
    }

    //buffer for messages
    char buffer[BUFFER_SIZE];

    //send some data
    sprintf(buffer, "hello from unix socket client");
    ssize_t size = send(unix_socket, buffer, strlen(buffer), 0);
    if(size == -1){
        printf("Error: Send data failed\n");
        exit(EXIT_FAILURE);
    }

    //close socket
    close(unix_socket);

    return EXIT_SUCCESS;
}