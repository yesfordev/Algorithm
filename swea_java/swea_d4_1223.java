package hw_algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class swea_d4_1223 {

	static Stack<Character> change = new Stack<Character>();
	static Stack<Integer> calc = new Stack<Integer>();
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<Character, Integer> priority = new HashMap<Character, Integer>();
		priority.put('+', 2);
		priority.put('*', 1);
		for (int t = 1; t <= 10; t++) {
			StringBuilder sb = new StringBuilder();
			change.clear(); calc.clear();
			N = Integer.parseInt(in.readLine());
			
			String str = in.readLine();
			char[] charArr = str.toCharArray();
			
			// 후위 계산식으로 변환
			for (int i = 0; i < charArr.length; i++) {
				if(charArr[i] == '+' || charArr[i]=='*') {
					if(change.isEmpty()) {
						change.push(charArr[i]);
						continue;
					} else {
						if(priority.get(charArr[i]) >= priority.get(change.peek())) {
//							while(!change.isEmpty()) {
//								sb.append(change.pop());
//							}
							sb.append(change.pop());
							change.push(charArr[i]);
						} else {
							change.push(charArr[i]);
						}
					}
				} else {
					sb.append(charArr[i]);
				}
			}
			while(!change.isEmpty()) sb.append(change.pop());
			
			char[] sic = sb.toString().toCharArray();
			
			// 후위 계산
			for (int i = 0; i < sic.length; i++) {
				
				if(sic[i] == '+') {
					int temp = calc.pop() + calc.pop();
					calc.push(temp);
					continue;
				}
				if(sic[i] == '*') {
					int temp = calc.pop() * calc.pop();
					calc.push(temp);
					continue;
				}
				calc.push(sic[i]-'0');
			}
			
			System.out.println("#"+t+" "+calc.pop());
		}
		
	}

}
