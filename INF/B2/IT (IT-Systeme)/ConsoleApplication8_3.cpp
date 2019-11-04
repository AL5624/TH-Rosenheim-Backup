// ConsoleApplication8.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"

int i, j;
int a[5][7];

int main()
{
	int b[5][7] = { 0 };

	i = 3;
	j = 2;

	a[i][j] = 17;
	b[i][j] = 17;

    return 0;
}

__declspec (naked) void lokarray(void) {
	__asm {
		push ebx
			push esi
			push edi
			push ebp
			mov ebp, esp
			sub esp, 140

			mov eax, DWORD PTR [i]	//eax = i
			mov ebx, DWORD PTR[j]	//ebx = j
			imul eax, 7*4			//eax =i*7*4
			lea eax, [eax+ebx*4]	//eax = i*7*4 + j*4
			lea eax, [ebp - 140 +eax]//eax = ebp - 140 +i*7*4 + j*4
			mov DWORD PTR [eax], 17

			mov esp, ebp
			pop ebp
			pop edi
			pop esi
			pop ebx
			ret
	}
}
