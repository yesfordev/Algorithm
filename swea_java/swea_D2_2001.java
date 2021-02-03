package algo0203;

import java.util.Scanner;

public class swea_D2_2001 {

	static int T,N,M,answer;
	static int[][] map;
	public static void main(String[] args) {		
		Scanner scann = new Scanner(System.in);	
		T=scann.nextInt();
		for (int t = 1; t <= T; t++) {
			N=scann.nextInt();
			M=scann.nextInt();
			answer=Integer.MIN_VALUE;
			map=new int[N+2][N+2];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j]=scann.nextInt();
				}
			}
			for (int i = 0; i<= N-M; i++) {
				for (int j = 0; j <= N-M; j++) {
					int sum=0;
					for (int m = 0; m < M; m++) {
						for (int k = 0; k < M; k++) {
							sum+=map[i+m][j+k];
						}
					}
					answer=Math.max(sum, answer);
				}
			}
			System.out.println("#"+t+" "+answer);
		}
	}
}
