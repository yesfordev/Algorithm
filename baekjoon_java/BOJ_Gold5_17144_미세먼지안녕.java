package algo0414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: T초가 지난 후 방에 남아있는 미세먼지의 양  
 * 제약 사항: 
 * 문제 유형: BFS
 * 요구 개념: BFS
 * 
 * <풀이법 요약>
 * 1. 처음 미세먼지 적힌 칸 다 적기
 * 2. 확산될 미세먼지 체크할 칸 만들어서 확산될 때마다 여기에 적기
 * 3. 두 칸 합치기
 * 4. 공기청정기~ 
 */
public class BOJ_Gold5_17144_미세먼지안녕 {

	static int R, C, T;
	static int map[][], spreadMap[][];
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int upAirR, downAirR;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		spreadMap = new int[R][C];
		
		boolean checkFirst = true;
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1 && checkFirst) {
					upAirR = i;
					checkFirst = false;
				}
				if(map[i][j] == -1 && !checkFirst) {
					downAirR = i;
				}
			}
		}
		
		for (int t = 0; t < T; t++) {
			spreadMeonji();
			airConditional();
		}
		
		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != -1 && map[i][j] != 0) {
					count += map[i][j];
				}
			}
		}
		System.out.println(count);
	}
	
	private static void spreadMeonji() {
		initSpreadMap();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != 0 && map[i][j] != -1) {
					int spread = map[i][j]/5;
					int cnt=0;
					
					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];
						
						if(nr<0 || nr>=R || nc<0 || nc>=C || map[nr][nc] == -1) continue;
						spreadMap[nr][nc] += spread;
						++cnt;
					}
					
					map[i][j] -= spread*cnt;
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += spreadMap[i][j];
			}
		}
	}

	private static void initSpreadMap() {
		for (int i = 0; i < R; i++) {
			Arrays.fill(spreadMap[i], 0);
		}
	}

	private static void airConditional() {
		//위쪽 공기청정기
		for (int r = upAirR; r > 0; r--) {
			map[r][0] = map[r-1][0];
		}
		for (int c = 0; c < C-1; c++) {
			map[0][c] = map[0][c+1];
		}
		for (int r = 0; r < upAirR; r++) {
			map[r][C-1] = map[r+1][C-1];
		}
		for (int c = C-1; c > 0; c--) {
			map[upAirR][c] = map[upAirR][c-1];
		}
		map[upAirR][0] = -1;
		map[upAirR][1] = 0;
		
		//아래쪽 공기청정기
		for (int r = downAirR; r < R-1; r++) {
			map[r][0] = map[r+1][0];
		}
		for (int c = 0; c < C-1; c++) {
			map[R-1][c] = map[R-1][c+1];
		}
		for (int r = R-1; r > downAirR; r--) {
			map[r][C-1] = map[r-1][C-1];
		}
		for (int c = C-1; c > 0; c--) {
			map[downAirR][c] = map[downAirR][c-1];
		}
		map[downAirR][0] = -1;
		map[downAirR][1] = 0;
	}
}
