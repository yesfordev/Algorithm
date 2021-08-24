package algo2108_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2579_Silver3_계단오르기_dp {

	static int n, arr[], dp[][], maxSum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		n = Integer.parseInt(in.readLine());
		
		arr = new int[n+1];
		
		for (int idx = 1; idx <= n; idx++) {
			arr[idx] = Integer.parseInt(in.readLine());
		}
		
		dp = new int[3][n+1];
		
		dp[1][1] = arr[1];
		
		for (int idx = 2; idx <= n; idx++) {
			dp[1][idx] = Math.max(dp[2][idx-2], dp[1][idx-2]) + arr[idx];
			dp[2][idx] = dp[1][idx-1] + arr[idx];
		}
		
		maxSum = Math.max(dp[1][n], dp[2][n]);

		bw.write(String.valueOf(maxSum));
		
		bw.flush();
		bw.close();
		in.close();
	}
}
