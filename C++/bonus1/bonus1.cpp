#include <iostream>
#include <sstream>
#include <cstdlib>


using namespace std;

int Conv(char* charr)
{
    int intNum=0;
    stringstream ss(charr);
    ss >> intNum;
    if(ss.fail())
    {
        cerr << "can't convert" << endl;
        exit(-5);

    }
    return intNum;
}

int main(int argc, char* argv[])
{
 int Length = Conv(argv[1]);
 int term = 1;
 for(int i = 1; i <= Length; i ++)
 {
     cout << term << " ";
     term *= i;
 }
}
