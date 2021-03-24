#include <iostream>
#include <cstdlib>
#include <sstream>
#include <iomanip>

using namespace std;

int GenerateRandom(int lowerBound , int upperBound)
    {
        int range = upperBound - lowerBound + 1;
        return (rand() % range )+ lowerBound;
    }

 int Conv(char* charr)
 {
     int intNum = 0;
   stringstream ss(charr);
   ss >> intNum;
   if(ss.fail())
   {
       cerr << "Cannot Convert" << endl;
       exit(-1);
   }
   return intNum;
 }

int main(int argc, char* argv[])
{
    srand(Conv(argv[3]));
    int intRows = Conv(argv[1]);
    int intCols = Conv(argv[2]);

    int arrRectangle[intRows][intCols];

    int intRandomNum = 0;

    for(int r = 0; r < intRows; r++)
    {
        for(int c = 0; c < intCols; c++)
        {
            intRandomNum = GenerateRandom(1,100);
            if(intRandomNum <= 10)
            {
                arrRectangle[r][c] = 1;
            }else
            arrRectangle[r][c] = 2;
        }
    }

    for(int r = 0; r < intRows; r++)
    {
        for(int c = 0;c < intCols; c++)
        {
            cout << setw(2);
            switch(arrRectangle[r][c])
            {
            case 1:
                cout << '*';
                break;
            case 2:
                cout << '.';
                break;
            }
        }
        cout << endl;
    }

    return 0;
}
