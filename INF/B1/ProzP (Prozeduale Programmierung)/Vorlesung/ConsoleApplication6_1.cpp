// ConsoleApplication6.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"

struct struktur { 
	int a[1000]; 
};

struct struktur struct_fkt(struct struktur p) {
	struct struktur l;

	l = p;

	return l;
}

void struct_fkt_opt(struct struktur * zp, struct struktur p) {
	struct struktur l;

	l = p;

	*zp = l;
	return;
}

void vertausche(int a, int b) {
	int h;

	h = a;
	a = b;
	b = h;

	return;
}

void vertausche_zeiger(int * a, int * b) {
	int h;

	h = *a;
	*a = *b;
	*b = h;

	return;
}

int main()
{
	int a = 1;
	int b = 2;

	int * ptr_a;
	int * ptr_b;

	vertausche(a, b);

	vertausche_zeiger(&a, &b);

	ptr_a = &a;
	ptr_b = &b;
	vertausche_zeiger(ptr_a, ptr_b);

	{
		int a = 1;
		int * ptr_a = &a;
		int ** ptr_ptr_a =&ptr_a;

		a = 2;
		*ptr_a = 3;
		**ptr_ptr_a = 4;
	}

	{
		struct struktur s1, s2 = { 0 };

		s1 = struct_fkt(s2);
		struct_fkt_opt(&s1, s2);
	}
    return 0;
}

