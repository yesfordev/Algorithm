package algo2108_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Silver1_1679_숨바꼭질 {

	static int N, K;
	static boolean visited[];
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int d[] = {-1,1,2};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[100001];
		
		visited[N] = true;
		queue.offer(N);
		
		int time = 0;
		boolean success = false;
		while(!queue.isEmpty()) {
			++time;
			int size = queue.size();
			for (int a = 0; a < size; a++) {
				int curN = queue.poll();
				
				for (int dir = 0; dir < 3; dir++) {
					int next = 0;
					if(dir == 2) {
						next = curN * d[dir];
					} else {
						next = curN + d[dir];
					}
					
					if(next<0 || next>100000 || visited[next]) continue;
					
					if(next == K) {
						success = true;
						break;
					}			
					queue.offer(next);
					visited[next] = true;
				}
				if(success) break;
			}
			if(success) break;
		}	
		System.out.println(time);
	}
}
