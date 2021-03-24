#include <iostream>
#include <windows.h>

using namespace std;

typedef void (*Obonus)();
int main(int argc, char* argv[])
{
    string FName = argv[1];
    string DLLName = argv[2];


    Obonus BonusF = nullptr;
    HINSTANCE hndlDLL = LoadLibrary(DLLName.c_str());

    if(hndlDLL)
    {
      BonusF = (Obonus)GetProcAddress(hndlDLL,FName.c_str());

      if(BonusF)
      {
        BonusF();
      }

    }

    return 0;
}
