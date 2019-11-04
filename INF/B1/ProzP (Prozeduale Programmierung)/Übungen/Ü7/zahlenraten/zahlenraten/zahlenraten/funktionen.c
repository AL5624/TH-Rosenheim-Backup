/*******************************************************************
// Programm    : Zahlenraten / Funktionen
// Verfasser   : Schmidt
// Datum       : Urprogramm: 7.11.2012
// Aufgabe     : Benutzer muss Zahl zwischen 1 und 100 erraten
// Änderungen  : 7.11.2012
*******************************************************************/
#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include "funktionen.h"

/* globale Konstanten für boolesche Ausdrücke */
const int FALSE = 0;
const int TRUE = 1;


void strich(int n, char c) // gibt n mal das Zeichen c aus
{
	int i;
	for (i = 1; i <= n; i++)
		printf("%c", c);
	printf("\n");
}

void zahlenraten(int MaxZahl)
{
	int a;
	char antwort;
	do
	{
		int Zufallszahl;
		
		Zufallszahl = rand() % MaxZahl;
		printf("%d\n", Zufallszahl);

		rate(Zufallszahl);

		printf("Nochmal spielen? (j/n)\n");
		scanf(" %c", &antwort);

		if (antwort == 'J' || antwort == 'j') { a = 1; }
		else { a = 0; };

	} while (a);

}

void rate(int compZahl)
{
	int SpielerZahl;

	printf("Ich habe mir eine Zahl zwischen 1 und 100 ausgedacht. Welche ist es?\n");
	scanf("%d", &SpielerZahl);
	while (SpielerZahl < compZahl || SpielerZahl > compZahl)
	{
		if (SpielerZahl < compZahl) printf("Die Zahl ist zu klein!\n");
		if (SpielerZahl > compZahl) printf("Die Zahl ist zu gross!\n");
		scanf("%d", &SpielerZahl);
	}

	if (SpielerZahl == compZahl) printf("Richtig!\n");

}
