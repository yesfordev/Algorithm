package hw_algo;

import java.util.Scanner;

public class swea_D2_1954 {

	static int T,N;
	static int[][] map;
	static int[] dx= {0,1,0,-1}; // 오아왼위
	static int[] dy= {1,0,-1,0};
	
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		T=scann.nextInt();
		
		for (int t = 1; t <= T; t++) {
			N=scann.nextInt();
			map=new int[N+2][N+2];
			
			int x=0;
			int y=0;
			int dir=0;
			for (int num = 1; num <= N*N ; num++) {
				map[x][y]=num;
				int nx=x+dx[dir];
				int ny=y+dy[dir];
				
				if(nx>=N || nx<0 || ny>=N || ny<0 || map[nx][ny] != 0) {
					dir+=1;
					dir%=4;
					
					nx=x+dx[dir];
					ny=y+dy[dir];
				}
				x=nx;
				y=ny;
			}
			
			System.out.println("#"+t);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			
		}

	}

}
