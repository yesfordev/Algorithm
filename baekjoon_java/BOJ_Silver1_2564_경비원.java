package algo0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Silver1_2564_경비원 {

	static int width, height, shopCnt;
	static int shop[][]; // [0] = r, [1] = c
	static int person[];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		
		shopCnt = Integer.parseInt(in.readLine());
		
		shop = new int[shopCnt][2];
		person = new int[2];
		
		for (int i = 0; i < shopCnt; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			checkRC(shop[i], dir, dist);
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		int dir = Integer.parseInt(st.nextToken());
		int dist = Integer.parseInt(st.nextToken());
		checkRC(person, dir, dist);
		
		System.out.println(process());
	}
	
	private static int process() {
		int sumDistance = 2*(height + width);
		int minDistSum = 0;
		int personDist = 0;
		if(person[0] == height || person[1] == 0) {
			personDist = person[0] + person[1];
		} else {
			personDist = - person[0] - person[1];
		}
		
		for (int i = 0; i < shopCnt; i++) {
			int shopDist = 0;
			if(shop[i][0] == height || shop[i][1] == 0) {
				shopDist = shop[i][0] + shop[i][1];
			} else {
				shopDist = - shop[i][0] - shop[i][1];
 			}
			
			int distance = Math.abs(personDist - shopDist);
			
			minDistSum += Math.min(distance, sumDistance - distance);
		}
		return minDistSum;
	}

	private static void checkRC(int[] arr, int dir, int dist) {
		if(dir==1) {
			arr[0] = 0;
			arr[1] = dist;
		} else if(dir==2) {
			arr[0] = height;
			arr[1] = dist;
		} else if(dir==3) {
			arr[0] = dist;
			arr[1] = 0;
		} else if(dir==4) {
			arr[0] = dist;
			arr[1] = width;
		}
	}
}