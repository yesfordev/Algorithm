#include <bits/stdc++.h>
using namespace std;

int arr[10];
int N;
int nam;

int main(void)
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    
    if(N==0) {
        cout << 1;
        return 0;
    } else {
        while(N!=0) {
            nam = N%10;
            N = N/10;

            arr[nam]++;
        }

        int maxNum = arr[0];
        for(int i=1; i<9; i++) {
            maxNum = max(maxNum, arr[i]);
        }

        int k = arr[6]+arr[9];

        if(k%2==1) k = k/2+1;
        else k=k/2;

        int answer = max(k, maxNum);
        
        cout << answer;
    }

    return 0;
}