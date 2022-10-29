#include "Dice.h"
#include <random>

// TODO 2.1: Implement the Dice class

Dice::Dice(): Dice(1, 6) {};

Dice::Dice(unsigned int min, unsigned int max) : minimum(min), maximum(max) {};

unsigned int Dice::getRandomNumber() {
	std::random_device rd;
	std::uniform_int_distribution<int> dist(minimum, maximum);
	return dist(rd);
};