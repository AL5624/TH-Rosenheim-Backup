#include <stdio.h>
#include <math.h>

int main(void) {
	int n = 72;

	printf("PFZ von %d = ", n);

	while(n != 1) {

		int d;
		// 1. Suche kleinster Teiler von n
		for(d = 2; d <= n; d++) {

			if(n%d == 0) {
				printf("%d ", d);
				break;
			}
		}

		// 2. Iteriere mit:
		n=n/d;
	}

	return 0;
}
