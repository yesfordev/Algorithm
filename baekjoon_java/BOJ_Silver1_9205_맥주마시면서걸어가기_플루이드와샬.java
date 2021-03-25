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
 * -> 플루이드 와샬!!
 *
 */
public class BOJ_Silver1_9205_맥주마시면서걸어가기_플루이드와샬 {

	static int t, n;
	static int[][] dist;
	static boolean[][] possible;
	static int[][] point;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		t = Integer.parseInt(in.readLine());
		
		for (int c = 0; c < t; c++) {
			
			n = Integer.parseInt(in.readLine());
			
			point = new int[n+2][2];
			dist = new int[n+2][n+2];
			possible = new boolean[n+2][n+2];
			
			for (int i = 0; i < n+2; i++) {
				st = new StringTokenizer(in.readLine()," ");
				point[i][0] = Integer.parseInt(st.nextToken());
				point[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < n+2; i++) {
				for (int j = 0; j < n+2; j++) {
					if(i==j) continue;
					dist[i][j] = Math.abs(point[i][0] - point[j][0]) + Math.abs(point[i][1] - point[j][1]);
					if(dist[i][j] <= 1000) {
						possible[i][j] = true;
					}
				}
			}
			
			for (int k = 0; k < n+2; k++) {
				for (int i = 0; i < n+2; i++) {
					if(i==k) continue;
					for (int j = 0; j < n+2; j++) {
						if(possible[i][k] && possible[k][j]) possible[i][j] = true;
					}
				}
			}
			
			if(possible[0][n+1]) System.out.println("happy");
			else System.out.println("sad");
		}
	}
}