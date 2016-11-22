#include<iostream>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

using namespace std;


int main(){
	
	char test []= "abcdefghijklmnopqrstuvwxyzO";

	char zmienna;
	char * userz="209799";
	int user = atoi(userz);
	//pierwsze 26 znaków hasla jes wyliczne na podstawie algorytmu v6 = (user + 9 * i); mod = v6 % 27; test[mod]; -> wynik ze ³ancuacha znaków
	for (int i = 0; i<26; i++){
		int v6 = (user + 9 * i);
		int mod = v6 % 27;
		zmienna = test[mod];
		cout<< zmienna;
	}
	//kolejne 26 znaków hasla jest wyliczne na podstawie algorytmu v6 = (user + 7 * i); mod = v6 % 27; test[mod]; -> wynik ze ³ancuacha znaków
	for (int i = 26; i<52; i++){
		int v6 = (user + 7 * i);
		int mod = v6 % 27;
		zmienna = test[mod];
		cout << zmienna;
	}
	cout << endl;
	system("PAUSE");
	return 0;
}