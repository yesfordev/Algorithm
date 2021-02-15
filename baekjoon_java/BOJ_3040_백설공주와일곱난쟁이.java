package algo0215;

import java.util.Scanner;

public class BOJ_3040_백설공주와일곱난쟁이 {

	static int[] nan;
	static int[] realNan;
	static int[] copyNan;
	
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		nan = new int[9];
		realNan = new int[7];
		copyNan = new int[7];
		
		
		for (int i = 0; i < 9; i++) {
			nan[i]=scann.nextInt();
		}
		
		nCr(0,0);
		
		for(int real : copyNan) {
			System.out.println(real);
		}
	}

	private static void nCr(int cnt, int start) {
		if(cnt==7) {
			int sum=0;
			for (int i = 0; i < realNan.length; i++) {
				sum+=realNan[i];
			}
			if(sum==100) {
				System.arraycopy(realNan, 0, copyNan, 0, 7);
			}
			return;
		}
		
		for (int i = start; i < nan.length; i++) {
			realNan[cnt]=nan[i];
			nCr(cnt+1, i+1);
		}
		
		
	}

}
