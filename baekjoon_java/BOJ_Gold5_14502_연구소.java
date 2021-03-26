package algo0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 바이러스 유출 -> NXM 직사각형의 연구실 
 * 빈 칸, 벽으로 이루어져 있다.
 * 바이러스 -> 상하좌우 인접한 빈칸으로 모두 퍼져나갈 수 있다. 
 * 
 * 새로 세울 수 있는 벽의 개수는 3개 -> 꼭 3개를 세워야 한다.
 * => 벽을 3개 세웠을 때, 얻을 수 있는 안전 영역 크기의 최댓값!
 * 안전영역 -> 1이랑 2 제외한 0의 값만 해당
 * 3개의 벽 -> 조합!! 가로 세로 조합으로 뽑아서 구하자. => 0인 부분의 좌표를 다 arraylist에 저장해두고, 3가지 경우 뽑기.
 */
public class BOJ_Gold5_14502_연구소 {

	static int N, M, zeroSize, answer;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<int[]> zero;
	static ArrayList<int[]> virus;
	static int[] comb = new int[3];
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		zero = new ArrayList<int[]>();
		virus = new ArrayList<int[]>();
		answer = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==0) zero.add(new int[] {i,j});
				if(map[i][j]==2) virus.add(new int[] {i,j});
			}
		}
		
		zeroSize = zero.size();
		
		selectWall(0, 0);
		
		System.out.println(answer);
	}
	
	private static void selectWall(int cnt, int start) {
		if(cnt == 3) {
			findSafeArea();
			return;
		}
		
		for (int i = start; i < zeroSize; i++) {
			comb[cnt] = i;
			selectWall(cnt+1, i+1);
		}
		
	}

	private static void findSafeArea() {
		for (int i = 0; i < comb.length; i++) {
			int[] wall = zero.get(comb[i]);
			map[wall[0]][wall[1]] = 1;
		}
		
		spreadVirus();
		
		for (int i = 0; i < comb.length; i++) {
			int[] wall = zero.get(comb[i]);
			map[wall[0]][wall[1]] = 0;
		}
	}

	private static void spreadVirus() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		for (int i = 0; i < virus.size(); i++) {
			queue.clear();
			
			queue.offer(new int[] {virus.get(i)[0], virus.get(i)[1]});
			visited[virus.get(i)[0]][virus.get(i)[1]] = true;
			
			while(!queue.isEmpty()) {
				int curR = queue.peek()[0];
				int curC = queue.peek()[1];
				queue.poll();
				
				for (int dir = 0; dir < 4; dir++) {
					int nr = curR + dr[dir];
					int nc = curC + dc[dir];
					
					if(!check(nr, nc) || map[nr][nc] == 1 || visited[nr][nc]) continue;
					visited[nr][nc] = true;
					queue.offer(new int[] {nr,nc});
				}
			}
		}
		
		//안전영역 크기 구하기
		int sum=0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 0) ++sum;
			}
		}
		
		answer = Math.max(answer, sum);
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}
