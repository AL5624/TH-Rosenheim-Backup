#ifndef _MAINFILE_H_
#define _MAINFILE_H_

/* Deklarationen für Übung "Bruchrechnung" */

// einkommentieren zum Kürzen der Brüche
#define KUERZEN

struct bruch_s
{
	int zaehler;
	int nenner;
};


/* Prototypen der benötigten Funktionen */

/* berechnet ggT von a und b
   wichtig: beim Start muss gelten a >= b  */
int ggT(int a, int b);

struct bruch_s bruchKuerzen(const struct bruch_s b);
struct bruch_s bruchSumme(const struct bruch_s z1, const struct bruch_s z2);
struct bruch_s bruchDiff(const struct bruch_s z1, const struct bruch_s z2);
struct bruch_s bruchProd(const struct bruch_s z1, const struct bruch_s z2);
struct bruch_s bruchQuot(const struct bruch_s z1, const struct bruch_s z2);

#endif
