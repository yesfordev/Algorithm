import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Silver2_1260_DFS와BFS_arraylist {
	
	static int N,M,V;
	static ArrayList<Integer>[] adjList;
	static boolean[] dfsVisited;
	static int[][] inout;
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		dfsVisited = new boolean[N+1];
		inout = new int[M][2];
		
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		for (int i = 0; i <= N; i++) {
			Collections.sort(adjList[i]);
		}
		
		dfs(V);
		System.out.println();
		
		bfs();
	}
	
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N+1];
		
		queue.offer(V);
		visited[V] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			System.out.print(cur+" ");
			
			for(int i=0; i<adjList[cur].size(); i++) {
				if(!visited[adjList[cur].get(i)]) {
					queue.offer(adjList[cur].get(i));
					visited[adjList[cur].get(i)] = true;
				}
			}
		}
	}

	private static void dfs(int current) {
		dfsVisited[current] = true;
		System.out.print(current+" ");
		
		for(int i=0; i<adjList[current].size(); i++) {
			if(!dfsVisited[adjList[current].get(i)]) {
				dfs(adjList[current].get(i));
			}
		}
	}
}
