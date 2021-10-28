#include <iostream>
#include <string>
using namespace std;

#include "Buffer.h"

Buffer& append(Buffer& buf, string text) {
   buf.add(text);
   return buf;
}

Buffer& insert(Buffer& buf, string text, int position) {
   buf.insert(text, position);
   return buf;
}

int main() {
   Buffer buf("Hello");
   Buffer& temp = append(buf, "Guys"); // buf의 문자열에 "Guys" 덧붙임
   temp.print(); // HelloGuys" 출력
   buf.print(); // "HelloGyus" 출력
   buf.insert("hi", 2);
   buf.print(); // "HelloGyus" 출력

}