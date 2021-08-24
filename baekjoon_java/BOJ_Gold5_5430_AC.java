package algo2108_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_Gold5_5430_AC {

	static int T, n;
	static String p, str;
	static ArrayDeque<Integer> deque;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(in.readLine());

		for (int t = 1; t <= T; t++) {
			deque = new ArrayDeque<Integer>();
			p = in.readLine();
			n = Integer.parseInt(in.readLine());

			st = new StringTokenizer(in.readLine(), "[],");
			for (int i = 0; i < n; i++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}

			getNumber();
		}

		bw.write(sb.toString());

		bw.flush();
		bw.close();
		in.close();
	}

	private static void getNumber() {
		boolean order = true;

		for (char pChar : p.toCharArray()) {
			if (pChar == 'R') {
				order = !order;
				continue;
			}

			if(order) {
				if(deque.pollFirst() == null) {
					sb.append("error\n");
					return;
				}
			} else {
				if(deque.pollLast() == null) {
					sb.append("error\n");
					return;
				}
			}
		}

		if(deque.size() == 0) {
			sb.append("[]\n");
			return;
		}
		
		sb.append("[");

		if (order) {
			sb.append(deque.pollFirst());

			while (!deque.isEmpty()) {
				sb.append(',').append(deque.pollFirst());
			}
		} else {
			sb.append(deque.pollLast());

			while (!deque.isEmpty()) {
				sb.append(',').append(deque.pollLast());
			}
		}

		sb.append("]").append("\n");
	}
}
