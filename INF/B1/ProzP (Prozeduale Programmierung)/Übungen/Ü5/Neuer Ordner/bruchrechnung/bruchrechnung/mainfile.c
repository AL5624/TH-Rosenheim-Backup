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

int befehl;
bruch_t Bruch1, Bruch2, BruchMulti, BruchAdd, BruchDiff, BruchTeilen;


int main()
{
	NachZahlenFragen();
	   
	text();
	
	Fragen(Bruch1, Bruch2);


	system("pause");
	return(0);
}

void Fragen(bruch_t Bruch1, bruch_t Bruch2)
{

	printf("Bitte eine Zahl eingeben: ");
	scanf("%d", &befehl);
	if (befehl < 1 || befehl > 6)
	{
		printf("Bitte eine Zahl zwischen 1 und 6 eingeben.");
		Fragen(Bruch1, Bruch2);
	}
	else
	{
		switch (befehl)
		{
			case 1: 	BruchAdd = Addition(Bruch1, Bruch2);
						printf("\n%d/%d + %d/%d = %d/%d\n\n", Bruch1.zaehler, Bruch1.nenner, Bruch2.zaehler, Bruch2.nenner, BruchAdd.zaehler, BruchAdd.nenner);
						break;
			case 2:		BruchDiff = Differenz(Bruch1, Bruch2);
						printf("\n%d/%d - %d/%d = %d/%d\n\n", Bruch1.zaehler, Bruch1.nenner, Bruch2.zaehler, Bruch2.nenner, BruchDiff.zaehler, BruchDiff.nenner);
						break;
			case 3:		BruchMulti = Produkt(Bruch1, Bruch2);
						printf("\n%d/%d * %d/%d = %d/%d\n\n", Bruch1.zaehler, Bruch1.nenner, Bruch2.zaehler, Bruch2.nenner, BruchMulti.zaehler, BruchMulti.nenner);
						break;
			case 4:		BruchTeilen = Teilen(Bruch1, Bruch2);
						printf("\n%d/%d / %d/%d = %d/%d\n\n", Bruch1.zaehler, Bruch1.nenner, Bruch2.zaehler, Bruch2.nenner, BruchTeilen.zaehler, BruchTeilen.nenner);
						break;
			case 5:		NachZahlenFragen();
						break;
			case 6:		break;
		}
		if (befehl != 6)
		{
			text();
			Fragen(Bruch1, Bruch2);
		}

	}

}


void NachZahlenFragen(void)
{
	printf("Bitte Zaehler und Nenner Bruch 1 eingeben: ");
	scanf("%d %d", &Bruch1.zaehler, &Bruch1.nenner);

	printf("Bitte Zaehler und Nenner Bruch 2 eingeben: ");
	scanf("%d %d", &Bruch2.zaehler, &Bruch2.nenner);
}

void text(void)
{
	printf("\nBruch 1: %d/%d  Bruch 2: %d/%d\n\n", Bruch1.zaehler, Bruch1.nenner, Bruch2.zaehler, Bruch2.nenner);
	printf("Was brauchen Sie: \n");
	printf("1. Die Summe von Bruch 1 und Bruch 2\n");
	printf("2. Die Differenz von Bruch 1 und Bruch 2\n");
	printf("3. Das Produkt von Bruch 1 und Bruch 2\n");
	printf("4. Den Quotion von Bruch 1 und Bruch 2\n");
	printf("5. 2 Neue Brueche eingeben\n");
	printf("6. Ende\n\n");
}


bruch_t Produkt(bruch_t Bruch1, bruch_t Bruch2)
{
	bruch_t BruchNeu;
	BruchNeu.zaehler = Bruch1.zaehler * Bruch2.zaehler;
	BruchNeu.nenner = Bruch1.nenner * Bruch2.nenner;
	int i = 2;
	do
	{
		if (BruchNeu.zaehler % i == 0 && BruchNeu.nenner %i == 0)
		{
			BruchNeu.zaehler = BruchNeu.zaehler / i;
			BruchNeu.nenner = BruchNeu.nenner / i;
		}
		else
		{
			++i;
		}
	}while (i <= BruchNeu.zaehler && i <= BruchNeu.nenner);

	return BruchNeu;
}

bruch_t Addition(bruch_t Bruch1, bruch_t Bruch2)
{
	bruch_t BruchNeu;
	BruchNeu.zaehler = (Bruch1.zaehler * Bruch2.nenner) + (Bruch2.zaehler * Bruch1.nenner);
	BruchNeu.nenner = Bruch1.nenner * Bruch2.nenner;

	int i = 2;

	do
	{
		if (BruchNeu.zaehler % i == 0 && BruchNeu.nenner %i == 0)
		{
			BruchNeu.zaehler = BruchNeu.zaehler / i;
			BruchNeu.nenner = BruchNeu.nenner / i;
		}
		else
		{
			++i;
		}
	} while (i <= BruchNeu.zaehler && i <= BruchNeu.nenner);

	return BruchNeu;
}

bruch_t Differenz(bruch_t Bruch1, bruch_t Bruch2)
{
	bruch_t BruchNeu;
	BruchNeu.zaehler = (Bruch1.zaehler * Bruch2.nenner) - (Bruch2.zaehler * Bruch1.nenner);
	BruchNeu.nenner = Bruch1.nenner * Bruch2.nenner;

	int i = 2;

	do
	{
		if (BruchNeu.zaehler % i == 0 && BruchNeu.nenner %i == 0)
		{
			BruchNeu.zaehler = BruchNeu.zaehler / i;
			BruchNeu.nenner = BruchNeu.nenner / i;
		}
		else
		{
			++i;
		}
	} while (i <= BruchNeu.zaehler && i <= BruchNeu.nenner);

	return BruchNeu;
}

bruch_t Teilen(bruch_t Bruch1, bruch_t Bruch2)
{
	bruch_t BruchNeu;
	BruchNeu.zaehler = Bruch1.zaehler * Bruch2.nenner;
	BruchNeu.nenner = Bruch1.nenner * Bruch2.zaehler;

	int i = 2;

	do
	{
		if (BruchNeu.zaehler % i == 0 && BruchNeu.nenner %i == 0)
		{
			BruchNeu.zaehler = BruchNeu.zaehler / i;
			BruchNeu.nenner = BruchNeu.nenner / i;
		}
		else
		{
			++i;
		}
	} while (i <= BruchNeu.zaehler && i <= BruchNeu.nenner);

	return BruchNeu;
}