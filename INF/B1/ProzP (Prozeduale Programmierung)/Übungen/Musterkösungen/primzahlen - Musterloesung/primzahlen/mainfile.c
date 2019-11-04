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

/* Obergrenze für Primzahlberechnung */
#define OG 1000

int main () 
{
	int zahlen[OG+1];

	strich(50,'-');
	printf("Sieb des Eratosthenes\n");
	strich(50,'-');

	sieb(OG+1, zahlen);
	ausgabe(OG+1, zahlen);

	printf("\nServus! \n");
	system("pause");
	return 0;
}