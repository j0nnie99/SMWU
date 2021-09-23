#include <iostream>

int main() {
	for(int i=1; i<=9; i++) {
		for(int j=1; j<=i; j++)
			std::cout << '*';
		std::cout << '\n';
	}
    for(int i=9; i>=1; i--) {
		for(int j=1; j<=i; j++)
			std::cout << '*';
		std::cout << '\n';
	}
	return 0;
}