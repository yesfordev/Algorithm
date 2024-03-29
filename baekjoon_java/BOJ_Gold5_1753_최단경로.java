package yes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Gold5_1753_최단경로 {

	static int V,E,startV, INF=3000001;
	static int adjMatrix[][], distance[];
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startV = Integer.parseInt(in.readLine());
		
		adjMatrix = new int[V+1][V+1];
		distance = new int[V+1];
		
		Arrays.fill(distance, INF);
		visited = new boolean[V+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjMatrix[from][to] = w;
		}
		
		dijkstra(startV);
		
		for (int i = 1; i <= V; i++) {
			if(distance[i]==INF) {
				System.out.println("INF");
			} else {
				System.out.println(distance[i]);
			}
		}

	}

	private static void dijkstra(int start) {
		distance[start] = 0;
	
		for (int i = 1; i <= V; i++) {
			int min = Integer.MAX_VALUE;
			int current = 0;
			for(int j=1; j<=V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					current = j;
				}
			}
			
			visited[current] = true;
			
			for (int j = 1; j <= V; j++) {
				if(!visited[j] && adjMatrix[current][j]!=0 && distance[j] > min + adjMatrix[current][j]) {
					distance[j] = min+adjMatrix[current][j];
				}
			}
			
		}
		
		
	}

}
