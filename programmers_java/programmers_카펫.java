package algoStudy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <문제 요약>
 * 구해야 하는 것: 전체 카펫의 크기
 * 제약 사항: 카펫의 노란색과 갈색으로 색칠된 격자의 개수가 주어진다. 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 길다.
 * 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있다. 
 * 문제 유형: 완전 탐색 
 * 요구 개념: 단순 구현..? 완전 탐색 -> 모든 경우 체
 * 
 * <풀이법 요약>
 * 1. 사각형의 width, height의 곱이 brown+yellow와 같다는 것을 이용해 모든 경우를 탐색 
 * 2. brown이 한 줄이라는 사실에 의거해 탐색하면서, width=yw+2, height=yh+2를 만족하는 조건을 찾아 return 
 *
 */
public class programmers_카펫 {
	
	static int width, height, yw, yh;

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		
		int brown=scann.nextInt();
		int yellow=scann.nextInt();
		
		int[] lastAnswer = solution(brown, yellow);
		
		System.out.println(Arrays.toString(lastAnswer));

	}
	
	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		
		int sum = brown+yellow;
		// 가로가 세로보다 큰 것 체크 
		int start = Math.sqrt(sum)%1 > 0 ? (int) Math.sqrt(sum)+1 : (int) Math.sqrt(sum);
		for (int i = start; i <= sum; i++) { // i는 가로의 길
			if(sum%i == 0) {
				int width=i;
				int height=sum/i;
				
				// yellow width는 width-2, yellow height는 height-2 
				int yw=width-2;
				int yh=height-2;
				
				if(yw*yh == yellow) {
					answer[0] = width;
					answer[1] = height;
					break;
				}
			}
		}
		return answer;
	}
}
