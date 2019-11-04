#ifndef _MAINFILE_H_
#define _MAINFILE_H_
#define LAENGE 80
/* Prototypen der benötigten Funktionen */

typedef struct s_element
{
	char inhalt[LAENGE];
	struct s_element *next;
}	t_element;

typedef struct s_Listenkopf
{
	t_element *erstesElement;
	t_element *letztesElement;
	int anzahlElemente;
}	t_Listenkopf;

void Einfuegen(t_Listenkopf *a /*Lcursor*/, t_element *b/*Ecursor*/);

void initListe(t_Listenkopf *li);

void pushFront(t_Listenkopf *li, t_element *s);

void pushBack(t_Listenkopf *li, t_element *s);

void ListeAusgeben(t_Listenkopf *li);

void Abfrage(t_Listenkopf *li, t_element *s);

void textEinfuegen(t_Listenkopf *a, t_element *b);

void delete(t_Listenkopf *li);



#endif
