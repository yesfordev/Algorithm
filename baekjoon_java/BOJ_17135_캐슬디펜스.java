package algo0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 {

	static int N, M, D;
	static int[][] map = new int[17][17];
	static int[][] temp = new int[17][17];
	static int answer = Integer.MIN_VALUE;
	static ArrayList<RC> enemies = new ArrayList<>();
	static HashSet<RC> finishEnemies = new HashSet<>();
	static HashSet<RC> tempEnemies = new HashSet<>();

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					enemies.add(new RC(i, j));
				}
			}
		}

		mC3();

		System.out.println(answer);
	}

	private static void mC3() {
		int n = N;
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					check(n, i, j, k);
				}
			}
		}
	}

	private static void check(int n, int i, int j, int k) {
		initTemp();
		finishEnemies.clear();
//		System.out.println(i+" "+j+" "+k+" start");

		while (true) {
			if (n == 0)
				break;
			tempEnemies.clear();

			startBow(n, i);
			startBow(n, j);
			startBow(n, k);

			Iterator<RC> iter = tempEnemies.iterator();

			while (iter.hasNext()) {
				RC deleteTemp = iter.next();
//				System.out.println(deleteTemp.r+ " " + deleteTemp.c);
				temp[deleteTemp.r][deleteTemp.c] = 0;
			}
//			System.out.println();
			--n;
		}

		answer = Math.max(answer, finishEnemies.size());
	}

	private static void startBow(int n, int bowC) {
		int minDist = Integer.MAX_VALUE;
		int minR = -1;
		int minC = -1;
		for (RC enemy : enemies) {
			if (enemy.r >= n - D && enemy.r < n) {
				if(temp[enemy.r][enemy.c] != 1) continue;
				int dist = Math.abs(n - enemy.r) + Math.abs(bowC - enemy.c);
				if (dist <= D) {
					if (minDist > dist) {
						minDist = dist;
						minR = enemy.r;
						minC = enemy.c;
					} else if (minDist == dist && enemy.c < minC) {
						minR = enemy.r;
						minC = enemy.c;
					}
				}
			}
		}
		if (minDist != Integer.MAX_VALUE) {
			finishEnemies.add(new RC(minR, minC));
			tempEnemies.add(new RC(minR, minC));
		}
	}

	private static void initTemp() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
	}

	public static class RC {
		int r;
		int c;

		public RC(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public RC() {
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			RC rc = (RC) obj;
			if (rc.r == this.r && rc.c == this.c) {
				return true;
			} else {
				return false;
			}
		}
	}
}
