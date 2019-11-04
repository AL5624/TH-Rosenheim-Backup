#pragma once

struct element_s {
	int wert;
	struct element_s * next;
};

typedef struct element_s element;

void einfuegen(element ** ppe, int n);
void streichen(element ** ppe, int n);
void suchen(element * pe, int n);
void ausgeben(element * pe);