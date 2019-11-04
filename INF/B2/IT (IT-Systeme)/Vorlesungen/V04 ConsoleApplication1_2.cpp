// ConsoleApplication1.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"

int i, j;

int a[10];
int b[10][20];

float f;
double d;
long double ld;

int main()
{
	i = 4;
	j = 7;

	a[i] = 5;

	b[i][j] = 5;

	/*{
		__asm add EAX, DWORD PTR [0x000000FF + EBX + ECX*8]
	}*/

	{
		unsigned char uc=127;
		signed char sc = 127;
		unsigned short us = 0;
		signed short sshort = 0;
		unsigned int ui = -1;
		signed int sint = 0;
		float f = 1;
		double d = 2;

		sint = ui;
		sshort = uc;
		sshort = sc;

		uc = sint;

		d = f;
		sint = f;
		__asm {
			fld dword ptr [f]
			fistp dword ptr [sint]
		}
		f = sint;
		__asm {
			fild dword ptr [sint]
			fstp dword ptr [f]
		}

		f = ui;
		__asm {
			mov EAX, DWORD PTR [ui]

			sub ESP, 8
			mov DWORD PTR [ESP], EAX
			mov DWORD PTR [ESP+4], 0

			fild QWORD PTR [ESP]
			fstp DWORD PTR [f]
			add ESP, 8
		}
	}
	{
		struct stype {
			int a[2] = { 0 };
		}sv1, sv2;

		sv1 = sv2;
		__asm {
			mov EAX, DWORD PTR [sv2]
			mov DWORD PTR [sv1], EAX
			mov EAX, DWORD PTR[sv2+4]
			mov DWORD PTR[sv1+4], EAX
		}
	}

	{
		struct stypegross {
			int a[100] = { 0 };
		}sv1, sv2;

		sv1 = sv2;
		{
			int i;
			int * qi;
			int * zi;

			qi = sv2.a;
			zi = sv1.a;
			for (i = 100; i > 0; i--, qi++, zi++)
				*zi = *qi;
		}
	}
    return 0;
}

