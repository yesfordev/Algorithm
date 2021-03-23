package algo0323;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D4_3124_최소스패닝트리 {

	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int c;

		public Edge(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.c, e.c);
		}
	}
	
	static void makeSet() {
		p=new int[V];
		for (int i = 0; i < V; i++) {
			p[i]=i;
		}
		r=new int[V];
		Arrays.fill(r, 1); // 랭크 
	}
	
	static int find(int x) {
		if(x==p[x]) return p[x];
		else return p[x] = find(p[x]);
	}
	
	static boolean isConnect(int x, int y) {
		return find(x) == find(y);
	}
	
	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px == py) return false;
		
		if(r[px]>=r[py]) {
			p[py] = px;
			r[px]+=r[py];
		} else {
			p[px] = py;
			r[py]+=r[px];
		}
		return true;
	}
	
	static int TC;
	static int V,E;
	static int[] p;
	static int[] r;
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		TC = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				pq.offer(new Edge(a-1,b-1,c));
			}
			makeSet();
			long sum=0L; // 꼭 필요!! 간선이 20만개가 넘어가서. 
			
			int cnt=0;
			while(!pq.isEmpty()) {
				Edge next = pq.poll();

				if(!isConnect(next.a, next.b)) {
					union(next.a, next.b);
					sum += next.c;
					if(++cnt == V-1) break;
				}
			}
			System.out.println("#"+t+" "+sum);
		}
	}
}