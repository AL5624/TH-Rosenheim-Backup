//*********************************************************************
// Header File : liste01.h
// ADT für Verwaltung einer einfach verketteten Liste
// Verfasser   : R.Feindor / R.Hüttl
// Datum       : 18.02.2008
// Änderungen  : 21.12.2010 J. Schmidt
//*********************************************************************

#ifndef LISTE01_H
#define LISTE01_H

#include "inf1.h"
#include <string.h>

#define LAENGE 80
#define NULLSTRING ""

typedef struct t_element
{ 
   char inhalt[LAENGE];       // Inhalt als String fester Länge 
   struct t_element *next;    // Zeiger auf den Nachfolger 
} t_element;

typedef struct  
{
   struct t_element *erstesElement;
   struct t_element *letztesElement;
   int anzahlElemente;
} t_Listenkopf;


// besetzt li vor (NULL-Pointer, anzahlZeilen=0 )
void initListe (t_Listenkopf *li);

// erzeugt ein neues Element und fuegt ese vorne ein
void pushFront(t_Listenkopf *li, const char *s);

// gibt die Liste li am Bildschirm aus
void printListe(const t_Listenkopf *li);

// erzeugt ein neues Element und haengt es hinten an
void pushBack(t_Listenkopf *li, const char *s);

// gibt das erste Element zurück und entfernt es aus li
void popFront(t_Listenkopf *li, char *s);


#endif // LISTE01_H

