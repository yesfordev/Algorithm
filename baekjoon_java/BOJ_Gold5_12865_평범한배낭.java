package algo210710;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold5_12865_평범한배낭 {
	
	static int N, K, w[], v[], dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][K+1];
		w = new int[N+1];
		v = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i-1][j];
				
				if(j-w[i] >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]] + v[i]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
