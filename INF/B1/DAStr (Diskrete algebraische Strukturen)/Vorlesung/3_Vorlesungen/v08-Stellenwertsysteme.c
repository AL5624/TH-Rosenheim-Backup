#include <stdio.h>
#include <stdlib.h>

int main(void) {

	// Mittels Debugger im Memory ansehen:

	int a = 86; // Anzeige in Byte-Reihenfolge: Little Endian
	int* pointerAufA = &a; // Adresse im Memory der Variable a

	int b = 0b1010110; // Binaer

	char c = ' '; // Space 0x20

	return EXIT_SUCCESS;
}
