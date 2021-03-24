#include <iostream>
#include <cstdlib>
#include <cmath>
#include <string>


using namespace std;

int main()
{
    char chOption = '\0';
    bool blnContinue = true ;
do
{
    system("cls");
    cout << "Menu System" << endl;
    cout << "a) Run the Jacobsthal-Lucas sequence of numbers" << endl;
    cout << "b) Display Factors of a given number" <<endl;
    cout << "c) Print String" <<endl;
    cout << "x) Exit programme" <<endl;
    cout << "Enter Option:" ;


       string stOption = "";
        cin >> stOption;
         if(stOption.length()==1)
       {
          chOption = stOption[0];
       }

         switch(chOption)
         {
         case 'a':
         case 'A':
             {
                 system("cls");
                   int intNumOfTerms = 0;
             cout << "please enter number of terms: " ;
             cin >> intNumOfTerms;
             while(cin.fail())
             {
                 cerr << "Failed to convert to integer, please retry" << endl;
                 cin.clear();
                 string strJunk ;
                 cin>>strJunk;
                 cin >> intNumOfTerms;
             }
             for(int i=1 ; i <= intNumOfTerms ; i++)
             {
                 int intTerm = 0;
                 intTerm = pow(2, i) + pow(-1, i);

                     cout << intTerm << "," ;


             }
             cout << endl;

             }

            break;

         case 'b':
         case 'B':
            {
                system("cls");
               int intNumber = 0;
               cout << "Please enter number you wish to display the factors of: ";
               cin >> intNumber;
                while(cin.fail())
             {
                 cerr << "Failed to convert to integer, please retry" << endl;
                 cin.clear();
                 string strJunk ;
                 cin>>strJunk;
                 cin >> intNumber;
             }

             for(int i = 1; i <= intNumber; i++)
             {
                 if((intNumber % i)==0)
                 {
                    cout << i << endl;
                 }

             }
            }
            break;

         case 'c':
         case 'C':
             {
                 system("cls");
             string strBlanks = " ";
             string strWords = "";
             cout << "please enter some words"<<endl;
             cin.ignore(1);
             getline(cin,strWords);

             for(char c : strWords)
                {
                    cout << strBlanks
                         << c << endl;
                    strBlanks += " ";
                }

                for(unsigned int i = 0; i < strWords.length(); i++)
                {
                    for(unsigned int j = 0; j < i; j++)
                        cout << " ";
                    cout << strWords[i] << endl;
                }

             }

            break;
         case 'x':
         case 'X':
              blnContinue = false;
            break;
         default:
            cout << "invalid option  " << chOption << endl;


         }
         system("pause");

}while(blnContinue);
    return 0;
}
