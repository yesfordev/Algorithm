package algo0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109_빵집 {

	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int cnt;
	static int[] dr= {-1,0,1};

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(in.readLine()," ");
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		map=new char[R][];
		visited=new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i]=in.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			visited[i][0]=true;
			setPipe(i, 0);
		}
		
		System.out.println(cnt);
	}

	private static boolean setPipe(int rowNo, int colNo) {
		if(colNo==C-1) {
			cnt++;
			return true;
		}
				
		for (int dir = 0; dir < 3; dir++) {
			int nr = rowNo + dr[dir];
			int nc = colNo+1;
			
			if(nr<0 || nr>=R || map[nr][nc] == 'x' || visited[nr][nc]) continue;
				
			visited[nr][nc]=true;
			if(setPipe(nr, nc)) return true;
			
		}
		return false;
	}
}
