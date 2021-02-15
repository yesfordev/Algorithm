package algo0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_D3_6808_규영이와인영이의카드게임 {
	
	static int N=9;
	static boolean [] v;
	static int T;
	static int [] tt;
	static int [] gy;
	static int [] iy;
	static int [] num;
	static int ngy, niy;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		T=Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			tt=new int[19];
			gy = new int[9];
			iy = new int[9];
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < 9; i++) {
				gy[i]=Integer.parseInt(st.nextToken());
				tt[gy[i]]=1;
			}
			int idx=0;
			for (int i = 1; i <= 18; i++) {
				if(tt[i]==0) {
					iy[idx++]=i;
				}
			}
			Arrays.sort(iy);
			int answerWin = 0;
			int answerLose = 0;
			do {
				int sumKyu = 0;
				int sumIn = 0;
				
				for (int i = 0; i < gy.length; i++) {
					if(gy[i] > iy[i]) {
						sumKyu = sumKyu+gy[i]+iy[i];
					} else {
						sumIn = sumIn+gy[i]+iy[i];
					}
				}
				
				if(sumKyu>sumIn) answerWin++;
				else answerLose++;
				
			}while(np(iy.length-1));

			System.out.println("#"+t+" "+answerWin+" "+answerLose);
		}
	}
	private static boolean np(int size) {
		int i=size;
		while(i>0 && iy[i-1] >= iy[i]) --i;
		
		if(i==0) return false;
		
		int j=size;
		while(iy[i-1] >= iy[j]) --j;
		int temp=iy[i-1];
		iy[i-1]=iy[j];
		iy[j]=temp;
		
		int k=size;
		while(i<k) {
			temp=iy[i];
			iy[i]=iy[k];
			iy[k]=temp;
			k--;
			i++;
		}
		return true;
	}
}
