package algo0204;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swea_D3_1225 {
	static int N;
	static Queue<Integer> queue=new LinkedList<Integer>();
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        for (int t = 1; t <= 10; t++) {
        	queue.clear();
        	N=scann.nextInt();
			for (int i = 0; i < 8; i++) {
				queue.offer(scann.nextInt());
			}
			int num=1;
			while(true) {
				int temp = queue.poll() - num++;
				if(temp<=0) {
					queue.offer(0);
					break;
				}
				queue.offer(temp);
				if(num>5) {
					num=1;
				}
			}
			System.out.print("#"+t+" ");
			while(!queue.isEmpty()) {
				System.out.print(queue.poll() + " ");
			}
			System.out.println();
		}
    }
}
