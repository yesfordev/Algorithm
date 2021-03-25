package algo0325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_3307_최장증가부분수열 {

	static int T, N;
	static int[] arr;
	static int[] LIS;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		T = Integer.parseInt(in.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			
			arr=new int[N];
			LIS=new int[N];
			
			st = new StringTokenizer(in.readLine()," ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;
			
			for (int i = 0; i < N; i++) {
				LIS[i] = 1;
				for (int j = 0; j < i; j++) {
					if(arr[i]>arr[j] && LIS[j]+1 > LIS[i]) {
						LIS[i] = LIS[j]+1;
					}
				}
				max = Math.max(max, LIS[i]);
 			}
			
			System.out.println("#"+t+" "+max);
			
		}
	}
}
