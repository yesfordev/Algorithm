package algo2108_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_Silver4_4949_균형잡힌세상 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		String str;
		
		while(true) {
			stack.clear();
			str = in.readLine();
			
			if(str.equals(".")) {
				break;
			}
			
			boolean pass = true;
			for (int idx = 0; idx < str.length(); idx++) {
				if(str.charAt(idx) == '(') {
					stack.push(1);
				} else if(str.charAt(idx) == '[') {
					stack.push(2);
				}
				
				if(str.charAt(idx) == ')') {
					if(!stack.isEmpty() && stack.peek() == 1) {
						stack.pop();
					} else {
						pass = false;
						break;
					}
				} else if(str.charAt(idx) == ']') {
					if(!stack.isEmpty() && stack.peek() == 2) {
						stack.pop();
					} else {
						pass = false;
						break;
					}
				}
			}
			
			if(!stack.isEmpty()) {
				sb.append("no\n");
				continue;
			}
			
			if(pass) {
				sb.append("yes\n");
			} else {
				sb.append("no\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
