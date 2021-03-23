package algo0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jungol_1681_해밀턴순환회로 {

	static int N;
	static int[][] cost;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		cost = new int[N][N];
		visited = new boolean[N];
		
		StringTokenizer st=null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited[0] = true;
		dfs(0, 0, 0);
		System.out.println(answer);
	}

	private static void dfs(int now, int cnt, int sum) {
		if(cnt == N-1) {
			if(cost[now][0] == 0) return;
			int tempSum = sum + cost[now][0];
			answer = Math.min(answer, tempSum);
			return;
		}
		if(sum > answer) return;
		
		for (int i = 0; i < N; i++) {
			
			if(visited[i]) continue;
			if(cost[now][i] == 0) continue;
			
			visited[i] = true;
			dfs(i, cnt+1, sum+cost[now][i]);
			visited[i] = false;
		}
		
	}

}
