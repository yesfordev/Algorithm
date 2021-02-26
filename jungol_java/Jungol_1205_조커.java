package algo210227;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Jungol_1205_조커 {

	static int N;
	static int[] card;
	static boolean[] range;
	static int zCnt;
	static int answer=Integer.MIN_VALUE;
	static ArrayList<Integer> falseIndex = new ArrayList<Integer>();
	static int[] comb;
	
	public static void main(String[] args) {

		Scanner scann = new Scanner(System.in);
		N=scann.nextInt();
		card = new int[N];
		
		for (int i = 0; i < N; i++) {
			card[i] = scann.nextInt();
		}
		
		Arrays.sort(card);
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if(card[i] == 0) zCnt++;
			else {
				min = card[i];
				break;
			}
		}
		int max = card[N-1];
		
		range = new boolean[max-min+1+2*zCnt];
		
		//range에 index 이용하여 갯수 새기 
		for (int i = 0; i < N; i++) {
			if(card[i]!=0) {
				range[card[i]-min+zCnt] = true;
			}
		}
		
		// range에서 false인 부분의 index 저장
		for (int i = 0; i < range.length; i++) {
			if(!range[i]) {
				falseIndex.add(i);
			}
		}
		
		comb=new int[zCnt];
		
		// 인덱스로 조합! 
		nCr(0, 0);
		
		System.out.println(answer);
	}

	private static void nCr(int cnt, int start) {
		if(cnt == zCnt) {
			checkStrait();
			return;
		}
		
		for (int i = start; i < falseIndex.size(); i++) {
			comb[cnt]=falseIndex.get(i);
			nCr(cnt+1, i+1);
		}
		
	}

	private static void checkStrait() {
		for (int i = 0; i < zCnt; i++) {
			range[i] = true;
		}
		
		// 체크 
		int strait=0;
		for (int i = 0; i < range.length; i++) {
			if(range[i]) {
				strait++;
				answer = Math.max(strait, answer);
			} else {
				strait = 0;
			}
		}
		
		for (int i = 0; i < zCnt; i++) {
			range[i] = false;
		}
	}

}
