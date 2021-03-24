package algo0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * prim or kruskal로 해결할 수 있는 문제!
 * -> N의 값이 1000이므로, 2차원 배열로 충분히 감당 가능하지 않을까?
 * => 2차원 배열에 각 정점마다의 환경 부담금을 담아서 계산해보자!!
 * => PRIM 알고리즘으로 풀어보자! 
 * 
 * ## 주의사항!!
 * => 마지막에 소수점 마지막자리에서 반올림 위해 E는 마지막에 곱해준 후, Math.round를 취해준다.
 *
 */
public class Solution_D4_1251_하나로_prim {

	static int TC, N;
	static double E;
	static long[][] seom;
	static long[][] seomCost;
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		TC = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(in.readLine());
			
			seom = new long[N][2];
			seomCost = new long[N][N];
			visited = new boolean[N];
			long[] minEdge = new long[N]; // 신장트리에 연결된 정점에서 자신으로의 최소 간선 비용 
			
			st = new StringTokenizer(in.readLine()," ");
			for (int i = 0; i < N; i++) {
				seom[i][0] = Long.parseLong(st.nextToken());
			}
			st = new StringTokenizer(in.readLine()," ");
			for (int i = 0; i < N; i++) {
				seom[i][1] = Long.parseLong(st.nextToken());
			}
			E = Double.parseDouble(in.readLine());
			
			//로직 -> 각 정점의 환경부담금 넣기 
			
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					seomCost[i][j] = Math.abs(seom[i][0]-seom[j][0])*Math.abs(seom[i][0]-seom[j][0]) + Math.abs(seom[i][1]-seom[j][1])*Math.abs(seom[i][1]-seom[j][1]);
					seomCost[j][i] = seomCost[i][j];
				}
				minEdge[i] = Long.MAX_VALUE; //최소값 따지기 위해 가장 큰 값 놓고 출발 
			}
		
			long result = 0L;
			minEdge[0] = 0L;
			
			for (int c = 0; c < N; c++) {
				long min = Long.MAX_VALUE;
				int minVertex = 0;
				
				for (int i = 0; i < N; i++) {
					if(!visited[i] && min > minEdge[i]) {
						min = minEdge[i];
						minVertex = i;
					}
				}
				
				result += min;
				visited[minVertex] = true;
				
				for (int i = 0; i < N; i++) {
					if(!visited[i] && seomCost[minVertex][i] != 0L && minEdge[i] > seomCost[minVertex][i]) {
						minEdge[i] = seomCost[minVertex][i];
					}
				}
			}
			
			
			System.out.println("#"+t+" "+Math.round(result*E));
		}
	}
}
