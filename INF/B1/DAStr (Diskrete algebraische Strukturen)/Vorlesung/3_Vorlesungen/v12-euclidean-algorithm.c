#include <stdio.h>
#include <stdlib.h>

int ggT(int a, int b) {
	while (b > 0) {
		// a = q * b + r
		int q = a / b;
		int r = a % b;
		printf("%d = %d * %d + %d\n", a, q, b, r);
		a = b;
		b = r;
	}
	return a;
}

int ggTRec(int a, int b) {
	printf("ggTRec(%d,%d) = ", a, b);

	if (b == 0) {
		printf("%d",a);
		return a;

	} else {
		int r = a % b;

		return ggTRec(b, r);
	}
}

int main(void) {
	puts("Euklidischer Algorithmus");
	puts("========================");

	int a = 34675128;
	int b = 1478932;
	printf("ggT(%d,%d) = %d\n\n", a, b, ggT(a, b));

	a = 26236275;
	b = 98705123;
	printf("ggT(%d,%d) = %d\n", a, b, ggT(a, b));

	puts("\nEuklidischer Algorithmus rekursiv");
	puts("=================================");
	// Siehe Vorlesung:
	a = 3500;
	b = 120;
	ggTRec(a, b);

	return EXIT_SUCCESS;
}
