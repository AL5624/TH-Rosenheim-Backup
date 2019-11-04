//************************************************************************* 
// Modul     : polynom.c                                                   
// Verfasser : Feindor/Schmidt                                                    
// Datum     : Urprogramm: 1.10.96                                                      
// Inhalt    : alle nötigen Polynomfunktionen                                                          
// letzte Änderungen: 9.1.2013
//*************************************************************************
// Einbinden von nötigen Header-Dateien  
#include "inf1.h" // alles Allgemeine
#include "polynom.h"
#include <math.h>    // wegen math. Funktionen 


// Funktionen  
// liest Polynomgrad ein 
void EinlesenGrad(int * p_n)
{
	do
	{
		printf("Bitte Polynomgrad eingeben: ");
		scanf("%i",p_n);
		if (*p_n <= 0)
			printf ("Fehler! Bitte korrekt eingeben !!\n");
	} while (*p_n <= 0);
}

// liest Koeffizienten a[0],...a[n] ein 
void EinlesenKoeff(const int n, int a[])
{
	int i;
	printf("Bitte Koeffizienten des Polynoms eingeben. \n");
	for (i = 0; i <= n; i++)
	{
		printf("a[%i] : ",i);
		scanf("%i", &a[i]);
	}
}

// liest Argument ein
void EinlesenArg (double * p_x)
{
	printf("Bitte Argument eingeben: ");
	scanf("%lf",p_x);
}

// Berechnung des Wertes des Polynoms a[0] + a[1] x + ... + a[n] x hoch n 
// derzeit Berechnung mithilfe des Horner-Schemas 
double PolynomWert (const int n, const int a[], const double x)
{
	int i;
	double erg;
	erg = a[n];
	for (i = n-1; i >= 0; i--)
		erg = erg * x + a[i];
	return(erg);
}

// Berechnung des Wertes der 1. Ableitung des Polynoms an der Stelle x  
// mithilfe des Horner-Schemas  
double PolyAblWert (const int n, const int a[], const double x)
{
	int i;
	double erg;
	erg = a[n] * n;
	for (i = n-1; i >= 1; i--)
		erg = erg * x + i * a[i];
	return(erg);
}

// liest Startwert für Newton-Iteration ein  
void EinlesenStartwert(double * p_start)
{
	printf("Bitte Naeherungswert fuer Nullstelle eingeben: ");
	scanf("%lf",p_start);
}


// Berechnung einer Nullstelle des Polynoms (n, a) aus Startwert s  
// status 0: Nullstelle gefunden;1: waagrechte Tangente;2: keine Konvergenz 
double Nullstelle (const int n, const int a[],const double s, int * status)
{
	const double eps = 0.0001; // Genauigkeit für Iteration  
	const double cMax = 100; // Maximale Anzahl von Iterationen  
	int i;         // Zähler für Iterationen  
	double x, y, abl;

	x = s;
	y = PolynomWert(n, a, x);
	// Newton-Iteration   
	i=0;
	*status = 0;
	while ((fabs(y) > eps) && (i <= cMax) && (* status == 0))
	{
		abl = PolyAblWert(n,a,x);
		if (fabs(abl)> eps)
			x = x - y / abl;
		else
			*status = 1;
		y = PolynomWert(n,a,x);
#ifdef TEST
		printf("*Test * %5i %12.5lf %12.5lf %5i\n",i,x,y, *status);
#endif
		i++;
	}
	if (i >= cMax)
		*status = 2;

	return(x);
}
