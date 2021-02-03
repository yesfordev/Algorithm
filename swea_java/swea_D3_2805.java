package algo0203;

import java.util.Scanner;

public class swea_D3_2805 {

	static int T,N,sum;
	static int[][] map;
	
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		T=scann.nextInt();
		
		for (int t = 1; t <= T; t++) {
			N=scann.nextInt();
			sum=0;
			map=new int[N+2][N+2];
			for (int i = 0; i < N; i++) {
				String s=scann.next();
				char[] sc=s.toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j]=sc[j]-'0';
				}
			}
			
			diamondSum();
			
			System.out.println("#"+t+" "+sum);
		}

	}

	private static void diamondSum() {
		for (int i = 0; i <= N/2; i++) {
			for (int j = N/2-i; j <= N/2+i; j++) {
				sum+=map[i][j];
			}
		}
		for (int i = N-1; i > N/2; i--) {
			for (int j = i-N/2; j <= 3*N/2-1-i ; j++) {
				sum+=map[i][j];
			}
		}
	}

}
