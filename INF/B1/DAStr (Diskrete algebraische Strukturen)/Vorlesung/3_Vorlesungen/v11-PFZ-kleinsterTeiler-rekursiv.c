#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

unsigned long long kleinsterTeiler(unsigned long long n){
	for (unsigned long long d = 2; d <= n; ++d) {
		// Falls d teilt n, habe ich den kleinsten Teiler:
		if(n%d==0){
			return d;
		}
	}
	return -1;
}

void pfz(unsigned long long n){
	// 1. Bestimme kleinster Teiler (dieser ist mein erster Primfaktor):
	unsigned long long d = kleinsterTeiler(n);

	printf("%d", d); // Ausgabe

	// Nur falls d!=n ist mach ich weiter:
	if(d<n){
		printf(" * "); // Ausgabe
		// 2. Mache dasselbe für n/d:
		pfz(n/d);
	}
}

int main(void) {
	puts("PFZ");
	puts("===");

	unsigned long long n = 2003213256;

	printf("%llu = ", n);
	pfz(n);
	printf("\n");

	printf("%llu = ", ULLONG_MAX);
	pfz(ULLONG_MAX);

	return EXIT_SUCCESS;
}
