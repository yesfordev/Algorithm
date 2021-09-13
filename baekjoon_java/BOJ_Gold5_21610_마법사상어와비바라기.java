package algo2109_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 비바라기 -> 비구름
 * NxN
 * 각 칸에는 바구니가 하나 있고, 바구니는 칸 전체를 차지
 * 
 * 바구니에 저장할 수 있는 물의 양에는 제한이 없다.
 * 
 * (r,c)는 격자의 r행 c열에 있는 바구니
 * A[r][c] = 바구니에 저장되어 있는 물의 양
 * 
 * (1,1) ~ (N,N) => 1번 행과 N번 행을 연결, 1번 열과 N번 열을 연결
 * 
 * # 로직
 * 1. 비바라기 시전 -> (N,1), (N,2), (N-1,1), (N-1,2)에 비구름이 생긴다.
 * 구름은 칸 전체를 차지
 * 
 * 2. 구름에 이동을 M번 명령
 * i번째 이동 명령 : 방향 di와 거리 si로 이루어져 있다.
 * 방향은 총 8개
 * - r : 0,-1,-1,-1,0,1,1,1
 * - c : -1,-1,0,1,1,1,0,-1
 * 
 * ## 이동을 명령하면 다음이 순서대로 진행된다.
 * 1) 모든 구름이 di방향으로 si칸 이동
 * 2) 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
 * 3) 구름이 모두 사라짐
 * 4) 2)에서 물이 증가한 칸 (r,c)에 물복사버그 마법. 
 * : 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r,c)에 있는 바구니의 물의 양이 증가
 * -> 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
 * 5) 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다.
 * 이때 구름이 생기는 칸은 3)에서 구름이 사라진 칸이 아니어야 한다.(3번에서 구름이 사라진 칸에 구름 생기면 안됨)
 * 
 * 결과: M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합 구하기
 * 
 * 2<=N<=50
 * 1<=M<=100
 * 0<=A[r][c]<=100
 * 1<=di<=8
 * 1<=si<=50
 * 
 */
public class BOJ_Gold5_21610_마법사상어와비바라기 {
	
	static int N, M, map[][], cloud[][], move[][], curCloud[][];
	static boolean checkWaterPlus[][];
	static int dr[] = {0, 0,-1,-1,-1,0,1,1,1};
	static int dc[] = {0,-1,-1,0,1,1,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		cloud = new int[N+1][N+1];
		move = new int[M][2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			move[i][0] = Integer.parseInt(st.nextToken());
			move[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//비바라기 시전
		//비구름 생성
		cloud[N][1] = cloud[N][2] = cloud[N-1][1] = cloud[N-1][2] = 1;
		
		// M번 실행
		for (int m = 0; m < M; m++) {
			// 1. 모든 구름이 di 방향으로 si칸 이동한다.
			moveCloud(m);
			
			// 2. 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
			rain();
			
			// 3. 구름이 모두 사라진다. -> 1을 -1로 변경
			disappearCloud();
			
			// 4. 2에서 물이 증가한 칸 (r,c)에 물복사버그 마법을 시전한다.
			duplicateWaterMagic();
			// 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름 생기고, 물의 양이 2 줄어든다.
			// 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함 -> -1로 체크해주고, 0으로 돌려놓기
			createCloud();
		}
		
		// 물의 양의 합
		System.out.println(getWaterSum());
	}

	private static int getWaterSum() {
		int waterSum = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				waterSum += map[r][c];
			}
		}
		return waterSum;
	}

	private static void createCloud() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (map[r][c] >= 2 && cloud[r][c] != -1) {
					cloud[r][c] = 1;
					map[r][c] -= 2;
				}
				if(cloud[r][c] == -1) cloud[r][c] = 0;
			}
		}
	}

	static int xdr[] = {-1,-1,1,1};
	static int xdc[] = {-1,1,1,-1};
	private static void duplicateWaterMagic() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (checkWaterPlus[r][c]) {
					int plusCnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nr = r + xdr[dir];
						int nc = c + xdc[dir];
						
						if (!check(nr, nc)) continue;
						
						if (map[nr][nc] >= 1) ++plusCnt;
					}
					
					map[r][c] += plusCnt;
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r>=1 && r<=N && c>=1 && c<=N;
	}

	private static void disappearCloud() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (cloud[r][c] == 1) {
					cloud[r][c] = -1;
				}
			}
		}
	}

	private static void rain() {
		checkWaterPlus = new boolean[N+1][N+1];
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (cloud[r][c] == 1) {
					checkWaterPlus[r][c] = true;
					++map[r][c];
				}
			}
		}
	}

	private static void moveCloud(int m) {
		curCloud = new int[N+1][N+1];
		
		int dir = move[m][0];
		int dist = move[m][1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (cloud[r][c] == 1) {
					
					int nr = r + dist*dr[dir];
					int nc = c + dist*dc[dir];
					
					// 음수일 때
					if (nr < 0) {
						int share = Math.abs(nr) / N;
						
						if (Math.abs(nr) % N != 0) {
							++share;
						}
						
						nr = share*N + nr;
						if (nr == 0) nr = N;
					} else if (nr >= 0) { // 양수일 때
						nr = nr%N == 0 ? N : nr%N;
					}
					
					// 음수일 때
					if (nc < 0) {
						int share = Math.abs(nc) / N;
						
						if (Math.abs(nc) % N != 0) {
							++share;
						}
						
						nc = share*N + nc;
						if (nc == 0) nc = N;
					} else if (nc >= 0) { // 양수일 때
						nc = nc%N == 0 ? N : nc%N;
					}
					
					curCloud[nr][nc] = 1;
				}
			}
		}
		
		for (int i = 0; i <= N; i++) {
			cloud[i] = curCloud[i].clone();
		}
	}

}
