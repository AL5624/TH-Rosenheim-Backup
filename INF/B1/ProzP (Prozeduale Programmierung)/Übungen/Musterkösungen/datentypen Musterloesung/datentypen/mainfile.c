/*******************************************************************
/ Programm    : Datentypen                                          
/ Verfasser   : Schmidt                                           
/ Datum       : Urprogramm: 17.10.2012                                          
/ Eingabe     : Vier Zeichen                            
/ Verarbeitung: diverse Berechnungen                   
/ Ausgabe     : Ergebnisse
/ Änderungen  : 17.10.2012
/*******************************************************************/

/* Einbinden von nötigen Header-Dateien                               */
#include <stdio.h>    /* Standard Input/ Output  z.B. scanf, printf */
#include <stdlib.h>   /* Standard-Bibliothek, z.B. für system */

char kleinstesZeichen(char c1, char c2, char c3, char c4);
char groesstesZeichen(char c1, char c2, char c3, char c4);

int main()
{
	char c1, c2, c3, c4;
	int ascii_durch_i;
	float ascii_durch_f;
	int min, max, diff;

	printf("Bitte geben Sie vier einzelne Zeichen ein: ");
	scanf("%c %c %c %c", &c1, &c2, &c3, &c4); 

	ascii_durch_i = (c1 + c2 + c3 + c4) / 4;
	ascii_durch_f = (float)(c1 + c2 + c3 + c4) / 4.0f;

	printf("Eingegeben wurden:       %c %c %c %c\n", c1, c2, c3, c4);
	printf("ASCII Codes:             %d %d %d %d\n", c1, c2, c3, c4);
	printf("ASCII Code (Mittel,int): %d\n", ascii_durch_i);
	printf("ASCII Code (Mittel,int): %x\n", ascii_durch_i);
	printf("ASCII Code (Mittel,fl):  %f\n", ascii_durch_f);
	printf("\"Mittleres\" Zeichen:     %c\n", ascii_durch_i);

	min = kleinstesZeichen(c1, c2, c3, c4);
	printf("Kleinstes Zeichen:       %c\n", min);
	max = groesstesZeichen(c1, c2, c3, c4);
	printf("Groesstes Zeichen:       %c\n", max);

	diff = max - min;
	printf("Abstand Max/Min:         %d Zeichen\n", diff);

	system("pause");
	return(0);
}

char kleinstesZeichen(char c1, char c2, char c3, char c4)
{
	char min = c1;

	if(c2 < min) min = c2;
	if(c3 < min) min = c3;
	if(c4 < min) min = c4;

	return min;
}

char groesstesZeichen(char c1, char c2, char c3, char c4)
{
	char max = c1;

	if(c2 > max) max = c2;
	if(c3 > max) max = c3;
	if(c4 > max) max = c4;

	return max;
}
