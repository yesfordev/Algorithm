package algo0202;

import java.util.Scanner;

public class swea_D3_1208 {

	static int T=10;
	static int W=100;
	static int [] map;
	static int dump;
	
	public static void main(String[] args) {
		
		Scanner scann = new Scanner(System.in);
		
		for (int t = 1; t <= T; t++) {
			dump=scann.nextInt();
			map=new int[W];
			for (int i = 0; i <W; i++) {
				map[i]=scann.nextInt();
			}
			
			int diff=0;
			for (int i = 0; i <= dump; i++) {
				int hmax=Integer.MIN_VALUE;
				int hmin=Integer.MAX_VALUE;
				int hindex=-1;
				int lindex=-1;
				
				
				// 로직
				for (int j = 0; j < W; j++) {
					if(hmax<map[j]) {
						hmax=map[j];
						hindex=j;
					}
					if(hmin>map[j]) {
						hmin=map[j];
						lindex=j;
					}
				}

				diff=hmax-hmin;
				
				map[hindex]--;
				map[lindex]++;
				
				if(hmax-hmin<=1) {
					break;
				}
				//
			}
			System.out.println("#"+t+" "+diff);
		}

	}

}
