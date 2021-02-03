#include <iostream>
#include <algorithm>
#include <queue>
#include <utility>
#include <vector>
#define X first
#define Y second
using namespace std;

// int board[52][52];
// int dist[52][52];
// int n, m;
// int dx[4] = {1, 0 ,-1, 0};
// int dy[4] = {0, 1, 0, -1};

// void go(int idx) {
//     if(idx == virus.size()) {
        
//     }
// }

// int main() {
//     ios::sync_with_stdio(0);
//     cin.tie(0);

//     cin >> n >> m;

//     vector<pair<int, int> > virus; // 판에서 바이러스 다 골라서 넣는 벡터
//     queue<pair<int, int> > liveVirus; // 활성화된 바이러스 큐
//     vector<bool> check;

//     for(int i=0; i<n; i++) { // 입력
//         for(int j=0; j<n; j++) {
//             cin >> board[i][j];
//             if(board[i][j] == 2) virus.push_back(make_pair(i, j));
//         }
//     }
    
    // for(int i=0; i<virus.size(); i++) {
    //     cout<<i <<"번째 virus: " << virus[i].X << ' ' << virus[i].Y << "\n";
    // }

    // //바이러스 고르기 조합(next_permutation 사용)
    // vector<int> brute(virus.size(), 0);

    // fill(brute.begin(), brute.end() + m, 1);

    // int answer = 0x7f7f7f7f;

    // do {
    //     //뽑고
    //     for(int i=0; i<n; i++) {
    //         for(int j=0; j<n; j++) {
    //             dist[i][j] = -1;
    //         }
    //     }

    //     for(int i=0; i<virus.size(); i++) {
    //         if(brute[i] == 1) continue;

    //         int x = virus[i].X;
    //         int y = virus[i].Y;
    //         liveVirus.push(make_pair(x, y));
    //         dist[x][y] = 0; // 활성 바이러스에는 0을 넣어줌
    //         // cout << "1"
    //         //      << "\n";
    //     }
    //     //BFS() 큐

    //     // cout<< "2" << "\n";
    //     //큐 돌리기

    //     while (!liveVirus.empty()) // 한번 계산 끝
    //     {
    //         // cout << "6"
    //         //      << "\n";
    //         for (int dir = 0; dir < 4; dir++)
    //         {
    //             pair<int, int> curLiveVirus = liveVirus.front();
    //             liveVirus.pop();
    //             cout<<curLiveVirus.X << ' ' << curLiveVirus.Y<<"\n";
    //             int nx = curLiveVirus.X + dx[dir];
    //             int ny = curLiveVirus.Y + dy[dir];

    //             // cout << "4"
    //             //      << "\n";

    //             if(nx>=0 && nx<n && ny >=0 && ny<n) {
    //                 if(dist[nx][ny] == -1 && board[nx][ny] != 1) { // 방문하지 않았거나 벽이 아닌 경우
    //                     dist[nx][ny] = dist[curLiveVirus.X][curLiveVirus.Y] +1;
    //                     liveVirus.push(make_pair(nx, ny));
    //                 }
    //             }
    //         }
    //         // cout << "7"
    //         //      << "\n";
    //     }

    //     int tmp = 0;
    //     for (int i = 0; i < n; i++)
    //     {
    //         for (int j = 0; j < n; j++)
    //         {
    //             if (board[i][j] == 0 && dist[i][j] == -1)
    //             {
    //                 tmp = 0x7f7f7f7f;
    //                 break;
    //             }
    //             if (board[i][j] == 0)
    //                 tmp = max(tmp, dist[i][j]);
    //         }
    //     }

    //     answer = min(answer, tmp);
    // }while(next_permutation(virus.begin(), virus.end()));

    // if(answer == 0x7f7f7f7f) 
    // {
    //     cout<<-1<<"\n";
    //     return 0;
    // }
    // cout << answer << "\n";


    //바이러스 고르기 조합(dfs, 재귀 사용)



// }

int n,m;
int arr[8];
int c[8];
bool isused[8];

// void func(int k, int num) {
//     if(k==m) {
//         for(int i=0; i<m; i++) {
//             cout << c[i] << ' ';
//         }
//         cout << "\n";
//         return;
//     }

//     for(int i=num; i<n; i++) {
//         if(!isused[i]) {
//             c[k] = arr[i];
//             isused[i] = true;
//             func(k+1, i+1);
//             isused[i] = false;
//         }
//     }
// }

void func(int k, int idx) {
    if(k==m) {
        for(int i=0; i<m; i++) {
            cout << c[i] << ' ';
        }
        cout<<"\n";
    }

    for(int i=idx; i<n; i++) {
        if(!isused[i]) {
            c[k] = arr[i];
            isused[i] = true;
            func(k+1, i+1);
            isused[i] = false;
        }
    }


}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >>n >>m;

    for(int i=0; i<n; i++) {
        arr[i] = i+1;
    }

    func(0, 0);
}

