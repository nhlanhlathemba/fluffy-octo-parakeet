#include <iostream>
#include <vector>
#include <cstdlib>
#include <string>
#include <sstream>
#include <cmath>

using namespace std;

void displayVec(vector<int> VecUser)  // function for printing vectors
{
    for( int intNum : VecUser)
 {
     cout << intNum ;
 }
 cout << endl;
}

void ConvToHD(vector <int> Binary)
{
    if((Binary.size() % 4)!=0)
    {
        while((Binary.size() % 4)!=0)
        {
            Binary.push_back(0);
        }
    }
    cout << Binary.size()<< endl;
        int intNumGroups = Binary.size() / 4;

        int intHexadecimal[intNumGroups] = {0};
        char chHexadecimal = '\0';
        int n = 0;
        for(unsigned int i = 0; i <= Binary.size() ; i++ )
        {
            int digitCount = 4;
            int total = 0;
            while(digitCount!=0)
            {
               if(Binary[i]==1)
               {
                  total += pow(2, digitCount);
               }

                digitCount--;
            }
            cout << total<<endl;
            intHexadecimal[n] = total;
            n++;
        }

        for(int i = 0; i < intNumGroups; i++)
        {
              if(intHexadecimal[i] >= 10)
            {
                switch(intHexadecimal[i])
            {

            case 10:
                chHexadecimal = 'A';
                cout << chHexadecimal;
                break;
            case 11:
                chHexadecimal = 'B';
                cout << chHexadecimal;
                break;
            case 12:
                chHexadecimal = 'C';
                cout << chHexadecimal;
                break;
            case 13:
                chHexadecimal = 'D';
                cout << chHexadecimal;
                break;
            case 14:
                chHexadecimal = 'E';
                cout << chHexadecimal;
                break;
            case 15:
                chHexadecimal = 'F';
                cout << chHexadecimal;
                break;

            }
            }else
            cout << intHexadecimal;


        }

}




int Convert(char* charr)
{
  int intNum = 0;
  stringstream ss(charr);
  ss>>intNum;
  if(ss.fail())
  {
      cerr << "Cant convert" << endl;
      exit(-5);
  }
  return intNum;
}

int main(int argc, char* argv[])
{
 int integer = Convert(argv[1]);
    int seBinary = 0;
    vector<int> reverseBinary;


    while(integer != 0)
    {
        seBinary = (integer % 2);
        reverseBinary.push_back(seBinary);
        integer =(integer/2);
    }


    vector<int> Binary;

    for( int i = (reverseBinary.size() - 1); i >= 0 ; i--)
    {
       Binary.push_back(reverseBinary[i]);
    }

   displayVec(Binary);
   ConvToHD(Binary);

    return 0;
}
