#include <iostream>
using namespace std;

//2번
int main(){
    int a[5] = {5, 4, 6, 7, 3};
    int *pa = a;
    cout << pa[2];
}
//3번
/*
void swap(int *pa, int *pb);
int main(){
    int a = 10, b = 20;
    cout << " a => " << a << "  b => " << b << "\n";
    swap(&a, &b);
    cout << " a => " << a << "  b => " << b << "\n";
}

void swap(int *pa, int *pb){
    int tmp = *pa;
    *pa = *pb;
    *pb = tmp;
}
*/