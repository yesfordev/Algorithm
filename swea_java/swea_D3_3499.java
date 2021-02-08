package algo0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_D3_3499 {

	static int T, N;
	static Queue<String> queue1 = new LinkedList<>();
	static Queue<String> queue2 = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");		
			int count = st.countTokens();			
			if(count%2==0) {
				for (int i = 0; i < count/2; i++) {
					queue1.offer(st.nextToken());
				}
				for (int i = count/2; i < count; i++) {
					queue2.offer(st.nextToken());
				}
			} else {
				for (int i = 0; i <= count/2; i++) {
					queue1.offer(st.nextToken());
				}
				for (int i = count/2+1; i < count; i++) {
					queue2.offer(st.nextToken());
				}
			}
			System.out.print("#"+t+" ");
			while(!queue1.isEmpty()) {
				System.out.print(queue1.poll()+" ");
				if(!queue2.isEmpty()) {
					System.out.print(queue2.poll()+" ");
				}
			}
			System.out.println();
		}
	}
}
