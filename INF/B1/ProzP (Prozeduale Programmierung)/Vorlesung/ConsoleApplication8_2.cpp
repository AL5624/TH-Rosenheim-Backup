// ConsoleApplication8.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"
#include <string.h>
#include <stdarg.h>

void myprintf(char * fmt, ...) {
	va_list argptr;

	va_start(argptr, fmt);

	
	printf("%s#\n", fmt);
	printf("%lf#\n", va_arg(argptr, double));
	printf("%s#\n", va_arg(argptr, char *));
	printf("%d\n", va_arg(argptr, int));


	return;
}

char * mystrcpy(char * ziel, char * quelle) {

	do {
		*ziel++ = *quelle++;
		//ziel++;
		//quelle++;
	} while (*(quelle-1) != '\0');

	return ziel;
}

int main()
{
	
	char text[9] = "Hallo!";
	char * cp = NULL;

	cp = mystrcpy(text, "GutenTag");

	myprintf(" %lf %s %d", 5.0, "geht über zwei Zeilen\n", 7);

    return 0;
}

