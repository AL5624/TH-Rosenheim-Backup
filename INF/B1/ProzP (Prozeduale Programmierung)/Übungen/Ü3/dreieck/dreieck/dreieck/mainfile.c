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
	double a;  // Seitenlänge von Seite a
	double alpha_deg;  // Winkel in Grad

	// Dialogeröffnung 
	strich(50,'-');
	printf ("Rechtwinkliges Dreieck\n");
	strich(50,'-');

	do // Seite einlesen
	{
		printf("Bitte Laenge Seite a eingeben: ");
		scanf("%lf", &a);
		if (a <= 0) printf("Bitte eine Zahl ueber 0 eingeben\n");
		// if (a = ) printf("Bitte eine Zahl eingeben\n");
	} while (a <= 0);

	do // Winkel einlesen
	{
		printf("Bitte Winkel Alpha in Grad eingeben: ");
		scanf("%lf", &alpha_deg);
		if (alpha_deg <= 0.0) printf("Bitte eine Zahl ueber 0 eingeben\n");
		if (alpha_deg >= 90.0) printf("Bitte eine Zahl unter 90 eingeben\n");
	} while (alpha_deg >= 90.0 || alpha_deg <= 0.0);//&& für AND, || für OR


	// Berechnung aller Seiten und Winkel

	double alpha_rad;
	alpha_rad = deg2rad(alpha_deg);

	double gamma_deg;
	gamma_deg = 90.0;

	double sin_alpha_deg;
	sin_alpha_deg = sin(alpha_rad);

	double cos_alpha_deg;
	cos_alpha_deg = cos(alpha_rad);

	double tan_alpha_deg;
	tan_alpha_deg = tan(alpha_rad);

	double beta_deg;
	beta_deg = 180.0 - gamma_deg - alpha_deg;

	double beta_rad;
	beta_rad = deg2rad(beta_deg);

	double gamma_rad;
	gamma_rad = deg2rad(gamma_deg);

	// speichern aller Ergebnisse in einer neuen Variablen

	double b;
	b = a / tan_alpha_deg;
	
	double c;
	c = a / sin_alpha_deg;

	// abrunden aller Seiten auf die nächste ganze Zahl

	double a_down;
	a_down = f_down(a);

	double b_down;
	b_down = f_down(b);

	double c_down;
	c_down = f_down(c);

	// aufrunden aller Seiten auf die nächste ganze Zahl

	double a_up;
	a_up = ceil(a);

	double b_up;
	b_up = ceil(b);

	double c_up;
	c_up = ceil(c);

	// (korrektes) runden aller Seiten auf die nächste ganze Zahl

	double a_cor;
	if (a - a_down >= 0.5) { a_cor = a_up; }
	else { a_cor = a_down; }

	double b_cor;
	if (b - b_down >= 0.5) { b_cor = b_up; }
	else { b_cor = b_down; }

	double c_cor;
	if (c - c_down >= 0.5) { c_cor = c_up; }
	else { c_cor = c_down; }

	// Ausgabe aller Seiten und Winkel
	printf("       2 NK-Stellen   gerundet   abgerundet   aufgerundet\n");
	strich(57,'-');
	printf("a        %10.2f %10.0f   %10.0f    %10.0f\n", a, a_cor, a_down, a_up);
	printf("b        %10.2f %10.0f   %10.0f    %10.0f\n", b, b_cor, b_down, b_up);
	printf("c        %10.2f %10.0f   %10.0f    %10.0f\n", c, c_cor, c_down, c_up);
	printf("Alpha (Grad) %6.2f\n", alpha_deg);
	printf("Beta  (Grad) %6.2f\n", beta_deg);
	printf("Gamma (Grad) %6.2f\n", gamma_deg);
	printf("Alpha (rad) %7.3f\n", alpha_rad);
	printf("Beta  (rad) %7.3f\n", beta_rad);
	printf("Gamma (rad) %7.3f\n", gamma_rad);

}

// Eingabe: w_deg, Winkel in Grad
// Ausgabe: w_rad, Winkel in rad
double deg2rad(double w_deg)
{
	double w_rad = 0.0;

	if (w_deg == 0) {
		return w_rad;
	}
	else {
		// Grad in rad umrechnen, Ergebnis in w_rad
		w_rad = w_deg * PI / 180.0;
		return w_rad;
	}
}

// Sinnlose Funktionen
double f_down(double E_Wert)
{
	double A_Wert = 0.0;

	A_Wert = floor(E_Wert);

	return A_Wert;
}

// gibt n mal das Zeichen c aus 
void strich (int n, char c) 
{
	int i;
	for (i=1; i<=n; i++)
		printf("%c", c);
	printf("\n");
}

