package algo210306;

/**
 * <문제 요약>
 * 구해야 하는 것: 네트워크의 수를 구한다.
 * 제약 사항: 간접적으로 이어진 것도 하나의 네트워크로 본다. 
 * 문제 유형: 백트래킹(DFS) 
 * 요구 개념: 백트래킹(DFS) 
 * 
 * <풀이법 요약>
 * 1. 0번 인덱스부터 시작해주기 위해서 idx에 -1부터 담음
 * 2. 백트래킹을 돌면서 확인
 *
 */
public class PM_L3_네트워크 {

	public static void main(String[] args) {
		int[][] com = {{1,1,0},{1,1,0},{0,0,1}};
//		int[][] com = {{1,1,0},{1,1,1},{0,1,1}};
		System.out.println(solution(3, com));

	}

	static int cnt=0;
	static boolean[][] visited;
	public static int solution(int n, int[][] computers) {
		
		visited = new boolean[n][n];
		DFS(0, false, n, computers);
		
		return cnt;
	}
	
	// 처음에 푼 방식... 앞에서부터 세다가 뒤에서 0 이 되면 false가 되는 것을 고려하지 않고 해서 틀림!
	private static void DFS(int r, boolean checkNetwork, int n, int[][] com) {
		if(r==n-1) return;
		int c=r+1;
		while(c<n) {
			if(com[r][c] == 1 && checkNetwork && !visited[r][c]) {
				visited[r][c]=true;
				DFS(c, true, n, com);
			} else if(com[r][c]==1 && !checkNetwork && !visited[r][c]) {
				visited[r][c]=true;
				cnt++;
				DFS(c, true, n, com);
			} else if(com[r][c]==0) {
				checkNetwork=false;
				visited[r][c]=true;
				cnt++;
			}
			c++;
		}
	}

}
