package algo0215;

import java.util.Scanner;

public class BOJ_2961_도영이가만든맛있는음식 {
	
	static int N;
	static int[] sour;
	static int[] sseun;
	static boolean[] isSelected;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		N=scann.nextInt();
		sour = new int[N];
		sseun = new int[N];
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			sour[i] = scann.nextInt();
			sseun[i] = scann.nextInt();
		}
		subset(0);
		System.out.println(answer);
	}

	private static void subset(int cnt) {
		if(cnt==N) {
			int mulSour=1;
			int sumSseun=0;
			int check=0;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					mulSour*=sour[i];
					sumSseun+=sseun[i];
					check++;
				}
			}
			if (check > 0) {
				int cha = Math.abs(mulSour - sumSseun);
				answer = Math.min(answer, cha);
			}
			return;
		}
		
		isSelected[cnt]=true;
		subset(cnt+1);
		isSelected[cnt]=false;
		subset(cnt+1);
	}
}
