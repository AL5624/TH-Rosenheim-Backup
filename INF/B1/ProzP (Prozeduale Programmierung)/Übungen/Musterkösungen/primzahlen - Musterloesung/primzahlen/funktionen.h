#ifndef _PRIM_H_
#define _PRIM_H_

/* Definition globale Konstanten */
#define NICHT_PRIM 0
#define PRIM  1


/* Prototypen der benötigten Funktionen */
void sieb(int obergrenze, int zahlen[]);
void ausgabe(int obergrenze, int zahlen[]);
void strich (int n, char c); /* gibt n mal das Zeichen c aus  */


#endif
