package algo210718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Silver3_3273_두수의합 {

	static int n, arr[], x, cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(in.readLine());

		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int idx = 0; idx < n; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		}
		
		x = Integer.parseInt(in.readLine());
		
		Arrays.sort(arr);
		
		int head = 0;
		int tail = n-1;
		
		while(head < tail) {
			if(arr[head] + arr[tail] < x) ++head;
			else if(arr[head] + arr[tail] > x) --tail;
			else {
				++cnt;
				++head;
			}
		}
		
		System.out.println(cnt);
	}
}
