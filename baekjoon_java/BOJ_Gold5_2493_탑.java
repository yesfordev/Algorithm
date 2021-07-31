package algo2108_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_Gold5_2493_탑 {

	static Stack<Integer> stackIdx = new Stack<>();
	static Stack<Integer> stack = new Stack<>();
	static int top[], result[], N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());
		
		top = new int[N+1];
		result = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int idx = 1; idx <= N; idx++) {
			top[idx] = Integer.parseInt(st.nextToken());
		}
		
		for (int idx = 1; idx <= N; idx++) {
			while(!stack.isEmpty() && stack.peek() < top[idx]) {
				stackIdx.pop();
				stack.pop();
			}
			
			if(!stack.isEmpty()) {
				result[idx] = stackIdx.peek();
			}
			
			sb.append(result[idx]+" ");
			
			stack.add(top[idx]);
			stackIdx.add(idx);
		}
		
		System.out.println(sb.toString());
	}
}
