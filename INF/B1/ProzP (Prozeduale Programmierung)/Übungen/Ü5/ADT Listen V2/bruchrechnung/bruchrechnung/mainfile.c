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
	t_Listenkopf *listenkopfpointer;
	listenkopfpointer = (t_Listenkopf *)malloc(sizeof(t_Listenkopf));

	InitialisierungListenkopf(listenkopfpointer);

	EinfuegenInListe(listenkopfpointer);



	system("pause");
	return(0);
}

void InitialisierungListenkopf(t_Listenkopf *a)
{
	a->ErstesElement = 0;
	a->LetzteElement = 0;
	a->anzahlElemente = 0;
}

void EinfuegenInListe(t_Listenkopf *b)
{
	t_element *elementpointer;
	elementpointer = (t_element *)malloc(sizeof(t_element));

	char name[NAMENLAENGE];

	printf("Bitte Name eingeben: ");
	scanf("%s", name);

	int vonv = 1; //verfügbar(1) oder nicht verfügbar(0)
	vonv = NameAufVerfuegbarkeitPruefen(name, b);

	while (vonv == 0)
	{
		printf("\nBitte geben Sie einene anderen Namen ein: ");
		scanf("%s", name);
		vonv = NameAufVerfuegbarkeitPruefen(name, b);
	}

	strcpy(elementpointer->name, name);

	if (b->anzahlElemente == 0)
	{
		b->ErstesElement = elementpointer;
		b->LetzteElement = elementpointer;
		b->anzahlElemente = 1;
		elementpointer->next = b->LetzteElement;
		elementpointer->listenstelle = 1;
	}
	else
	{
		int h = PositionNachfrage(b); // Position des Namens
		elementpointer->listenstelle = h;

	}
	
}

int NameAufVerfuegbarkeitPruefen(char c[], t_Listenkopf *d)
{
	t_element *e;
	e = (t_element *)malloc(sizeof(t_element));

	e = d->ErstesElement;

	for (int laufvariable = 0; laufvariable < d->anzahlElemente; laufvariable++)
	{
		if (strcmp(c, e->name) == 0)
		{
			printf("\nName bereits Vergeben.");
			
			return 0;
		}
		else
		{
			e = e->next;
		}
	}
	return 1;
}

int PositionNachfrage(t_Listenkopf *f)
{
	int g;

	printf("\nWelche Postition soll der Name in der Liste haben? \n");
	scanf("%d", g);


	return g;
}

void NameInDieListeEinfuegen(int j, t_element *k, t_Listenkopf *l)
{
	for (j; j < l->anzahlElemente; j++)
	{

	}
}