/*******************************************************************
// Programm    : Eurotest                                               
// Verfasser   : Schmidt                                             
// Datum       : Urprogramm: 21.11.2012                                           
// Aufgabe     : Prüfung von Euro-Banknoten Seriennummern
// Änderungen  : 21.11.2012
*******************************************************************/

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <string.h>
#include "funktionen.h"


int quersumme(const char *sn)
{
	int summe = sn[0];
	int i;

	for(i = 1; i < (int)strlen(sn); i++)
		summe = summe + (int)(sn[i]) - (int)'0';

	return summe;
}

t_errcode eurotest(const char *sn)
{
	t_errcode ret = ec_ok;
	int i;

	/* prüfe Länge */
	if(strlen(sn) < SNLEN)
		ret = ec_zukurz;
	else if(strlen(sn) > SNLEN)
		ret = ec_zulang;
	else if(sn[0] < 'A' || sn[0] > 'Z')		/* prüfe Ländercode auf Buchstabe */
		ret = ec_LCfalsch;
	else  
	{
		/* prüfe SN auf Ziffern */
		for(i=1; i < SNLEN; i++)
		{
			if(sn[i] < '0' || sn[i] > '9')
				ret = ec_SNkeineZiffer;
		}

		/* weiter, wenn alles ok */
		if(ret != ec_SNkeineZiffer)
			if(quersumme(sn) % 9 == 0)
				ret = ec_ok;
			else
				ret = ec_pz_falsch;
	}

	return ret;
}

void ausgabe(t_errcode r)
{
	switch(r)
	{
	case ec_ok:
		printf("Nummer ok!\n");
		break;
	case ec_pz_falsch:
		printf("Pruefziffer falsch!\n");
		break;
	case ec_zukurz:
		printf("Nummer zu kurz!\n");
		break;
	case ec_zulang:
		printf("Nummer zu lang!\n");
		break;	
	case ec_LCfalsch:
		printf("Laendercode falsch!\n");
		break;
	case ec_SNkeineZiffer:
		printf("Enthaelt Zeichen, die keine Ziffern sind!\n");
		break;
	default:
		printf("Ungueltiger Fehlercode\n");
	}
}

void strich (int n, char c) // gibt n mal das Zeichen c aus
{
	int i;
	for (i = 1; i <= n; i++)
		printf("%c", c);
	printf("\n");
}

