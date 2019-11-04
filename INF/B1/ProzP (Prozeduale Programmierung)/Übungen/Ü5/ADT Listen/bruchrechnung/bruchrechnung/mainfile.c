/*******************************************************************
/ Programm    : Bruchrechnung                                         
/ Verfasser   : Schmidt                                           
/ Datum       : Urprogramm: 24.10.2012                                          
/ Eingabe     : 2 rationale Zahlen                          
/ Verarbeitung: diverse Berechnungen                   
/ Änderungen  : 24.10.2012
/ *******************************************************************/

/* Einbinden von nötigen Header-Dateien                             */
#include <stdio.h>    /* Standard Input/ Output  z.B. scanf, printf */
#include <stdlib.h>   /* Standard-Bibliothek, z.B. für system       */
#include "mainfile.h"
#include <string.h>


int main()
{
	t_Listenkopf *Lcursor;
	t_element *Ecursor;

	Ecursor = (t_element *)malloc(sizeof(t_element));
	Lcursor = (t_Listenkopf *)malloc(sizeof(t_Listenkopf));

	initListe(Lcursor);

	Einfuegen(Lcursor, Ecursor);
	
	Abfrage(Lcursor, Ecursor);



	system("pause");
	return(0);
}

void Abfrage(t_Listenkopf *li, t_element *s)
{
	printf("\n\nBitte Waehlen Sie: ");
	printf("\n1. Liste ausgeben");
	printf("\n2. Neuen Namen einfuegen");
	printf("\n3. Programm beenden");
	printf("\n4. erstes Element löschen");
	printf("\nBitte Nummer eingeben: ");
	int n = 3;
	scanf("%d", &n);
	if (n < 1 || n > 4)
	{
		printf("\nBitte gueltige Nummer eingeben");
		Abfrage(li, s);
	}
	
	switch (n)
	{
	case 1: ListeAusgeben(li);
		Abfrage(li, s);
		break;
	case 2: Einfuegen(li, s);
		Abfrage(li, s);
		break;
	case 3: break;
	case 4: delete(li);
		Abfrage(li, s);
		break;
	}

	
}

void delete(t_Listenkopf *li)
{
	t_element *p;
	p = li->erstesElement;
	if (p != NULL && li->anzahlElemente > 0)
	{
		if (li->erstesElement != li->letztesElement)
		{
			li->erstesElement = p->next;
		}
		free(p);
		li->anzahlElemente -= 1;
		if (li->anzahlElemente < 0)
		{
			li->anzahlElemente = 0;
		}
	}
}

void Einfuegen(t_Listenkopf *a /*Lcursor*/, t_element *b/*Ecursor*/)
{
	char name[LAENGE];

	printf("\nBitte Name eingeben: ");
	scanf("%s", &name);

	

	if (a->anzahlElemente == 0)
	{
		strcpy(b->inhalt, name);
		a->erstesElement = b;
		b->next = a->letztesElement;
		a->letztesElement = a->erstesElement;
		a->anzahlElemente = 1;
	}

	else
	{
		b = (t_element *)malloc(sizeof(t_element));
		strcpy(b->inhalt, name);
		textEinfuegen(a, b);
	}

}

void textEinfuegen(t_Listenkopf *a, t_element *b)
{
	int n = 1;
	printf("\nWaehlen Sie: ");
	printf("\n1. Den Namen an den Anfang setzen");
	printf("\n2. Den Namen an das Ende setzen");
	printf("\nBitte Nummer eingeben: ");
	scanf("%d", &n);
	if (n < 1 || n > 2)
	{
		printf("\nBitte gueltige Nummer eingeben\n");
		textEinfuegen(a, b);
	}

	switch (n)
	{
	case 1: pushFront(a, b);
		break;
	case 2: pushBack(a, b);
		break;
	}
}

void initListe(t_Listenkopf *li)
{
	li->erstesElement = 0;
	li->letztesElement = 0;
	li->anzahlElemente = 0;
}

void pushFront(t_Listenkopf *li, t_element *s)
{
	/*t_element *p;

	p = (t_element *)malloc(sizeof(t_element));
	
	strcpy(p->inhalt, s);
	
	p->next = li->erstesElement;

	li->erstesElement = p;

	li->anzahlElemente += 1;*/

	
	s->next = li->erstesElement;

	li->erstesElement = s;
	
	li->anzahlElemente += 1;

	if (li->letztesElement == NULL)
	{
		li->letztesElement == li->erstesElement;
	}
}
void pushBack(t_Listenkopf *li, t_element *s)
{
	s->next = li->letztesElement->next;

	li->letztesElement->next = s;

	li->letztesElement = s;

	if (li->erstesElement == NULL)
	{
		li->erstesElement = li->letztesElement;
	}

	li->anzahlElemente += 1;
}

void ListeAusgeben(t_Listenkopf *li)
{
	t_element *s;
	s = (t_element *)malloc(sizeof(t_element));
	s = li->erstesElement;

	printf("\n");

	for(int i = 0; i < li->anzahlElemente; i++)
	{
		printf("%s\n", s->inhalt);

		s = s->next;
	}
}
