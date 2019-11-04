#ifndef _MAINFILE_H_
#define _MAINFILE_H_

/* Prototypen der benötigten Funktionen */

struct bruch_s
{
	int Zaehler;
	int Nenner;
};


struct bruch_s mul(struct bruch_s B1, struct bruch_s B2);

struct bruch_s teilen(struct bruch_s B1, struct bruch_s B2);

struct bruch_s add(struct bruch_s B1, struct bruch_s B2);

struct bruch_s sub(struct bruch_s B1, struct bruch_s B2);

int ggT(Zahl_1, Zahl_2);


#endif
