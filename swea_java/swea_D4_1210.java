package algo0202;

import java.util.Scanner;

public class swea_D4_1210 {

	static int T=10;
	static int N;
	static int[] dr = {0,0,-1}; //왼오위
	static int[] dc = {-1,1,0}; //왼오위
	static int[][] ladder;
	
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		for (int t = 0; t < T; t++) {
			N=scann.nextInt();
			
			ladder = new int[102][102];
			int goalR = -1;	//2의 위치
			int goalC = -1;	//2의 위치
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = scann.nextInt();
					if(ladder[i][j]==2) {
						goalR = i; //행 99
						goalC = j; //열
					}
				}
			}

			int d=2;	//윗방향 시작
			while(goalR!=0) {
				if(d!=1 && goalC-1>=0 && ladder[goalR][goalC-1]>0) {	//오른쪽
					d=0;
				}
				// 위로가다 오른쪽으로 이동, 원래 오른쪽
				if(d!=0 && goalC+1<100 && ladder[goalR][goalC+1]>0) {	//왼쪽
					d=1;
				}
				int nr=goalR+dr[d];
				int nc=goalC+dc[d];
				
				if(nr>=100 || nr<0 || nc>=100 || nc<0 || ladder[nr][nc]==0) {
					d=2;
					nr=goalR+dr[d];
					nc=goalC+dr[d];
				}
				goalR=nr;
				goalC=nc;
				
			}
			System.out.println("#"+t+" "+ goalC);
		}

	}

}
