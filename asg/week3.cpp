#include <string>
#include <iostream>

using namespace std;

int score;
int frame[11], isStrike[11], isSpare[11];

/*
01. 한 게임 당 10 프레임
02. 볼링핀 - 10개, 핀당 1점
03. 프레임 당 2번의 공 굴릴 기회
04. 스트라이크(S) - 1회차에 모두 핀을 쓰러뜨리는 것
05. 스페어(P) - 1회차 S가 아니고 2회차에서 남은 핀 모두 제거
06. 스트라이크를 치면 다음 2번의 기회 동안 볼링핀의 개수만큼 추가 점수
07. 마지막 10프레임을 제외한 프레임(1~9 프레임)에서 스트라이크 시 -> 두 번째 기회 제공 X
08. 스페어를 치면 2회차에 친 개수만큼 추가점수
09. 10번째 프레임에서 스트라이크(S)를 칠 경우, 2번의 보너스 기회 제공 -> 이때의 점수에 대한 추가 점수 X
10. 10번째 프레임에서 스페어(P)를 칠 경우, 1번의 보너스 기회 제공 -> 이때의 점수에 대한 추가 점수 X

스트라이크: S
스페어: P
하나도 쓰러뜨리지 못함: -

*/


int totalsum(int frameNum) {  //total score 계산용
	int sum = 0;
	for (int i = 1; i <= frameNum; i++) {
		sum += frame[i];
	}
	return sum;
}

int main(void) {

	string s; cin >> s;
	int id = 0; 
	char ch;
	for (int i = 1; i <= 10; i++) {

		ch = s[id++];
		int frameScore = 0;

        //점수 처리 ( - == 0, S = 10, '0'~'9'를 0~9로 처리)
		if (ch == '-') score = 0;
		else if (ch >= '1' && ch <= '9') score = (ch - '0');
		else if (ch == 'S') score = 10;
    
		frameScore += score, frame[i] += score;

		if (i - 2 >= 1 && isStrike[i - 2] > 0) { 
			isStrike[i - 2] -= 1;
			frame[i - 2] += score;
		}

		if (i - 1 >= 1 && isStrike[i - 1] > 0) { 
			isStrike[i - 1] -= 1;
			frame[i - 1] += score;
		}
		if (i - 1 >= 1 && isSpare[i - 1] > 0) { 
			isSpare[i - 1] -= 1;
			frame[i - 1] += score;
		}

		if (score == 10) {
			isStrike[i] += 2;
			continue;
		}

		ch = s[id++];
		if (ch == '-') score = 0;
		else if (ch >= '1' && ch <= '9') score = (ch - '0');
		else if (ch == 'P') score = 10 - frameScore;

		frameScore += score; frame[i] += score;
		if (i - 1 >= 1 && isStrike[i - 1] > 0) { 
			isStrike[i - 1] -= 1;
			frame[i - 1] += score;
		}
		if (frameScore == 10) {
			isSpare[i] += 1;
			continue;
		}
	}

	if (isStrike[10]) { 
		ch = s[id++]; 
		int frameScore = 0;
		if (ch == '-') score = 0;
		else if (ch >= '1' && ch <= '9') score = (ch - '0');
		else if (ch == 'S') score = 10;
		
		frameScore += score;
		frame[10] += score;

		if (isStrike[9]) {
			frame[9] += score;
			isStrike[9] -= 1;
		}

		ch = s[id++];
		if (ch == '-') score = 0;
		else if (ch >= '1' && ch <= '9') score = (ch - '0');
		else if (ch == 'P') score = 10 - frameScore;
		else if (ch == 'S') score = 10;

		frame[10] += score;
	}
	else if (isSpare[10]) {
		ch = s[id++];
		int frameScore = 0;
		if (ch == '-') score = 0;
		else if (ch >= '1' && ch <= '9') score = (ch - '0');
		else if (ch == 'S') score = 10;
		frame[10] += score;
	}

    cout << "Frame    ";

    for (int i=1; i<=10; i++){
        cout << i << "     ";
    }

    cout <<"Total    " << "\n" << "Score  ";
    
    for (int i=0; i<=s.length(); i++){
        cout << s[i] << "  ";
    }
    

    cout <<"\n" << "Sum     ";
	cout << totalsum(10) << '\n';
	return 0;
}