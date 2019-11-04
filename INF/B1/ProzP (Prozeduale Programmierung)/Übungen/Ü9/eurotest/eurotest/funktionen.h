#ifndef _EURO_H_
#define _EURO_H_


typedef enum 
{
	ec_ok,				/* Seriennummer g�ltig */
	ec_pz_falsch,		/* Pr�fziffer falsch */
						/* (also: Rest bei Division durch 9 ungleich Null)*/
	ec_zukurz,			/* Seriennummer zu kurz */
	ec_zulang,			/* Seriennummer zu lang */
	ec_LCfalsch,		/* L�ndercode ist kein Gro�buchstabe */
	ec_SNkeineZiffer	/* 10-stellige Seriennummer oder */
						/* Pr�fziffer enth�lt Zeichen, */
						/* die nicht Ziffern sind */
} t_errcode;


/* Prototypen der ben�tigten Funktionen */
void strich (int n, char c); /* gibt n mal das Zeichen c aus  */

int quersumme(char seriennummer[]);

t_errcode eurotest(char srnnmr[]);

int IsUpper(char c);

int IsNumber(char n);

int testaufzahl(char seriennum[]);

int testobgrossbuchstabe(char n[]);

void testauflaenge(char k[]);

void abfrage();

#endif
