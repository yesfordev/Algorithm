package algo0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 맥주 한박스에 맥주 20개 -> 출발은 상근이네 집 
 * 50m에 한병씩 마신다 
 * 추가로 맥주 구매해야 할 수도 -> 빈 병은 버리고 새 맥주 병을 산다 
 * 박스에 들어있는 맥주는 20병을 넘을 수 없다.
 * 주어지는 것 : 편의점, 상근이네 집, 펜타포트 락 페스티벌의 좌표
 * 편의점 개수 100개까지 가능 -> 스택 사용 불가... 수열 불가
 * 
 * 풀이방법
 * 1. 그냥 시뮬로 풀었는데... 조건을 잘못 생각한 것 같다.
 * -> 플루이드 와샬 or 큐 이용해서 풀어야 함 
 * => 큐 이용하는 부분.. 다시 공부해야함!!! 거리 조건, 방문 조건 생각 다시 하며 풀어보기
 *
 */
public class BOJ_Silver1_9205_맥주마시면서걸어가기_큐 {

	static int t, n;
	static int[][] point;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		t = Integer.parseInt(in.readLine());
		
		for (int c = 0; c < t; c++) {
			
			n = Integer.parseInt(in.readLine());
			
			point = new int[n+2][2];
			visited = new boolean[n+2];
			
			for (int i = 0; i <= n+1; i++) {
				st = new StringTokenizer(in.readLine()," ");
				point[i][0] = Integer.parseInt(st.nextToken());
				point[i][1] = Integer.parseInt(st.nextToken());
			}
			
			Queue<int[]> queue = new LinkedList<int[]>();
			int[] start = point[0];
			int[] end = point[n+1];
			
			queue.offer(start);
			visited[0] = true;
			boolean success=false;
			
			while(!queue.isEmpty()) {
				int[] current = queue.poll();
				
				if(current.equals(end)) {
					success = true;
					break;
				}
				
				for (int i = 1; i < n+2; i++) {
					if(!visited[i] && Math.abs(point[i][0] - current[0]) + Math.abs(point[i][1] - current[1]) <= 1000) {
						queue.offer(point[i]);
						visited[i]=true;
					}
				}
			}
			
			if(success) System.out.println("happy");
			else System.out.println("sad");
		}
	}
}