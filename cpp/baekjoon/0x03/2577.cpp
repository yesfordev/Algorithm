#include <bits/stdc++.h>
using namespace std;

string a, b;
int Aarr[30];
int Barr[30];
int num;
int sum;

int main(void)
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> a >> b;

    for(int i=0; i<a.length(); i++) {
        Aarr[a[i]-'a']++;
    }

    for(int i=0; i<b.length(); i++) {
        Barr[b[i]-'a']++;
    }

    for(int i=0; i<26; i++) {
        sum = Aarr[i] + Barr[i] + sum;

        if(Aarr[i] != 0 && Barr[i] != 0) {
            num = min(Aarr[i], Barr[i]);
            sum = sum - 2 * num;
        }
    }

    cout << sum;


    return 0;
}