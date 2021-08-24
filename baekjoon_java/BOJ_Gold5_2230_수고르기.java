package algo2108_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Gold5_2230_수고르기 {

	static int N, M, A[], minDiff = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		
		for (int idx = 0; idx < N; idx++) {
			A[idx] = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(A);
		
		int pointerA = 0;
		int pointerB = 1;
		
		while(pointerA != N-1) {
			int diff = A[pointerB] - A[pointerA];
			if(diff >= M) {
				minDiff = Math.min(minDiff, diff);
				++pointerA;
				pointerB = pointerA + 1;
				continue;
			}
			if(pointerB == N-1) {
				++pointerA;
				pointerB = pointerA + 1;
				continue;
			}
			++pointerB;
		}
		
		System.out.println(minDiff);
	}
}
