#include <stdio.h>
#include <math.h>
#include <time.h>

// sieves all primes to SIEVE_BOUND-1
#define  SIEVE_BOUND 1000

void printPrimes(char numbers[SIEVE_BOUND], char consoleOrFile);

int main(void) {
	puts("Sieve of Eratosthenes");
	puts("=====================");

	time_t start = time(&start); // measure time

	// Use a char-Array: if n is a prime then set numbers[n] to 1, otherwise 0
	char numbers[SIEVE_BOUND];

	numbers[0] = 0; // 0 is not prime
	numbers[1] = 0; // 1 is not prime

	// Initialize with 1s, since every number n is a prime candidate
	for (int n = 2; n < SIEVE_BOUND; ++n) numbers[n] = 1;

	// Cancel every multiple of a given number n
	for (int n = 2; n <= sqrt(SIEVE_BOUND); n++) // Optimization: sieve only to the square root of SIEVE_BOUND
	{
		if(numbers[n] == 1) // Optimization: cancel multiples only if n is a prime
		{
			// Cancel multiples i of n. Optimization: start at i=n*n, since q*n with q<n has been already cancelled before!
			for (int i = n*n; i < SIEVE_BOUND; i = i + n)
			{
				printf("cancel i=%d (as a multiple of n=%d)\n", i, n);
				numbers[i] = 0; // cancel i
			}
		}
	}
	// DONE!
	time_t stop = time(&stop);
	char consoleOrFile = 'c';
	printf("\nSieved the primes <= %d (took %ld seconds). Write to File ('f') or Console ('c'): '%c' ...\n\n", SIEVE_BOUND-1, stop-start, consoleOrFile);
	printPrimes(numbers, consoleOrFile); // prints it to file with parameter 'f', else to console

	return 0;
}


void printPrimes(char numbers[SIEVE_BOUND], char consoleOrFile) {
	FILE* f;
	if(consoleOrFile == 'f') {
		f = fopen("primes.txt", "w"); // print to file
		if (f == NULL) {
			printf("Error opening file!\n");
			return;
		}
	} else {
		f = stdout; // print to console
	}

	int count = 0;
	for (int n = 0; n < SIEVE_BOUND; n++) {
		if (numbers[n] == 1) {
			fprintf(f, "%6d, ", n);
			count++;
			if (count % 10 == 0) fprintf(f, "\n"); // Every 10 break the line
		}
	}
	fclose(f);
}
