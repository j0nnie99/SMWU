#include <iostream>
using namespace std;

class Tower {
	int height;
public:
	Tower() { height = 1; }
	Tower(int h);
	int getHeight();
};

Tower::Tower(int h) {
	height = h;
}

int Tower::getHeight() {
	return height;
}

int main() {
	Tower myTower; // 1 ����
	Tower seoulTower(100); // 100 ����
	cout << "���̴� " << myTower.getHeight() << "����" << endl;
	cout << "���̴� " << seoulTower.getHeight() << "����" << endl;
}

/*
���̴� 1����
���̴� 100����
*/