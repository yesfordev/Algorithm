package algo0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Vertex implements Comparable<Vertex>{
	int end;
	int weight;
	public Vertex(int end, int weight) {
		super();
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Vertex o) {
		return this.weight - o.weight;
	}
}

public class BOJ_Gold5_1753_최단경로_연습 {

	static int V,E,startV;
	static ArrayList<Vertex>[] NodeList;
	static int[] distance;
	static final int INF = 1000000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startV = Integer.parseInt(in.readLine());
		
		NodeList = new ArrayList[V+1];
		
		for (int i = 0; i <= V; i++) {
			NodeList[i] = new ArrayList<Vertex>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			NodeList[start].add(new Vertex(end, weight));
		}
		
		distance = new int[V+1];
		
		Arrays.fill(distance, INF);
		
		DikstraMethod(startV);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= V; i++) {
			if(distance[i] == INF) sb.append("INF\n");
			else sb.append(distance[i]+"\n");
		}
		System.out.println(sb.toString());
	}

	private static void DikstraMethod(int start) {
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		distance[start]=0;
		pq.offer(new Vertex(start, 0));
		boolean[] visited = new boolean[V+1];
		
		while(!pq.isEmpty()) {
			Vertex curNode = pq.poll();
			int curEnd = curNode.end;
			
			if(visited[curEnd]) continue;
			visited[curEnd] = true;
			
			for(Vertex node : NodeList[curEnd]) {
				if(distance[node.end] > distance[curEnd] + node.weight) {
					distance[node.end] = distance[curEnd] + node.weight;
					pq.offer(new Vertex(node.end, distance[node.end]));
				}
			}
		}
		
	}

}
