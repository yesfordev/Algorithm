package yes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_Gold5_1753_최단경로_pq {

	static class Vertex implements Comparable<Vertex> {
		int to;
		int w;
		public Vertex(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	static int V,E,startV, INF=3000001;
	static int distance[];
	static boolean visited[];
	static ArrayList<Vertex>[] nodes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startV = Integer.parseInt(in.readLine());
		
		nodes = new ArrayList[V+1];
		
		for (int i = 0; i <= V; i++) {
			nodes[i] = new ArrayList<Vertex>();
		}
		
		distance = new int[V+1];
		
		Arrays.fill(distance, INF);
		visited = new boolean[V+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			nodes[from].add(new Vertex(to, w));
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
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		distance[start] = 0;
		pq.offer(new Vertex(start, 0));
		
		while(!pq.isEmpty()) {
			Vertex minNode = pq.poll();
			int current = minNode.to;
			int minW = minNode.w;
			
			if(visited[current]) continue;
			visited[current] = true;
			
			for(Vertex node : nodes[current]) {
				if(distance[node.to] > distance[current] + node.w) {
					distance[node.to] = distance[current] + node.w;
					pq.offer(new Vertex(node.to, distance[node.to]));
				}
			}
		}
		
	}

}
