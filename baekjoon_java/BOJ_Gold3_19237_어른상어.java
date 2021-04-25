package yes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 상어 - 1 이상 M 이하의 자연수 번호
// 1의 번호를 가진 어른 상어는 가장 강력해서 나머지 모두를 쫓아낼 수 있다.
// NxN 크기의 격자 중 M개의 칸에 상어가 한 마리씩 들어 있다.
// 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다.
// 1초마다 모든 상어가 동시에 상하좌우 인접한 칸 중 하나로 이동, 자신의 냄새를 그 칸에 뿌린다.
//	-> 냄새는 상어가 k번 이동하고나면 사라진다.
//
// 각 상어가 이동 방향을 결정할 때 : 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다.
// -> 이런 경우 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다. 이 때는 특정한 우선순위를 따른다.
// 같은 상어라도 현재 상어가 보고 있는 방향에 따라 또 다를 수 있다.

// 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어를
// 제외하고 모두 격자 밖으로 쫓겨난다.
public class BOJ_Gold3_19237_어른상어 {
	
	static class Shark {
		int number;
		int dir;
		int r;
		int c;
		boolean alive;
		
		int[] up = new int[4];
		int[] down = new int[4];
		int[] left = new int[4];
		int[] right = new int[4];
		
		
		public Shark() {}
		
		public Shark(int number, boolean alive) {
			super();
			this.number = number;
			this.alive = alive;
		}
	}

	static int N,M,k,map[][],time,sharkCnt;
	static int smell[][][];
	static Shark sharks[];
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		smell = new int[N][N][2];
		sharkCnt = M;
		
		sharks = new Shark[M+1];
		
		for (int i = 1; i <= M; i++) {
			sharks[i] = new Shark(i, true);
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					sharks[map[i][j]].r = i;
					sharks[map[i][j]].c = j;
					smell[i][j][0] = map[i][j];	// 상어 번호
					smell[i][j][1] = k; // 뿌린 냄새 남은 시간
				}
			}
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= M; i++) {
			sharks[i].dir = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(in.readLine(), " ");
				int order[] = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				if(j==0) {
					sharks[i].up = order;
				} else if(j==1) {
					sharks[i].down = order;
				} else if(j==2) {
					sharks[i].left = order;
				} else if(j==3) {
					sharks[i].right = order;
				}
			}
		}
		
		System.out.println(process());

	}
	private static int process() {
		while(true) {
			if(time>1000) {
				return -1;
			}
			
			++time;
			
			for (int i = 1; i <= M; i++) {
				if(sharks[i].alive) { // 해당 상어가 살아있다면
					moveShark(sharks[i]);
				}
			}
			
			//상어 이동이 끝난 후, 냄새 감소 
			smellRemove();
			
			// 같은 칸에 있는 상어 확인 =>  가장 작은 번호를 가진 상어 제외하고 쫓겨난다.
			checkShark();
			
			// 현재 상어가 있는 칸에 냄새 뿌리기
			currentSmell();
			
			// 상어 하나만 남았는지 체크
			if(checkOneShark()) {
				return time;
			}
			
		}
	}
	
	private static boolean checkOneShark() {
		int cnt=0;
		for (int i = 1; i <= M; i++) {
			if(sharks[i].alive) ++cnt;
		}
		
		return cnt==1? true : false;
	}
	private static void currentSmell() {
		for (int i = 1; i <= M; i++) {
			if(sharks[i].alive) {
				smell[sharks[i].r][sharks[i].c][0] = sharks[i].number;
				smell[sharks[i].r][sharks[i].c][1] = k;
			}
		}
		
	}
	private static void checkShark() {
		for (int i = 1; i <= M; i++) {
			if(sharks[i].alive) {
				for (int j = i+1; j <= M; j++) {
					if(sharks[j].alive && sharks[i].r == sharks[j].r && sharks[i].c == sharks[j].c) {
						if(sharks[i].number < sharks[j].number) {
							sharks[j].alive = false;
						} else {
							sharks[i].alive = false;
						}
					}
				}
			}
		}
		
	}
	private static void smellRemove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(smell[i][j][1]!=0) {
					if(--smell[i][j][1]==0) {
						smell[i][j][0] = 0;
					}
				}
			}
		}
		
	}
	// 상어 이동
	private static void moveShark(Shark shark) {
		boolean priority = false;
		switch (shark.dir) {
		case 1:
			for(int d : shark.up) {
				int nr = shark.r + dr[d];
				int nc = shark.c + dc[d];
				
				// 격자 밖으로 나가거나 특정 냄새가 있는 칸이면
				if(!check(nr, nc) || smell[nr][nc][1] != 0) continue; //
				priority = true;
				shark.r = nr;
				shark.c = nc;
				shark.dir = d;
				
				break;
			}
			
			if(!priority) {
				for(int d : shark.up) {
					int nr = shark.r + dr[d];
					int nc = shark.c + dc[d];
					
					// 격자 밖으로 나가거나 내 냄새가 있는 칸이 아니면
					if(!check(nr, nc) || smell[nr][nc][0] != shark.number) continue;
					
					shark.r = nr;
					shark.c = nc;
					shark.dir = d;
					break;
				}
			}
			break;
		case 2:
			for(int d : shark.down) {
				int nr = shark.r + dr[d];
				int nc = shark.c + dc[d];
				
				// 격자 밖으로 나가거나 특정 냄새가 있는 칸이면
				if(!check(nr, nc) || smell[nr][nc][1] != 0) continue; //
				priority = true;
				shark.r = nr;
				shark.c = nc;
				shark.dir = d;
				
				break;
			}
			
			if(!priority) {
				for(int d : shark.down) {
					int nr = shark.r + dr[d];
					int nc = shark.c + dc[d];
					
					// 격자 밖으로 나가거나 내 냄새가 있는 칸이 아니면
					if(!check(nr, nc) || smell[nr][nc][0] != shark.number) continue;
					
					shark.r = nr;
					shark.c = nc;
					shark.dir = d;
					break;
				}
			}
			break;
		case 3:
			for(int d : shark.left) {
				int nr = shark.r + dr[d];
				int nc = shark.c + dc[d];
				
				// 격자 밖으로 나가거나 특정 냄새가 있는 칸이면
				if(!check(nr, nc) || smell[nr][nc][1] != 0) continue; //
				priority = true;
				shark.r = nr;
				shark.c = nc;
				shark.dir = d;
				
				break;
			}
			
			if(!priority) {
				for(int d : shark.left) {
					int nr = shark.r + dr[d];
					int nc = shark.c + dc[d];
					
					// 격자 밖으로 나가거나 내 냄새가 있는 칸이 아니면
					if(!check(nr, nc) || smell[nr][nc][0] != shark.number) continue;
					
					shark.r = nr;
					shark.c = nc;
					shark.dir = d;
					break;
				}
			}
			break;
		case 4:
			for(int d : shark.right) {
				int nr = shark.r + dr[d];
				int nc = shark.c + dc[d];
				
				// 격자 밖으로 나가거나 특정 냄새가 있는 칸이면
				if(!check(nr, nc) || smell[nr][nc][1] != 0) continue; //
				priority = true;
				shark.r = nr;
				shark.c = nc;
				shark.dir = d;
				
				break;
			}
			
			if(!priority) {
				for(int d : shark.right) {
					int nr = shark.r + dr[d];
					int nc = shark.c + dc[d];
					
					// 격자 밖으로 나가거나 내 냄새가 있는 칸이 아니면
					if(!check(nr, nc) || smell[nr][nc][0] != shark.number) continue;
					
					shark.r = nr;
					shark.c = nc;
					shark.dir = d;
					break;
				}
			}
			break;
		default:
			break;
		}

	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
