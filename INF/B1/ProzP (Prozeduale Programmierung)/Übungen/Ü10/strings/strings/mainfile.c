#include <stdio.h>  /* Standard Input/Output  z.B. scanf, printf */
#include <string.h>

void printText(char *text[], int nrWords)
{

	for (int i = 1; i < nrWords; i++)
		printf("%s ", text[i]);

}

void BuchstabenReverse(char *text2[], int nrWords2)
{
	for (int k = 1; k < nrWords2; k++)
		for (int j = 0, g = 1; j < (strlen(*(text2 + k)) - g); g++, j++)
		{
			char t = *(*(text2 + k) + j);
			*(*(text2 + k) + j) = *(*(text2 + k) + (strlen(*(text2 + k)) - g));
			*(*(text2 + k) + (strlen(*(text2 + k)) - g)) = t;
		}
	for (int a = 1; a < nrWords2; a++)
		printf("%s ", text2[a]);

}

void textReverse(char *text3[], int nrWords3)
{
	for (int u = 1; u < (nrWords3 - u); u++)
	{
		char *o = *(text3 + u);
		*(text3 + u) = *(text3 + nrWords3 - u);
		*(text3 + nrWords3 - u) = o;
	}
	for (int x = 1; x < nrWords3; x++)
		printf("%s ", text3[x]);
}

void strich(int n, char c) // gibt n mal das Zeichen c aus
{
	int i;
	for (i = 1; i <= n; i++)
		printf("%c", c);
	printf("\n");
}

int main(int argc, char *argv[])
{

	strich(23, '-');
	printf("Statistik Kommandozeile\n");
	strich(23, '-');

	//char *statistik = (char*) calloc(256, sizeof(char));
	char statistik[256] = { 0 };



	int i, stringstelle, stringfeld;

	for (i = 1; i < argc; i++)
		printf("%s ", argv[i]);

	printf("\nAnzahl der Buchstaben der Parameter:\n");

	for (stringfeld = 1; stringfeld < argc; stringfeld++)
	{
		for (stringstelle = 0; stringstelle <= (int)strlen(*(argv + stringfeld)); stringstelle++)
		{
			statistik[(int)*(*(argv + stringfeld) + stringstelle)] += 1;
		}
	}

	for (int k = 1; k <= 255; k++)
	{
		char c;
		if (statistik[k] != 0)
		{
			c = (char)k;
			printf("%c: %d ", c, statistik[k]);
		}
	}


	/*int op = strlen(statistik);
	printf("\nStatistiklaenge: %d", op);
	/*
	stringfeld = 1;
	stringstelle = 0;
	statistik[(int)*(*(argv + stringfeld) + stringstelle)] += 1;
	printf("%d", statistik[(int)*(*(argv + stringfeld) + stringstelle) + 1]);
	*/



	printf("\n\nAufgabe 2\n\n");

	// Aufgabe 2


	printText(argv, argc);

	printf("\n");

	BuchstabenReverse(argv, argc);

	printf("\n");

	textReverse(argv, argc);
	



	//printf("\n%d\n", argc);


}



