package algo0218;

import java.util.Scanner;

public class swea_1247_최적경로 {

	static int T,N;
	static int[][] map;
	static int[] company = new int[2];
	static int[] home = new int[2];
	static int answer;
	static int[] p;
	
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		T=scann.nextInt();
		
		for (int t = 1; t <= T; t++) {
			N=scann.nextInt();
			
			company[0]=scann.nextInt();
			company[1]=scann.nextInt();
			home[0]=scann.nextInt();
			home[1]=scann.nextInt();
			
			map=new int[N][2];
			
			for (int i = 0; i < N; i++) {
				map[i][0] = scann.nextInt();
				map[i][1] = scann.nextInt();
			}
			
			p=new int[N];
			
			for (int i = 0; i < N; i++) {
				p[i]=i;
			}
			
			answer=Integer.MAX_VALUE;
			do {
				checkDist();
			}while(np(p.length-1));
			
			System.out.println("#"+t+" "+answer);
		}

	}

	private static void checkDist() {
		int sumDist = 0;
		for (int i = 0; i < N-1; i++) {
			sumDist = sumDist + Math.abs(map[p[i+1]][0]-map[p[i]][0]) + Math.abs(map[p[i+1]][1]-map[p[i]][1]);
		}
		sumDist = sumDist + Math.abs(company[0]-map[p[0]][0]) + Math.abs(company[1]-map[p[0]][1]);
		sumDist = sumDist + Math.abs(home[0]-map[p[N-1]][0]) + Math.abs(home[1]-map[p[N-1]][1]);
		
		answer=Math.min(answer, sumDist);
	}

	private static boolean np(int size) {
		int i=size;
		while(i>0 && p[i-1]>=p[i]) --i;
		
		if(i==0) return false;
		
		int j=size;
		while(p[i-1]>=p[j]) --j;
		int temp=p[i-1];
		p[i-1]=p[j];
		p[j]=temp;
		
		int k=size;
		while(i<k) {
			temp=p[i];
			p[i]=p[k];
			p[k]=temp;
			k--; i++;
		}
		return true;
	}
}