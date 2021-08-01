package algo2108_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Gold3_1238_파티 {

	static int N, M, X, dist[][], maxTime;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		maxTime = -1;
		
		dist = new int[N+1][N+1];
		
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dist[i], 990001);
			dist[i][i] = 0; // 추가 필요
		}
		for (int idx = 0; idx < M; idx++) {
			st = new StringTokenizer(in.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			dist[start][end] = time;
		}
		
		if(N==1) {
			System.out.println(dist[1][1]);
			return;
		}
		
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(k==i) continue;
				for (int j = 1; j <= N; j++) {
					if(k==j || i==j) continue;
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
		for (int start = 1; start <= N; start++) {
			
			int sumTime = dist[start][X] + dist[X][start];
			
			maxTime = Math.max(maxTime, sumTime);
		}
		
		System.out.println(maxTime);
	}
}
