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
#include <math.h>     // Standard-Bibliothek für math. Funktionen z.B. sqrt
#include "mainfile.h"

struct bruch_s Bruch1, Bruch2, erg_mul, erg_div, erg_add, erg_sub;



int ggT(Zahl_1, Zahl_2)
{
	float i = 2;
	float a;
	float b;
	int groeßter_gemeinsamer_Teiler = 1;
	int n = 1;

	do
	{
		if (Zahl_1 < 0) Zahl_1 = -Zahl_1;
		if (Zahl_2 < 0) Zahl_2 = -Zahl_2;
		a = Zahl_1 / i;
		b = Zahl_2 / i;

		if ((a - floor(a) != 0.0) || (b - floor(b) != 0.0))
		{
			++i;
		}
		else
		{

			Zahl_1 = a;
			Zahl_2 = b;

			n = n * i;
			
			groeßter_gemeinsamer_Teiler = n;
		}
	} while (i <= (Zahl_1 / 2) && i <= (Zahl_2 / 2));

	return groeßter_gemeinsamer_Teiler;
}


struct bruch_s mul(struct bruch_s B1, struct bruch_s B2)
{
	struct bruch_s Ergebnis;

	Ergebnis.Zaehler = B1.Zaehler * B2.Zaehler;
	Ergebnis.Nenner = B1.Nenner * B2.Nenner;

	int a = ggT(Ergebnis.Zaehler, Ergebnis.Nenner);

	Ergebnis.Zaehler = Ergebnis.Zaehler / a;
	Ergebnis.Nenner = Ergebnis.Nenner / a;

	return Ergebnis;
}

struct bruch_s teilen(struct bruch_s B1, struct bruch_s B2)
{
	struct bruch_s Ergebnis;

	Ergebnis.Zaehler = B1.Zaehler * B2.Nenner;
	Ergebnis.Nenner = B1.Nenner * B2.Zaehler;

	int a = ggT(Ergebnis.Zaehler, Ergebnis.Nenner);

	Ergebnis.Zaehler = Ergebnis.Zaehler / a;
	Ergebnis.Nenner = Ergebnis.Nenner / a;

	return Ergebnis;
}

struct bruch_s add(struct bruch_s B1, struct bruch_s B2)
{
	struct bruch_s Ergebnis;

	Ergebnis.Zaehler = (B1.Zaehler * B2.Nenner) + (B2.Zaehler * B1.Nenner);
	Ergebnis.Nenner = B1.Nenner * B2.Nenner;

	int a = ggT(Ergebnis.Zaehler, Ergebnis.Nenner);

	Ergebnis.Zaehler = Ergebnis.Zaehler / a;
	Ergebnis.Nenner = Ergebnis.Nenner / a;

	return Ergebnis;
}

struct bruch_s sub(struct bruch_s B1, struct bruch_s B2)
{
	struct bruch_s Ergebnis;

	Ergebnis.Zaehler = (B1.Zaehler * B2.Nenner) - (B2.Zaehler * B1.Nenner);
	Ergebnis.Nenner = B1.Nenner * B2.Nenner;

	int a = ggT(Ergebnis.Zaehler, Ergebnis.Nenner);

	Ergebnis.Zaehler = Ergebnis.Zaehler / a;
	Ergebnis.Nenner = Ergebnis.Nenner / a;

	return Ergebnis;
}



int main()
{
	do
	{
		printf("Bitte Zaehler und Nenner Bruch 1 eingeben: ");
		scanf("%d %d", &Bruch1.Zaehler, &Bruch1.Nenner);
		if (Bruch1.Nenner == 0) printf("0 kann nicht als Nenner definiert werden!\n");

	} while (Bruch1.Nenner == 0);

	do
	{
	printf("Bitte Zaehler und Nenner Bruch 2 eingeben: ");
	scanf("%d %d", &Bruch2.Zaehler, &Bruch2.Nenner);
	if (Bruch2.Nenner == 0.0) printf("0 kann nicht als Nenner definiert werden!\n");

	} while (Bruch2.Nenner == 0.0);

	printf("Bruch 1: %5d/%1d\n", Bruch1.Zaehler, Bruch1.Nenner);
	printf("Bruch 2: %5d/%1d\n", Bruch2.Zaehler, Bruch2.Nenner);
	
	struct bruch_s erg_mul = mul(Bruch1, Bruch2);

	printf("%1d/%1d * %1d/%1d = %1d/%1d\n",Bruch1.Zaehler, Bruch1.Nenner, Bruch2.Zaehler, Bruch2.Nenner, erg_mul.Zaehler, erg_mul.Nenner);
	
	struct bruch_s erg_div = teilen(Bruch1, Bruch2);

	printf("%1d/%1d / %1d/%1d = %1d/%1d\n", Bruch1.Zaehler, Bruch1.Nenner, Bruch2.Zaehler, Bruch2.Nenner, erg_div.Zaehler, erg_div.Nenner);
	
	struct bruch_s erg_add = add(Bruch1, Bruch2);

		printf("%1d/%1d + %1d/%1d = %1d/%1d\n", Bruch1.Zaehler, Bruch1.Nenner, Bruch2.Zaehler, Bruch2.Nenner, erg_add.Zaehler, erg_add.Nenner);

	struct bruch_s erg_sub = sub(Bruch1, Bruch2);

		printf("%1d/%1d - %1d/%1d = %1d/%1d\n", Bruch1.Zaehler, Bruch1.Nenner, Bruch2.Zaehler, Bruch2.Nenner, erg_sub.Zaehler, erg_sub.Nenner);




	system("pause");
	return(0);
}





//double i = 2;
//double a;
//double b;
//
//do
//{
//	a = Ergebnis.Zaehler / i;
//	b = Ergebnis.Nenner / i;
//
//
//if ((a - floor(a) != 0.0) || (b - floor(b) != 0.0))
//	{
//		i = i + 1;
//	}
//	else
//	{
//		Ergebnis.Zaehler = a;
//		Ergebnis.Nenner = b;
//	}
//} while (i < Ergebnis.Nenner && Ergebnis.Zaehler);