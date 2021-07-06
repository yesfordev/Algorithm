package algo210703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold3_1937_욕심쟁이판다 {
	
	static int n, map[][], visited[][], maxDay;
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int dr[] = {1,-1,0,0};
	static int dc[] = {0,0,1,-1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(in.readLine());
		
		map = new int[n][n];
		visited = new int[n][n];
		maxDay = -1;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				
				if(visited[r][c] != 0) continue;
				
				visited[r][c] = 1;
				queue.offer(new int[] {r,c});
				
				while(!queue.isEmpty()) {
					int curR = queue.peek()[0];
					int curC = queue.peek()[1];
					queue.poll();
					
					for (int dir = 0; dir < 4; dir++) {
						int nr = curR + dr[dir];
						int nc = curC + dc[dir];
						
						if(!check(nr,nc) || map[nr][nc] >= map[curR][curC]) continue;
						if(visited[nr][nc] < visited[curR][curC] + 1) {
							visited[nr][nc] = visited[curR][curC] + 1;
							
							if(maxDay < visited[nr][nc]) maxDay = visited[nr][nc];
							queue.offer(new int[] {nr,nc});
						}
					}
				}
			}
		}
		
		System.out.println(maxDay);
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<n && c>=0 && c<n;
	}
}
