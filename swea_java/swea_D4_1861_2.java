//package algo0205;
//
//import java.util.Scanner;
//import java.util.Stack;
//
//public class swea_D4_1861_2 {
//	
//	static int T,N,startNum, visitNum;
//	static int[][] map;
//	static int[][] visit;
//	static int[] dx = {1,0,-1,0};
//	static int[] dy = {0,1,0,-1};
//	static Stack<int[]> stack = new Stack<int[]>();
//	
//	public static void main(String[] args) {
//		Scanner scann = new Scanner(System.in);
//		T=scann.nextInt();
//		for (int t = 1; t <= T; t++) {
//			N=scann.nextInt();
//			
//			map=new int[N+2][N+2];
//			visit=new int[N+2][N+2];
//			stack.clear();
//			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					map[i][j]=scann.nextInt();
//				}
//			}
////			startNum=Integer.MAX_VALUE;
////			visitNum=0;
////			dfs();
//			
//			for(int i=0; i<N; i++) {
//				for (int j = 0; j < N; j++) {
//					if(visit[i][j]!=) continue;
//					
//					dfs(i,j);
//				}
//			}
//			
//			System.out.println("#"+t+" "+startNum+" "+visitNum);			
//		}		
//	}
//
//	private static void dfs(int i, int j) {
//			visit[nr][]
//			for (int dir = 0; dir < 4; dir++) {
//				int nx=i+dx[dir];
//				int ny=j+dy[dir];
//				
//				if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny] != map[i][j]+1) continue;
//				
//				visit[nx][ny]=visit[i][j]+1;
//				dfs(nx,ny);
//			}
//	}
//}
