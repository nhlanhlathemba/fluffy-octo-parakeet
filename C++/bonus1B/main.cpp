#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

struct Countries
{
    string name;
    string PIndex;
    string Year;
};

int ConvToInt(char* charr)
{
    stringstream ss(charr);
    int Num;
    ss >> Num;
    return Num;
}



int main(int argc, char* argv[])
{
    ifstream FS;
    Countries Country;
    FS.open("bonus1.txt", ios::in);
    int NC = 0;
    //string Index, Year
  //  FS.seekg(0, ios::beg);
 // cout << ConvToInt(argv[2]) << endl;

    while((FS >> Country.name
             >> Country.PIndex
             >> Country.Year) && (NC != ConvToInt(argv[2])))
    {


        if(Country.Year == argv[1])
        {
            cout << Country.name << " "
             << Country.PIndex << " "
             << Country.Year << endl;
             NC++;
        }

    }
    FS.close();


    return 0;
}
