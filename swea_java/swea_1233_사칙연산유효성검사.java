package algo0209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_1233_사칙연산유효성검사 {
	
	static int N;
	static char[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(in.readLine());
			boolean isS=true;
			
			if(N%2==0) {
				isS=false;
			}
			
			arr=new char[N+1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				if(st.countTokens()==4) {
					st.nextToken();
					String ss=st.nextToken();
					if(!isOper(ss)) isS=false;
				} else if(st.countTokens()==2) {
					st.nextToken();
					String ss=st.nextToken();
					if(isOper(ss)) isS=false;
				}
				
			}
			
			
			System.out.println("#"+t+" "+(isS?1:0));
		}
	}

	private static boolean isOper(String ss) {
		return ss.equals("+") || ss.equals("-") 
				|| ss.equals("*") || ss.equals("/");
	}


}
