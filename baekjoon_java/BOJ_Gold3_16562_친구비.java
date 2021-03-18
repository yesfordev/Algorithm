package algo0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Gold3_16562_친구비 {

	static int N,M,k,minK;
	static int[] parent;
	static int[] V;
	static boolean[] visited;
	static int[] minArr;
	static int[] rank;
	
	static void make() {
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(a==parent[a]) return a;
		return parent[a] = findSet(parent[a]);
	}
	
//	static boolean union(int a, int b) {
//		int aRoot = findSet(a);
//		int bRoot = findSet(b);
//		
//		if(aRoot==bRoot) return false;
//		
//		parent[bRoot] = aRoot;
//		return true;
//	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot==bRoot) return false;
		
		if(rank[aRoot] < rank[bRoot]) {
			parent[aRoot] = bRoot;
		} else {
			parent[bRoot] = aRoot;
			
			if(rank[aRoot] == rank[bRoot]) {
				rank[aRoot]++;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		parent = new int[N+1];
		V = new int[N+1];
		visited = new boolean[N+1];
		rank = new int[N+1];
		
		make();
		
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		
		minArr = new int[N+1];
		Arrays.fill(minArr, Integer.MAX_VALUE);
		for (int i = 1; i <= N; i++) {
			int group = findSet(i);
			minArr[group] = Math.min(minArr[group], V[i]);
		}
		
		for (int i = 1; i <= N; i++) {
			if(minArr[i] != Integer.MAX_VALUE) {
				minK += minArr[i];
			}
		}
		
		//출력
		if(minK <= k) {
			System.out.println(minK);
		} else {
			System.out.println("Oh no");
		}

	}

}
