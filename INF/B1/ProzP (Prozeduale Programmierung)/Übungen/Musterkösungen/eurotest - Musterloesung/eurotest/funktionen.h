#ifndef _EURO_H_
#define _EURO_H_

#define SNLEN 12

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
int quersumme(const char *sn);
t_errcode eurotest(const char *sn);
void ausgabe(t_errcode r);
void strich (int n, char c); /* gibt n mal das Zeichen c aus  */


#endif
