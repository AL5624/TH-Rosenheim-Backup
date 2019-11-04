/*******************************************************************
// Programm    : Primzahlen                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 13.11.2012                                           
// Aufgabe     : Primzahlberechnung nach Sieb des Eratosthenes
// Änderungen  : 13.11.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <math.h>
#include "funktionen.h"

void sieb(int obergrenze, int zahlen[])
{
	int i,j;
	int SQOG = (int)(sqrt((float)(obergrenze)));

	// markiere alles als prim
	for(i = 0; i < obergrenze; i++)
		zahlen[i] = PRIM;
	
	for(i = 2; i <= SQOG; i++)
		if(zahlen[i] == PRIM)
			for(j = 2; j*i < obergrenze; j++) 
				zahlen[j*i] = NICHT_PRIM;  // nicht prim
}

void ausgabe(int obergrenze, int zahlen[])
{
	int i;
	printf("Primzahlen zwischen 1 und %d:\n", obergrenze - 1);
	for(i = 2; i < obergrenze; i++)
		if(zahlen[i] == PRIM)
			printf("%d, ", i);
}

void strich (int n, char c) // gibt n mal das Zeichen c aus
{
	int i;
	for (i = 1; i <= n; i++)
		printf("%c", c);
	printf("\n");
}

