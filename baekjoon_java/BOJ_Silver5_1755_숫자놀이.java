import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 방법
 * 1. 먼저 입력받은 수들을 string으로 바꾼 후 pq에 저장 
 * 2. 출력하면서 숫자로 변환
 * @author yes
 *
 */
public class BOJ_Silver5_1755_숫자놀이 {

	//숫자와 영어 문자열을 같이 저장할 수 있도록 만든 클래스
	static class Num implements Comparable<Num> {
		int number;
		String eng;
		public Num(int number, String eng) {
			super();
			this.number = number;
			this.eng = eng;
		}
		@Override
		public int compareTo(Num o) {
			return this.eng.compareTo(o.eng);
		}
	}
	
	static int M, N;
	static PriorityQueue<Num> pq = new PriorityQueue<Num>(); // 사전순 출력 위해 필요함 
	static Map<Integer, String> check = new HashMap<Integer, String>(); // 세팅 위해 필요한 자료구조
	
	public static void main(String[] args) throws IOException {
		//입력
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 숫자에 맞는 영어 문자열 세팅
		setMap();
		
		// M부터 N까지의 수를 영어 문자열로 변경하여서 pq에 숫자와 문자열 같이 저장
		for (int i = M; i <= N; i++) {
			if(i>=10) {
				String tempEng = check.get(i/10) + " " + check.get(i%10);
				pq.offer(new Num(i, tempEng));
			} else {
				String tempEng = check.get(i);
				pq.offer(new Num(i, tempEng));
			}
		}
		
		// 출력(영어 사전 순으로)
		int cnt=0;
		while(!pq.isEmpty()) {
			if(cnt == 10) {
				System.out.println();
				cnt=0;
			}
			++cnt;
			
			System.out.print(pq.poll().number+" ");
		}
	}
	
	private static void setMap() {
		check.put(0, "zero");
		check.put(1, "one");
		check.put(2, "two");
		check.put(3, "three");
		check.put(4, "four");
		check.put(5, "five");
		check.put(6, "six");
		check.put(7, "seven");
		check.put(8, "eight");
		check.put(9, "nine");
	}
}
