package algo0209;

import java.util.Scanner;

public class BOJ_1168_요세푸스문제 {
	
	static int[] arr;
	static int idx,N,K;

	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		StringBuilder sb=new StringBuilder();
		
		N=scann.nextInt();
		K=scann.nextInt();
		
		arr=new int[N];
		for (int i = 0; i < N; i++) {
			arr[i]=i+1;
		}
		int cur=0;
		idx=N-1;
		
		sb.append("<");
		
		while(true) {
			if(idx==0) {
				sb.append(arr[0]+", ");
				break;
			}
			cur = (cur+K-1)%idx;
			sb.append(arr[cur]);
			delete(cur);
			idx--;
		}
		sb.append(">");
		
		System.out.println(sb.toString());
	}

	private static void delete(int delIdx) {
		for (int i = delIdx; i < idx; i++) {
			arr[i]=arr[i+1];
		}
		for (int i = 0; i < idx; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		
	}

}
