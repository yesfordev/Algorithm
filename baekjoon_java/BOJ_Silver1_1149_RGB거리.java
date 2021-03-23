package algo0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP -> 다이나믹 프로그래밍 
 * @author yes
 *
 */
public class BOJ_Silver1_1149_RGB거리 {

	static int N;
	static int[][] cost;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(in.readLine());

		cost = new int[N][3];
		dp = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = cost[0][0];
		dp[0][1] = cost[0][1];
		dp[0][2] = cost[0][2];
		
		for (int i = 1; i < N; i++) {
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
			dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + cost[i][2];
		}
		
		int answer=Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			answer = Math.min(answer, dp[N-1][i]);
		}
		
		System.out.println(answer);
	}

}
