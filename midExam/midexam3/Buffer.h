#ifndef BUFFER_H
#define BUFFER_H
#include <iostream>
#include <string>
using namespace std;

class Buffer {
   string text;
public:
   Buffer(string text) { this->text = text; }
   void add(string next) {
      text += next;
   }
   void print() {
      cout << text << endl;
   }

   void insert(string next, int position) {
      text += next;
   }
};


#endif // !BUFFER_H