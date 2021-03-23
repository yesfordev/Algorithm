package algo0323;

import java.util.Scanner;

/**
 * DP 문제!
 * @author yes
 *
 */
public class BOJ_Silver3_1463_1로만들기 {

	static int N;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) {

		Scanner scann = new Scanner(System.in);
		
		N = scann.nextInt();
		
		int[] D = new int[N+1];
		
		D[0] = 0;
		D[1] = 0;
		
		for (int i = 2; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			if(D[i-1]+1 < min) min = D[i-1]+1;
			if(i>=2 && i%2==0 && D[i/2]+1<min) min = Math.min(min, D[i/2]+1);
			if(i>=3 && i%3==0 && D[i/3]+1<min) min = Math.min(min, D[i/3]+1);
			D[i] = min;
		}
		System.out.println(D[N]);
	}
}