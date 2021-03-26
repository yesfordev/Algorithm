package algo0326;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1. 먼저, flood-fill 이용해서 섬 1,2,3,4..로 채워주기 
 * 2. union-find 세팅
 * 3. 각 섬을 잇는 최소값 찾기 => 저장 => 크루스칼??로 해주기...
 * 
 * !!!! union parent 찾을 때, findSet(x)가 아닌 parent[x]라고 해서 틀렸다.. 완벽 숙지 하기 ***
 * @author yes
 *
 */
public class BOJ_Gold2_17472_다리만들기2 {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int distance;
		
		public Edge(int from, int to, int distance) {
			super();
			this.from = from;
			this.to = to;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	static void makeSet() {
		parent = new int[seomIdx+1];
		rank = new int[seomIdx+1];
		Arrays.fill(rank, 1);
		for (int i = 1; i <= seomIdx; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(x==parent[x]) return parent[x];
		else return parent[x] = findSet(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int XRoot = findSet(x);
		int YRoot = findSet(y);
		
		if(XRoot==YRoot) return false;

		if(rank[XRoot] >= rank[YRoot]) {
			parent[YRoot] = XRoot;
			rank[XRoot] += rank[YRoot];
		} else {
			parent[XRoot] = YRoot;
			rank[YRoot] += rank[XRoot];
		}
		return true;
	}
	
	static boolean isConnect(int x, int y) {
		return findSet(x) == findSet(y);
	}
	
	static int N, M, seomIdx;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<int[]> seom;
	static int[] parent;
	static int[] rank;
	static PriorityQueue<Edge> edgeList = new PriorityQueue<Edge>();
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		seom = new ArrayList<int[]>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) seom.add(new int[] {i,j});
			}
		}
		
		//flood-fill - 섬 각각 번호 붙여서 영역 표시 + 섬 개수 새기
		namingSeom();
		
		makeSet();
		
		//모든 경우의 다리를 만들어서 저장 
		for (int start = 1; start <= seomIdx; start++) {
			for (int dest = start+1; dest <= seomIdx; dest++) {
				makeBridge(start, dest);
			}
		}
		
		//크루스칼
		int answer = 0;
		int edgeCnt = 0;
		boolean success = false;
		while(!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();
			if(union(edge.from, edge.to)) {
				answer+=edge.distance;
				if(++edgeCnt == seomIdx-1) {
					success = true;
					break;
				}
			}
		}
		
		if(success) System.out.println(answer);
		else System.out.println("-1");
	}
	
	private static void makeBridge(int startIdx, int destIdx) {
		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == startIdx) {
					for (int dir = 0; dir < 4; dir++) {
						int r = i;
						int c = j;
						int len=0;
						while(true) {
							int nr = r+dr[dir];
							int nc = c+dc[dir];
							
							if(!check(nr, nc)) break;
							if(map[nr][nc] != destIdx && map[nr][nc] != 0) break;
							
							if(map[nr][nc]==destIdx) {
								if(len>=2) {
									minLen = Math.min(minLen, len);
								}
								break;
							}
							++len;
							r=nr;
							c=nc;
						}
					}
				}
			}
		}
		
		if(minLen == Integer.MAX_VALUE) return;
		edgeList.offer(new Edge(startIdx, destIdx, minLen));
	}

	private static void namingSeom() {
		seomIdx=0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {	
					++seomIdx;
					queue.clear();
					map[i][j] = seomIdx;
					visited[i][j] = true;
					queue.offer(new int[]{i,j});
					
					while(!queue.isEmpty()) {
						int curR = queue.peek()[0];
						int curC = queue.peek()[1];
						queue.poll();

						for (int dir = 0; dir < 4; dir++) {
							int nr = curR+dr[dir];
							int nc = curC+dc[dir];
							
							if(!check(nr, nc) || map[nr][nc] == 0 || visited[nr][nc]) continue;
							map[nr][nc] = seomIdx;
							visited[nr][nc] = true;
							queue.offer(new int[] {nr, nc});
						}
					}
				}
			}
		}
		
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
