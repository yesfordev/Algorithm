#include <iostream>
#include <algorithm>
using namespace std;

int s[100005];
int d[100005];
int N;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    for(int i=1; i<=N; i++) {
        cin >> s[i];
    }

    d[1] = s[1];
    
    for(int k=2; k<=N; k++) {
        d[k] = max(d[k-1]+s[k], s[k]);
    }

    int answer = *max_element(d+1, d+N+1);

    cout << answer << "\n";

    return 0;
}
