//*******************************************************************
// Programm    : strings
// Verfasser   : H�ttl/ Feindor / Schmidt
// �bungsbeispiel f�r:
//               Kommandozeilen Argumente und
//               dynamische Speicherverwaltung
// Verarbeitung: Kommandozeilen-Argumente werden ausgewertet
//               umkopiert und in der Reihenfolge vertauscht.
// Datum       : 1.11.2002
// letzte �nderungen  : 4.12.2012
//*******************************************************************

#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <string.h>  // f�r String-Operationen     
#include <stdlib.h>

#define ANZ_BUCHST 256  // Gr��e Feld f�r Statistik
#define ZEICHENZAHL 13  // Anzahl Zeichen je Zeile in der Ausgabe

// gibt einen Text ((Vektor von Zeichenketten) mit nrWords Worten aus
void printText(char *p_text[], int nrWords);

// Invertiert in einem Text (Vektor von Zeichenketten) die Reihenfolge
// der W�rter; die Invertierung erfolgt ausschliesslich durch Vertauschung
// von Zeigern, nicht durch umkopieren der W�rter !!
void textReverse(char *p_text[], int nrWords);

// gibt den Speicherplatz f�r die W�rter des Textes wieder frei
void deleteText(char *p_text[], int nrWords);

void statistik(int argc, char *argv[]);
void reverse1(int argc, char *argv[]);
void reverse2(int argc, char *argv[]);


int main (int argc, char *argv[]) 
{
	statistik(argc, argv);

	reverse1(argc, argv);

	reverse2(argc, argv);

	system("pause");

	return EXIT_SUCCESS;
}


void statistik(int argc, char *argv[])
{
	int i, j, len;
	int buchstaben[ANZ_BUCHST];

	printf("Aufgabe 1\n");

	printText(argv, argc);      // gibt Kommandozeile aus

	// Initialisierung des Felds f�r die H�ufigkeit der Zeichen
	for (i = 0; i < ANZ_BUCHST; i++)
		buchstaben[i] = 0;

	// Lesen der Kommandozeilen Argumente und Z�hlen der einzelnen Zeichen
	for (i = 1; i < argc; i++)                  // f�r jeden Parameter
	{
		len = (int)strlen(argv[i]);
		for (j = 0; j < len; j++)     // f�r jedes Zeichen
			buchstaben[argv[i][j]]++;  
	}

	// Ausgabe des Ergebnisses  (mehrzeilig)
	printf("\nAnzahl der Buchstaben der Parameter: \n");
	j = 0;
	for (i = 0; i < ANZ_BUCHST; i++)       // zwei Zeilen
	{
		if (buchstaben[i] > 0)
		{
			printf("%c: %i ", i, buchstaben[i]);
			j++;
			if ( (j % ZEICHENZAHL) == (ZEICHENZAHL-1) ) printf("\n"); // Zeilenumbruch nach ZEICHENZAHL Zeichen
		}
	}
	printf("\n");

}

// Umdrehen der Parameter - Variante mit strcpy
void reverse1(int argc, char *argv[])
{
	char** p_text;   // Zeiger auf Array mit Zeichenketten 
	int i;

	printf("\nAufgabe 2 - Variante 1\n");

	// Reservierung von Speicherplatz f�r ein Feld von Zeichenketten
	p_text = (char **) malloc(sizeof(char*) * (argc-1));
	if(p_text == NULL)
	{
		printf("Fehler - kein Speicher\n");
		exit(EXIT_FAILURE);
	}

	// Kopieren der Kommandozeilen Argumente in das Feld
	for (i = 1; i < argc; i++)
	{
		p_text[i-1] = (char *) malloc(sizeof(char) * (strlen(argv[i])+1)); // Speicher f�r Text, +1 hier n�tig wegen "Terminierungsbyte"
		if(p_text[i-1] == NULL)
		{
			printf("Fehler - kein Speicher\n");
			exit(EXIT_FAILURE);
		}
		strcpy(p_text[i-1], argv[i]);                          // Kopieren
	}

	printText(p_text, argc-1);   // Ausgabe des Vektors in korrekter Reihenfolge
	textReverse(p_text, argc-1); // Umkehrung des Vektors
	printText(p_text, argc-1);   // Ausgabe der Vektors in umgekehrter Reihenfolge

	deleteText(p_text, argc-1);  // Speicher freigeben f�r die W�rter
	p_text = NULL;
}


// Umdrehen der Parameter - Variante mit Zeigern auf argv
void reverse2(int argc, char *argv[])
{
	char** p_text;   // Zeiger auf Array mit Zeichenketten 
	int i;

	printf("\nAufgabe 2 - Variante 2\n");

	// Reservierung von Speicherplatz f�r ein Feld von Zeichenketten
	p_text = (char **) malloc(sizeof(char*) * (argc-1));
	if(p_text == NULL)
	{
		printf("Fehler - kein Speicher\n");
		exit(EXIT_FAILURE);
	}

	// Kopieren der Zeiger auf argv in das Feld
	for (i = 1; i < argc; i++)
	{
		p_text[i-1] = argv[i];
	}

	printText(p_text, argc-1);   // Ausgabe des Vektors in korrekter Reihenfolge
	textReverse(p_text, argc-1); // Umkehrung des Vektors
	printText(p_text, argc-1);   // Ausgabe der Vektors in umgekehrter Reihenfolge

	// deleteText gibt hier nicht viel Sinn
	if(p_text != NULL)
		free(p_text);                // Speicher freigeben f�r Feld von Zeigern
}


// gibt einen Text ((Feld von Zeichenketten) mit nrWords Worten aus
void printText(char *p_text[], int nrWords)
{
	int i;

	for (i = 0; i < nrWords; i++)
		printf("%s ", p_text[i]);
	printf("\n");
}

// Invertiert in einem Text (Feld von Zeichenketten) die Reihenfolge
// der W�rter; die Invertierung erfolgt ausschliesslich durch Vertauschung
// von Zeigern, nicht durch umkopieren der W�rter !
void textReverse(char *p_text[], int nrWords)
{
	char *hilfsPtr;
	int i;

	for (i = 0; i < nrWords/2; i++)
	{
		hilfsPtr = p_text[i];
		p_text[i] = p_text[nrWords - 1 - i];
		p_text[nrWords - 1 - i] = hilfsPtr;
	}
}

// gibt den Speicherplatz f�r die W�rter des Textes wieder frei
void deleteText(char *p_text[], int nrWords)
{
	int i;

	if(p_text != NULL)
	{

		for (i = 0; i < nrWords; i++)
		{
			if(p_text[i] != NULL)
			{
				free(p_text[i]);
				p_text[i] = NULL;
			}
		}
		free(p_text);                // Speicher freigeben f�r Feld von Zeigern
	}
}
