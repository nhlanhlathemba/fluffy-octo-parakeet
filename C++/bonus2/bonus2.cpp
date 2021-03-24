#include <iostream>
#include <cstdlib>
#include <sstream>
#include <vector>

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
  int integer = Conv(argv[1]);

  int binaryDigit = 0 ;

  vector <int> ReversedBinary;

  while(integer!=0)
  {
     binaryDigit = integer % 2;
      ReversedBinary.push_back(binaryDigit);
     cout << binaryDigit;
     integer = integer / 2;

  }
  cout<<endl;
  cout<< ReversedBinary[1];
  vector <int> Binary;
 // system("pause");
 // cout << Binary.size()<<endl;
 for(unsigned int i = ReversedBinary.length(); i < 0;i--)
 {
    Binary.push_back(ReversedBinary[i]);
    cout << ReversedBinary[i];

 //  cout << Binary[i];
 }

 for(int i : Binary)
 {
     cout << i;
 }
    return 0;

}
