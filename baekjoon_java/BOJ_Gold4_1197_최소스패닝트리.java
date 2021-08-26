package algo2108_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold4_1197_최소스패닝트리 {

	static class Edge implements Comparable<Edge>{
		int u;
		int v;
		int weight;

		public Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int V, E, p[];
	static Queue<Edge> queue = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		p = new int[V+1];
		
		for (int idx = 0; idx < E; idx++) {
			st = new StringTokenizer(in.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			queue.offer(new Edge(u, v, weight));
		}
		
		make();
		
		int sumWeight=0, selectE=0;
		
		while(selectE < V-1) {
			Edge curE = queue.poll();
			
			if(union(curE.u, curE.v)) {
				sumWeight += curE.weight;
				++selectE;
			}
		}
		
		System.out.println(sumWeight);
	}

	static void make() {
		for (int idx = 1; idx <= V; idx++) {
			p[idx] = idx;
		}
	}
	
	static int find(int a) {
		if(p[a] == a) return a;
		
		return p[a] = find(p[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		p[bRoot] = aRoot;
		return true;
	}
}
