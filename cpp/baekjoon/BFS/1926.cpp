#include <iostream>
#include <queue>
#include <utility>
#include <algorithm>
// #include <bits/stdc++.h>
#define X first
#define Y second
using namespace std;

int board[502][502];
bool vis[502][502] = {0,};
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int n, m;
    int maxArea = 0;
    int count = 0;

    cin >> n >> m;

    for(int i=0; i<n; i++) {
        for(int j=0; j<m; j++) {
            cin >> board[i][j];
        }
    }
    
    // queue<pair<int, int> > Q;
    // Q.push({0, 0});
    // vis[0][0] = 1;

    for(int i=0; i<n; i++) {
        for(int j=0; j<m; j++) {
            if(board[i][j] == 0 || vis[i][j]) continue;
            count++;

            queue<pair<int, int> > Q;
            vis[i][j] = 1;
            Q.push({i, j});

            int area = 0;

            while(!Q.empty()) {
                pair<int, int> cur = Q.front(); Q.pop();
                area++;
                
                // cout << "(" << cur.X << ", " << cur.Y << ") -> ";
                
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.X + dx[dir];
                    int ny = cur.Y + dy[dir];

                    if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                    if(board[nx][ny] != 1 || vis[nx][ny]) continue;

                    vis[nx][ny] = 1;
                    Q.push({nx, ny});
                }
            }
            maxArea = max(maxArea, area);
        }
    }

    // cout << "\n" << count << "\n";
    cout << count << "\n";
    cout <<  maxArea << "\n";
}