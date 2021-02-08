package hw_algo;

import java.util.Scanner;

public class swea_D3_9229 {
	static int TC, N, M;
	static int[] snack;
	static int R = 2;
	static int answer;
	static int[] pick;
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);

		TC = scann.nextInt();

		for (int t = 1; t <= TC; t++) {
			N = scann.nextInt();
			M = scann.nextInt();

			answer = -1;
			snack = new int[N];
			pick = new int[R];
			for (int i = 0; i < N; i++) {
				snack[i] = scann.nextInt();
			}
			nCr(0, 0);
			System.out.println("#" + t + " " + answer);
		}
	}
	private static void nCr(int cnt, int start) {
		if (cnt == R) {
			int sum = 0;
			for (int i = 0; i < R; i++) {
				sum += pick[i];
			}
			if (sum <= M) {
				answer = Math.max(answer, sum);
			}
			return;
		}
		for (int i = start; i < N; i++) {
			pick[cnt] = snack[i];
			nCr(cnt + 1, i + 1);
		}
	}
}
