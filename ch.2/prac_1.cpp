#include <iostream>
using namespace std;

int main(){
    for(int i=1; i<101; i++){
        cout<<i<<"\t";
        if(i%10 == 0){
            cout <<"\n";
        }
    }
}