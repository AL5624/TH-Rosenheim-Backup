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
#include <stdlib.h>
#include <string.h>




int main()
{
	strich(50, '-');
	printf("Eurotest\n");
	strich(50, '-');

	//char seriennummer1 [13] = "P02571225193";

	//char seriennummer2 [  ] = "C65461651655";
	
	//char seriennummer3 [13] = "C54513418945";

	/*int b = quersumme(seriennummer2);

	int a = quersumme(seriennummer1);
	
	printf("%s\n", seriennummer1);
	printf("%d\n", a);
	printf("%s\n", seriennummer2);
	printf("%d\n", b);*/

	abfrage();

	printf("\nServus! \n");
	system("pause");
	return 0;
}