package algo0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 0인 지점에서 상어까지의 거리 다 체크하기!
 * => 8방은 굳이... 장애물 없으면 bfs로 안해도 된다. => x의 차이와 y의 차이 중 큰 값으로 해도 됨(대각선 이동 가능해서) 
 * => 가장 큰 안전거리를 저장 
 * 
 *
 */
public class BOJ_Silver2_17086_아기상어2 {

	static class Shark {
		int x;
		int y;
		public Shark(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int N,M,answer=Integer.MIN_VALUE;
	static int[][] map;
	static List<Shark> sharks = new ArrayList<Shark>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) sharks.add(new Shark(i, j));
			}
		}
		
		//로직 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					findDist(i,j);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void findDist(int r, int c) {
		
		int min = Integer.MAX_VALUE;
		for(Shark shark : sharks) {
			int dist = Math.max(Math.abs(shark.x - r), Math.abs(shark.y - c)); // 이 부분 중요!
			if(dist < min) {
				min = dist;
			}
		}
		
		answer = Math.max(answer, min);
		
	}


}
