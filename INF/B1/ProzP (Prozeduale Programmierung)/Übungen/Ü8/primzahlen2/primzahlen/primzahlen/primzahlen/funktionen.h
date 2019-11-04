#ifndef _PRIM_H_
#define _PRIM_H_
#define OG 1000
#define ZEILENUMBRUCH 10
/* Definition globale Konstanten  */
#define NICHT_PRIM 0
#define PRIM  1


/* Prototypen der benötigten Funktionen */
void strich (int n, char c); /* gibt n mal das Zeichen c aus  */

int sieb(int *zahlenfeld, int obergrenze);

void ausgabe(int* zahlenfeld2, int obergrenze2);
#endif
