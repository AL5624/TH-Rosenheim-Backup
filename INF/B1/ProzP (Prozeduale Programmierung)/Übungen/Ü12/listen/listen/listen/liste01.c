//*********************************************************************
// Source File : liste01.c
// ADT für Verwaltung einer einfach verketteten Liste
// Verfasser   : R.Feindor / R.Hüttl
// Datum       : 18.02.2008
// Änderungen  : 21.12.2010 J. Schmidt
//*********************************************************************
#include "liste01.h"

// gibt die Liste li am Bildschirm aus
void printListe(const t_Listenkopf *li)
{
	t_element *p;
	p = li->erstesElement;

	while (p != NULL)
	{
		printf("%s\n", p->inhalt);
		p = p->next;
	}

	/*for (int i = 0; i < li->anzahlElemente; i++)
	{
		printf("%s\n", p->inhalt);
		p = p->next;
	}*/
}

// erzeugt ein neues Element und haengt es hinten an
void pushBack(t_Listenkopf *li, const char *s)
{
}

// gibt das erste Element zurück und entfernt es aus li
void popFront(t_Listenkopf *li, char *s)
{
	t_element *p;
	p = li->erstesElement;
	li->erstesElement = p->next;
	free(p);
}

// besetzt li vor (NULL-Pointer, anzahlZeilen=0 )
void initListe (t_Listenkopf *li)
{
   li->erstesElement = NULL;
   li->letztesElement = NULL;
   li->anzahlElemente = 0;
}

// erzeugt ein neues Element und fuegt es vorne ein
void pushFront(t_Listenkopf *li, const char *s)
{
   t_element *p;                                     // pointer
   p = (t_element*) malloc(sizeof(t_element));       // Speicher anfordern
   Assert(p != NULL, "kein Speicher bei malloc");     // prüfen 
   strcpy (p->inhalt, s);                            // Element füllen
   p->next = NULL;

   // vorn einfügen:
   p->next = li->erstesElement;
   li->erstesElement = p;

   if (li->letztesElement == NULL) // wenn Liste bisher leer, sonst bleibt das letzte Element
      li->letztesElement = p;

   li->anzahlElemente++;
 }

