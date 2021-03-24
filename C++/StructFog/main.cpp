#include <cstdlib>
#include <ctime>
#include <iostream>

#include "GameLib.h"

using namespace std;
using namespace GameSpace;

int main(int argc, char* argv[])
{
    srand(time(nullptr));

    if(argc != 3)
    {
        cerr << "Usage: " << argv[0] << " NUM_ROWS NUM_COLS" << endl;
        exit(ERROR_CMD_ARG_COUNT);
    }

    int intRows = convertToInt(argv[1]);
    int intCols = convertToInt(argv[2]);

    GameWorld recWorld = makeWorld(intRows, intCols);

    bool blnContinue = true;
    do
    {
        system("cls");

        showWorld(recWorld);
        cout << "w) Up a) Left s) Down d) Right x) Exit" << endl
             << "Select: ";

        char chSelection = '\0';
        cin >> chSelection;

        switch(chSelection)
        {
            case 'w':
            case 'W':
                movePlayer(recWorld, MOVE_UP);
            break;

            case 'a':
            case 'A':
                movePlayer(recWorld, MOVE_LEFT);
            break;

            case 's':
            case 'S':
                movePlayer(recWorld, MOVE_DOWN);
            break;

            case 'd':
            case 'D':
                movePlayer(recWorld, MOVE_RIGHT);
            break;

            case 'x':
            case 'X':
                blnContinue = false;
            break;

            default:
                cerr << "Invalid selection " << chSelection << endl;
                system("pause");
        }
    } while(blnContinue);


    freeWorld(recWorld);
    return SUCCESS;
}
