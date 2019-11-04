#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef unsigned long long ull;

ull euclidean_prime(int n) {
	if(n == 1) {
		return 2;
	} else {
		// Produkt aller vorhergehenden Euklid-Primzahlen:
		ull product = 1;
		for (int i = 1; i <= n-1; ++i) {
			product *= euclidean_prime(i);
		}

		// kleinster Teiler von product + 1:
		ull euclidNumber = product + 1;
		for (ull d = 2; d <= sqrt(euclidNumber); d++) {
			if (euclidNumber % d == 0) {
				//printf("kleinster Teiler von %llu ist %llu\n", euclidNumber, d);
				return d;
			}
		}
		return euclidNumber;
	}
}

int main(void) {
	puts("Euklidische Primzahlen");
	puts("======================");

	for (int n = 1; n < 11; ++n) {
		printf("%d. Euklidische Primzahl: %llu\n", n, euclidean_prime(n));
	}

	return EXIT_SUCCESS;
}
