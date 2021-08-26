package algo2108_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold4_1806_부분합 {

	static int N, S, arr[], minLen = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];

		st = new StringTokenizer(in.readLine(), " ");
		for (int idx = 0; idx < N; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		}
		
		//logic
		//pointer
		int head = 0, tail = 0;
		int sum = arr[0];
		
		while(true) {
			if (sum >= S) {
				minLen = Math.min(minLen, tail-head+1);
				if (tail > head) {
					sum -= arr[head];
					++head;
				} else if (tail < N-1) {
					sum += arr[++tail];
				} else break;
			} else if (sum < S) {
				if (tail >= N-1) break;
				sum += arr[++tail];
			}
		}
		
		if(minLen == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(minLen);
	}
}
