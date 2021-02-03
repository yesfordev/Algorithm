package algo0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_D3_1873 {

	static int T, H, W, N, curX, curY, dir;
	static String state;
	static char map[][];
	static int[] dx = { -1, 1, 0, 0 }; // 위아왼오
	static int[] dy = { 0, 0, -1, 1 }; // 위아왼오

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			curX = -1;
			curY = -1;
			dir = -1;

			map = new char[H + 2][W + 2];

			for (int i = 0; i < H; i++) {
				String temp = in.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = temp.charAt(j);

					if (temp.charAt(j) == '^') {
						curX = i;
						curY = j;
						dir = 0;
					} else if (temp.charAt(j) == 'v') {
						curX = i;
						curY = j;
						dir = 1;
					} else if (temp.charAt(j) == '<') {
						curX = i;
						curY = j;
						dir = 2;
					} else if (temp.charAt(j) == '>') {
						curX = i;
						curY = j;
						dir = 3;
					}
				}
			}

			N = Integer.parseInt(in.readLine());

			state = in.readLine();

			// 로직

			for (int i = 0; i < N; i++) {
				if (state.charAt(i) == 'S') {
					int shX = curX;
					int shY = curY;
					int shDir = dir;
					shoot(shX, shY, shDir);
				} else {
					move(state.charAt(i));
				}
			}

			//
			System.out.print("#" + t + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}

	}

	private static void move(char stateChar) {
		if (stateChar == 'U') {
			map[curX][curY] = '^';
			dir = 0;
		} else if (stateChar == 'D') {
			map[curX][curY] = 'v';
			dir = 1;
		} else if (stateChar == 'L') {
			map[curX][curY] = '<';
			dir = 2;
		} else if (stateChar == 'R') {
			map[curX][curY] = '>';
			dir = 3;
		}

		int nx = curX + dx[dir];
		int ny = curY + dy[dir];

		if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '#' || map[nx][ny] == '*' || map[nx][ny] == '-')
			return;
		map[nx][ny] = map[curX][curY];
		map[curX][curY] = '.';

		curX = nx;
		curY = ny;
	}

	private static void shoot(int shX, int shY, int shDir) {
		while (true) {
			int nx = shX + dx[shDir];
			int ny = shY + dy[shDir];

			if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '#')
				break; // 맵 밖으로 나가거나 강철 만나면 종료

			if (map[nx][ny] == '*') {
				map[nx][ny] = '.';
				break;
			}
			shX = nx;
			shY = ny;
		}
	}

}
