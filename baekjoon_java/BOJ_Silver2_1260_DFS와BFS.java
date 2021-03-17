import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Silver2_1260_DFS와BFS {

	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
		public Node(Node next) {
			super();
			this.next = next;
		}
	}
	
	static int N,M,V;
	static Node[] adjList;
	static boolean[] dfsVisited;
	static int[][] inout;
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N+1];
		dfsVisited = new boolean[N+1];
		inout = new int[M][2];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			inout[i][0] = Integer.parseInt(st.nextToken());
			inout[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(inout, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0]==o2[0]) {
					return Integer.compare(o2[1], o1[1]);
				} else return Integer.compare(o1[0], o2[0]);
			}
		});
		
		for (int i = 0; i < M; i++) {
			int from = inout[i][0];
			int to = inout[i][1];
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
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
			
			for(Node temp=adjList[cur]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex]) {
					queue.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
			}
		}
	}

	private static void dfs(int current) {
		dfsVisited[current] = true;
		System.out.print(current+" ");
		
		for(Node temp = adjList[current]; temp != null; temp=temp.next) {
			if(!dfsVisited[temp.vertex]) {
				dfs(temp.vertex);
			}
		}
	}
}
