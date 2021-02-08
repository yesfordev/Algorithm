package algo0208;

import java.util.Scanner;

public class swea_D3_5215 {
	static int T,N,L;
	static int[] flavor;
	static int[] cal;
	static int answer;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		T=scann.nextInt();
		
		for (int t = 1; t <= T; t++) {
			N=scann.nextInt();
			L=scann.nextInt();
			
			answer = Integer.MIN_VALUE;
			flavor=new int[N];
			cal=new int[N];
			isSelected=new boolean[N];
			for (int i = 0; i < N; i++) {
				flavor[i]=scann.nextInt();
				cal[i]=scann.nextInt();
			}
			subSet(0);
			System.out.println("#"+t+" "+answer);
		}
	}
	private static void subSet(int cnt) {
		if(cnt==N) {
			int calSum=0;
			int calFlavor=0;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					calSum+=cal[i];
					calFlavor+=flavor[i];
				}
			}
			if(calSum<=L) {
				answer = Math.max(answer, calFlavor);
			}
			return;
		}
		isSelected[cnt]=true;
		subSet(cnt+1);
		isSelected[cnt]=false;
		subSet(cnt+1);
	}
}
