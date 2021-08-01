package algo2108_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_14500_테트로미노 {

	static int N, M, map[][], maxSum;
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				dfs(r, c, 1, map[r][c]);
				exception(r, c);
			}
		}
		
		System.out.println(maxSum);
	}
	
	static int dr[] = {0,0,-1,1};
	static int dc[] = {1,-1,0,0};
	private static void dfs(int r, int c, int depth, int sum) {
		if(depth == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		visited[r][c] = true;
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(!check(nr, nc) || visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			dfs(nr, nc, depth+1, sum + map[nr][nc]);
			visited[nr][nc] = false;
		}
		
		visited[r][c] = false;
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

	private static void exception(int r, int c) {
		int sum = map[r][c];
		int wing = 0;
		int minSquare = 1001;
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(!check(nr, nc)) continue;
			
			++wing;
			sum += map[nr][nc];
			minSquare = Math.min(minSquare, map[nr][nc]);
		}
		
		if(wing <= 2) {
			return;
		} else if(wing == 3) {
			maxSum = Math.max(maxSum, sum);
			return;
		} else if(wing == 4) {
			maxSum = Math.max(maxSum, sum - minSquare);
			return;
		}
	}
}
