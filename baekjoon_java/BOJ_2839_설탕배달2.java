package algo0216;

import java.util.Arrays;
import java.util.Scanner;

//OK - dp
public class BOJ_2839_설탕배달2 {
	

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		int N=scann.nextInt();
		int[] sugar=new int[N+10];
		Arrays.fill(sugar, 9999);
		
		sugar[3]=1;
		sugar[5]=1;
		
		for (int i = 6; i < sugar.length; i++) {
			sugar[i]=Math.min(sugar[i-5], sugar[i-3])+1;
		}
		if(sugar[N]>=9999) System.out.println(-1);
		else System.out.println(sugar[N]);
		
	}
}
