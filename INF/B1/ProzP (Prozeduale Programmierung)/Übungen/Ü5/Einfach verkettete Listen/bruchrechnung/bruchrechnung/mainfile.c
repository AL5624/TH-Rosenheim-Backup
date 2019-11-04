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


	struct element_s *anfang = NULL, *cursor = NULL;
	char name[20] = { 0 };

	printf("Name eingeben: ");
	while (strlen(name) != 1)
	{
		fgets(name, 20, stdin);

		/*if (strlen(name) == 1)
		{
			break;
		}
		*/
		cursor = (struct element_s*)malloc(sizeof(struct element_s));

		if (cursor == NULL)
		{
			printf("Speicherplatzmangel\n");
			exit(1);
		}

		strcpy(cursor->name, name);

		cursor->next = anfang;
		anfang = cursor;

	}

	printf(".....umgekehrt :\n");

	while (cursor != NULL)
	{
		printf("%s", cursor->name);
		cursor = cursor->next;
	}
	
	system("pause");
	return(0);
}

