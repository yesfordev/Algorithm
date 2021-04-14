package algo0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것:격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지 최소일수
 * 제약 사항: 
 * 1. 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 * 2. 2<=M,N<=1000
 * 3. 1은 토마토, 0은 익지 않은 토마토, -1은 토마토가 들어있지 않음
 * 4. 토마토가 하나 이상 있는 경우만 입력으로 주어짐
 * => 모두 익지 못하는 상황이면 -1을 출력
 * 
 * 문제 유형: BFS
 * 요구 개념: BFS
 * 
 * <풀이법 요약>
 * 1. M,N, 토마토 입력 받으면서 0과 1인 칸의 개수 세기
 * 2. 1인 토마토 다 방문처리 + 큐에 넣기
 * 3. BFS 돌리기 => -1인 자리 빼고 다 방문
 * 4. 처음에 센 개수와 맞지 않으면 -1 출력, 중간에 개수가 같아지면 출력하기
 */
public class BOJ_Silver_7576_토마토 {
	
	static int N, M, map[][];
	static boolean visited[][];
	static int allTomatoCnt=0, goTomatoCnt=0;
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					++goTomatoCnt;
					++allTomatoCnt;
					visited[i][j] = true;
					queue.offer(new int[] {i, j});
				} else if(map[i][j] == 0) {
					++allTomatoCnt;
				}
			}
		}
		
		System.out.println(bfs());;
	}

	private static int bfs() {
		if(allTomatoCnt == goTomatoCnt) return 0;
		
		int time=0;
		while(!queue.isEmpty()) {
			int cycle = queue.size();
			++time;
			
			for (int a = 0; a < cycle; a++) {
				int curR = queue.peek()[0];
				int curC = queue.peek()[1];
				queue.poll();
				
				for (int dir = 0; dir < 4; dir++) {
					int nr = curR + dr[dir];
					int nc = curC + dc[dir];
					
					if(!check(nr, nc) || visited[nr][nc] || map[nr][nc] == -1) continue;
					visited[nr][nc] = true;
					++goTomatoCnt;
					queue.offer(new int[] {nr,nc});
					
					if(goTomatoCnt == allTomatoCnt) return time;
				}
			}
		}
		return -1;
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
