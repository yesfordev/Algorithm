package algo2108_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2579_Silver3_계단오르기 {

	static int n, arr[], maxSum = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(in.readLine());
		
		arr = new int[n+1];
		
		for (int idx = 1; idx <= n; idx++) {
			arr[idx] = Integer.parseInt(in.readLine());
		}
		
		go(0, 0, 0);

		bw.write(String.valueOf(maxSum));
		
		bw.flush();
		bw.close();
		in.close();
	}
	
	private static void go(int curPos, int continueCnt, int sum) {
		if(curPos == n) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		if(curPos > n) return;
		
		if(continueCnt < 2 && curPos+1 <= n) {
			go(curPos+1, continueCnt+1, sum+arr[curPos+1]);
		}
		if(curPos+2 <= n) {
			go(curPos+2, 1, sum+arr[curPos+2]);
		}
	}

}
