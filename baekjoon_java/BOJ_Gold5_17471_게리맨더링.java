package algo0416;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_Gold5_17471_게리맨더링 {

	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}

	static int N, population[], answer = Integer.MAX_VALUE;
	static boolean selected[];	// 부분집합을 위한 배열
	static Node[] nodes; // 구역을 인접리스트로 표현
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		population = new int[N+1];
		selected = new boolean[N+1];
		nodes = new Node[N+1];
		visited = new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			
			int len = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < len; j++) {
				nodes[i] = new Node(Integer.parseInt(st.nextToken()), nodes[i]);
			}
		}
		
		subset(1);
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
		
	}

	private static void subset(int cnt) {
		if(cnt == N+1) {
			if(checkAdj()) {
				answer = Math.min(answer, checkPopulation());
			};
			return;
		}
		
		selected[cnt] = true;
		subset(cnt + 1);
		selected[cnt] = false;
		subset(cnt + 1);
	}

	private static int checkPopulation() {
		int sumA=0, sumB=0;
		for (int i = 1; i <= N; i++) {
			if(selected[i]) {
				sumA += population[i];
			} else {
				sumB += population[i];
			}
		}
		
		return Math.abs(sumA - sumB);
	}

	static ArrayList<Integer> firstHave = new ArrayList<Integer>();
	static ArrayList<Integer> secondHave = new ArrayList<Integer>();
	static ArrayList<Integer> seonge = new ArrayList<Integer>();
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	private static boolean checkAdj() {
		firstHave.clear();
		secondHave.clear();
		seonge.clear();
		queue.clear();
		
		Arrays.fill(visited, false);
		
		int firstStart = -1;
		int secondStart = -1;
		for (int i = 1; i <= N; i++) {
			if(selected[i]) {
				firstStart = i;
				firstHave.add(i);
			} else {
				secondStart = i;
				secondHave.add(i);
			}
		}
		
		if(firstStart == -1 || secondStart == -1) return false; // 적어도 한개의 선거구를 포함해야 하므로
		
		//첫 선거구
		seonge.add(firstStart);
		queue.offer(firstStart);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (Node temp = nodes[cur]; temp!=null; temp=temp.next) {
				if(!visited[temp.vertex] && selected[temp.vertex]) {
					visited[temp.vertex] = true;
					queue.offer(temp.vertex);
					seonge.add(temp.vertex);
					
				}
			}
		}
		
		for(Integer first : firstHave) {
			if(!seonge.contains(first)) return false;
		}
		
		seonge.clear();
		// 두번째 선거구
		seonge.add(secondStart);
		queue.offer(secondStart);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (Node temp = nodes[cur]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && !selected[temp.vertex]) {
					visited[temp.vertex] = true;
					queue.offer(temp.vertex);
					seonge.add(temp.vertex);

				}
			}
		}

		for (Integer second : secondHave) {
			if (!seonge.contains(second)) return false;
		}
		
		return true;
	}

}
