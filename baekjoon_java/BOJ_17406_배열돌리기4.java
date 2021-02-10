package algo0210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17406_배열돌리기4 {
	
	static int N,M,K;
	static int[] p;
	static int[][] arr;
	static int[][] tempArr;
	static ArrayList<int[]> list = new ArrayList<>();
	static int answer=Integer.MAX_VALUE;
	static int[] dr= {0,1,0,-1};
	static int[] dc= {1,0,-1,0};

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(in.readLine(), " ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		arr=new int[N+1][M+1];
		tempArr=new int[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			st=new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		p=new int[K];
		for (int i = 0; i < K; i++) {
			st=new StringTokenizer(in.readLine(), " ");
			int[] s = new int[3];
			s[0]=Integer.parseInt(st.nextToken());
			s[1]=Integer.parseInt(st.nextToken());
			s[2]=Integer.parseInt(st.nextToken());
			
			list.add(s);
			p[i]=i;
		}
		// 로직 - nextPermutation
		
		do {
			initArr();
			for (int i = 0; i < p.length; i++) {
				checkRotate(list.get(p[i])[0], list.get(p[i])[1], list.get(p[i])[2]);
			}
			
			int sum=calculateSum();
			
			answer=Math.min(sum, answer);
		} while(next_permutation(p.length-1));
		
		System.out.println(answer);
	}

	private static int calculateSum() {
		int checkSum=Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int check=0;
			for (int j = 1; j <= M; j++) {
				check+=tempArr[i][j];
			}
			checkSum=Math.min(checkSum, check);
		}
		return checkSum;
	}

	private static void checkRotate(int r, int c, int s) {
		for (int i = 0; i < s; i++) {
			int dir=0;
			int curR=r-s+i;
			int curC=c-s+i;
			int temp=tempArr[curR][curC];
			int temp2=0;
			while(true) {
				int nr = curR+dr[dir];
				int nc = curC+dc[dir];
				
				if(nr<r-s+i || nr>r+s-i || nc<c-s+i || nc>c+s-i) {
					dir=(dir+1)%4;
					
					nr=curR+dr[dir];
					nc=curC+dc[dir];
				}
				temp2=tempArr[nr][nc];
				tempArr[nr][nc]=temp;
				temp=temp2;
				
				if(nr==r-s+i && nc==c-s+i) break;
				
				curR=nr;
				curC=nc;
			}
		}
	}

	private static void initArr() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				tempArr[i][j]=arr[i][j];
			}
		}		
	}

	private static boolean next_permutation(int size) {
		int i=size;
		while(i>0 && p[i-1]>=p[i]) i--;
		
		if(i==0) return false;
		
		int j=size;
		while(p[i-1]>=p[j]) j--;
		int temp=p[i-1];
		p[i-1]=p[j];
		p[j]=temp;
		
		int k=size;
		while(i<k) {
			temp=p[i];
			p[i]=p[k];
			p[k]=temp;
			k--;
			i++;
		}
		return true;
	}

}
