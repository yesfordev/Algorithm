package algo0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * prim or kruskal로 해결할 수 있는 문제!
 * -> N의 값이 1000이므로, 2차원 배열로 충분히 감당 가능하지 않을까?
 * -> kruskal로 풀어보자 
 * 
 * ## 주의사항!!
 * => 마지막에 소수점 마지막자리에서 반올림 위해 E는 마지막에 곱해준 후, Math.round를 취해준다.
 * => 간선 리스트 꼭 정렬 해주기!!
 *
 */
public class Solution_D4_1251_하나로_kruskal {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long weight;
		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	static void makeSet() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(x == parent[x]) return parent[x];
		else return parent[x] = findSet(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int Yroot = findSet(x);
		int Xroot = findSet(y);
		
		if(Xroot == Yroot) return false;
		
		if(rank[Xroot] >= rank[Yroot]) {
			parent[Yroot] = Xroot;
			rank[Xroot] += rank[Yroot];
		} else {
			parent[Xroot] = Yroot;
			rank[Yroot] += rank[Xroot];
		}
		return true;
	}
	
	static int TC, N;
	static double E;
	static long[][] seom;
	static List<Edge> edges;
	static boolean[] visited;
	static int[] parent;
	static int[] rank;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		TC = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(in.readLine());
			
			seom = new long[N][2];
			visited = new boolean[N];
			parent = new int[N];
			rank = new int[N];
			Arrays.fill(rank, 1);
			edges = new ArrayList<Edge>();
			
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
					long weight = Math.abs(seom[i][0]-seom[j][0])*Math.abs(seom[i][0]-seom[j][0]) + Math.abs(seom[i][1]-seom[j][1])*Math.abs(seom[i][1]-seom[j][1]);
					edges.add(new Edge(i, j, weight));
				}
			}
			
			Collections.sort(edges);
			
			makeSet();
			
			int cnt=0;
			long result = 0L;
			
			for(Edge edge : edges) {
				if(union(edge.from, edge.to)) {
					result += edge.weight;
					if(++cnt >= N-1) break;
				}
			}
			System.out.println("#"+t+" "+Math.round(result*E));
		}
	}
}
