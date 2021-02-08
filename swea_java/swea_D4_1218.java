package algo0204;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class swea_D4_1218 {
	
	static int N;

	public static void main(String[] args) {
		
		Scanner scann=new Scanner(System.in);
		for (int t = 1; t <= 10; t++) {
			int size=scann.nextInt();
			char[] str=scann.next().toCharArray();
			int answer = 1;
			
			HashMap<Character, Character> map=new HashMap<>();
			map.put('(', ')');
			map.put('{', '}');
			map.put('[', ']');
			map.put('<', '>');
			
			Stack<Character> stack = new Stack<>();
						
			for (int i = 0; i < size; i++) {
				if(map.containsKey(str[i])) {
					stack.push(str[i]);
				} else {
					if(map.get(stack.pop()) != str[i]) {
						answer = 0;
						break;
					}
				}
			}

			System.out.println("#"+t+" "+answer);
		}

	}

}
