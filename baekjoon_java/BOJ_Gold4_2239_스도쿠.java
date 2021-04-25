package yes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_Gold4_2239_스도쿠 {

	static int zeroCnt=0, realMap[][];
	static List<int[]> list = new ArrayList<int[]>();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int map[][] = new int[9][9];
		
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(in.readLine());
			char[] str = st.nextToken().toCharArray();
			
			for (int j = 0; j < 9; j++) {
				map[i][j] = str[j] - '0';
				if(map[i][j] == 0) {
					list.add(new int[] {i, j});
				}
			}
		}
		
		
//		dfs2(1, 0, 0, map);
//		dfs(0, map);
		dfs3(0, map);
		
	}
	
	private static void dfs3(int idx, int[][] map) {
		
		if(idx == list.size()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		
		int curR = list.get(idx)[0];
		int curC = list.get(idx)[1];
		
		for (int num = 1; num <= 9; num++) {
			if(checkRow(curR, curC, num, map) && checkCol(curR, curC, num, map) && checkRange(curR, curC, num, map)) {
				map[curR][curC] = num;
				dfs3(idx+1, map);
				map[curR][curC] = 0;
			}
		}
	}

//	private static void dfs2(int cnt, int r, int c, int[][] map) {
//
//		if(cnt == 2) {
////			for (int i = 0; i < 9; i++) {
////				for (int j = 0; j < 9; j++) {
////					System.out.print(map[i][j]);
////				}
////				System.out.println();
////			}
//			copyMap(map);
//			return;
//		}
//		
//		if(r==9) {
//			return;
//		}
//		
//		if(map[r][c] != 0) {
//			dfs2(cnt, (c+1)>=9?r+1:r,(c+1)>=9?(c+1)%9:(c+1), map);
//			return;
//		}
//		
//		// 0 이라면
////		if(c==0) num = 0;
//
////		int check=0;
//		while(true) {
////			++check;
//			++map[r][c];
//
//			if(map[r][c] == 10) {
//				map[r][c]=0;
//				return;
//			}
//			if(!checkRow(r, c, map) || !checkCol(r, c, map) || !checkRange(r,c,map)) { // 통과 못할 때
//				continue;
//			} else {
//				dfs2(cnt+1, (c+1)>=9?r+1:r,(c+1)>=9?(c+1)%9:(c+1), map);
//			}
//		}
//	}
	// 먼저 dfs 들어가면, 행 열 범위 체크 => 해당 안되면 더하기


	private static boolean checkRow(int r, int c, int num, int[][] map) {
		for (int i = 0; i < 9; i++) {
			if(i==c) continue;
			if(map[r][i] == num) return false;
		}
		return true;
	}

	private static boolean checkCol(int r, int c, int num, int[][] map) {
		for (int i = 0; i < 9; i++) {
			if(i==r) continue;
			if(map[i][c] == num) return false;
		}
		return true;
	}

	private static boolean checkRange(int r, int c, int num, int[][] map) {
		int startR=0, startC=0;
		if(r>=0 && r<3) startR = 0;
		else if(r>=3 && r<6) startR = 3;
		else startR = 6;
		
		if(c>=0 && c<3) startC = 0;
		else if(r>=3 && r<6) startC = 3;
		else startC = 6;
		
		for (int i = startR; i < startR+3; i++) {
			for (int j = startC; j < startC+3; j++) {
				if(i==r && j==c) continue;
				if(num == map[i][j]) return false;
			}
		}
		return true;
	}


}
