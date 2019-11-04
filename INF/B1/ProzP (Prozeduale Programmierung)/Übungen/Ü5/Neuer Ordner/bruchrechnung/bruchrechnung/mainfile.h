#ifndef _MAINFILE_H_
#define _MAINFILE_H_

/* Prototypen der benötigten Funktionen */


typedef struct bruch_s
{

	int zaehler;
	int nenner;

}bruch_t;


bruch_t Produkt(bruch_t Bruch1, bruch_t Bruch2);

bruch_t Addition(bruch_t Bruch1, bruch_t Bruch2);

bruch_t Differenz(bruch_t Bruch1, bruch_t Bruch2);

bruch_t Teilen(bruch_t Bruch1, bruch_t Bruch2);

void NachZahlenFragen(void);

void text(void);

void Fragen(bruch_t Bruch1, bruch_t Bruch2);

#endif
