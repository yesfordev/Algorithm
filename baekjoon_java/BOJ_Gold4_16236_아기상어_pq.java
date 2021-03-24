package algo0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold4_16236_아기상어_pq {
	
	static class Fish implements Comparable<Fish> {
		int x;
		int y;
		int size;
		
		public Fish(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public int compareTo(Fish o) {
			if(this.x==o.x) {
				return Integer.compare(this.y, o.y);
			} else {
				return Integer.compare(this.x, o.x);
			}
		}
	}

	static int N, time, tempTime;
	static int[][] map;
	static int sharkX, sharkY, sharkSize=2, eatCnt;
	static int[] dr= {1,-1,0,0};
	static int[] dc= {0,0,1,-1};
	static int[][] visited;
	static PriorityQueue<Fish> fishpq = new PriorityQueue<Fish>();
	static Queue<int[]> queue = new LinkedList<int[]>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		visited = new int[N][N];
		
		StringTokenizer st=null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) {
					sharkX = i;
					sharkY = j;
				}
			}
		}
		
		while(bfs()) {
			Fish eatFish = fishpq.poll();
			++eatCnt;
			if(eatCnt == sharkSize) {
				++sharkSize;
				eatCnt = 0;
			}
			
			map[sharkX][sharkY]=0;
			map[eatFish.x][eatFish.y] = 9;
			sharkX = eatFish.x;
			sharkY = eatFish.y;
			
			time += visited[eatFish.x][eatFish.y];
		}
		
		System.out.println(time);
	}

	private static boolean bfs() {
		fishpq.clear();
		queue.clear();
		
		queue.offer(new int[] {sharkX, sharkY});
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], 0);
		}
		
		while(!queue.isEmpty()) {
			int cycle = queue.size();
			for (int a = 0; a < cycle; a++) {
				int[] cur = queue.poll();
				
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur[0] + dr[dir];
					int nc = cur[1] + dc[dir];
					
					if(!check(nr,nc)) continue;
					if(map[nr][nc] != 9 && visited[nr][nc] == 0 && map[nr][nc] <= sharkSize) {
						if(map[nr][nc] < sharkSize && map[nr][nc] != 0) {
							fishpq.add(new Fish(nr, nc, map[nr][nc]));
						}
						visited[nr][nc] = visited[cur[0]][cur[1]] + 1;
						queue.offer(new int[] {nr,nc});
					}
				}
			}
			if(!fishpq.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
