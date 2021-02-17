package algo0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686_치킨거리 {

	static int N,M;
	static int chickCnt, homeCnt;
	static ArrayList<int[]> chickAll = new ArrayList<>();
	static ArrayList<int[]> home = new ArrayList<>();
	static int[] chickChoice;
	static int map[][] = new int[52][52];
	static int answer=Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(in.readLine(), " ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		chickChoice=new int[M];
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) { 	// 치킨집 저장 
					chickAll.add(new int[] {i,j});
					chickCnt++;
				}
				if(map[i][j]==1) {	// 집 저장 
					home.add(new int[] {i,j});
					homeCnt++;
				}
			}
		}
		
		nCr(0,0);
		System.out.println(answer);
	}

	private static void nCr(int cnt, int start) {
		if(cnt==M) {
			checkDist();
			return;
		}
		
		for (int i = start; i < chickCnt; i++) {
			chickChoice[cnt]=i;
			nCr(cnt+1, i+1);
		}
	}

	private static void checkDist() {
		int sum=0;
		for (int i = 0; i < homeCnt; i++) {
			int minDist=Integer.MAX_VALUE;
			for (int j = 0; j < M; j++) {
				int dist=Math.abs(home.get(i)[0]-chickAll.get(chickChoice[j])[0]) + Math.abs(home.get(i)[1]-chickAll.get(chickChoice[j])[1]);
				minDist = Math.min(minDist, dist);
			}
			sum+=minDist;
		}
		answer=Math.min(sum, answer);
	}
}
