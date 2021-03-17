package algo0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Silver3_14889_스타트와링크 {
	
	static int N, R, answer = 9999;
	static int[][] power;
	static boolean[] pick;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		
		power = new int[N][N];
		pick = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine()," ");
			for (int j = 0; j < N; j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		selectTeam(0, 0);
		
		System.out.println(answer);
	}

	private static void selectTeam(int idx, int cnt) {
		if(cnt == N/2) {
			int start=0;
			int link=0;
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					if(pick[i] && pick[j]) start=start+power[i][j]+power[j][i];
					else if(!pick[i] && !pick[j]) link=link+power[i][j]+power[j][i];
				}
			}
			answer = Math.min(answer, Math.abs(start-link));
			return;
		}
		if(idx==N) return;
		
		pick[idx] = true;
		selectTeam(idx + 1, cnt+1);
		pick[idx]= false;
		selectTeam(idx+1, cnt);
	}
}
