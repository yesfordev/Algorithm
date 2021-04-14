package yes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <문제 요약>
 * 구해야 하는 것: 순서대로 진행하다가, 종료되었을 때 몇 번째 단계였는지 출력
 * 제약 사항: 2<=N<=100, 1<=K<=2N, 1<=내구도<=1000
 * 문제 유형: 시뮬레이션
 * 요구 개념: 시뮬레이션
 * 
 * <풀이법 요약>
 * 
 */
public class BOJ_Silver1_20055_컨베이어벨트위의로봇 {

	static class Belt {
		boolean robotExist;
		int power;
		
		public Belt(boolean robotExist, int power) {
			super();
			this.robotExist = robotExist;
			this.power = power;
		}
	}

	static int N, K, n, zeroCnt;
	static Belt[] upBelt;
	static Belt[] downBelt;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		upBelt = new Belt[N+1];
		downBelt = new Belt[N+1];
		
		st = new StringTokenizer(in.readLine()," ");
		for (int i = 1; i <= N; i++) {
			int p = Integer.parseInt(st.nextToken());
			upBelt[i] = new Belt(false, p);
			if(p == 0) ++zeroCnt;
		}
		for (int i = 1; i <= N; i++) {
			int p = Integer.parseInt(st.nextToken());
			downBelt[i] = new Belt(false, p);
			if(p == 0) ++zeroCnt;
		}
		
		System.out.println(process());
	}
	
	private static int process() {
		int stage=0;
		
		while(true) {
			++stage;
			// 1. 벨트가 한 칸 회전한다.
			Belt upBeltTemp = upBelt[N];
			upBeltTemp.robotExist = false;
			for (int i = N; i > 1; i--) {
				upBelt[i] = upBelt[i-1];
			}
			Belt downBeltTemp = downBelt[N];
			for (int i = N; i > 1; i--) {
				downBelt[i] = downBelt[i-1];
			}
			upBelt[1] = downBeltTemp;
			downBelt[1] = upBeltTemp;
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
			// 만약 이동할 수 없다면 가만히 있는다.
			
			for (int i = N; i >= 1; i--) {
				if(upBelt[i].robotExist) {
					if (i == N) {
						upBelt[i].robotExist = false;
					} else if (i != N && !upBelt[i + 1].robotExist && upBelt[i + 1].power >= 1) {
						upBelt[i].robotExist = false;
						upBelt[i + 1].robotExist = true;

						if (--upBelt[i + 1].power == 0) ++zeroCnt;

						if (zeroCnt >= K) return stage;
					}
				}
			}
			
			// 3. 올라가는 위치에 로봇이 없다면 로봇을 하나 올린다.
			if(!upBelt[1].robotExist && upBelt[1].power >= 1) {
				upBelt[1].robotExist = true;
				
				if(--upBelt[1].power == 0) ++zeroCnt;
				
				if(zeroCnt >= K) return stage;
			}
			
			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정 종료
//			if(zeroCnt >= K) return stage;
		}
	}
}
