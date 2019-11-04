/*******************************************************************
// Programm    : Primzahlen                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 13.11.2012                                           
// Aufgabe     : Primzahlberechnung nach Sieb des Eratosthenes
// Änderungen  : 13.11.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#define _USE_MATH_DEFINES
#include<math.h>
#include<string.h>
#include <stdlib.h>
#include "funktionen.h"

cl_errors_t scanCommandLine(int argc, char **argv, mainParameter_t *params)
{
	int curarg = 1;		/* aktuell bearbeiteter Index in argv */

	cl_errors_t ret = CL_NOERROR;
	boolean_t foundEMIN, foundEMAX, foundR;	/* speichert, welcher Parameter vorhanden war */
	foundEMIN = foundEMAX = foundR = FALSE;
	
	/* prüfe Argumente */
	while(curarg < argc)
	{
		if(strcmp(argv[curarg], "-emin") == 0)
		{
			curarg++;
			params->emin = atoi(argv[curarg]);
			foundEMIN = TRUE;
		}
		else if(strcmp(argv[curarg], "-emax") == 0)
		{
			curarg++;
			params->emax = atoi(argv[curarg]);
			foundEMAX = TRUE;
		}
		else if(strcmp(argv[curarg], "-r") == 0)
		{
			curarg++;
			params->radius = atof(argv[curarg]);
			foundR = TRUE;
		}
		else
		{
			ret = CL_UNKOWNPARAM;
			curarg = argc;	/* Schleifenabbruch */
		}

		curarg++;
	}

	/* Prüfe Gültigkeit, wenn alle Parameter da*/
	if(foundEMIN == TRUE && foundEMAX == TRUE && foundR == TRUE)
	{
		if(params->emin <= 2)
			ret = CL_EMINOUTOFRANGE;
		if(params->emax < params->emin)
			ret = CL_EMAXOUTOFRANGE;
		if(params->radius <= 0.0)
			ret = CL_RADIUSINVALID;
	}
	else
		ret = CL_PARAMMISSING;

	return ret;
}

void printCLError(cl_errors_t cl_error)
{
	switch(cl_error)
	{
	case CL_NOERROR:
		break;

	case CL_EMINOUTOFRANGE:
		printf("-emin kleiner/gleich 2!\n");
		exit(EXIT_SUCCESS);
		break;

	case CL_EMAXOUTOFRANGE:
		printf("-emax kleiner -emin!\n");
		exit(EXIT_SUCCESS);
		break;

	case CL_RADIUSINVALID:
		printf("Ungueltiger Radius!\n");
		exit(EXIT_SUCCESS);
		break;

	case CL_PARAMMISSING:
		printf("Fehlender Parameter!\n");
		exit(EXIT_SUCCESS);
		break;

	case CL_UNKOWNPARAM:
		printf("Unbekannter Parameter!\n");
		exit(EXIT_SUCCESS);
		break;

	default:
		printf("Unbekannter enum Eintrag!\n");
		exit(EXIT_FAILURE);
		break;
	}
}


void berechnePolygone(polygon_t daten[], mainParameter_t params)
{
	int i;

	for(i = params.emin; i <= params.emax; i++)
	{
		daten[i-params.emin].anzEcken = i;
		daten[i-params.emin].laenge = params.radius * 2*sin(M_PI / (double)i);
		daten[i-params.emin].flaeche = (i * daten[i-params.emin].laenge * daten[i-params.emin].laenge) / (4*tan(M_PI/(double)i));
		daten[i-params.emin].abweichungKreis = params.radius * params.radius * M_PI - daten[i-params.emin].flaeche;
	}
}

void AusgabePolygone(polygon_t daten[], int anzZeilen)
{
	int i;

	printf("Anzahl Ecken	Seitenlaenge    Flaeche		Abweichung zu Kreisflaeche\n");
	for(i = 0; i < anzZeilen; i++)
		printf("%3d           %10.2f     %10.2f           %10.2f\n", daten[i].anzEcken,  daten[i].laenge, daten[i].flaeche, daten[i].abweichungKreis);
}

void strich (int n, char c) /* gibt n mal das Zeichen c aus */
{
	int i;
	for (i = 1; i <= n; i++)
		printf("%c", c);
	printf("\n");
}

