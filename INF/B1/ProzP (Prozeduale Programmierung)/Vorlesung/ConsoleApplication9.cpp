// ConsoleApplication9.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"
#include <malloc.h>


int main()
{
	int * iptr;
	int i;

	iptr = (int *) malloc(10 * sizeof(int));
	for (i = 0; i < 10; i++)
		iptr[i] = i; // *(iptr+i) = i;
	
	iptr = (int *)realloc(iptr, 10 * 2 * sizeof(int));
	for (i = 10; i < 20; i++)
		iptr[i] = i; // *(iptr+i) = i;

	free(iptr);
	iptr = NULL;
	

    return 0;
}

