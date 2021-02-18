package algo0218;

import java.util.Scanner;

public class swea_D5_1265_달란트2 {

	static int T,N,P;
	
	public static void main(String[] args) {
	
		Scanner scann=new Scanner(System.in);
		T=scann.nextInt();
		for (int t = 1; t <= T; t++) {
			N=scann.nextInt();
			P=scann.nextInt();
			System.out.println("#"+t+" "+dal(N,P));
		}

	}

//	static long dal(int n, int p) {
//		if(p==1) {
//			return 1L*n;
//		} else {
//			return 1L*n/p*dal(n-(n/p),p-1);
//		}
//	}
	
	private static long dal(int n, int p) {
		
		int soo = n/p;
		int na = n%p;
		
		long answer=1L;
		if(na>0) {
			for (int i = 1; i <= p; i++) {
				if(na>0) {
					answer = answer * (soo+1) * 1L;
					--na;
				} else {
					answer = answer * soo * 1L;
				}
			}
		} else {
			for (int i = 1; i <= p; i++) {
				answer = answer * soo * 1L;
			}
		}
		return answer;
	}

}
