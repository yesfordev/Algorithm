package algo211011_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_Silver3_9095_123더하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		int dp[];
		
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(in.readLine());
			
			dp = new int[n+1];
			
			dp[0] = dp[1] = 1;
			
			if (n>=2) {
				dp[2] = 2;
			}
			
			if (n>=3) {
				for (int idx = 3; idx <= n; idx++) {
					dp[idx] = dp[idx-1] + dp[idx-2] + dp[idx-3];
				}
			}
			System.out.println(dp[n]);
		}
	}
}
