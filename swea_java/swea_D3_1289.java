import java.util.List;
import java.util.Scanner;


public class swea_D3_1289 {

	static int T;
	
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		T=scann.nextInt();
		
		for (int t = 1; t <= T; t++) {
			String ss=scann.next();
			int count=0;
			//
			char temp= '1';
			for (int i = 0; i < ss.length(); i++) {
				if(ss.charAt(i) == temp) {
					count++;
					
					if(temp == '0') {
						temp='1';
					} else {
						temp='0';
					}
				}
			}
			
			//
			System.out.println("#"+t+" "+count);
		}

	}

}
