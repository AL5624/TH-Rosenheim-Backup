#include <stdio.h>
#include <math.h>

int main(void) {
	int n = 72;

	printf("PFZ von %d = ", n);

	while(n != 1) {
		// 1. Suche kleinster Teiler von n UND prüfe ob n prim
		int isPrime = 1;
		int d;
		for(d = 2; d <= sqrt(n); d++) {
			if(n % d == 0) {
				printf("%d ", d); // habe kleinsten Teiler
				isPrime = 0; // d teilt n: n ist nicht prim

				break; // verlasse for Schleife
			}
		}
		if (isPrime) {
			// kein d <= sqrt(n) hat n geteilt: d = n ist prim
			d = n;
			printf("%d ", d); // Ausgabe des letzten Primfaktors n
		}

		// 2. Iteriere mit:
		n=n/d;
	}

	return 0;
}
