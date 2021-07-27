package algo210731;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ_Silveer4_10866_덱 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Deque<Integer> deque = new LinkedList<Integer>();
		
		int N = Integer.parseInt(in.readLine());
		int cur = 0;
		for (int i = 0; i < N; i++) {
			String[] strArr = in.readLine().split(" ");
			
			switch (strArr[0]) {
			case "push_back":
				deque.offerLast(Integer.parseInt(strArr[1]));
				break;
			case "push_front":
				deque.offerFirst(Integer.parseInt(strArr[1]));
				break;
			case "pop_front":
			    cur = deque.peekFirst() != null ? deque.pollFirst() : -1;
				sb.append(cur+"\n");
				break;
			case "pop_back":
				cur = deque.peekLast() != null ? deque.pollLast() : -1;
				sb.append(cur + "\n");
				break;
			case "size":
				sb.append(deque.size());
				sb.append("\n");
				break;
			case "empty":
				cur = deque.isEmpty() ? 1 : 0;
				sb.append(cur + "\n");
				break;
			case "front":
				cur = deque.peekFirst() != null ? deque.peekFirst() : -1;
				sb.append(cur + "\n");
				break;
			case "back":
				cur = deque.peekLast() != null ? deque.peekLast() : -1;
				sb.append(cur + "\n");
				break;
			default:
				break;
			}
		}
		System.out.println(sb.toString());
	}
}
