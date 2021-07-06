package algo210619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2294_Silver1_동전2 {

	static int n, k, dp[], coin[];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[k+1];
		coin = new int[n];
		
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(coin);
		Arrays.fill(dp, -1);
		
		dp[0] = 0;
		
		// 가장 적은 동전으로 세팅 
		for (int i = coin[0]; i <= k; i++) {
			if(dp[i-coin[0]] >= 0) {
				dp[i] = dp[i-coin[0]] + 1;
			}
		}
		
		// 2번째로 큰 동전부터 찾기
		for (int idx = 1; idx < n; idx++) {
			for (int i = coin[idx]; i <= k; i++) {
				if(dp[i-coin[idx]] >= 0) {
					// dp[i]가 -1이면 갱신이 안되고 그냥 -1이 들어간다.
					dp[i] = Math.min(dp[i-coin[idx]] + 1, dp[i]);
				}
			}
		}
		
		System.out.println(dp[k]);
	}
}
