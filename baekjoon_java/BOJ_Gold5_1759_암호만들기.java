import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Gold5_1759_암호만들기 {

	static int L, C;
	static char[] sec;
	static char[] answerSec;
	static ArrayList<Character> moeum = new ArrayList<Character>();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine(), " ");
		
		moeum.add('a');
		moeum.add('e');
		moeum.add('i');
		moeum.add('o');
		moeum.add('u');
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sec = new char[C];
		answerSec = new char[L];
		
		
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < C; i++) {
			sec[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(sec);
		
		nCr(0, 0);

	}
	private static void nCr(int cnt, int start) {
		if(cnt == L) {
			if(check()) {
				for (int i = 0; i < L; i++) {
					System.out.print(answerSec[i]);
				}
				System.out.println();
			};
			return;
		}
		
		for (int i = start; i < C; i++) {
			answerSec[cnt] = sec[i];
			nCr(cnt+1, i+1);
		}
		
	}
	private static boolean check() {
		
		int moeumCnt=0;
		int jaeumCnt=0;
		for (int i = 0; i < L; i++) {
			if(moeum.contains(answerSec[i])) {
				++moeumCnt;
			} else {
				++jaeumCnt;
			}
		}
		
		if(moeumCnt>=1 && jaeumCnt>=2) {
			return true;
		} else {
			return false;
		}
	}
}
