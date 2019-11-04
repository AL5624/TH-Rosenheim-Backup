// ConsoleApplication7.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"

#define ANZ_ZEICHEN 20
#define ANZ_EINTRAEGE 100
struct Stadt_s {
	char name[ANZ_ZEICHEN];
	char land[3];
	long einwohner;
}; 

struct Stadt_s staedte_liste[ANZ_EINTRAEGE] = {
	{"Rosenheim", "BY", 60000},
	{"München", "D", 200000}
};

void ausgebenStadt(const struct Stadt_s *stadt) {
	printf("Stadt: %s - Land: %s AnzEinwohner: %ld\n", (*stadt).name, stadt->land, stadt->einwohner);
}

int main()
{
	ausgebenStadt(staedte_liste);
	ausgebenStadt(staedte_liste+1);
	ausgebenStadt(&staedte_liste[0]);
	ausgebenStadt(&staedte_liste[1]);
	/*long zahlen[10];

	zahlen[3] = 5;
	*((char *)zahlen + 8) = 7;
	*((long *)((char *)zahlen + 8)) = 9;
	*/
    return 0;
}

