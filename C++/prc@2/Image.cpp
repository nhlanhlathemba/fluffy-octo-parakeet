#include "Image.h"
#include <vector>
#include <iostream>
#include <string>
#include <sstream>

using namespace std;

namespace ImageSpace
{
    int ConvToInt(char* Charr)
    {
        stringstream ss(Charr);
        int Num = 0;
        ss >> Num;
        return Num;
    }

    char ConvToChar(char* Symbol)
    {
        stringstream ss(Symbol);
        char Print = '\0';
        ss >> Print;
        return Print;

    }
}

Image::Image()
{
   Init(DEFAULT_ROWS, DEFAULT_COLS, '@');
}

void Image::Display()
{
    for(int r = 0; r < Rows; r++)
    {
        for(int c = 0; c < Cols; c++)
        {
            cout << ascii[r][c];
        }
        cout << endl;
    }
}

int Image::getRows() const
{
    return Rows;
}

int Image::getCols() const
{
    return Cols;
}

Image::Image(int Rows, int Cols, char ChToPrint)
{
    Init(Rows, Cols, ChToPrint);
}

void Image::SetPoints(int Positions, char Charr)
{
      for(int i = 0; i < Positions; i+=2)
      {
          ascii[Points[i]][Points[i + 1]] = Charr;
      }
}

void Image::Init(int intRows, int intCols, char Charr)
{
    Rows = intRows;
    Cols = intCols;

    int Positions = NUM_POINTS * 2;
    Points = new int[Positions];
    ascii = new char*[Rows];
    for(int r = 0; r < Rows; r++)
    {
        ascii[r] = new char[Cols];
        for(int c = 0; c < Cols; c++)
        {
            ascii[r][c] = 177;
        }
    }

    for(int i = 0; i < Positions; i++)
    {
        int Num = 0;
        cin >> Num;
        if(Num < 0 || Num > 100)
        {
            Num = 0;
        }

            Points[i] = Num;
    }

    SetPoints(Positions,Charr);
    Display();

}
