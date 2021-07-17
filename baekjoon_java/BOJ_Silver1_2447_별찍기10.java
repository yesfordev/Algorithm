package algo210710;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_Silver1_2447_별찍기10 {

	static int N;
	static boolean map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		map = new boolean[N][N];
		
		func(N, 0, 0, true);
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c]) sb.append('*');
				else sb.append(' ');
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		in.close();
	}
	
	private static void func(int n, int startR, int startC, boolean check) {
		if(n == 1) {
			map[startR][startC] = check;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(i==1 && j==1) continue;
				func(n/3, startR + i*n/3, startC + j*n/3, true);
			}
		}
	}
}
