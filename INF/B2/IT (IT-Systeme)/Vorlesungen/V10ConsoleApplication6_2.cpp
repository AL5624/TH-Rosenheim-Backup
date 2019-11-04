// ConsoleApplication6.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"

__declspec (naked) unsigned int fibonacci(unsigned int n) {
	__asm {
			//push EBX
			//push ESI
			//push EDI
		//push EBP
		//mov EBP, ESP //n entspricht DWORD PTR [EBP+20] oder
		//sub ESP, 0    //n entspricht DWORD PTR [ESP+20 + 0]
		enter 0, 0 //erste 0 gibt Anz. Bytes fuer lokale Variablen an
	}
	//if (n < 2)
	__asm cmp DWORD PTR [EBP+20], 2
	__asm jnl ElseTeil
		//return 1;
		__asm mov EAX, 1
	__asm jmp Epilog
	//else
	ElseTeil:
		//return fibonacci(n - 1) + fibonacci(n - 2);
		__asm mov EAX, DWORD PTR [EBP+8]
		__asm sub EAX, 1
		__asm push EAX
		__asm call fibonacci
		__asm add ESP, 4
		__asm push EAX

		__asm mov EAX, DWORD PTR[EBP + 8] // 8 = 20 -3*4
		__asm sub EAX, 2
		__asm push EAX
		__asm call fibonacci
		__asm add ESP, 4

		__asm pop ECX
		__asm add EAX, ECX
	__asm {
		//add ESP, 0 //mov ESP, EBP
		//pop EBP
		leave
			//pop EDI
			//pop ESI
			//pop EBX
		ret
	}
}

int main()
{
    return 0;
}

