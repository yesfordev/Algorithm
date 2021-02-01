package hw_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon_1244 {

	static int N, stuCnt;
	static int[] arr = new int[105];
	
	/**
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		StringTokenizer st1 = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			arr[i]=Integer.parseInt(st1.nextToken());
		}
		
		stuCnt = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < stuCnt; i++) {
			StringTokenizer st2 = new StringTokenizer(in.readLine(), " ");
			int gender = Integer.parseInt(st2.nextToken());
			int number = Integer.parseInt(st2.nextToken());
			
			changeArr(gender, number);
		}
		
		//출력
		for (int i = 1; i <= N; i++) {
			System.out.print(arr[i]+" ");
			if(i%20==0) {
				System.out.println();
			}
		}
	}

	private static void changeArr(int gender, int number) {
		if(gender == 1) {
			int mul=1;
			while(true) {
				if(number*mul > N) break;
				changeState(number*mul);
				mul++;
			}
		} else {
			int temp=1;
			while(true) {
				if(number-temp<1 || number+temp>N || arr[number-temp] != arr[number+temp]) {
					temp--;
					break;
				}
				temp++;
			}
			for (int i = number-temp; i <= number+temp; i++) {
				changeState(i);
			}
		}
	}
	
	private static void changeState(int num) {
		if(arr[num]==1) {
			arr[num]=0;
		} else {
			arr[num]=1;
		}
 	}


}
