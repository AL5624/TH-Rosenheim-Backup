/*******************************************************************
// Programm    : Eurotest                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 21.11.2012                                           
// Aufgabe     : Prüfung von Euro-Banknoten Seriennummern
// Änderungen  : 21.11.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include "funktionen.h"
#include <stdlib.h>
#include <string.h>

/*
int IsNumber(char n) {
	if (0x30 <= n <= 0x39) {
		return 1;
	}
	return 0;
}

int IsUpper(char c) {
	if (0x41 <= c <= 0x5A) {
		return 1;
	}
	return 0;
}
*/

void testauflaenge(char k[])
{

	if (strlen(k) < 12) return printf("nicht ok");
	if (strlen(k) > 12) return printf("nicht ok 2");
	return printf("ok");

}

int testaufzahl(char seriennum[])
{
	for (int k = 1; k < 12; k++)
	{
		for (int p = 48; p <= 57; )
		{
			if ((int)seriennum[k] == p)
			{
				break;
			}
			else
			{
				++p;
				if (p > 57 || (int)seriennum[k] < 48) return printf("nicht ok");
			}
		}
	}

	return printf("ok");
}

int testobgrossbuchstabe(char n[])
{
	int i = 65;

	do
	{

		if ((int)n[0] == i) break;

		++i;

	} while (i <= 90);

	if (i > 90)
	{
		return printf("nicht ok");
	}
	else
	{
		return printf("ok");
	}
}



void strich (int n, char c) // gibt n mal das Zeichen c aus
{
	int i;
	for (i = 1; i <= n; i++)
		printf("%c", c);
	printf("\n");
}

int quersumme(char seriennummer[])
{
	int i = (int)seriennummer[0];

	int qs;

	int r = 0;

	for (int j = 1; j < 12; j++)
	{
		int u = seriennummer[j] - '0';

		r = r + u;
	}

	qs = i + r;

	return qs;
}

int ok = 0;

t_errcode eurotest(char srnnmr[])
{

	/*
	if (!IsUpper(srnnmr[0])) {
		return ec_LCfalsch;
	}

	for (int i = 1; i < 12; i++)
	{
		if (!IsNumber(srnnmr[i]))
		{
			return ec_SNkeineZiffer;
		}
	}
	*/
	ok = ok - ok;

		int i = 65;

		do
		{

			if ((int)srnnmr[0] == i) break;

			++i;

		} while (i <= 90);

		if (i > 90)  ec_LCfalsch, printf("Das erstes Zeichen muss ein Grossbuchstabe sein.\n") && ++ok;

		if (strlen(srnnmr) < 12)  ec_zukurz, printf("Die angegebene Seriennummer ist zu kurz.\n") && ++ok;
		if (strlen(srnnmr) > 12)  ec_zulang, printf("Die angegebene Seriennummer ist zu lang.\n") && ++ok;

		for (int k = 1; k < strlen(srnnmr); k++)
		{
			{
			for (int p = 48; p <= 57; )
				if ((int)srnnmr[k] == p)
				{
					break;
				}
				else
				{
					++p;
					if (p > 57)  ec_SNkeineZiffer, printf("Die Seriennummer enthaelt Zeichen, die keine Zahlen sind (abgesehen vom Laendercode).\n") && ++ok;
					if ((int)srnnmr[k] < 48)
					{
						printf("Die Seriennummer enthaelt Zeichen, die keine Zahlen sind (abgesehen vom Laendercode).\n") && ++ok;
						break;
					}
				}
			}
		}

	if (quersumme(srnnmr) % 9 != 0)  printf("Die Seriennummer oder die Pruefziffer ist falsch.\n") && ++ok, ec_pz_falsch;


	if (ok == 0)
	{
		ec_ok, printf("alles ok\n");
	}
	
	return;


}


void abfrage()
{
	char abf[100];

	printf("Bitte eine gueltige Seriennummer einer Euro-Banknote eingeben: \n");
	scanf("%s", abf);

	eurotest(abf);

	while (ok != 0)
	{
		printf("\nBitte erneut eingeben:\n");
		scanf("%s", abf);
		eurotest(abf);
	}

	return 0;

}