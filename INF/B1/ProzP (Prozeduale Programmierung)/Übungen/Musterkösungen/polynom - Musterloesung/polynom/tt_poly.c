//*******************************************************************  
// Programm    : tt_poly                                               
// Übungsbeispiel für:                                                 
//               Testprogramm für Polynom                               
// Verfasser   : Feindor / Schmidt                                          
// Datum       : Urprogramm: 1.11.1997                                             
// Eingabe     : keine
// Verarbeitung: Erzeugen Testdaten,  Durchführen Test
// Ausgabe     : Testprotokoll
// Änderungen  : 5.11.2010
//*******************************************************************

// Einbinden von nötigen Header-Dateien *****************************
#include "inf1.h" // alles Allgemeine
#include "polynom.h"  // Polynom-spezifischer Header; Code in .c

#include <time.h>    // wegen time
#include <math.h>    // wegen math. Funktionen pow()

/* nötige globale Deklarationen */
#define MAX_GRAD 20

// Erzeugung der benötigten Test- Daten (Koeffizienten)       
// erzeugt zufällig Koeffizienten a[0],...a[grad] 
void ErzeugenKoeff(const int grad, int a[])
{
	int i;
	for (i=0; i<= grad; i=i+1)
	{
		a[i] = (rand()%100) -50;    // Zufallszahl -50..49
		// hier könnte ein beliebiger Algorithmus stehen, auch ohne random
	}
}
// Definition der benötigten Test- Funktionen       
// Berechnung des Wertes des Polynoms a[0] + a[1] x + ... + a[n] x hoch n 
// zur Kontrolle mithilfe Standardfunktion pow 
double KontrollePolynomWert (const int n, const int a[], const double x)
{
	int i;
	double erg;
	erg = 0;
	for (i = 0; i<= n; i++)
		erg = erg + a[i] * pow (x,i);
	return(erg);
}

// Berechnung des Wertes des Polynoms a[0] + a[1] x + ... + a[n] x hoch n 
// zur Kontrolle mithilfe Standardfunktion pow 
double KontrollePolyAblWert (const int n, const int a[], const double x)
{
	int i;
	double erg;
	erg = 0;
	for (i = 1; i<= n; i++)
		erg = erg + i * a[i] * pow (x,i-1);
	return(erg);
}

void tt_polyAbl(void)
{
	//*********************** Vereinbarungsteil **************************
	// Polynom:
	int n;          // Polynomgrad 
	int a[MAX_GRAD+1];      // Koeffizientenarray a[0] ... a[MAX_GRAD]  

	const int anzTest = 100;    // Anzahl Testdurchläufe  
	const double eps = 1E-6;  // Schranke für Fehler 
	const double xmin = -10.0, xmax = 10.0, xdelta = 0.1;   // Wertebereich 
	int t,          // Test-Lauf 
		status;		 // Ergebnis der Nullstellensuche 
	double x,        // Argument  
		s;		  // Startwert für Nullstellensuche 
	double y1, y2;  // Polynomwert und Kontrollwert 
	double absrelF, // Absolutbetrag des relativen Fehlers 
		sumF;    // Summe der relativen Fehler je n

	//************************ Anweisungsteil  ****************************
	// Dialogeröffnung 
	dialog("tt_poly2: Testtreiber fuer PolyAblWert");

	// Anweisungen    
	srand((unsigned) time(NULL));  // Initialisierung des Zufallsgenerators 

	for (t=1; t<= anzTest; t++)       // Test wird anzTest mal durchlaufen 
	{
		printf("Testlauf %i gestartet! \n", t);
		for (n= 1; n<= MAX_GRAD; n++)  // für jeden Grad wird ein Polynom erzeugt 
		{
			printf("Test %2i mit n= %2i gestartet ",t,n);
			ErzeugenKoeff(n, a);
			// Test Polynomwerte und Ableitungswerte
			sumF =0;  // Summe relative Fehler je Polynom 
			for (x=xmin; x<= xmax; x=x+ xdelta)  // viele Werte für jedes Polynom 
			{
				// Polynomwert kontrollieren
				y1 = PolynomWert(n, a, x);
				y2 = KontrollePolynomWert(n, a, x);
				if (fabs(y1)> eps)
					absrelF= fabs((y2-y1)/y1);// Absolutbetrag des relativen Fehlers
				else
					absrelF= fabs(y2);    // sollte dann ebenfalls fast Null sein!
				sumF = sumF + absrelF;     // wird summiert
				if (absrelF > eps)
				{
					printf("\n\t Fehler in Polynomberechnung! \n " \
						"\t n= %i x= %8.4lf  y1= %15.10lf  y2= %15.10lf \n", n,x,y1,y2);
					system("pause");
				}
				// Ableitungswert kontrollieren
				y1 = PolyAblWert(n, a, x);
				y2 = KontrollePolyAblWert(n, a, x);
				if (fabs(y1)> eps)
					absrelF= fabs((y2-y1)/y1);// Absolutbetrag des relativen Fehlers
				else
					absrelF= fabs(y2);    // sollte dann ebenfalls fast Null sein!

				sumF = sumF + absrelF;     // wird summiert
				if (absrelF > eps)
				{
					printf("\n\t Fehler in Berechnung der Ableitung! \n " \
						"\t n= %i x= %8.4lf  y1= %15.10lf  y2= %15.10lf \n", n,x,y1,y2);
					system("pause");
				}

			}

			// Test Nullstellensuche (einfach)
			s = 1.0 * (rand()%20) -10; // Zufallszahl im Intervall -10 .. 9
			y1= Nullstelle (n, a, s, &status);
			if (status == 0)  // Nullstelle gefunden
				if (fabs(PolynomWert(n,a,y1)) > 0.01)
				{
					printf("Fehler in Berechnung der Nullstelle! \n ");
					system("pause");
				};

			if (status == 1)  // waagrechte Tangente
				if (fabs(PolyAblWert(n,a,y1)) > eps)
				{
					printf("Fehler in Berechnung der Nullstelle! \n ");
					system("pause");
				};


			printf("und beendet. Summe rel. Fehler: %18.16lf\n", sumF);
		}
		printf("Testlauf %2i erfolgreich beendet! \n\n", t);
	}
	printf("Servus!");

}


void tt_polyWert(void)
{
	//*********************** Vereinbarungsteil **************************
	int n;          // Polynomgrad
	int a[MAX_GRAD+1];      // Koeffizientenarray a[0] ... a[MAX_GRAD]

	const int anzTest = 100;    // Anzahl Testdurchläufe
	const double eps = 1E-9;  // = 0.000000001; Schranke für relativen Fehler
	const double xmin = -10.0, xmax = 10.0, xdelta = 0.1;   // Wertebereich
	int t;          // Test-Lauf
	double x;        // Argument
	double y1, y2;  // Polynomwert und Kontrollwert
	double absrelF, // Absolutbetrag des relativen Fehlers
		sumF;    // Summe der relativen Fehler je n

	//************************ Anweisungsteil  ****************************
	// Dialogeröffnung
	dialog("tt_poly: Testprogramm fuer PolynomWert");

	// Anweisungen
	srand((unsigned) time(NULL));  // Initialisierung des Zufallsgenerators

	for (t = 1; t <= anzTest; t++)       // Test wird anzTest mal durchlaufen
	{
		printf("Testlauf %i gestartet! \n", t);
		for (n = 1; n <= MAX_GRAD; n++)  // für jeden Grad wird ein Polynom erzeugt
		{
			printf("Test %2i mit n= %2i gestartet ", t, n);
			ErzeugenKoeff(n, a);
			sumF = 0;  // Summe relative Fehler je n

			for (x = xmin; x <= xmax; x = x + xdelta)  // viele Werte für jedes Polynom
			{
				y1 = PolynomWert(n, a, x);
				y2 = KontrollePolynomWert(n, a, x);

				if (fabs(y1)> eps)
					absrelF = fabs((y2 - y1) / y1);// Absolutbetrag des relativen Fehlers
				else
					absrelF = fabs(y2);    // sollte dann ebenfalls fast Null sein!

				sumF = sumF + absrelF;   // wird summiert

				if (absrelF > eps)
				{
					printf("\n\t Fehler in Polynomberechnung! \n " \
						"\t n= %i x= %8.4lf  y1= %15.10lf  y2= %15.10lf \n", n,x,y1,y2);
					system("pause");
				}
			}
			printf("und beendet. Summe rel. Fehler: %18.16lf\n", sumF);
		}
		printf("Testlauf %2i erfolgreich beendet! \n\n", t);
	}
	printf("Servus!");
	system("pause");
} 
