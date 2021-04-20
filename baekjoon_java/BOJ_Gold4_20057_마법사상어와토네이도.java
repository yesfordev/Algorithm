package yes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Gold4_20057_마법사상어와토네이도 {

	static int N, map[][];
	static int dr[] = {0,1,0,-1};	// 토네이도 방향 
	static int dc[] = {-1,0,1,0};
	static int rate[] = {1,1,2,2,7,7,10,10,5};
	static int moveR[] = {-1,1,-2,2,-1,1,-1,1,0};
	static int moveC[] = {1,1,0,0,0,0,-1,-1,-2};
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(in.readLine());
		
		map = new int[N][N];
		
		int totalGround = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				totalGround += map[i][j];
			}
		}
		
		process();
		
		int remain = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				remain += map[i][j];
			}
		}
		
		System.out.println(totalGround-remain);
		
	}
	private static void process() {
		int go = 0;
		int plus = 2;
		int r = N/2;
		int c = N/2;
		int dir = 0;
		int tempGo = 0;
		int tempPlus = 0;
		
		c = c-1;
		for (int i = 0; i < N*N; i++) {
			
			if(map[r][c] != 0)
				moveGround(dir, map[r][c], r, c);
			
			if(r==0 && c==0) {
				break;
			}
			
			int nr = r+dr[dir];
			int nc = c+dc[dir];
			
			if(!check(nr,nc) || tempGo == go) {
				if(++tempPlus == plus) {
					++go;
					tempPlus = 0;
				}
				dir = (dir+1)%4;
				nr = r + dr[dir];
				nc = c + dc[dir];
				tempGo = 0;
			} else {
				++tempGo;
			}
			
			r = nr;
			c = nc;
		}	
	}
	
	private static void moveGround(int dir, int curGround, int r, int c) {
		int goGround = 0;
		
		if(dir == 0) {
			for (int i = 0; i < 9; i++) {
				int tempGround = curGround*rate[i]/100;
				
				if(tempGround >= 1) {
					int tempR = r+moveR[i];
					int tempC = c+moveC[i];
					if(!check(tempR, tempC)) {
						goGround += tempGround;
					} else {
						map[tempR][tempC] += tempGround;
						goGround += tempGround;
					}
				}
			}
			
			int remainGround = map[r][c] - goGround;
			
			if(check(r,c-1)) {
				map[r][c-1] += remainGround;
			}
			map[r][c] = 0;
		} else if(dir == 1) {
			for (int i = 0; i < 9; i++) {
				int tempGround = curGround*rate[i]/100;
				
				if(tempGround >= 1) {
					int tempR = r-moveC[i];
					int tempC = c+moveR[i];
					if(!check(tempR, tempC)) {
						goGround += tempGround;
					} else {
						map[tempR][tempC] += tempGround;
						goGround += tempGround;
					}
				}
			}

			int remainGround = map[r][c] - goGround;

			if (check(r+1, c)) {
				map[r+1][c] += remainGround;
			}
			map[r][c] = 0;
		} else if(dir == 2) {
			for (int i = 0; i < 9; i++) {
				int tempGround = curGround*rate[i]/100;
				
				if(tempGround >= 1) {
					int tempR = r+moveR[i];
					int tempC = c-moveC[i];
					if(!check(tempR, tempC)) {
						goGround += tempGround;
					} else {
						map[tempR][tempC] += tempGround;
						goGround += tempGround;
					}
				}
			}

			int remainGround = map[r][c] - goGround;

			if (check(r, c+1)) {
				map[r][c+1] += remainGround;
			}
			map[r][c] = 0;
		} else if(dir == 3) {
			for (int i = 0; i < 9; i++) {
				int tempGround = curGround*rate[i]/100;
				
				if(tempGround >= 1) {
					int tempR = r+moveC[i];
					int tempC = c+moveR[i];
					if(!check(tempR, tempC)) {
						goGround += tempGround;
					} else {
						map[tempR][tempC] += tempGround;
						goGround += tempGround;
					}
				}
			}

			int remainGround = map[r][c] - goGround;

			if (check(r-1, c)) {
				map[r-1][c] += remainGround;
			}
			map[r][c] = 0;
		}
		
	}
	
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
