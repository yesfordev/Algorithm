package algo0205;

import java.util.Scanner;
import java.util.Stack;

public class swea_D4_1861 {

	static int T, N, startNum, visitNum;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Stack<int[]> stack = new Stack<int[]>();

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		T = scann.nextInt();
		for (int t = 1; t <= T; t++) {
			N = scann.nextInt();

			map = new int[N + 2][N + 2];
			visit = new boolean[N + 2][N + 2];
			stack.clear();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = scann.nextInt();
				}
			}
			startNum = Integer.MAX_VALUE;
			visitNum = 0;
			dfs();

			System.out.println("#" + t + " " + startNum + " " + visitNum);
		}
	}

	private static void dfs() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j])
					continue;
				int tempVisitNum = 1;
				int tempStartNum = 0;
				int[] start = { i, j };
				stack.push(start);
				tempStartNum = map[i][j];
				visit[i][j] = true;

				while (!stack.isEmpty()) {
					int[] cur = stack.pop();

					for (int dir = 0; dir < 4; dir++) {
						int nx = cur[0] + dx[dir];
						int ny = cur[1] + dy[dir];

						if (nx < 0 || nx >= N || ny < 0 || ny >= N)
							continue;
						if (visit[nx][ny])
							continue;

						if (map[nx][ny] == map[cur[0]][cur[1]] + 1) {
							tempVisitNum++;
							visit[nx][ny] = true;

							int[] next = { nx, ny };
							stack.push(next);
						}
					}
				}
				if (tempVisitNum >= visitNum) {
					visitNum = tempVisitNum;
					startNum = Math.min(startNum, tempStartNum);
				}
				
				if(tempVisitNum == 1) {
					startNum = tempStartNum;
				}
			}
		}
	}
}
