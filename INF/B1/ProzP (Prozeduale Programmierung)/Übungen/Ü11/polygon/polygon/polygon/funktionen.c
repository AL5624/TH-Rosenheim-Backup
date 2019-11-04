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



void strich (int n, char c) /* gibt n mal das Zeichen c aus */
{
	int i;
	for (i = 1; i <= n; i++)
		printf("%c", c);
	printf("\n");
}

void berechnePolygone(polygon_t data[], mainParameter_t params)
{
	for (int ecken = params.minecken, i = 0; ecken <= params.maxecken; ecken++, i++)
	{
		data[i].seitenlaenge = berecheSeitenlaenge(params.umkreisradius, ecken);
		data[i].anzahlecken = ecken;
		data[i].flaeche = berechneFlaeche(ecken, data[i].seitenlaenge);
		data[i].abweichung = Kreisflaeche(params.umkreisradius) - data[i].flaeche;
	}
}

double berecheSeitenlaenge(double radius, int n)					//Seitenlänge wird berechnet mit (Radius, Ecken)
{
	return 2 * radius * sin(M_PI / n);
}

double berechneFlaeche(int n, double l)							//Fläche wird berechnet mit (Ecken, Seitenlänge)
{
	return (n * l * l) / (4 * tan(M_PI / n));
}

double Kreisflaeche(double radius)								//Kreisfläche wird berechnet mit (Radius)
{
	return radius * radius * M_PI;
}


double rad2deg(double w_rad)									//deg wird in rad umgewandelt mit (rad)
{
	return (w_rad * 180) / M_PI;
}

void AusgabePolygone(polygon_t daten[], int anzZeilen)
{
	printf("\nAnzahl Ecken    Seitenlaenge    Flaeche         Abweichung zu Kreisflaeche\n");

	for (int o = 0; o < anzZeilen; o++)
	{
		int i = daten[o].anzahlecken;
		double s = daten[o].seitenlaenge;
		double f = daten[o].flaeche;
		double a = daten[o].abweichung;
		printf(" %2d %20.2lf %14.2lf %20.2lf\n", i, s, f, a);
	}

}