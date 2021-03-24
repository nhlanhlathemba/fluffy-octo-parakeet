#include <iostream>
#include <algorithm>
#include <sstream>
#include <vector>
#include <fstream>

using namespace std;

int ConvToInt(char* charr)
{
    stringstream ss(charr);
    int Num;
    ss >> Num;
    return Num;
}


void sortNumbers(vector<int> &Numbers)
{
    int a;
    for(unsigned int j=0; j<Numbers.size(); j++)
    {
        for(unsigned int k=0; k<Numbers.size() - 1; k++)
        {
            if (Numbers[k]>Numbers[k+1])
            {
                a=Numbers[k];
                Numbers[k]=Numbers[k+1];
                Numbers[k+1]=a;
            }
        }
    }
}

int main(int argc, char *argv[])
{
    vector<int> Numbers;
    int index = 1;

    while(ConvToInt(argv[index]) != -1)
    {
        int n = ConvToInt(argv[index]);
       // cout << n << endl;
        Numbers.push_back(n);

        index++;
    }

    sortNumbers(Numbers);

    fstream fFile;
    fFile.open("bonus3.txt", ios::out);

    for(unsigned int j=0; j<Numbers.size(); j++)
    {
        fFile << Numbers[j] << endl;
    }
    fFile.close();
    return 0;
}
