// ConsoleApplication5.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"

__declspec(naked) int fak(int n) {
	//int l = 1;
Prolog:
	__asm{
		push EBP
		mov EBP, ESP
		push EBX
		push ESI
		push EDI
		sub ESP, 4
	}
	//l=1
	__asm mov DWORD PTR [EBP-16], 1
	//if (n > 1)
	__asm {
		cmp DWORD PTR [EBP+8], 1
		jng Epilog
	}
		//l = fak(n - 1)* n;
	__asm {
		mov EAX, DWORD PTR [EBP+8]
		sub EAX, 1
		push EAX
		call fak
		add ESP, 4
		imul EAX, DWORD PTR [EBP+8]
		mov DWORD PTR [EBP-16], EAX
	}
	//return l;
Epilog:
	__asm {
		mov EAX, DWORD PTR [EBP-16]
		add ESP, 4
		pop EDI
		pop ESI
		pop EBX
		pop EBP
		ret
	}
}

int main()
{
	int i;

	i = fak(5);

    return 0;
}

