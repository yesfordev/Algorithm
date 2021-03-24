package algo0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BFS!
 * => 처음에 무한루프에 빠짐 -> 방문했을 때 가지치기 제대로 안해줘서!
 * => melting(없앤 치즈) map[i][j] == 1 인 조건 명시도 필요!!
 * @author yes
 *
 */
public class BOJ_Gold5_2636_치즈 {

	static int[][] map;
	static int[][] copy;
	static int N, M, melting;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static Queue<int[]> queue = new LinkedList<int[]>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time=0;
		while(isChesese()) {
			melting = 0;
			++time;
			checkOut(); // 바깥쪽을 -1로 체크 
			
			checkCheese(); // 녹는 치즈 체크 
		}
		
		System.out.println(time);
		System.out.println(melting);
		
	}
	
	// 치즈가 남아 있는지 확인
	private static boolean isChesese() {
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < M-1; j++) {
				if(map[i][j] == 1) return true;
			}
		}
		return false;
	}

	private static void checkCheese() {
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < M-1; j++) {
				if(map[i][j]==1) { // 이 부분 중요 
					int cnt=0;
					for (int dir = 0; dir < 4; dir++) {
						int nr = i+dr[dir];
						int nc = j+dc[dir];
						
						if(copy[nr][nc] == -1) ++cnt;
					}
					
					if(cnt>=1) {
						map[i][j] = 0;
						++melting;
					}
				}
			}
		}
	}

	private static void checkOut() {
		copyMap();
		
		queue.clear();
		queue.offer(new int[] {0,0});
		copy[0][0] = -1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = cur[0] + dr[dir];
				int nc = cur[1] + dc[dir];
				
				if(!check(nr,nc) || map[nr][nc] == 1 || copy[nr][nc] == -1) continue; // 이 부분 중요! 
				copy[nr][nc] = -1;
				queue.offer(new int[] {nr, nc});
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

	private static void copyMap() {
		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, copy[i], 0, M);
		}
	}
}
