#include "Player.h"

// TODO 2.1: Implement the Player class

unsigned int Player::rollDice(Dice dice) {
	unsigned int val = dice.getRandomNumber();
	score += val;
	return val;
};

unsigned int Player::getScore() {
	return score;
}