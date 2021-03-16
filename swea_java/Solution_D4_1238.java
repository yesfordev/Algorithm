import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_1238 {
	
	static int len, start;
	static boolean[][] node;
	static int[] visited;
	static Queue<Integer> queue;
	static int answer;

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			len = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			node = new boolean[101][101];
			visited = new int[101];
			queue = new LinkedList<Integer>();
			
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < len/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				node[from][to] = true;
			}
			
			
			// 로직 시작
			// 탐색 
			BFS();
			
			// 가장 마지막으로 연락한 노드 중 가장 큰 노드 찾기
			findNode();
			
			// 출력
			System.out.println("#"+t+" "+answer);
		}

	}

	private static void findNode() {
		int maxCnt = 0;
		
		// 마지막으로 방문한 cnt 찾기
		for (int i = 1; i <= 100; i++) {
			if(visited[i] > maxCnt) maxCnt = visited[i];
		}
		
		// 가장 큰 노드 찾기 
		int maxNode = 0;
		for (int i = 1; i <= 100; i++) {
			if(visited[i] == maxCnt && maxNode < i) {
				maxNode = i;
			}
		}
		
		answer = maxNode;
	}

	private static void BFS() {
		int cnt = 1;
		// 시작 노드 
		for (int i = 1; i <= 100; i++) {
			if(node[start][i]) {
				queue.offer(i);
				visited[i] = cnt;
			}
		}
		
		cnt++;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int i = 1; i <= 100; i++) {
				if(node[cur][i] && visited[i] == 0) {
					queue.offer(i);
					visited[i]=visited[cur]+1;
				}
			}
		}
	}
}
