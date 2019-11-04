// ConsoleApplication4.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"
#include <stdarg.h>

int h(int n) {
	int loc;

	//if (n <= 1)
	__asm {
		cmp DWORD PTR[n], 1
		jnle ElseTeil
	}
		//return 1;
		__asm mov EAX, 1
		__asm jmp EndeIf
	//else {

ElseTeil:
		//loc = h(n - 1);
		__asm {
			mov EAX, DWORD PTR [n]
			dec EAX

			push EAX	// sub ESP, 4
						// mov DWORD PTR [ESP], EAX
			call h		// push AdresseVonAdd
						// jmp h
AdresseVonAdd:
			add ESP, 4

			mov DWORD PTR [loc], EAX
			//return n * loc;
			mov EAX, DWORD PTR [n]
			imul EAX, DWORD PTR [loc]
		}
	//}
	EndeIf:
		return;
}

int summe_c(int anz, ...) {
	int s = 0;
	va_list vptr;

	va_start(vptr, anz);

	for (; anz > 0; anz--)
		s += va_arg(vptr, int);

	return s;
}

int summe_asm(int anz, ...) {
	int s = 0;

	__asm {
		lea EBX, DWORD PTR [anz]	// EBX entspr. vptr
		add EBX, 4					// va_start

		mov ECX, DWORD PTR [anz]
ForSchleife:
		mov EAX, DWORD PTR [EBX]
		add DWORD PTR [s], EAX
		add EBX, 4
		Loop ForSchleife
	}

	return s;
}

struct styp {
	int a[100];
} sv;

struct styp sfkt(struct styp sp) {
	struct styp sloc;

	sloc = sp;

	return sloc;
}

void sfktopt(struct styp *zielptr, struct styp sp) {
	struct styp sloc;

	sloc = sp;

	*zielptr = sloc;
}

int main()
{
	sv = sfkt(sv);
	sfkt(sv); //In C zulässig, in Java nicht

	sfktopt(&sv, sv);

	unsigned ui = 0;

	int s = summe_c(3, 1, 2, 3);

	int i = h(5);

	switch (ui) {
	case 0: printf("0");
	case 1: printf("1");
	case 2: printf("2");
	case 3: printf("3");
	default: printf("sonst");
	}

	switch (ui) {
	case 3: printf("3");
	case 1: printf("1");
	case 2: printf("2");
	case 0: printf("0");
	default: printf("sonst");
	}

	switch (ui) {
	case 10: printf("10");
	case 1: printf("1");
	case 20: printf("20");
	case 3: printf("3");
	default: printf("sonst");
	}

    return 0;
}

