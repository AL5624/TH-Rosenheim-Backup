//*********************************************************************
// Source File : tt_liste01.c
// Testtreiber für ADT liste (einfach verkettete Liste)
// Verfasser   : R.Feindor / R.Hüttl
// Datum       : 18.02.2008
// Änderungen  : 21.12.2010 J. Schmidt
//*********************************************************************
#include "liste01.h"
#include <time.h>

#define  N 10  // Anzahl der Elemente

#define TEST     // falls man zusehen möchte

char testListe[N][LAENGE];   // globale Testdaten N Elemente der Standard-LAENGE

// Generieren von zufälligen globalen Testdaten
void GenTestdaten(void) 
{	
	int i, z;
	srand((int) time(0)); // Start des Zufallsgenerators
	for (i=0; i < N; i++)
	{
		z = rand() % (10*N);    // Zufallszahl 0..10N
		sprintf (testListe[i], "Testelement %5i", z); // füllen Elemente in testListe
	}
}

void testTeil1 (t_Listenkopf *li)
{
	int i;
	char s[LAENGE];

	// Testfall 1:
	printf("Testfall 1: push und print\n");
	for (i=0; i<N; i++)
		pushFront (li, testListe[i]);

	printListe (li);
	Assert (li->anzahlElemente == N, "falsche Elementezahl");
#ifdef TEST
	system("pause");
#endif

	// Testfall 2
	printf("Testfall 2: pop einzeln\n");

	for (i=0; i< N; i++)
	{
		popFront(li, s);
		printf("pop: %s Rest: \n",s);
		Assert (strcmp(s, testListe[N-i-1])==0, "falsche Reihenfolge");// prüfen Folge
		Assert (li->anzahlElemente == (N-i-1), "falsche Elementezahl");//Anzahl
		printListe (li);
#ifdef TEST
		system("pause");
#endif
	}
	Assert (li->anzahlElemente == 0, "falsche Elementezahl");
}

void testTeil2 (t_Listenkopf *li)
{
	int i;
	char s[LAENGE];
	// Testfall 3:
	printf("Testfall 3: anfuegen und zeigen\n");
	for (i=0; i<N; i++)
		pushBack (li, testListe[i]);

	printListe (li);
	Assert (li->anzahlElemente == N, "falsche Elementezahl");
#ifdef TEST
	system("pause");
#endif

	// Testfall 4
	printf("Testfall 4: pop einzeln\n");
	for (i=0; i< N; i++)
	{
		popFront(li, s);
		printf("pop: %s  Rest: \n",s);
		Assert (strcmp(s, testListe[i])==0, "falsche Reihenfolge");//prüfen Folge
		Assert (li->anzahlElemente == (N-i-1), "falsche Elementezahl");//Anzahl
		printListe (li);
#ifdef TEST
		system("pause");
#endif
	}
	Assert (li->anzahlElemente == 0, "falsche Elementezahl");
}

int main (void)
{
	t_Listenkopf liste;

	GenTestdaten();
	initListe(&liste);
	printf("Testtreiber beginnt mit leerer Liste:\n");
	printListe(&liste); 
	testTeil1(&liste);   
	testTeil2(&liste); 
	printf("Testtreiber erfolgreich beendet.");

	return (0);
}
