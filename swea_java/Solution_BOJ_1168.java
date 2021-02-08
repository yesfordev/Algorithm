package algo0209;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution_BOJ_1168 {

	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		int N=scann.nextInt();
		int K=scann.nextInt();
		ArrayList<Integer> origin=new ArrayList<Integer>();
		ArrayList<Integer> josep=new ArrayList<Integer>();
		
		for (int i = 1; i <= N; i++) {
			origin.add(i);
		}
		int stemp=0;
		
		while(!origin.isEmpty()) {
			stemp=(stemp+K-1)%origin.size();
			josep.add(origin.remove(stemp));
		}
		
		System.out.print("<");
		for (int i = 0; i < josep.size()-1; i++) {
			System.out.print(josep.get(i) +", ");
		}
		System.out.print(josep.get(josep.size()-1));
		System.out.println(">");
	}
}