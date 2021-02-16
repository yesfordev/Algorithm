package algo0216;

import java.util.Scanner;

public class BOJ_2839_설탕배달 {

	static int N;
	static int minC=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		N=scann.nextInt();
		
		//로직 
//		sugar(N,0,0);
		for (int i = 0; i < N/5+1; i++) {
			if((N-i*5)%3==0) {
				minC = Math.min(minC, i+(N-i*5)/3);
			}
		}
		//출력
		System.out.println(minC==Integer.MAX_VALUE?-1:minC);
	}

	private static void sugar(int n, int f, int t) {
		if(t>N/3+1) return;
		if(n<0) return;
		if(n==0) {
			minC=Math.min(minC, f+t);
			return;
		}
		sugar(n-5,f+1,t);
		sugar(n-3, f, t+1);
	}
}
