#pragma once
#ifndef INC_02_CLASSES_PLAYER_H
#define INC_02_CLASSES_PLAYER_H
#include "Dice.h"


// TODO 2.1: Declare the Player class

class Player {
private:
	unsigned int score = 0;

public:
	unsigned int rollDice(Dice dice);

	unsigned int getScore();
};

#endif //INC_02_CLASSES_PLAYER_H
