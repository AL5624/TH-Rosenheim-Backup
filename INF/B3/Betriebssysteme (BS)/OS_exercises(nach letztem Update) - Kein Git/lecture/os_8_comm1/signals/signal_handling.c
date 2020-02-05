#include <stdio.h>  //printf
#include <stdlib.h> //EXIT_SUCCESS
#include <signal.h> //signal
#include <unistd.h> //sleep

void signal_handler(int signal) {
    printf("No, I don't want to terminate right now!\n");
}

int main(int argc, char** argv) {
    //register the signal handler
    signal(SIGTERM, signal_handler);

    for(long long int i = 0; i < __LONG_LONG_MAX__; ++i) { //do something usefull...
        printf("sleeping!!\n");
        sleep(5);
    }

    printf("%s exits main() now!\n", argv[0]);
    return EXIT_SUCCESS;
}
