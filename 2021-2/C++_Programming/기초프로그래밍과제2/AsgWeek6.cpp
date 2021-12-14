#include <iostream>
#include <fstream>
#include <string>
#include <vector>

using namespace std;

struct WORD {
	string str;
	int count;
};

vector <WORD*> words;

int FindWords(const string& s);
void CountWords(const string& s);
void ShowWords();

int main() {
	
	//파일 읽어오기
	string filename("C:\\Users\\maste\\Desktop\\C++ 6주차 과제\\ted.txt"); //파일경로(개인PC 기준. 수정 필요)
	vector<string> words;
	string word;

	ifstream input_file(filename);
	if (!input_file.is_open()) {
		cerr << "Could not open the file - '"
			<< filename << "'" << endl;
		return EXIT_FAILURE;
	}

	//단어 단위로 vector에 저장하기
	while (input_file >> word) {
		words.push_back(word);
	}

	//각 단어에 대한 CountWords 함수 실행
	for (const auto& i : words) {
		CountWords(i);
	}
	input_file.close();


	ShowWords();
	

	return 0;
}


void CountWords(const string& s) { //각 단어가 반복된 횟수를 세는 함수
	int index = FindWords(s);

	if (index == -1) {
		WORD* pWord = new WORD;
		pWord->str = s;
		pWord->count = 1; //없었던 단어에 대해 count 추가
		words.push_back(pWord); //words의 끝에 새로운 단어 추가
	}

	else {
		words[index]->count++; //index 증가
	}
}

int FindWords(const string& s) {
	for (int i = 0; i < words.size(); i++) {
		if (words[i]->str == s)
			return i;
	}
	return -1;
}


void ShowWords() { //각 단어에 대한 횟수를 출력하는 함수
	for (int i = 0; i < words.size(); i++)
		cout << words[i]->str << " : " << words[i]->count << "번" << endl;
}
