// ConsoleApplication10.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"
#include "Header.h"
#include <malloc.h>

element * kopf = NULL;

int main()
{
	int eingabe;
	int n;

	do {
		printf("Was moechten sie tun:\n");
		printf("0 - fertig\n");
		printf("1 - einfuegen\n");
		scanf("%d", &eingabe);
		switch (eingabe) {
		case 1:
			printf("Was soll eingefuegt werden?\n");
			scanf("%d", &n);
			einfuegen(&kopf, n);
		}
	} while (eingabe != 0);
    return 0;
}

void einfuegen(element ** ppe, int n) {
	element * tmp;

	if (*ppe == NULL)//Fall 1
	{
		tmp = (element *)malloc(sizeof(element)); //Schritt 1
		if (tmp == NULL)
			return; // hier sollte Fehlerbehandlung erfolgen
		tmp->wert = n; //Schritt 2
		tmp->next = *ppe; //Schritt 3
		*ppe = tmp; //Schritt 4
	}
	else
		if ((**ppe).wert < n)//Fall 2.1
		{
			einfuegen(&((**ppe).next), n);
		}
		else //Fall 2.2
		{
			tmp = (element *)malloc(sizeof(element)); //Schritt 1
			if (tmp == NULL)
				return; // hier sollte Fehlerbehandlung erfolgen
			tmp->wert = n; //Schritt 2
			tmp->next = *ppe; //Schritt 3
			*ppe = tmp; //Schritt 4
		}

}

void streichen(element ** ppe, int n) {

}

void suchen(element * pe, int n) {

}

void ausgeben(element * pe) {

}
