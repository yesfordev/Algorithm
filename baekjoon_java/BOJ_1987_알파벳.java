package algo0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {

	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static boolean[] alphabet=new boolean[26];
	static int answer=Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(in.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		
		visited[0][0]=true;
		dfs(0,0,1);
		
		System.out.println(answer);
	}

	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	
	private static void dfs(int rr, int cc, int cnt) {
		alphabet[map[rr][cc]-'A']=true;
		
		for (int dir = 0; dir < 4; dir++) {
			int nr=rr+dr[dir];
			int nc=cc+dc[dir];
			
			if(nr<0 || nr>=R || nc<0 || nc>=C || visited[nr][nc] || alphabet[map[nr][nc]-'A']) continue;
			
			visited[nr][nc]=true;
			dfs(nr, nc, cnt+1);
			visited[nr][nc]=false;
		}
		alphabet[map[rr][cc]-'A']=false;
		
		answer = Math.max(answer, cnt);
		return;
	}
}
