package algo0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold4_16236_아기상어 {
	
	static class Fish {
		int x;
		int y;
		int size;

		public Fish(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}

	static int N;
	static int[][] map;
	static int[][] dist;
	static int sharkEatCnt, time;
	static Fish shark;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		dist = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Fish(i,j,2);
				}
			}
		}
		
		while(true) {
			
			//먹을 수 있는 물고기가 있을 때 bfs 돌기 
			BFS();
			
			if(!eatFish()) {
				break;
			}
		}
		
		System.out.println(time);
		
	}

	private static boolean eatFish() {
		int minDist = 9999;
		int cnt=0;
		int eatX = -1;
		int eatY = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] < shark.size && map[i][j] != 0 && dist[i][j] != 0) {
					if (minDist > dist[i][j]) {
						minDist = dist[i][j];
						cnt = 1;
						eatX = i;
						eatY = j;
					} 
				}
			}
		}
		
		if(cnt == 0) {
			return false;
		} else {
			map[eatX][eatY] = 9;
			map[shark.x][shark.y] = 0;
			sharkEatCnt++;
			time+=minDist;
			checkSharkSize();
			shark.x = eatX;
			shark.y = eatY;
			return true;
		} 
	}

	private static void checkSharkSize() {
		if(sharkEatCnt == shark.size) {
			shark.size+=1; 
			sharkEatCnt = 0;
		}
	}

	private static void BFS() {
		Queue<int[]> queue = new LinkedList<int[]>();
		initDist();
		
		queue.offer(new int[] {shark.x, shark.y});
		
		while(!queue.isEmpty()) {
			int curSharkX = queue.peek()[0];
			int curSharkY = queue.peek()[1];
			queue.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = curSharkX + dr[dir];
				int ny = curSharkY + dc[dir];
				
				if(!checkBoundary(nx,ny) || map[nx][ny] > shark.size || dist[nx][ny] != 0) continue;
				if(nx == shark.x && ny == shark.y) continue;
				dist[nx][ny] = dist[curSharkX][curSharkY]+1;
				queue.offer(new int[] {nx,ny});
			}
		}
	}

	private static boolean checkBoundary(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}

	private static void initDist() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], 0);
		}
	}
}
