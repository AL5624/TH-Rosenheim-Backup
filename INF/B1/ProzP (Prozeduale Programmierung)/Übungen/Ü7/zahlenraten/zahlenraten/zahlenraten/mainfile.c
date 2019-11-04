/*******************************************************************
// Programm    : Zahlenraten / Hauptdatei                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 7.11.2012                                           
// Aufgabe     : Benutzer muss Zahl zwischen 1 und 100 erraten
// Änderungen  : 7.11.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <stdlib.h>   /* Standard-Bibliothek, z.B. für system       */
#include "funktionen.h"


int main () 
{
	strich(50,'-');
	printf("Zahlenraten\n");
	strich(50,'-');

	srand((unsigned int)time(NULL));

	zahlenraten(CON);

	printf("Servus! \n");
	system("pause");
	return 0;
}


