package algo210725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_Silver4_10773_제로 {

	static int K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(in.readLine());
		
		Stack<Long> stack = new Stack<Long>();
		
		for (int idx = 0; idx < K; idx++) {
			long curNum = Long.parseLong(in.readLine());
			
			if(curNum == 0) stack.pop();
			else stack.push(curNum);
		}
		
		long sum = 0;
		while(!stack.isEmpty()) {
			sum += stack.pop();
		}
		
		System.out.println(sum);
	}

}
