#include <iostream>
#include <fstream>
#include <cmath>
#include <sstream>

using namespace std;

struct Countries
{
    char name[32];
    double PIndex;
    int Year;
};

int ConvToInt(string word)
{
    stringstream ss(word);
    int Num;
    ss >> Num;
    if(ss.fail())
    {
        Num = 0;
    }
    return Num;
}

double ConvToDouble(string word)
{
    stringstream ss(word);
    double Num;
    ss >> Num;
    if(ss.fail())
    {
        Num = 0.00;
    }
    return Num;
}

int main(int argc, char* argv[])
{
    fstream FSTxt;
    fstream FSBin;
    Countries Country;
    FSTxt.open(argv[1], ios::in);
    FSBin.open("bonus2.dat", ios::binary | ios::out);
    string Index, Year;
    while((FSTxt >> Country.name
             >> Index
             >> Year))
    {
        Country.PIndex = ConvToDouble(Index);
        Country.Year = ConvToInt(Year);

       FSBin.write(reinterpret_cast<char*>(&Country), sizeof(Countries));
    }

    FSTxt.close();
    FSBin.close();


    return 0;
}
