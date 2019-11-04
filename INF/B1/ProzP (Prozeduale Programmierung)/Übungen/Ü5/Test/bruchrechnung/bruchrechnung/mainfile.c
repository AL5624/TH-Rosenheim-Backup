/*******************************************************************
/ Programm    : Bruchrechnung                                         
/ Verfasser   : Schmidt                                           
/ Datum       : Urprogramm: 24.10.2012                                          
/ Eingabe     : 2 rationale Zahlen                          
/ Verarbeitung: diverse Berechnungen                   
/ Änderungen  : 24.10.2012
/ *******************************************************************/

/* Einbinden von nötigen Header-Dateien                             */
#include <stdio.h>    /* Standard Input/ Output  z.B. scanf, printf */
#include <stdlib.h>   /* Standard-Bibliothek, z.B. für system       */
#include "mainfile.h"
#include <string.h>

int main()
{
	char a[60] = "Hallo";
	char b[60] = "Hallo";
	
	if (strcmp(a, b) == 0) {

		int c = strcmp(a, b);

		printf("\n%d\n", c);
	}
	system("pause");
	return(0);
}

