package algo0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1992_쿼드트리 {

	static int N;
	static char[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(in.readLine());
		
		// map N의 크기만큼 동적할당 
		map=new char[N][N];
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(in.readLine());
			map[i]=st.nextToken().toCharArray();
		}
		
		// 전체가 0이나 1일 때 체크 
		if(checkQuad(0,0,N)) {
			System.out.println("("+sb.toString()+")");
			return;	//전체가 0이나 1이면 0이나 (0)이나 (1)을 리턴하고 종
		}
		
		quadTree(0,0,N); // 분할정복 시작 
		
		System.out.println(sb.toString());
	}
	
	//분할정복(사등분) 
	private static void quadTree(int x, int y, int width) {
		if(width==1) {
			sb.append(map[x][y]);
			return;
		}
		sb.append("(");
		
		int w=width/2;
		
		if(!checkQuad(x,y,w)) {	//왼쪽 위 영역의 전체가 0이나 1이 아니면 다시 사등분 
			quadTree(x,y,w);
		}
		if(!checkQuad(x,y+w,w)) {//오른쪽 위 영역의 전체가 0이나 1이 아니면 다시 사등분
			quadTree(x,y+w,w);
		}
		if(!checkQuad(x+w,y,w)) {//왼쪽 아래 영역의 전체가 0이나 1이 아니면 다시 사등분
			quadTree(x+w,y,w);
		}
		if(!checkQuad(x+w,y+w,w)) {//오른쪽 아래 영역의 전체가 0이나 1이 아니면 다시 사등분
			quadTree(x+w,y+w,w);
		}
		sb.append(")");
	}

	// 사등분한 부분의 영역이 전체가 0이나 1이 아닌지 확인 
	private static boolean checkQuad(int x, int y, int width) {
		char start = map[x][y];//맨 첫 시작의 수를 저장해 전체 수와 비교 
		
		for (int i = x; i < x+width; i++) {
			for (int j = y; j < y+width; j++) {
				if(start != map[i][j]) return false;
			}
		}	
		sb.append(start);	// 전부 다 0이나 1이 나오면 0이나 1을 출력 
		return true;
	}
}
