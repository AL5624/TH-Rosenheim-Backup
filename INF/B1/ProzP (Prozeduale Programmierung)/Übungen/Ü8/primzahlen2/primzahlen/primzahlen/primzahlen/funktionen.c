/*******************************************************************
// Programm    : Primzahlen                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 13.11.2012                                           
// Aufgabe     : Primzahlberechnung nach Sieb des Eratosthenes
// Änderungen  : 13.11.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include "funktionen.h"
#include <math.h>


void strich (int n, char c) // gibt n mal das Zeichen c aus
{
	int i;
	for (i = 1; i <= n; i++)
		printf("%c", c);
	printf("\n");
}

int sieb(int *zahlenfeld, int obergrenze)
{
	int i;
	for (i = 2; i < obergrenze; i++)
	{
		zahlenfeld[i] = PRIM;
	}

	zahlenfeld[0] = NICHT_PRIM;
	zahlenfeld[1] = NICHT_PRIM;

	for (i = 2; i <= sqrt(obergrenze); i++)
	{
		if (zahlenfeld[i] == PRIM)
		{
			for (int j = 2; (i * j) < obergrenze; j++)
			{
				int g;
				g = i * j;
				zahlenfeld[g] = NICHT_PRIM;
			}
		}
	}
}

void ausgabe(int *zahlenfeld2, int obergrenze2)
{
	int z = 1;
	for (int i = 2; i < obergrenze2; i++)
	{
		if (zahlenfeld2[i] == PRIM)
		{
			if (i == 997)
			{
				printf(" %d\n", i);
			}
			else
			{	
				if (z == ZEILENUMBRUCH)
				{
					printf(" %d,\n", i);
					z = 1;
				}
				else
				{
					z++;
					printf(" %d,", i);
				}
			}
		}
	}
}
