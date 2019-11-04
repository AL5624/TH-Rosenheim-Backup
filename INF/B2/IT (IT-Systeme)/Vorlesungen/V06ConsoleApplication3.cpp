// ConsoleApplication3.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"

#define N 5

int f(int *a, int n) {
	int summe, i;

	//summe = 0;
	__asm mov DWORD PTR [summe], 0

	//for (i = 0; i<n; i++)
	__asm {
		mov DWORD PTR[i], 0
		jmp Bedingung_f
Rumpf_f:
	}
		//summe = summe + a[i];
		__asm {
			mov EAX, DWORD PTR[i]
			mov EBX, DWORD PTR[a]
			mov EAX, DWORD PTR [EBX+4*EAX]
			add DWORD PTR [summe], EAX
		}
	__asm {
		inc DWORD PTR [i]
Bedingung_f:
		mov EAX, DWORD PTR[i]
		cmp EAX, DWORD PTR [n]
		jl Rumpf_f
	}
	
	return summe;
}

void g(int a[N][N]) {
	int i, j;

	for (i = 0; i<N; i++)
		for (j = 0; j < N; j++) {
			//if (i == j)
			__asm {
				mov EAX, DWORD PTR[i]
					cmp EAX, DWORD PTR[j]
					jne Else_g
			}
			//a[i][j] = 1; //a[i][j] entspricht a + i*N*4 + j*4
							//					a + (i*N +j)*4
			__asm {
				mov EAX, DWORD PTR[a]
					mov EBX, DWORD PTR[i]
					imul EBX, N
					add EBX, DWORD PTR[j]
					mov DWORD PTR[EAX + 4 * EBX], 1
			}
			__asm {
				jmp Ende_if_g
					Else_g :
			}
			//else
				//a[i][j] = 0;
			__asm {
				mov EAX, DWORD PTR[a]
					mov EBX, DWORD PTR[i]
					imul EBX, N
					add EBX, DWORD PTR[j]
					mov DWORD PTR[EAX + 4 * EBX], 0
			}
			__asm {
Ende_if_g:
			}
		}
}

int my_strlen(char * s) {
	int laenge;

	//laenge = 0;
	__asm mov DWORD PTR [laenge], 0
	while (s[laenge++]);
	__asm {
		mov EAX, DWORD PTR [s]
		mov EBX, EAX
		jmp Bedingung_my_strlen
Anfang_my_strlen:
Bedingung_my_strlen:
		cmp BYTE PTR [EBX], 0
		jz Ende_my_strlen
		inc EBX
		jmp Anfang_my_strlen
Ende_my_strlen:
		sub EBX, EAX
		mov DWORD PTR [laenge], EBX
	}


	return laenge;
}

int main()
{
	int a[] = { 1,2,3,4,5 };
	int b[N][N];

	f(a, N);
	g(b);
	my_strlen("Hallo, Rosenheim");

    return 0;
}

