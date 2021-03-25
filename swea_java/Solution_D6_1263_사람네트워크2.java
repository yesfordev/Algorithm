package algo0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구해야 하는 것 - 누가 가장 영향력이 있는 사람인지! 분석!
 * N - 입력 사람 네트워크(그래프)의 노드 수 
 * CC - 네트워크 상에서 한 사용자가 다른 모든 사람에게 얼마나 가까운가를 나타냄
 * CC(i) = j*dist(i,j)의 합 / dist(i,j)는 노드 i로부터 노드 j까지의 최단거리
 * -> 시간이 충분히 주어져서, n이 1000까지지만 플루이드-와샬 알고리즘 사용 가능! => 이걸로 풀자!
 * 
 */
public class Solution_D6_1263_사람네트워크2 {
	static int T, N, answer;
	static int[][] dist;
	static int[] CC;
	static final int INF = 10000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			
			dist = new int[N][N];
			CC = new int[N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Integer.parseInt(st.nextToken());
					if(i!=j && dist[i][j] == 0) dist[i][j]=INF;
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if(k==i) continue;
					for (int j = 0; j < N; j++) {
						if(k==j || i==j) continue;
						if(dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
						}
					}
				}
			}
			
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				int sum=0;
				for (int j = 0; j < N; j++) {
					sum+=dist[i][j];
				}
				answer = Math.min(answer, sum);
			}
			
			System.out.println("#"+t+" "+answer);
		}
	}

}
