// midExam2.cpp : 이 파일에는 'main' 함수가 포함됩니다. 거기서 프로그램 실행이 시작되고 종료됩니다.
//

#include <iostream>
#include <string>
using namespace std;

class Student {
    int id;
    string name;
    string club;
public:
    Student();
    string getName() { return name; }
    string getClubName() { return club; }
    void set(int id, string name, string club);
};

Student::Student() {
    id = 0; name = " "; club = " ";
}

void Student::set(int id, string name, string club) {
    this->id = id;
    this->name = name;
    this->club = club;
}

int main()
{
    cout << "학번 이름 동아리이름을 입력해주세요." << endl;
    Student student[3];
    int x; string y, z;
    for (int i = 0; i < 3; ++i) {
        cout << "학생 " << i + 1 << " : ";
        cin >> x >> y >> z;
        student[i].set(x, y, z);
    }
    cout << "모든 학생의 이름은 ";
    for (int i = 0; i < 3; ++i)
        cout << student[i].getName() << ' ';
    cout << endl << "동아리를 검색하기 위해 이름을 입력하세요 : ";
    cin >> y;
    for (int i = 0; i < 3; ++i) {
        if (y == student[i].getName()) cout << "동아리는 " << student[i].getClubName() << " 입니다." << endl;
    }
}

//학번 이름 동아리이름을 입력해주세요.
//학생 1 : 2100001 김숙명 유네스코
//학생 2 : 2101031 박순헌 사물놀이
//학생 3 : 2124156 이명신 종이접기
//모든 학생의 이름은 김숙명 박순헌 이명신 입니다.
//동아리를 검색하기 위해 이름을 입력하세요 : 이명신
//동아리는 종이접기 입니다.