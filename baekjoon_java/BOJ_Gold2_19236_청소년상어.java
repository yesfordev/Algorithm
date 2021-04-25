package yes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold2_19236_청소년상어 {

	static class Fish implements Comparable<Fish>{
		int number;
		int dir;
		int r;
		int c;
		boolean alive;
		public Fish(int number, int dir, int r, int c, boolean alive) {
			super();
			this.number = number;
			this.dir = dir;
			this.r = r;
			this.c = c;
			this.alive = alive;
		}
		
		@Override
		public int compareTo(Fish o) {
			return Integer.compare(this.number, o.number);
		}
	}
	
	static int dr[] = {0,-1,-1,0,1,1,1,0,-1};
	static int dc[] = {0,0,-1,-1,-1,0,1,1,1};
	static int maxSum = Integer.MIN_VALUE; // 상어는 -1로 표시
	static Fish shark;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] position = new int[4][4]; // 물고기, 상어가 존재하는 곳
		int[][] direction = new int[4][4]; // 해당 위치의 방향

		StringTokenizer st = null;
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				position[i][j] = num;
				direction[i][j] = dir;
			}
		}
		
		shark = new Fish(-1, 0, 0, 0, true);

		int sum = position[0][0];
		position[0][0] = -1;
		shark.r = 0;
		shark.c = 0;
		shark.dir = direction[0][0];

		dfs(position, direction, sum);
		
		System.out.println(maxSum);
	}

	private static void dfs(int[][] position, int[][] direction, int sum) {
		
		maxSum = Math.max(maxSum, sum);
		
		int Nposition[][] = new int[4][4];
		int Ndirection[][] = new int[4][4];
		
		moveFish(position, direction);
		
		// 상어 옮기기
		int plus = 0;
		while(true) {
			copy(position, Nposition);
			copy(direction, Ndirection);
			
			int nr = shark.r + dr[shark.dir]*(++plus);
			int nc = shark.c + dc[shark.dir]*(plus);
			
			if(!check(nr, nc)) return;
			
			if(Nposition[nr][nc] != 0 && Nposition[nr][nc] != -1) {
				int eatFish = Nposition[nr][nc];
				sum += eatFish;
				Nposition[nr][nc] = -1;
				Nposition[shark.r][shark.c] = 0;
				Ndirection[shark.r][shark.c] = 0;
				
				int tempR = shark.r;
				int tempC = shark.c;
				int tempDir = shark.dir;
				shark.r = nr;
				shark.c = nc;
				shark.dir = Ndirection[nr][nc];
				
				dfs(Nposition, Ndirection, sum);
				
				sum -= eatFish;
				shark.r = tempR;
				shark.c = tempC;
				shark.dir = tempDir;
			}
		}
	}

	private static void copy(int[][] srcarr, int[][] copyarr) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copyarr[i][j] = srcarr[i][j];
			}
		}
		
	}

	private static void moveFish(int[][] position, int[][] direction) {
		for (int num = 1; num <= 16; num++) {
			boolean success = false;
			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 4; c++) {
					if(position[r][c] == num) {
						int dir = direction[r][c];
						
						for (int plus = 0; plus < 8; plus++) {
							int d = dir+plus >= 9 ? (dir+plus)%8 : dir+plus;
							
							int nr = r + dr[d];
							int nc = c + dc[d];
							
							if(!check(nr, nc) || position[nr][nc] == -1) continue;
							
							// 상어 교체
							int temp = position[r][c];
							position[r][c] = position[nr][nc];
							position[nr][nc] = temp;
							
							direction[r][c] = direction[nr][nc];
							direction[nr][nc] = d;
							break;
						}
						success = true;
						break;
					}
				}
				if(success) break;
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<4 && c>=0 && c<4;
	}
}
