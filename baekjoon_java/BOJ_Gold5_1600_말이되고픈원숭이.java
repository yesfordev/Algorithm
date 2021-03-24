package algo0324;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 방문 표시 안하면 시간초과 난다!!!
 * BFS
 * visit 3차원 배열 만드는거 중요 *** => 같은 방식으로만 이동하는 것이아니라, 여러 방식으로 이동할 때 3차원 배열로! 
 * @author yes
 *
 */
public class BOJ_Gold5_1600_말이되고픈원숭이 {
	
	// 동서남북 좌우상하(말)
	static int[] dr = {1,-1, 0, 0};
	static int[] dc = {0, 0, 1, -1}; 
	static int[] hr = {-2, -2, 2, 2, 1, -1, 1, -1};
	static int[] hc = {1, -1, 1, -1, 2, 2, -2, -2};
	
	static int K, W, H, answer=Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] visit; // 중요!!
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
		
		if(answer == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(answer);
	}

	private static void solve() {
		Queue<Monkey> queue = new LinkedList<Monkey>();
		
		queue.offer(new Monkey(0,0,0,K));
		
		while(!queue.isEmpty()) {
			Monkey curMonkey = queue.poll();
			
			if(curMonkey.r == H-1 && curMonkey.c == W-1 ) {
				answer = Math.min(answer, curMonkey.cnt);
				return;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nr = curMonkey.r + dr[dir];
				int nc = curMonkey.c + dc[dir];
				
				if(!check(nr,nc) || map[nr][nc] == 1 || visit[nr][nc][curMonkey.k]) continue;
				visit[nr][nc][curMonkey.k] = true; // 방문 표시 필수!!
				queue.offer(new Monkey(nr,nc,curMonkey.cnt+1,curMonkey.k));
			}
			
			if(curMonkey.k >= 1) {
				for (int dir = 0; dir < 8; dir++) {
					int nr = curMonkey.r + hr[dir];
					int nc = curMonkey.c + hc[dir];
					
					if(!check(nr,nc) || map[nr][nc] == 1 || visit[nr][nc][curMonkey.k-1]) continue;
					visit[nr][nc][curMonkey.k-1] = true;// 방문 표시 필수!!
					queue.offer(new Monkey(nr, nc, curMonkey.cnt+1, curMonkey.k-1));
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W;
	}
}
