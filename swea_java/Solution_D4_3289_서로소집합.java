package algo0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3289_서로소집합 {

	static int T, n, m;
	static int parents[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
		
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parents = new int[n+1];
			
			make();
			
			System.out.print("#"+t+" ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				if(st.nextToken().equals("0")) {
					union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				} else {
					if(isConnected(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
						System.out.print("1");
					} else {
						System.out.print("0");
					};
				}
			}
			System.out.println();	
		}
	}
	
	private static void make() {
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

	private static boolean isConnected(int a, int b) {
		return findSet(a) == findSet(b);
	}
	
	private static int findSet(int a) {
		if(parents[a]==a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot==bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}

}
