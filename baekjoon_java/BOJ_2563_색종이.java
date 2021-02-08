package hw_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2563_색종이 {
	
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		int N = Integer.parseInt(in.readLine());
		arr = new int[102][102];
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			checkArea(x, y);
		}
		int sum=0;
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				sum+=arr[i][j];
			}
		}
		System.out.println(sum);
	}
	private static void checkArea(int x, int y) {
		for (int i = x; i < x+10; i++) {
			for (int j = y; j < y+10; j++) {
				arr[i][j]=1;
			}
		}
	}
}
