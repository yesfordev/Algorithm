package algo0216;

import java.util.Scanner;

public class BOJ_1074_Z {

	static int N,r,c,cnt;
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		N=scann.nextInt();
		r=scann.nextInt();
		c=scann.nextInt();
		
		cnt=0;
		//로직
		z(0,0,1<<N); // 1<<N : 2의 N
	}
	
	private static void z(int rr, int cc, int width) {
		if (rr == r && cc == c) {
			System.out.println(cnt);
			return;
		}
		
		if(r>=rr && r<rr+width && c>=cc && c<cc+width) {
			int w=width/2;

			z(rr, cc, w);
			z(rr, cc + w, w);
			z(rr + w, cc, w);
			z(rr+w, cc+w, w);
		} else cnt+=width*width;
	}
}
