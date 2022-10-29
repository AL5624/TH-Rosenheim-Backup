#include "Dice.h"
#include "Player.h"
#include <iostream>

int main() {
    Player p1;
    Player p2;

    Dice dice;

    unsigned int numRounds = 5;

    for (int i = 0; i < numRounds; i++) {
        p1.rollDice(dice);
        std::cout << "P1 score: " << p1.getScore() << "\n";

        p2.rollDice(dice);
        std::cout << "P2 score: " << p2.getScore() << "\n";
    }

    return 0;
}
