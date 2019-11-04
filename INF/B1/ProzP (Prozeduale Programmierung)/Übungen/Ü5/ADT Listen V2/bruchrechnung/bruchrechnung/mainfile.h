#ifndef _MAINFILE_H_
#define _MAINFILE_H_
#define NAMENLAENGE 30

typedef struct s_element 
{
	char name[NAMENLAENGE];
	int listenstelle;
	struct s_element *next;
}	t_element;

typedef struct s_Listenkopf
{
	t_element *ErstesElement;
	t_element *LetzteElement;
	int anzahlElemente;
}	t_Listenkopf;

void InitialisierungListenkopf(t_Listenkopf *a);

void EinfuegenInListe(t_Listenkopf *b);

int NameAufVerfuegbarkeitPruefen(char c[], t_Listenkopf *d);

int PositionNachfrage(t_Listenkopf *f);

void NameInDieListeEinfuegen(int j, t_element *k, t_Listenkopf *l);


#endif
