#include <iostream>
#include <algorithm>
#include <vector>
#include <utility>
using namespace std;

int A[102][102];
// bool used[102][102];
int num[102];
int r, c, k;
int lengthX = 3;
int lengthY = 3;
int t = 0;

// bool sortbyfirst(const pair<int, int> &a, const pair<int, int> &b) {
//     return a.first < b.first;
// }
// bool sortbysecond(const pair<int, int> &a, const pair<int, int> &b) {
//     return a.second < b.second;
// }
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    vector<pair<int, int>> v;

    cin >> r >> c >> k;

    for (int i = 1; i <= 3; i++)
    {
        for (int j = 1; j <= 3; j++)
        {
            cin >> A[i][j];
        }
    }
    while (true)
    {
        if (A[r][c] == k)
        {
            cout << t << "\n";
            cout << "answer test"
                 << "\n";
            break;
        }

        if (t > 100)
        {
            cout << "-1" << "\n";
            break;
        }

        // fill(used[0], used[102], false);
        cout << "used test finish" << "\n";

        int idx = 1;
        if (lengthX >= lengthY)
        {
            for (int i = 1; i <= lengthX; i++)
            {
                fill(num, num + 102, 0);
                for (int j = 1; j <= lengthY; j++)
                {
                    num[A[i][j]]++;
                    A[i][j] = 0;
                }
                for (int m = 1; m <= 100; m++)
                {
                    if (num[m] == 0) continue;
                    v.push_back(make_pair(num[m], m));
                }
                sort(v.begin(), v.end());

                int tmp = 1;
                for (int m = 0; m < v.size(); m++)
                {
                    A[i][tmp++] = v[m].first;
                    // used[i][tmp++] = true;
                    A[i][tmp++] = v[m].second;
                    // used[i][tmp++] = true;
                }
                tmp--;
                idx = max(idx, tmp);
            }

            cout << "test1"
                 << "\n";
            lengthY = idx;

            v.clear();

            // for (int i = 1; i <= lengthX; i++)
            // {
            //     for (int j = 1; j <= lengthY; j++)
            //     {
            //         if (!used[i][j])
            //             A[i][j] = 0;
            //     }
            // }
            t++;

            cout << "test2"
                 << "\n";
        }
        else
        {
            for (int j = 1; j <= lengthY; j++)
            {
                fill(num, num + 102, 0);
                for (int i = 1; i <= lengthX; i++)
                {
                    num[A[i][j]]++;
                }

                for (int m = 1; m <= 100; m++)
                {
                    if (num[m] != 0) continue;
                    v.push_back(make_pair(num[m], m));
                }

                sort(v.begin(), v.end());

                int tmp = 1;
                for (int m = 0; m < v.size(); m++)
                {
                    A[tmp++][j] = v[m].first;
                    // used[tmp++][j] = true;
                    A[tmp++][j] = v[m].second;
                    // used[tmp++][j] = true;
                }
                tmp--;
                idx = max(idx, tmp);
            }
            lengthX = idx;

            cout << "test3"
                 << "\n";

            v.clear();

            // for (int i = 1; i <= lengthX; i++)
            // {
            //     for (int j = 1; j <= lengthY; j++)
            //     {
            //         if (!used[i][j])
            //             A[i][j] = 0;
            //     }
            // }
            t++;

            cout << "test4"
                 << "\n";
        }
        if (lengthX > 100)
            lengthX = 100;
        if (lengthY > 100)
            lengthY = 100;
    }

    return 0;
    
}