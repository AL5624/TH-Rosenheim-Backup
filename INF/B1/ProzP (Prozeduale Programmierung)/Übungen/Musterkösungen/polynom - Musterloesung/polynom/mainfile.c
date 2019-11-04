#include "tt_poly.h"
#include "polynom.h"
#include "inf1.h"
#include <stdlib.h>

int main()
{
	int p_n; // Polynomgrad
	int *a;  // Feld für Koeffizienten
	double p_x;  // Argument
	double y;  // Wert

	EinlesenGrad(&p_n);

	a = (int*) malloc((p_n + 1) * sizeof(int));
	Assert(a != NULL, "Malloc fehlgeschlagen!");

	EinlesenKoeff(p_n, a);
	EinlesenArg(&p_x);
	y = PolynomWert (p_n, a, p_x);

	printf("Polynomwert an Stelle %.2f ist: %.2f\n", p_x, y);

	free(a);

	system("pause");

	// Einkommentieren zum Start des Testtreibers für Polynomwertberechnung (Aufgabe 1)
	tt_polyWert();
	
	// Einkommentieren zum Start des Testtreibers für Nullstellenberechnung (Aufgabe 2)
	tt_polyAbl();
	return 0;
}