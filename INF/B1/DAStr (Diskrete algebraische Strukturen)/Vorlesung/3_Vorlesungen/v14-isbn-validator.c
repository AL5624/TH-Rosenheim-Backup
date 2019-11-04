#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool isIsbnValid(char isbn[10]) {

	int sum = 0;
	int i;
	for (i = 1; i <= 9; ++i) {
		int z = isbn[i-1] - '0'; // konvertiere char in int z.B. '0'=0x30 -> 0
		sum += i * z;
	}

	int sumMod11 = sum % 11;
	char pruefZiffer = isbn[9] - '0'; // konvertiere char in int z.B. '0'=0x30 -> 0

	// Sonderbehandlung fuer die Pruefziffer 'X'

	if (sumMod11 == pruefZiffer || (pruefZiffer == 'X' && sumMod11 == 10)) {
		printf("ISBN ist gültig :-)");
		return true;
	} else {
		printf("ISBN ist NICHT gültig :-(");
		return false;
	}
}

int main(void) {
	setvbuf(stdout, NULL, _IONBF, 0); // nur fuer Eclipse
	setvbuf(stderr, NULL, _IONBF, 0); // nur fuer Eclipse

	puts("ISBN");
	puts("====");

	char isbn[10]; // z.B. {'3','4','4','2','4','7','8','9','6','X'}; oder "344247896X"

	printf("Bitte ISBN eingeben: ");
	scanf("%s", isbn);

	printf("\n\nIs valid? %d", isIsbnValid(isbn));

	return EXIT_SUCCESS;
}
