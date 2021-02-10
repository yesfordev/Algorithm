package algo0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16926_배열돌리기1 {

	static int N,M,R;
	static int[][] arr;
	static int[] dn= {1,0,-1,0};
	static int[] dm= {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		
		st=new StringTokenizer(in.readLine(), " ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		
		arr=new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st=new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < R; i++) {
			int plus=0;
			if(M<=N) {
				while(plus<M/2) {
					rotateArr(M-plus, N-plus, plus);
					plus++;
				}
			} else {
				while(plus<N/2) {
					rotateArr(M-plus, N-plus, plus);
					plus++;
				}
			}
		}
		print();
	}
	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	private static void rotateArr(int curM, int curN, int plus) {
		int n=plus, m=plus;
		int dir=0;
		int temp=arr[n][m];
		int temp2=0;
		while(true) {
			int nextN=n+dn[dir];
			int nextM=m+dm[dir];
			
			if(nextN<plus || nextN>=curN || nextM<plus || nextM>=curM) {
				dir=(dir+1)%4;
				nextN=n+dn[dir];
				nextM=m+dm[dir];
			}
			
			temp2=arr[nextN][nextM];
			arr[nextN][nextM]=temp;
			temp=temp2;
			
			if(nextN==plus && nextM==plus) {
				break;
			}
			n=nextN;
			m=nextM;
		}
	}
}
