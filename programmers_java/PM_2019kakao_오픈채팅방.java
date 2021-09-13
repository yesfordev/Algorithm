package algo2109_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 요구사항
 * 
 * 채팅방에서 닉네임을 변경하는 방법
 * 1. 채팅방을 나간 후, 새로운 닉네임으로 다시 들어간다.
 * 2. 채팅방에서 닉네임을 변경한다.
 * 
 * 닉네임을 변경할 때는, 기존에 채팅방에 출력되어 있던 메시지의 닉네임도 전부 변경된다.
 * 
 * record - 채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열 record가 매개변수로 주어짐
 * 
 * 구해야할 것 : 모든 기록이 처리된 후, 최종적으로 방을 개설한 사람이 보게 되는 메시지를 문자열 배열 형태로 return
 * 
 * # 조건
 * 1<=record.length<=100,000
 * record : 모든 유저는 유저 아이디로 구분
 * 유저 아이디: 닉네임으로 채팅방에 입장
 * 각 단어는 공백으루 이루어져 있다.
 * 
 * 유저 아이디 - 알파벳 대문자, 소문자 구분
 * 
 * 1<=유저아이디, 닉네임 길이 <=10
 *
 */
public class PM_2019kakao_오픈채팅방 {

	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		System.out.println(Arrays.toString(PM_2019kakao_오픈채팅방.solution(record)));
	}
	
	public static String[] solution(String[] record) {
		ArrayList<String[]> chatLogs = new ArrayList<>();
		Map<String, String> user = new HashMap<>(); 
		
		for (String oneRecord : record) {
			String[] oneRecordArr = oneRecord.split(" ");
			
			// 입장시
			if (oneRecordArr[0].equals("Enter")) {
				user.put(oneRecordArr[1], oneRecordArr[2]);
				chatLogs.add(new String[] {oneRecordArr[1], oneRecordArr[0]});
			} else if (oneRecordArr[0].equals("Leave")) {
				chatLogs.add(new String[] {oneRecordArr[1], oneRecordArr[0]});
			} else { // 닉네임 변경시
				user.put(oneRecordArr[1], oneRecordArr[2]);
			}
		}
		
		String[] answer = new String[chatLogs.size()];
		int answerIdx = 0;
		
		for (String[] chatLog : chatLogs) {
			StringBuilder sb = new StringBuilder();
			sb.append(user.get(chatLog[0]));
			
			if (chatLog[1].equals("Enter")) {
				sb.append("님이 들어왔습니다.");
			} else if (chatLog[1].equals("Leave")) {
				sb.append("님이 나갔습니다.");
			}
			
			answer[answerIdx++] = sb.toString();
		}
		
        return answer;
    }
}
