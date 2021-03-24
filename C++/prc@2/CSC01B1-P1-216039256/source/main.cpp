#include <iostream>
#include <vector>
#include "Image.h"
#include <cstdlib>

using namespace std;
using namespace ImageSpace;

int main(int argc, char* argv[])
{
    if(argc != 4)
    {
        cerr << "Format : "<< argv[0] << " ROWS COLS CHAR_TO_PRINT" <<endl;
        exit(-1);

    }

    int Rows = ConvToInt(argv[1]);
    int Cols = ConvToInt(argv[2]);
    char CTP = ConvToChar(argv[3]);
    Image recPic(Rows, Cols, CTP);
    return 0;
}
