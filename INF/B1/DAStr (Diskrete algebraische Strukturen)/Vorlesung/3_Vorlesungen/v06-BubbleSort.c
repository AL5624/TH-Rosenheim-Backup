#include <stdio.h>
#include <stdlib.h>

int main(void) {
	puts("Bubble Sort");

	int zahlen[] = { 3, 6, 1, 9, 2, 0, 4, 5 };
	int size = sizeof(zahlen) / sizeof(int);

	for (int i = 0; i < size; ++i) {
		printf("%d, ", zahlen[i]);
	}
	puts("");

	for (int count = 1; count <= size - 1; ++count) {
		for (int i = 0; i < size - 1; ++i) {
			if (zahlen[i] > zahlen[i + 1]) {
				int temp = zahlen[i];
				zahlen[i] = zahlen[i + 1];
				zahlen[i + 1] = temp;
			}
		}
	}

	for (int i = 0; i < size; ++i) {
		printf("%d, ", zahlen[i]);
	}

	return EXIT_SUCCESS;
}
