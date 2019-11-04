#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void primTest(int p[], int n) {
	for (int i = 0; p[i] <= sqrt(n); ++i) {
		if (n % p[i] == 0) {
			printf("d=%d | n=%d, keine Primzahl :'-(", p[i], n);
			return;
		} else {
			printf("!(d=%d | n=%d), koennte bisher Primzahl sein :-o\n", p[i],
					n);
		}
	}
	printf("n=%d ist eine Primzahl! :-)", n);
}

int main(void) {
	puts("Primzahltest");

	int p[] = {2,     3,     5,     7,    11,    13,    17,    19,    23,    29,    31,    37,    41,    43,
	   47,    53,    59,    61,    67,    71,    73,    79,    83,    89,    97,   101,   103,   107,
	  109,   113,   127,   131,   137,   139,   149,   151,   157,   163,   167,   173,   179,   181,
	  191,   193,   197,   199,   211,   223,   227,   229,   233,   239,   241,   251,   257,   263,
	  269,   271,   277,   281,   283,   293,   307,   311,   313,   317,   331,   337,   347,   349,
	  353,   359,   367,   373,   379,   383,   389,   397,   401,   409,   419,   421,   431,   433};

	int n = 76457;
	primTest(p, n);

	n = 2345387;
	primTest(p, n);

	primTest(p, 23462347);


	return EXIT_SUCCESS;
}
