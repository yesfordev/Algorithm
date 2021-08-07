package algo2108_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 조건 체크를 제대로 안해줬다!! 꼭 해야 한다!! (파란색이 먼저 들어가면 실패) => 문제 꼼꼼히 읽기

 사용한 반례 
5 5
#####
#...#
#.#.#
#BRO#
#####

9 9
#########
###.....#
#..#..#O#
##..R.#B#
####....#
#.....#.#
#.#.....#
##.###..#
#########
 
 */
public class BOJ_Gold2_13460_구슬탈출2 {

	static int N, M, minCount = Integer.MAX_VALUE, rR, cR, rB, cB;
	static char map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int r = 0; r < N; r++) {
			String line = in.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
				
				if(map[r][c] == 'R') {
					rR = r;
					cR = c;
				}
				if(map[r][c] == 'B') {
					rB = r;
					cB = c;
				}
			}
		}
		
		dfs(rR, cR, rB, cB, 1);
		
		if(minCount == Integer.MAX_VALUE) minCount = -1;
		
		System.out.println(minCount);
	}
	
	static int[] dr = {1,-1,0,0}; //아 위 오 왼
	static int[] dc = {0,0,1,-1};
	
	private static void dfs(int redR, int redC, int blueR, int blueC, int count) {
		if(count > 10) {
			return;
		}
		
		for (int dir = 0; dir < 4; dir++) {
			int[] nextRed = go(redR, redC, dir);
			int[] nextBlue = go(blueR, blueC, dir);
			
			int nrR = nextRed[0];
			int ncR	= nextRed[1];
			int nrB = nextBlue[0];
			int ncB = nextBlue[1];
			
			// 탈출구 확인
			
			// 중요!! 파란색이 먼저 들어가면 실패
			if(map[nrB][ncB] == 'O') {
				continue;
			}
			if(map[nrR][ncR] == 'O') {
				if(map[nrB][ncB] == 'O') {
					continue; // 여기 중요
				} else {
					minCount = Math.min(minCount, count);
					return;
				}
			}
			
			// 빨간색 위치 == 파란색 위치일 때 위치 조정
			if(nrR == nrB && ncR == ncB) {
				if(dir == 0) { // 아래쪽일 때
					if(redR > blueR) {
						--nrB;
					} else {
						--nrR;
					}
				} else if(dir == 1) { // 위쪽일 때
					if(redR > blueR) {
						++nrR;
					} else {
						++nrB;
					}
				} else if(dir == 2) { // 오른쪽일 때
					if(redC > blueC) {
						--ncB;
					} else {
						--ncR;
					}
				} else if(dir == 3) { // 왼쪽일 때
					if(redC > blueC) {
						++ncR;
					} else {
						++ncB;
					}
				}
			}
			
			if(nrR == redR && ncR == redC && 
					nrB == blueR && ncB == blueC) {
				continue;
			}
			
			dfs(nrR, ncR, nrB, ncB, count+1);

		}
	}

	private static int[] go(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		
		while(true) {
			nr = nr + dr[dir];
			nc = nc + dc[dir];
			
			if(map[nr][nc] == 'O') {
				break;
			}
			
			if(map[nr][nc] == '#') {
				nr = nr - dr[dir];
				nc = nc - dc[dir];
				break;
			}
		}
		
		int next[] = new int[] {nr, nc};
		
		return next;
	}
}
