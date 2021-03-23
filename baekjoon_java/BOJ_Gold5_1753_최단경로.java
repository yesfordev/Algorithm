package algo0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int end;
	int weight;
	public Node(int end, int weight) {
		super();
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class BOJ_Gold5_1753_최단경로 {

	static int V,E,startV;
	static ArrayList<Node>[] NodeList;
	static int[] distance;
	static int INF = 1000000000;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startV=Integer.parseInt(in.readLine());
		
		NodeList = new ArrayList[V+1];
		distance = new int[V+1]; // startV부터 정점까지의 최소거리를 저장
		visited = new boolean[V+1];
		
		for (int i = 0; i <= V; i++) {
			NodeList[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			NodeList[start].add(new Node(end, weight));
		}

		//로직 
		for (int i = 1; i <= V; i++) {
			distance[i] = INF;
		}
		
		
		DikstraMethod(startV);
	
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if(distance[i] == INF) {
				sb.append("INF\n");
			} else sb.append(distance[i]+"\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void DikstraMethod(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(start, 0));
		distance[startV] = 0;
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.end;
			
			if(visited[cur]) continue;
			
			visited[cur] = true;
			
			for(Node node : NodeList[cur]) {
				if(distance[node.end] > distance[cur] + node.weight) {
					distance[node.end] = distance[cur] + node.weight;
					pq.offer(new Node(node.end, distance[node.end]));
				}
			}
		}
		
	}

}
