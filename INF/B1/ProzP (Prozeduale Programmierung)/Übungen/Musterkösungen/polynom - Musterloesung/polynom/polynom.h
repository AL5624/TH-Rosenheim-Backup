//*******************************************************************
// include-file: polynom.h
// Header-Datei für Polynom
// Verfasser   : Feindor/Schmidt
// Datum       : Urprogramm 1.10.1996
// letzte Änderungen: 9.1.2013
// 
//*******************************************************************
#ifndef POLYNOM_H
#define POLYNOM_H


// Prototypen der benötigten Funktionen
// liest Polynomgrad ein
void EinlesenGrad(int * p_n);

// liest Koeffizienten a[0],...a[n] ein
void EinlesenKoeff(const int n, int a[]);

// liest Argument ein
void EinlesenArg (double * p_x);

// Berechnung des Wertes des Polynoms a[0] + a[1] x + ... + a[n] x hoch n
double PolynomWert (const int n, const int a[], const double x);

// liest Startwert start für Newton-Iteration ein
void EinlesenStartwert (double * start);

// Berechnung des Wertes der 1. Ableitung des Polynoms an der Stelle x
double PolyAblWert (const int n, const int a[], const double x);

// Berechnung einer Nullstelle des Polynoms (n, a) aus Startwert s
// status 0: Nullstelle gefunden;1: waagrechte Tangente;2: keine Konvergenz
double Nullstelle (const int n, const int a[],const double s, int * status);

#endif
