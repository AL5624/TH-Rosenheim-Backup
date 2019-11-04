/*******************************************************************
// Programm    : Primzahlen                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 13.11.2012                                           
// Aufgabe     : Primzahlberechnung nach Sieb des Eratosthenes
// Änderungen  : 13.11.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <stdlib.h>   /* Standard-Bibliothek, z.B. für system       */
#include "funktionen.h"


int main () 
{
	strich(50,'-');
	printf("Sieb des Eratosthenes\n");
	strich(50,'-');

	int Zahlenfeld[OG + 1];
	sieb(Zahlenfeld, OG);
	for (int i = 0; i < OG; i++) {
		if (Zahlenfeld[i] == NICHT_PRIM) {
			continue;
		}
		printf(" %5d,", i);
	};






	printf("\nServus! \n");
	system("pause");
	return 0;
}