#include <iostream>
#include <algorithm>
#include <utility>
#include <vector>
using namespace std;

struct sharkStruct {
    int name;
    int x;
    int y;
    int moveDist;
    int dir;
    int size;
    int live; // live가 0이면 살아있고, -1이면 죽은 것
};
vector<int> sharkNameCount[100][100];
sharkStruct shark[10000];
int r,c,m;
int dx[5] = {0, -1, 1, 0, 0};
int dy[5] = {0, 0, 0, 1, -1};
vector<sharkStruct> compare;

int main(){
    cin >> r >> c >> m;

    for(int i=0; i<m; i++) {
        shark[i].name = i;
        cin >> shark[i].x >> shark[i].y >> shark[i].moveDist >> shark[i].dir >> shark[i].size;
        sharkNameCount[shark[i].x][shark[i].y].push_back(i);
    }

    int personY = 0;
    int sum = 0;

    for(int i=1; i<=c; i++) {

        //1. 상어를 먹는다
        for(int j=0; j<m; j++) {
            if(shark[m].live == 0 && i==shark[m].y) {
                compare.push_back(shark[m]);
            }
        }

        //낚시왕과 같은 열에 상어가 여러마리 있으면
        if(!compare.empty()) {
            int EatedSharkX = shark[0].x;
            int EatedSharkIndex = 0;
            for(int num=1; i<compare.size(); i++ ) {
                if(shark[i].x < EatedSharkX) {
                    EatedSharkX = shark[i].x;
                    EatedSharkIndex = i;
                }
            }

            shark[EatedSharkIndex].live = -1; // 잡아먹힘
            sum += shark[EatedSharkIndex].size;
        }

        //2. 상어 이동
    }


}