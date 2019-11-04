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


int main()
{
	struct bruch_s z1, z2, sum, diff, prod, quot;

	printf("Bitte Zaehler und Nenner Bruch 1 eingeben: ");
	scanf("%d %d", &z1.zaehler, &z1.nenner);
	printf("Bitte Zaehler und Nenner Bruch 2 eingeben: ");
	scanf("%d %d", &z2.zaehler, &z2.nenner);

	sum = bruchSumme(z1, z2);
	diff = bruchDiff(z1, z2);
	prod = bruchProd(z1, z2);
	quot = bruchQuot(z1, z2);

	printf("%d/%d * %d/%d = %d/%d \n", z1.zaehler, z1.nenner, z2.zaehler, z2.nenner,  prod.zaehler, prod.nenner);
	printf("%d/%d / %d/%d = %d/%d \n", z1.zaehler, z1.nenner, z2.zaehler, z2.nenner,  quot.zaehler, quot.nenner);
	printf("%d/%d + %d/%d = %d/%d \n", z1.zaehler, z1.nenner, z2.zaehler, z2.nenner,  sum.zaehler, sum.nenner);
	printf("%d/%d - %d/%d = %d/%d \n", z1.zaehler, z1.nenner, z2.zaehler, z2.nenner,  diff.zaehler, diff.nenner);

	system("pause");
	return(0);
}


struct bruch_s bruchSumme(const struct bruch_s z1, const struct bruch_s z2)
{
	struct bruch_s sum;

	sum.nenner = z1.nenner * z2.nenner;
	sum.zaehler = z1.zaehler * z2.nenner + z2.zaehler * z1.nenner;

#ifdef KUERZEN
	sum = bruchKuerzen(sum);
#endif

	return sum;
}

struct bruch_s bruchDiff(const struct bruch_s z1, const struct bruch_s z2)
{
	struct bruch_s diff;

	diff.nenner = z1.nenner * z2.nenner;
	diff.zaehler = z1.zaehler * z2.nenner - z2.zaehler * z1.nenner;

#ifdef KUERZEN
	diff = bruchKuerzen(diff);
#endif

	return diff;
}

struct bruch_s bruchProd(const struct bruch_s z1, const struct bruch_s z2)
{
	struct bruch_s prod;

	prod.zaehler = z1.zaehler * z2.zaehler;
	prod.nenner = z1.nenner * z2.nenner;

#ifdef KUERZEN
	prod = bruchKuerzen(prod);
#endif

	return prod;
}

struct bruch_s bruchQuot(const struct bruch_s z1, const struct bruch_s z2)
{
	struct bruch_s quot;

	quot.zaehler = z1.zaehler * z2.nenner;
	quot.nenner = z1.nenner * z2.zaehler;

#ifdef KUERZEN
	quot = bruchKuerzen(quot);
#endif

	return quot;
}

/* berechnet ggT von a und b
   wichtig: beim Start muss gelten a >= b  */
int ggT(int a, int b)
{
	int ret;

	if(b == 0) ret = a;
	else ret = ggT(b, a%b);

	return ret;
}

struct bruch_s bruchKuerzen(const struct bruch_s b)
{
	struct bruch_s gekb;  // gekürzter Bruch
	int ggtb;

	// ggT berechnen
	if(abs(b.zaehler) >= abs(b.nenner))
		ggtb = ggT(abs(b.zaehler), abs(b.nenner));
	else
		ggtb = ggT(abs(b.nenner), abs(b.zaehler));

	// festellen, ob bereits gekürzt
	if(ggtb != 1)  // kann gekürzt werden
	{
		gekb.zaehler = b.zaehler / ggtb;
		gekb.nenner = b.nenner / ggtb;
	}
	else
		gekb = b;

	return gekb;
}
