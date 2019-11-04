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
	strich(50, '-');
	printf("Flaeche regelmaessiger Polygone\n");
	strich(50, '-');

	mainParameter_t *parameter;
	parameter = (mainParameter_t*)malloc(sizeof(mainParameter_t));


	//printf("\nBitte minimale Anzahl der Ecken eingeben: ");
	//scanf(" %d", &parameter->minecken);
	parameter->minecken = 3;
	//printf("\nBitte maximale Anzahl der Ecken eingeben: ");
	//scanf("%d", &parameter->maxecken);
	parameter->maxecken = 15;
	//printf("\nBitte Umkreisradius eingeben: ");
	//scanf("%lf", &parameter->umkreisradius);
	parameter->umkreisradius = 3.20;

	int anzZeilen = (parameter->maxecken) - (parameter->minecken) + 1;

	polygon_t *daten = NULL;
	daten = (polygon_t*)malloc(anzZeilen * sizeof(polygon_t));
	//polygon_t daten[100];
	

	if (daten == NULL) printf("\nFehler!\n");

	berechnePolygone(daten, *parameter);


	AusgabePolygone(daten, anzZeilen);

	if (daten != NULL)
	{
		free(daten);
		daten = NULL;
	}
	

	



	printf("\nServus! \n");
	system("pause");
	return 0;
}
