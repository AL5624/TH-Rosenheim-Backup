// ConsoleApplication5.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"

int ggt(void) {
	int a, b;  
	int durchlaeufe = 0;
	
	//scanf("%d", &a);
	//scanf("%d", &b);
	a = 2 * 3 * 5  * 7 * 11 *13*17;
	b = 2;
	
	while (a != b)
	{
		if (a > b)        
			a = a-b;
		else
			b = b-a;
		durchlaeufe++;
	} 
	
	printf("ggT = %d\n", a);

	return 0;
}

void forfunktion() {
	int i, j, k;
	int summe;


	// Berechne summe = 0+1+...+15
	for (i = 0, summe = 0; i <= 15; i++) {
		summe = summe + i;
	}

	//Berechne fakultät 5
	int fakultaet = 1;
	for (i = 1; i < 6; i++) {
		fakultaet = fakultaet * i;
	}

	//Berechne Fibonacci von 10, Variante 1
	int fibn, fibnm1, fibnm2;
	int n;

	fibnm2 = fibnm1 = fibn = 1;

	for (n = 2; n < 11; n++) {
		fibnm2 = fibnm1;
		fibnm1 = fibn;
		fibn = fibnm1 + fibnm2;
	}

	// Zahlen von 1 bis 10, nicht durch 3 teilbar
	for (i = 1; i <= 10; i++) {
		if (i % 3 == 0)
			continue;
		printf("%d\n", i);
	}
	for (i = 1; i <= 10; i++) {
		if (i % 3 != 0)
			printf("%d\n", i);
	}
}

int main()
{
	int i = 1000;

	//while (i--);

	/*do {
		i--;
	} while (i > 0);
	*/
	//ggt();

	forfunktion();

    return 0;
}

