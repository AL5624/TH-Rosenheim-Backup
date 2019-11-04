/*******************************************************************
// Programm    : Polygon                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 5.12.2012                                           
// Aufgabe     : Berechnung Fläche regelmäßiger Polygone
// Änderungen  : 13.11.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <stdlib.h>   /* Standard-Bibliothek, z.B. für system       */
#include "funktionen.h"

int main(int argc, char **argv)    
{
	mainParameter_t params;
	cl_errors_t sc_error;
	polygon_t *polyFeld;
	int anzZeilen;

	strich(50,'-');
	printf("Flaeche regelmaessiger Polygone\n");
	strich(50,'-');

	sc_error = scanCommandLine(argc, argv, &params);
	printCLError(sc_error);

	/* wenn man hier ankommt, ist mit den Parametern alles ok */
	anzZeilen = (params.emax - params.emin + 1);
	polyFeld = (polygon_t*) malloc(sizeof(polygon_t) * anzZeilen);
	if(polyFeld == NULL)
	{
		printf("Malloc-Fehler!\n");
		exit(EXIT_FAILURE);
	}

	berechnePolygone(polyFeld, params);
	AusgabePolygone(polyFeld, anzZeilen);

	free(polyFeld);

	printf("\nServus! \n");
	system("pause");
	return 0;
}
