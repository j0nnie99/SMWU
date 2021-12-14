#include <iostream>
#include <string>
#include <cstring>
using namespace std;

class Date {
	int day;
	int month;
	int year;
public:
	Date(int y, int m, int d);
	Date(string s);
	void show();
	int getYear() { return year; }
	int getMonth() { return month; }
	int getDay() { return day; }
};

Date::Date(int y, int m, int d) {
	year = y;
	month = m;
	day = d;
}

Date::Date(string s) {
	int index1 = s.find("/");
	string y = s.substr(0, index1);
	int index2 = s.find("/", index1+1);
	string m = s.substr(index1+1, index2-index1-1);
	string d = s.substr(index2+1, s.length());

	// <string> ��� ���Ͽ��� �����Ǵ� C++ string �Լ��� �̿��ϴ� ���
	year = stoi(y);
	month = stoi(m);
	day = stoi(d);

	// ������ C ���̺귯�� �Լ��� atoi()�� �̿��ϴ� ���
	//year = atoi(y.c_str());
	//month = atoi(m.c_str());
	//day = atoi(d.c_str());
}

void Date::show() {
	cout << year << "��" << month << "��" << day << "��" << endl;
}

int main() {
	Date birth(2014, 3, 20); // 2014�� 3�� 20��
	Date independenceDay("1945/8/15"); // 1945�� 8�� 15��
	independenceDay.show();
	cout << birth.getYear() << ',' << birth.getMonth() << ',' << birth.getDay() << endl;
}