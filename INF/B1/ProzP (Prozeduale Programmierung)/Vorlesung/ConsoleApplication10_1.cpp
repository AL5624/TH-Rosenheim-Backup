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
	int i;

	for (i = 0; i < 10; i++)
		einfuegen( &kopf, i * 3);

	do {
		printf("Was moechten sie tun:\n");
		printf("0 - fertig\n");
		printf("1 - einfuegen\n");
		printf("2 - ausgeben\n");
		printf("3 - rekursiv ausgeben\n");
		printf("4 - suchen\n");
		printf("5 - streichen\n");
		scanf("%d", &eingabe);
		switch (eingabe) {
		case 1:
			printf("Was soll eingefuegt werden?\n");
			scanf("%d", &n);
			einfuegen(&kopf, n);
			break;
		case 2:
			ausgeben(kopf);
			break;
		case 3:
			ausgeben_rekursiv(kopf);
			break;
		case 4:
			printf("Was soll gesucht werden?\n");
			scanf("%d", &n);
			suchen(kopf, n);
			break;
		case 5:
			printf("Was soll gestrichen werden?\n");
			scanf("%d", &n);
			streichen(&kopf, n);
			break;
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
	if (*ppe == NULL || (*ppe)->wert > n) { //Fall 1
		printf("%d wurde nicht gefunden und nicht gestrichen\n", n);
		return;
	}
	if ((*ppe)->wert == n) { //Fall 2
		element * tmp;

		tmp = *ppe;
		*ppe = (*ppe)->next;
		free(tmp);

		return;
	}
	//Fall 3
	streichen(&((*ppe)->next), n);
}

void suchen(element * pe, int n) {
	do {
		if (pe == NULL || pe->wert > n) {
			printf("%d wurde nicht in der Liste gefunden\n", n);
			return;
		}
		// hier ist pe != NULL
		if (n == pe->wert) {
			printf("%d wurde in der Liste gefunden\n", n);
			return;
		}
		// hier ist pe != NULL und pe->wert != n
		pe = pe->next;
	} while (1);
}

void ausgeben(element * pe) {
	do {
		if (pe != NULL) { //Fall 1
			printf("%d\n", (*pe).wert);
			pe = (*pe).next; 
		}
		else
			printf("Ende der Liste\n"); //Fall 2
	} while (pe != NULL);
}

void ausgeben_rekursiv(element * pe) {
		if (pe != NULL) { //Fall 1
			printf("%d\n", (*pe).wert);
			ausgeben_rekursiv((*pe).next);
		}
		else
			printf("Ende der Liste\n"); //Fall 2
}
