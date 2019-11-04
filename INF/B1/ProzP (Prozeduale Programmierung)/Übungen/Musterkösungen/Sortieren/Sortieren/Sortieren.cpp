// Sortieren.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"
#include <stdlib.h>
#include <time.h>

#define N 10

int arr[N];

int _tmain(int argc, _TCHAR* argv[])
{
	int i, zufall;
	
	srand((unsigned int)time(NULL));

	for(i = 0; i < N; i++)
	{
		zufall = rand();
		//printf("%i \n", zufall);
		arr[i] = zufall;
	}

	quicksort(arr, 0, N-1);
	quicksort(arr, 0, N-1);

	printf("\nSortiert:\n"); 

	for(i=0; i < N; i++)
	{
	//	printf("%i \n", arr[i]);
	}

	system("pause");

	return 0;
}

void quicksort(int a[], int anfang, int ende)
{
	int teiler;

	if(anfang < ende)
	{
		teiler = teile(a, anfang, ende);
		quicksort(a, anfang, teiler-1);
		quicksort(a, teiler+1, ende);
	}
}

int teile(int a[], int anfang, int ende)
{
	int i, j, pivot;
	
	i = anfang;
	j = ende  ; //statt ende-1

	{	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		int zufall;
		int tmp;
			
		zufall = anfang + (rand() % (ende - anfang + 1));
		
		tmp = a[zufall];
		a[zufall] = a[ende];
		a[ende] = tmp;
	}	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	pivot = a[ende]; 

	do
	{
		while(a[i] <= pivot && i < ende)
			i++;		

		while(a[j] >= pivot && j > anfang)
			j--;
		
		if(i < j)
		{
			int tmp;
			tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}

	}
	while(i < j);
		
	if(a[i] > pivot)
	{
		int tmp;
		tmp = a[i];
		a[i] = a[ende];
		a[ende] = tmp;
	}
	
	return i;
}
