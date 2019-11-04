//*******************************************************************
// Programm    : Dreieck                                          
// Verfasser   : Schmidt                                           
// Datum       : Urprogramm: 11.10.2012                                          
// Eingabe     : Seite + Winkel eines rechtwinkligen Dreiecks                            
// Verarbeitung: Berechnung aller Seiten und Winkel                   
// Ausgabe     : Ergebnis als Tabelle
// Änderungen  : 11.10.2012
//*******************************************************************

// Einbinden von nötigen Header-Dateien                              
#include <stdio.h>    // Standard Input/ Output  z.B. scanf, printf
#include <stdlib.h>   // Standard-Bibliothek, z.B. für system
#include <math.h>     // Standard-Bibliothek für math. Funktionen z.B. sqrt
#include "mainfile.h" // eigene Header-Datei mit Funktionsprototypen


int main() // Beginn Hauptprogramm          **************************
{
	// Aufgabe: Dreieck
	dreieck();

	system("pause");
	return(0);
} //***************** Ende Hauptprogramm ***************************


// Definition der benötigten Funktionen      

void dreieck(void)
{
	double a = 0, b, c;  // Seitenlängen
	double alpha_deg, beta_deg;  // Winkel in Grad
	const double gamma_deg = 90.0;
	double ager, bger, cger;
	double ager_ab, bger_ab, cger_ab;
	double ager_auf, bger_auf, cger_auf;
	int anz;

	// Dialogeröffnung 
	strich(50,'-');
	printf ("Rechtwinkliges Dreieck\n");
	strich(50,'-');

	// Seite/Winkel einlesen
	do{
		char tmp;

		printf("Bitte Laenge Seite a eingeben: ");
		anz = scanf("%lf", &a);
		if(anz==0)
			scanf("%c", &tmp);
	} while((a<=0)||(anz == 0));

	printf("Bitte Winkel Alpha in Grad eingeben: ");
	scanf("%lf", &alpha_deg);

	// Berechnung aller Seiten und Winkel
	c = a / sin(deg2rad(alpha_deg));
	b = c * cos(deg2rad(alpha_deg));

	beta_deg = 180.0 - gamma_deg - alpha_deg;

	// (korrektes) Runden aller Seiten auf die nächste ganze Zahl
	// speichern des Erebnisses in einer neuen Variablen
	ager = floor(a + 0.5);
	bger = floor(b + 0.5);
	cger = floor(c + 0.5);

	ager_ab = floor(a);
	bger_ab = floor(b);
	cger_ab = floor(c);

	ager_auf = ceil(a);
	bger_auf = ceil(b);
	cger_auf = ceil(c);

	// Ausgabe aller Seiten und Winkel

	printf("       2 NK-Stellen   gerundet   abgerundet   aufgerundet\n");
	strich(57,'-');
	printf("a        %10.2f %10.0f   %10.0f    %10.0f\n", a, ager, ager_ab, ager_auf);
	printf("b        %10.2f %10.0f   %10.0f    %10.0f\n", b, bger, bger_ab, bger_auf);
	printf("c        %10.2f %10.0f   %10.0f    %10.0f\n", c, cger, cger_ab, cger_auf);
	printf("Alpha (Grad)  %4.2f \n", alpha_deg);
	printf("Beta  (Grad)  %4.2f \n", beta_deg);
	printf("Gamma (Grad)  %4.2f \n", gamma_deg);
	printf("Alpha (rad)   %4.3f \n", deg2rad(alpha_deg));
	printf("Beta  (rad)   %4.3f \n", deg2rad(beta_deg));
	printf("Gamma (rad)   %4.3f \n", deg2rad(gamma_deg));

}

double deg2rad(double w_deg)
{
	double w_rad;

	w_rad = w_deg * (2.0 * PI / 360.0);

	return w_rad;
}

// gibt n mal das Zeichen c aus 
void strich (int n, char c) 
{
	int i;
	for (i=1; i<=n; i++)
		printf("%c", c);
	printf("\n");
}

