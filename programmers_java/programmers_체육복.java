package algoStudy;

import java.util.Arrays;

/**
 * <문제 요약>
 * 구해야 하는 것:체육수업을 들을 수 있는 학생의 최댓
 * 제약 사항: 바로 앞번호 학생이나 바로 뒷번호 학생에게만 체육복을 빌려줄 수 있다. 
 * 문제 유형: 그리디  
 * 요구 개념: 그리디  
 * 
 * <풀이법 요약>
 * 1. n만큼 할당받은 students 배열을 1로 초기
 * 2. lost 배열의 인덱스의 값을 1씩 빼주고, 여벌 옷이 있는 사람들은 1을 증가 
 * 3. 여벌 옷이 있고, 도난 당하지 않은 사람들이 있으면 앞 -> 뒤로 확인하며 최적의 선택 찾기 
 * 4. 체육복 있는 학생 수 체크 
 *
 */
public class programmers_체육복 {

	static int n;
	public static void main(String[] args) {
		
		n=3;
		int[] lost = {3};
		int[] reserve = {1};
		int answer = solution(n,lost, reserve);
		
		System.out.println(answer);
	}
	
    public static int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n+1];
        
        Arrays.fill(students, 1);
        Arrays.sort(reserve);
        
        for (int i = 0; i < lost.length; i++) {
			students[lost[i]]--;
		}
        for (int i = 0; i < reserve.length; i++) {
			students[reserve[i]]++;
		}
        
        for (int i = 0; i < reserve.length; i++) {
			if(reserve[i] == 1) {
				if(students[reserve[i]]>1 && students[reserve[i]+1] == 0) {
					students[reserve[i]+1]++;
					students[reserve[i]]--;
				}
			}else if(reserve[i] == n) {
				if(students[reserve[i]]>1 && students[reserve[i]-1] == 0) {
					students[reserve[i]-1]++;
					students[reserve[i]]--;
				}
			} else {
				if(students[reserve[i]]>1 && students[reserve[i]-1] == 0) {
					students[reserve[i]-1]++;
					students[reserve[i]]--;
				} else if(students[reserve[i]]>1 && students[reserve[i]+1] == 0) {
					students[reserve[i]+1]++;
					students[reserve[i]]--;
				}
			}
		}

    	int answer = 0;
    	
        for (int i = 1; i <= n; i++) {
			if(students[i]>=1) answer++;
		}
        return answer;
    }
}
