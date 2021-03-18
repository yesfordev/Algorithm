package algo0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jungol_1863_종교 {

	static int n, m, answer;
	static int[] parent;
	static int[] rank;
	static boolean[] groupArr;
	
	static void make() {
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(a==parent[a]) return a;
		return parent[a] = findSet(parent[a]);
	}
	
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
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		rank = new int[n+1];
		groupArr = new boolean[n+1];
		
		make();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= n; i++) {
			int group = findSet(i);
			groupArr[group] = true;
		}
		
		for (int i = 1; i <= n; i++) {
			if(groupArr[i]) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}
