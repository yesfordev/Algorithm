#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int count[26];
    int N, pos;
    string cmp1, cmp2;

    cin >> N;

    for(int i=0; i<N; i++) {
        pos = 1;
        fill(count, count+26, 0);
        cin >> cmp1 >> cmp2;

        // for(char c : cmp1) {
        for(int j=0; j<cmp1.length(); j++) {
            count[cmp1[j]-'a']++;
            count[cmp2[j]-'a']--;
        }

        for(int i=0; i<26; i++) {
            if(count[i] != 0) {
                pos = 0;
                break;
            }
        }

        // // for(char c : cmp2) {
        // for(int j=0; j<cmp2.length(); j++) {
        //     c= cmp2.at(j);
        //     if(count[c-'a'] == 0) {
        //         cout << "Impossible" << "\n";
        //         pos = 0;
        //         break;
        //     }
        // }
        if(pos == 1) {
            cout << "Possible" << "\n";
        } else {
            cout << "Impossible" << "\n";
        }
    }
}