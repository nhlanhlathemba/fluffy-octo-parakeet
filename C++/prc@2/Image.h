#ifndef IMAGE_H_INCLUDED
#define IMAGE_H_INCLUDED

#include <vector>

class Image
{
public:
    Image();
    Image(int Rows, int Cols, char ChToPrint);

    int getRows() const;
    int getCols() const;

    const int NUM_POINTS = 3;

    void SetPoints(int Positios, char Charr);

    const static int DEFAULT_ROWS = 20;
    const static int DEFAULT_COLS = 20;

    void Display();

private:
    void Init(int Rows, int Cols, char Charr);
    int* Points;
    char** ascii;
    int Rows;
    int Cols;
};

namespace ImageSpace
{
    int ConvToInt(char* Charr);
    char ConvToChar(char* Symbol);

}

#endif // IMAGE_H_INCLUDED
