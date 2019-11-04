/*******************************************************************
// Programm    : Eurotest                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 21.11.2012                                           
// Aufgabe     : Prüfung von Euro-Banknoten Seriennummern
// Änderungen  : 21.11.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <stdlib.h>   /* Standard-Bibliothek, z.B. für system       */
#include "funktionen.h"


int main () 
{
	t_errcode r;
	char nummer[] = "P02571225193";

	strich(50,'-');
	printf("Eurotest\n");
	strich(50,'-');

	r = eurotest(nummer);
	
	printf("Nummer: %s\n", nummer);
	ausgabe(r);

	printf("\nServus! \n");
	system("pause");
	return 0;
}