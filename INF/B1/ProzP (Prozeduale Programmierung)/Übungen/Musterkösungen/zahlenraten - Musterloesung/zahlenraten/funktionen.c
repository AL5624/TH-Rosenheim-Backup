/*******************************************************************
// Programm    : Zahlenraten / Funktionen                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 7.11.2012                                           
// Aufgabe     : Benutzer muss Zahl zwischen 1 und 100 erraten
// Änderungen  : 7.11.2012
*******************************************************************/
#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <stdlib.h>   /* Standard-Bibliothek, z.B. für system       */
#include "funktionen.h"

/* globale Konstanten für boolesche Ausdrücke */
const int FALSE = 0;
const int TRUE = 1;

static void rate(int compZahl)
{
	int erraten = FALSE;
	int eingZahl;

	do
	{
		scanf("%d",&eingZahl);
		if(eingZahl > compZahl)
			printf("Die ist zu gross!\n");
		else if(eingZahl < compZahl)
			printf("Die ist zu klein!\n");
		else
		{
			printf("Richtig!\n");
			erraten = TRUE;
		}
	}while(!erraten);
}

void zahlenraten(int MAXZAHL)
{
	int nochmal;
	int compZahl;
	char c;

	do
	{
		nochmal = FALSE;
		compZahl = rand() % MAXZAHL + 1;
		printf("Ich habe mir eine Zahl zwischen 1 und %d ausgedacht. Welche ist es?\n", MAXZAHL);
		rate(compZahl);

		printf("Nochmal spielen? (j/n)\n");
		getchar();  /* newline entfernen */
		c = (char)getchar();
		if( c == 'j'|| c == 'J')
			nochmal = TRUE;
		else
			nochmal = FALSE;

	}while(nochmal);
}


void strich (int n, char c) // gibt n mal das Zeichen c aus
{
	int i;
	for (i=1; i<=n; i++)
		printf("%c", c);
	printf("\n");
}

