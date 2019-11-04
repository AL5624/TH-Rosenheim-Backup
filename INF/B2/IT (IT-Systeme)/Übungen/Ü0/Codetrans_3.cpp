#include <stdio.h>

int fak(int n) {
	if (n<=1)
		return 1;
	return n*fak(n-1);
}

int fakiter(int n) {
	return 0;
}

void typen(){
	int array[10];
	int i;
	int * ip;
	float f;

	array[4] = 1 << 30;

	printf("%d\n", array[4]);
	printf("%d\n", *(array+4));

	ip = array;
	ip = ip + 4;

	printf("%d\n", *ip);

	f = *ip;
	printf("%f\n", f);

	f = *((float *)ip);
	printf("%f\n", f);
}

void main(){
	int i;

	typen();

	i = fak(5);
	i = fakiter(5);

	typen();
}