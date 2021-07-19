package algo210725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_Silver3_1874_스택수열 {

	static int n, arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(in.readLine());
		arr = new int[n];

		for (int idx = 0; idx < n; idx++) {
			arr[idx] = Integer.parseInt(in.readLine());
		}

		int arrIdx = 0;

		int num = 1;
		while(num <= n) {
			if (!stack.isEmpty() && arr[arrIdx] == stack.peek()) {
				sb.append("-\n");
				stack.pop();
				++arrIdx;
			} else {
				stack.push(num);
				sb.append("+\n");
				++num;
			}
		}

		int size = stack.size();

		for (int i = 0; i < size; i++) {
			if (stack.peek() == arr[arrIdx]) {
				sb.append("-\n");
				stack.pop();
				++arrIdx;
			} else {
				break;
			}
		}

		if (arrIdx < n) {
			System.out.println("NO");
		} else {
			System.out.println(sb.toString());
		}
	}
}
