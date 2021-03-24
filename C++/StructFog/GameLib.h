#ifndef GAMELIB_H
#define GAMELIB_H

#include <string>

using namespace std;

namespace GameSpace
{
    typedef int* GameRow;
    typedef GameRow* GameArray;

    struct GameWorld
    {
        GameArray aryWorld;
        bool** aryVisible;
        int intRows;
        int intCols;
        int intPR;
        int intPC;
    };

    enum GameEntity
    {
        SPACE,
        ROCK,
        GOAL,
        PLAYER,
        HIDDEN
    };

    const char ARY_SYMBOLS[] = {'.','O','$','@','#'};

    enum Action
    {
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT
    };

    enum StatusCode
    {
        SUCCESS,
        ERROR_CMD_ARG_COUNT,
        ERROR_CONVERSION
    };

    const int ROCK_CHANCE = 10;

    GameWorld makeWorld(int intRows, int intCols);
    void showWorld(GameWorld recWorld);
    void freeWorld(GameWorld& recWorld);
    void movePlayer(GameWorld& recWorld, Action eAct);

    int convertToInt(string strNumber);
}

#endif // GAMELIB_H
