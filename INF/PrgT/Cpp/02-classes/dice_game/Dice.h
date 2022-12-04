#pragma once
#ifndef INC_02_CLASSES_DICE_H
#define INC_02_CLASSES_DICE_H


// TODO 2.1: Declare the Dice class

class Dice {
private:
	unsigned int minimum;

	unsigned int maximum;
	
public:
	Dice();

	Dice(unsigned int min, unsigned int max);

	unsigned int getRandomNumber();
};


#endif //INC_02_CLASSES_DICE_H
