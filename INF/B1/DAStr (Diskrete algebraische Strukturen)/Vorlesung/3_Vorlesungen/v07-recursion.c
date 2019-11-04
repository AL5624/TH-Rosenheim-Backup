#include <stdio.h>
#include <stdlib.h>

int fibonacci(int n) {
	if (n == 1 || n == 2) {
		// fibonacci(1)=1, fibonacci(2)=1
		return 1;
	} else if (n > 2) {
		return fibonacci(n - 1) + fibonacci(n - 2);
	} else {
		// Keine negativen Zahlen als Parameter zulässig - gebe Fehlercode -1 zurück:
		return -1;
	}
}

int fac(int n) {
	if (n == 0) {
		// fac(0) = 1
		return 1;
	} else {
		// fac(n)=n*fac(n-1)
		return n * fac(n - 1);
	}
	// ODER kurz:
	//return n== 0 ? 1 : n*fac(n-1);
}

int hanoi(int n) {
	if (n == 0) {
		return 0;
	} else {
		return 2 * hanoi(n - 1) + 1;
	}
}

int main(void) {
	for (int n = 0; n < 11; ++n) {
		printf("n=%d, fac(n)=%d\n", n, fac(n));
	}
	puts("=====================");

	for (int n = 1; n < 11; ++n) {
		printf("n=%d, fib(n)=%d\n", n, fibonacci(n));
	}
	puts("=====================");

	for (int n = 0; n < 11; ++n) {
		printf("n=%d, T(n)=%d\n", n, hanoi(n));
	}

	return EXIT_SUCCESS;
}
