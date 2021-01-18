#include <iostream>
using namespace std;

int func4(int N) {
    int val = 1;
    while(2*val <= N) {
        val *= 2;
    }
    return val;
}

int main(void) {
    cout<< func4(5) << endl;
    cout<< func4(97615282) << endl;
    cout<< func4(1024) << endl;
}
