package algo2108_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Gold4_1062_가르침 {

	static int N, K, maxWordCnt = 0;
	static boolean arr[];
	static String word[];
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		word = new String[N];
		arr = new boolean[26];
		
		for (int idx = 0; idx < N; idx++) {
			String str = in.readLine();
			word[idx] = str.substring(4, str.length()-4);
		}
		arr[0] = arr[2] = arr[8] = arr[13] = arr[19] = true;
		
		comb(1, 0);
		
		System.out.println(maxWordCnt);
	}
	
	private static void comb(int curIdx, int cnt) {
		if(cnt == K-5) {
			checkWord();
			return;
		}
		
		for (int idx = curIdx; idx < 26; idx++) {
			if(!arr[idx]) {
				arr[idx] = true;
				comb(idx+1, cnt+1);
				arr[idx] = false;
			}
		}
	}

	private static void checkWord() {
		int wordCnt = 0;
		for (int nIdx = 0; nIdx < N; nIdx++) {
			int strLen = word[nIdx].length();
			
			boolean pass = true;
			for (int strIdx = 0; strIdx < strLen; strIdx++) {
				if(!arr[word[nIdx].charAt(strIdx) - 'a']) {
					pass = false;
					break;
				}
			}
			
			if(pass) ++wordCnt;
		}
		
		maxWordCnt = Math.max(maxWordCnt, wordCnt);
	}
}
