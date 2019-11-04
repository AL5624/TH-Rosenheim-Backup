#include <stdio.h>
#include <stdlib.h>

#define MAX_STEPS 100

int eea(int a, int b) {

	int aa[MAX_STEPS];
	int bb[MAX_STEPS];
	int x[MAX_STEPS];
	int y[MAX_STEPS];

	int i = 0;

	while (b > 0) {
		// a = q * b + r
		aa[i] = a;
		bb[i] = b;
		printf("merke mir aa[%d] = %d, bb[%d] = %d\n", i, aa[i], i, bb[i]);

		int q = a / b;
		int r = a % b;
		printf("%d = %d * %d + %d\n", a, q, b, r);
		a = b;
		b = r;

		i++;
	}

	int ggT = a;

	puts("\nBerechne x,y von unten nach oben, so wie in der Vorlesung:");

	i--;
	x[i] = 0;
	y[i] = 1;
	printf("x[%d] = %d, y[%d] = %d\n", i, x[i], i, y[i]);
	i--;

	for(;i>=0; i--){
		x[i] = y[i+1];
		y[i] = (ggT- aa[i]*x[i])/bb[i];
		printf("x[%d] = %d, y[%d] = %d\n", i, x[i], i, y[i]);
	}

	return ggT;
}

int eea_prettyPrint(int a, int b) {

	int aa[MAX_STEPS];
	int bb[MAX_STEPS];
	int qq[MAX_STEPS];
	int rr[MAX_STEPS];

	int x[MAX_STEPS];
	int y[MAX_STEPS];

	int i = 0;

	while (b > 0) {
		// a = q * b + r
		int q = a / b;
		int r = a % b;

		// Speichere mir a, b, q, r
		aa[i] = a;
		bb[i] = b;
		qq[i] = q;
		rr[i] = r;

		a = b;
		b = r;

		i++;
	}

	int i_max = i;
	int ggT = a;

	// Berechne von unten nach oben die x/y:
	i--;
	x[i] = 0;
	y[i] = 1;
	i--;

	for(;i>=0; i--){
		x[i] = y[i+1];
		y[i] = (ggT- aa[i]*x[i])/bb[i];
	}

	// Ausgabe auf Konsole:
	printf("EEA mit a = %d und b = %d :\n", aa[0], bb[0]);
	puts("     a =      q *      b +      r |      x |      y |    ggT =      a *      x +      b *      y");
	puts("================================================================================================");
	for(i=0; i < i_max; i++) {
		printf("%6d = %6d * %6d + %6d | ", aa[i], qq[i], bb[i], rr[i]);
		printf("%6d | %6d | %6d = %6d * %6d + %6d * %6d\n", x[i], y[i], ggT, aa[i], x[i], bb[i], y[i]);
	}

	return ggT;
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
	puts("Erweiterter Euklidischer Algorithmus");
	puts("====================================");

	int a = 440;
	int b = 198;
	printf("ggT(%d,%d) = %d\n\n", a, b, eea(a, b));

	puts("Pretty Print:");
	puts("=============\n");
	eea_prettyPrint(a, b);

	return EXIT_SUCCESS;
}
