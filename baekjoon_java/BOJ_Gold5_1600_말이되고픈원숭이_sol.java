package algo0324;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_Gold5_1600_말이되고픈원숭이_sol {
	
	// 동서남북 좌우상하(말)
	static int[] dr = {1,-1, 0, 0};
	static int[] dc = {0, 0, 1, -1}; 
	static int[] hr = {-2, -2, 2, 2, 1, -1, 1, -1};
	static int[] hc = {1, -1, 1, -1, 2, 2, -2, -2};
	
	static int K, W, H, answer=Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] visit;
	static class Monkey{
		int r;
		int c;
		int cnt;
		int k;
		
		Monkey(int r, int c, int cnt, int k){
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.k = k;
		}
	}
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();
		
		map = new int[H][W];
		visit = new boolean[H][W][K+1];
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		visit[0][0][0] = true;
		solve();
		
//		if(answer == Integer.MAX_VALUE) System.out.println("-1");
//		else System.out.println(answer);
	}

	private static void solve() {
		Queue<Monkey> q = new LinkedList<Monkey>();
		q.add(new Monkey(0,0,0,K));
		while(!q.isEmpty()) {
			Monkey cur = q.poll();
			int r=cur.r;
			int c=cur.c;
			int cnt=cur.cnt;
			int curK=cur.k;
			
			if(r==H-1 && c==W-1) {
				System.out.println(cnt);
				return ;
			}
			if(!check(r,c)) continue;
			if(map[r][c]==1) continue; // 장애물있으면 점프
			if(visit[r][c][curK]) continue; // 온적있니?
			visit[r][c][curK]=true;
			//monkey
			for (int m = 0; m < 4; m++) {
				int nr = r+dr[m];
				int nc = c+dc[m];
				q.add(new Monkey(nr, nc, cnt+1, curK));
			}
			//horse
			if(curK==0) continue;
			visit[r][c][curK-1] = true;
			for (int m = 0; m < 8; m++) {
				int nr = r+hr[m];
				int nc = c+hc[m];
				q.add(new Monkey(nr, nc, cnt+1, curK-1)); //넣어놓고 다음에 체크!
			}
		}
		System.out.println("-1");
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W;
	}
}
