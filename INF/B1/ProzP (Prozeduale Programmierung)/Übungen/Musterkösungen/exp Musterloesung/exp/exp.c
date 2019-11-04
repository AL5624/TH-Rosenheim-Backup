/*******************************************************************
// Programm    : exp1                                                
// Verfasser   : Feindor/Schmidt                                             
// Datum       : Urprogramm: 1.10.1996                                           
// Eingabe     : obere und untere Grenzen                            
// Verarbeitung: Berechnung von Funktionen, Zählen, Summieren        
// Ausgabe     : Zahlentabellen                                      
// Änderungen  : 30.10.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <math.h>   /* Standard-Bibliothek für math. Funktionen  z.B. sqrt */
#include <stdlib.h>   /* Standard-Bibliothek, z.B. für system       */
#include "exp.h"


int main () 
{
	const int cMax = 20;
	int n;

	//************************ Anweisungsteil  ****************************
	// Dialogeröffnung 
	strich(50,'-');
	printf ("Exp: Berechnung von einfachen Funktionen\n");
	strich(50,'-');
	// Anweisungen    
	n = grenzeEinlesen(cMax);
	tabelleAusgeben(n);

	printf("Servus! \n");
	system("pause");
	return 0;
}


void strich (int n, char c) // gibt n mal das Zeichen c aus
{
	int i;
	for (i=1; i<=n; i++)
		printf("%c", c);
	printf("\n");
}

int grenzeEinlesen(int cMax)
{
	int n;

	do  // Einlesen Obergrenze mit Fehlerprüfung
	{
		printf ("Bitte positive obere Grenze eingeben (ganzzahlig <= %i):  ", cMax);
		scanf  ("%i", &n);
		if ((n <= 0) || (cMax < n)) printf ("Fehler! Bitte korrekt eingeben !!\n");
	}
	while ((n <= 0) || (cMax < n));

	return n;
}

void tabelleAusgeben(int grenze)
{
	int i;
	double kehr_i, sum_1i =0, fak=1, fak1, e=1;

	// Ausgeben Tabelle
	strich(75, '-');
	printf ("%4s %6s %10s %20s %15s %12s   \n",
		"i","1/i","Summe(1/i)","i!","1/i!","Naeherung e");
	strich(75, '-');

	for (i = 1; i <= grenze; i++)
	{
		kehr_i= 1.0/i;             // Kehrwert 1/i
		sum_1i=sum_1i + kehr_i;    // Summe der Kehrwerte 1/i
		fak = fak * i;             // Fakultät i!
		fak1 = 1.0/ fak;	          // Kehrwert der Fakultät
		e = e + fak1;              // e näherungsweise Summe 1/ i!
		printf ("%4i %6.4lf %10.7lf %20.0lf %15.14lf %14.12lf  \n",
			i  ,kehr_i, sum_1i, fak, fak1, e);
	}
}