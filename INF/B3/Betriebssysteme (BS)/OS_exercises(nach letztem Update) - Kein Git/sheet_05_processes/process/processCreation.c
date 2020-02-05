#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int counter = 0;

void work() {
    sleep(2); //simulates the "heavy" work!!

    //TODO: Add code for created processes here
	++counter;
}

int main(int argc, char** argv){

    int N = 0;

    //Get the number of processes which should be created
    if(argc == 2){
        N = atoi(argv[1]);
    } else {
        printf("Usage: %s N\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    //TODO: Write your code here
    pid_t pid[N];

	for (int i = 0; i < N; i++)
	{
		pid[i] = fork();

		switch (pid[i])
		{
		case -1: exit(EXIT_FAILURE);
		case 0: 
			work();
			exit(EXIT_SUCCESS);
		}
	}

	for (int i = 0; i < N; i++)
	{
		waitpid(pid[i], NULL, 0);
	}

	printf("%d\n", counter);

    return EXIT_SUCCESS;
}
