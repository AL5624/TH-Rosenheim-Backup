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

int grenzeEinlesen(int cMax)
{
	int ret;
 	do
	{
	printf("Bitte positive obere Grenze eingeben (ganzzahlig <= %d): ", cMax);
	scanf("%d", &ret);

	if (ret < 0 || ret > cMax) printf("Keine gueltige Zahl!\n");

	} while (ret < 0 || ret > cMax);
	strich(79, '-');

	return ret;
}




int main () 
{
	strich(50,'-');
	printf ("Exp: Berechnung von einfachen Funktionen\n");
	strich(50,'-');

	int grenze = grenzeEinlesen(20);

	tabelleAusgeben(grenze);

	printf("Servus! \n");
	system("pause");
	return 0;
}


/* gibt n mal das Zeichen c aus */
void strich (int n, char c)
{
	int i;
	for (i=1; i<=n; i++)
		printf("%c", c);
	printf("\n");
}

void tabelleAusgeben(int grenze)
{
	printf("%3s %7s %11s %21s %17s %15s\n", "i", "1/i", "Summe (1/i)", "i!", "1/i!", "Naeherung e");
	strich(79, '-');

	int i;
	float a;
	float a_div;
	float a_Summe = 0.0;
	int a_fkt = 1;
	float a_1_durch_fkt;
	float a_N_e = 1.0;

	for (i = 1; i <= grenze; i++)
	{
		a = (float)i;
		a_div = 1.0 / a;
		a_Summe = a_Summe + a_div;
		a_fkt = a_fkt * i;
		a_1_durch_fkt = 1.0 / a_fkt;
		a_N_e = a_N_e + a_1_durch_fkt;

		printf("%3d %7.4f %11.7f %21d %17.14f %15.12f\n", i, a_div, a_Summe, a_fkt, a_1_durch_fkt, a_N_e);


	}
}