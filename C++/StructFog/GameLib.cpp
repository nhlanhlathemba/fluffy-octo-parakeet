#include "GameLib.h"

#include <cstdlib>
#include <iostream>
#include <sstream>

using namespace std;

namespace GameSpace
{
    bool isInWorld(GameWorld recWorld, int intRow, int intCol)
    {
        if(intRow < 0) return false;
        if(intCol < 0) return false;
        if(intRow >= recWorld.intRows) return false;
        if(intCol >= recWorld.intCols) return false;

        return true;
    }

    void revealAround(GameWorld recWorld)
    {
        for(int r = recWorld.intPR - 1; r <= recWorld.intPR + 1; r++)
        {
            for(int c = recWorld.intPC - 1; c <= recWorld.intPC + 1; c++)
            {
                if(isInWorld(recWorld, r, c))
                {
                    recWorld.aryVisible[r][c] = true;
                }
            }
        }
    }

    int rangedRandom(int intMin, int intMax)
    {
        int intRange = intMax - intMin + 1;
        return rand() % intRange + intMin;
    }


    GameWorld makeWorld(int intRows, int intCols)
    {
        GameWorld recWorld;
        recWorld.intRows = intRows;
        recWorld.intCols = intCols;

        recWorld.aryWorld = new GameRow[recWorld.intRows];
        recWorld.aryVisible = new bool*[recWorld.intRows];

        for(int r = 0; r < recWorld.intRows; r++)
        {
            recWorld.aryWorld[r] = new int[recWorld.intCols];
            recWorld.aryVisible[r] = new bool[recWorld.intCols];

            for(int c = 0; c < recWorld.intCols; c++)
            {
                recWorld.aryVisible[r][c] = false;
                if(rangedRandom(1,100) <= ROCK_CHANCE)
                {
                    recWorld.aryWorld[r][c] = ROCK;
                } else
                {
                    recWorld.aryWorld[r][c] = SPACE;
                }
            }
        }

        int intGR = rangedRandom(0, recWorld.intRows - 1);
        int intGC = rangedRandom(0, recWorld.intCols - 1);
        recWorld.aryWorld[intGR][intGC] = GOAL;

        recWorld.intPR = rangedRandom(0, recWorld.intRows - 1);
        recWorld.intPC = rangedRandom(0, recWorld.intCols - 1);

        while((recWorld.intPR == intGR) &&
              (recWorld.intPC == intGC));
        {
            recWorld.intPR = rangedRandom(0, recWorld.intRows - 1);
            recWorld.intPC = rangedRandom(0, recWorld.intCols - 1);
        }

        revealAround(recWorld);

        return recWorld;
    }

    void showWorld(GameWorld recWorld)
    {
        for(int r = 0; r < recWorld.intRows; r++)
        {
            for(int c = 0; c < recWorld.intCols; c++)
            {
                if(recWorld.aryVisible[r][c])
                {
                    if((r == recWorld.intPR) &&
                       (c == recWorld.intPC))
                    {
                        cout << ARY_SYMBOLS[PLAYER];
                    }
                    else
                    {
                        cout << ARY_SYMBOLS[recWorld.aryWorld[r][c]];
                    }
                } else
                {
                    cout << ARY_SYMBOLS[HIDDEN];
                }

                cout << " ";
            }
            cout << endl;
        }
    }

    void movePlayer(GameWorld& recWorld, Action eAct)
    {
        int intTR = recWorld.intPR;
        int intTC = recWorld.intPC;

        switch(eAct)
        {
            case MOVE_UP:
                intTR--;
            break;

            case MOVE_DOWN:
                intTR++;
            break;

            case MOVE_LEFT:
                intTC--;
            break;

            case MOVE_RIGHT:
                intTC++;
            break;
        }

        if(isInWorld(recWorld, intTR, intTC))
        {
            int intTarget = recWorld.aryWorld[intTR][intTC];
            if(intTarget != ROCK)
            {
                recWorld.intPR = intTR;
                recWorld.intPC = intTC;
                revealAround(recWorld);

                if(intTarget == GOAL)
                {
                    system("cls");
                    showWorld(recWorld);
                    cout << "Well done" << endl;
                    exit(SUCCESS);
                }
            }
        }
    }


    void freeWorld(GameWorld& recWorld)
    {
        for(int r = 0; r < recWorld.intRows; r++)
        {
            delete [] recWorld.aryWorld[r];
            delete [] recWorld.aryVisible[r];
        }
        delete [] recWorld.aryWorld;
        delete [] recWorld.aryVisible;
        recWorld.aryVisible = nullptr;
        recWorld.aryWorld = nullptr;
    }

    int convertToInt(string strNumber)
    {
        stringstream ssConv;
        ssConv << strNumber;
        int intNumber = 0;
        ssConv >> intNumber;
        if(ssConv.fail())
        {
            cerr << "Unable to convert " << strNumber << endl;
            exit(ERROR_CONVERSION);
        }

        return intNumber;
    }
}
