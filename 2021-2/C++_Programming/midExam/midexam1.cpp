/*#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

class Random {
public:
   static void seed() { srand((unsigned)time(0)); }
   static int nextInt(int min = 0, int max = 16384);
   static char nextAlphabet();
   static double nextDouble();
};

int Random::nextInt(int min, int max) {
   return rand() % (max - min) + min;
}
char Random::nextAlphabet() {
   if (rand() % 2 == 0) return rand() % 26 + 'a';
   else return rand() % 26 + 'A';
}
double Random::nextDouble() {
   return (double)rand() / RAND_MAX;
}
int main() {
   cout << "1에서 100까지 랜덤한 정수 10개를 출력함" << endl;
   for (int i = 0; i < 10; i++) {
      cout << Random::nextInt(1, 100) << ' ';
   }
   cout << endl;

   cout << "알파벳을 랜덤하게 5개 출력함" << endl;
   for (int i = 0; i < 5; i++) {
      cout << Random::nextAlphabet() << ' ';
   }
   cout << endl;

   cout << "실수를 7개를 랜덤하게 출력함" << endl;
   for (int i = 0; i < 7; i++) {
      cout << Random::nextDouble() << ' ';
   }
   cout << endl;
   return 0;
}*/

#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;

class Random {
public:
    // 항상 다른 랜덤수를 발생시키기 위한 seed를 설정하는 함수
    static void seed() { srand((unsigned)time(0)); } // 씨드 설정
    static int nextInt(int min = 0, int max = 16384); // min과 max 사이의 정수 리턴
    static char nextAlphabet(); 
    static double nextDouble(); // 0보다 크고 1보다 적은 랜덤 실수 리턴
};

int Random::nextInt(int min, int max) {
    int r = rand() % (max - min + 1) + min;
    return r;
}

char Random::nextAlphabet() {
   if (rand() % 2 == 0) return rand() % 26 + 'a';
   else return rand() % 26 + 'A';
}

double Random::nextDouble() {
    double d = rand() / (double)(RAND_MAX + 1);
    return d;
}

int main() {
    Random::seed();
    cout << "1에서 100사이의 정수 10개를 랜덤하게 출력함" << endl;
    for (int i = 0; i < 10; ++i) {
        cout << Random::nextInt(1, 100) << ' ';
    }
    cout << endl;

    cout << "알파벳 5개를 랜덤으로 출력함" << endl;
    for (int i = 0; i < 5; ++i) {
        cout << Random::nextAlphabet() << ' ';
    }
    cout << endl;
    cout << "실수를 7개를 랜덤하게 출력함" << endl;
    for (int i = 0; i < 7; ++i) {
        cout << Random::nextDouble() << ' ';
    }
}