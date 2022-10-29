#include <iostream>

void printSquare(int size);

int main() {

    // TODO 1.3 a): Implement square output

    const int SIZE = 6;

    char square[SIZE][SIZE];

    const char s = '&';

    for (int i = 0; i < SIZE; i++) {
        if (i == 0 || i == SIZE - 1)
        {

        }
        else
        {
            for (int j = 0; i < SIZE; j++) {
                char symbol = (j == 0 || j == SIZE - 1) ? s : ' ';
                square[i][j] = symbol;
            }
        }
    }

    std::cout << square << "\n";

    return 0;
}
