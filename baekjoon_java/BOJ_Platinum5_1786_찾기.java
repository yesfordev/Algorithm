package algo2108_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * KMP 알고리즘! 공부 다시하기
 */
public class BOJ_Platinum5_1786_찾기 {

	static int pi[], cnt;
	static String T, P;
	static List<Integer> position = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		T = in.readLine();
		P = in.readLine();
		 
		pi = new int[P.length()];
		
		getPi();
		KMP();
		
		bw.write(cnt+"\n");
		for (Integer pos : position) {
			bw.write(pos+" ");
		}
		bw.flush();
		bw.close();
		in.close();
	}
	private static void getPi() {
		int j = 0;
		for (int i = 1; i < P.length(); i++) {
			while(j > 0 && P.charAt(j) != P.charAt(i)) {
				j = pi[j-1];
			}
			
			if(P.charAt(j) == P.charAt(i)) {
				pi[i] = ++j;
			}
		}
	}
	
	private static void KMP() {
		int j = 0;
		for (int i = 0; i < T.length(); i++) {
			while(j > 0 && P.charAt(j) != T.charAt(i)) {
				j = pi[j-1];
			}
			
			if(P.charAt(j) == T.charAt(i)) {
				if(j == P.length()-1) { // 패턴이 모두 일치할 경우
					++cnt;
					position.add(i-j+1);
//					j = 0; // 패턴은 맨 처음으로 되돌아가야 함
					j = pi[j]; // 패턴은 맨 처음으로 되돌아가야 함
				} else { // 일치하지 않을 경우 계속해서 패턴 맞는지 확인
					++j;
				}
			}
		}
		
	}

}
