package hw_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_2493 {
	
	static int N;
	static int[] top;
	static int[] answer;
	static Stack<Integer> stack = new Stack<>();
	static Stack<Integer> index = new Stack<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine(), " ");
		
		top = new int[N+2];
		answer = new int[N+2];
		
		for (int i = 0; i < N; i++) {
			top[i]=Integer.parseInt(st.nextToken());
			stack.push(top[i]);
		}
		
		int cnt=N;
		for (int i = N-1; i >= 0; i--) {
			while(true) {
				if(stack.isEmpty()) {
					answer[i]=0;
					break;
				}
				
				if(top[i]>stack.peek()) {
					stack.pop();
					--cnt;
				} else {
					answer[i]=cnt;
					break;
				}
			}
 		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(answer[i]+" ");
		}		
	}
}
