#include <bits/stdc++.h>
using namespace std;

int freq[26];

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0);
    string s;
    cin >> s;

    for(int i=0; i < s.length(); i++) {
        char c = s.at(i);
        freq[c-'a']++;
    }
    for(int i=0; i<26; i++) {
        cout << freq[i] << ' ';
    }
}

//3강 연습문제
// int func2(int arr[], int N) {
//     int occur[101] = {};
//     for(int i=0; i<N; i++) {
//         if(occur[100-arr[i]])
//             return 1;
//         occur[101-arr[i]] = 1;
//     }
//     return 0;
// }