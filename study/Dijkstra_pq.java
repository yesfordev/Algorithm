package algo210313;

import java.util.PriorityQueue;
// 참고 
// https://devlog-wjdrbs96.tistory.com/102
class Element implements Comparable<Element> {
	int index;
	int distance;

	public Element(int index, int distance) {
		super();
		this.index = index;
		this.distance = distance;
	}

	@Override
	public int compareTo(Element o) {
		return this.distance - o.distance;
	}
}

public class Dijkstra_pq {

	static int[] dist;
	static int[][] ad;
	static int V = 6;
	static int inf = 100000;

	public static void DijkstraMethod(int start) {
		PriorityQueue<Element> pq = new PriorityQueue<Element>();
		dist[start] = 0;
		pq.offer(new Element(start, dist[start]));

		while (!pq.isEmpty()) {
			int cost = pq.peek().distance;
			int here = pq.peek().index;
			pq.poll();

			// 현재 배열에 있는 값(최단거리)보다 cost(거리)가 더 길면 continue
			if (cost > dist[here])
				continue;

			System.out.println(here + " ");

			// 아래 for문이 다익스트라의 핵심 코드라고 생각함
			for (int i = 1; i <= V; i++) {
				if (ad[here][i] != 0 && dist[i] > (dist[here] + ad[here][i])) {
					dist[i] = dist[here] + ad[here][i];
					pq.offer(new Element(i, dist[i]));
				}
			}

			System.out.println();

			// 중간결과 보여주기
			for (int i = 1; i <= V; i++) {
				System.out.print(dist[i] + " ");
			}
		}
	}

	public static void main(String[] args) {
		dist = new int[V + 1];
		ad = new int[V + 1][V + 1];
		// 기본적으로 연결되지 않은 경우, 비용은 무한
		for (int i = 1; i <= V; i++) {
			dist[i] = inf;
		}

		ad[1][2] = 8;
		ad[1][3] = 1;
		ad[1][4] = 2;
		ad[3][4] = 2;
		ad[3][2] = 5;
		ad[4][5] = 3;
		ad[4][6] = 5;
		ad[5][6] = 1;
		ad[6][1] = 5;
		
		DijkstraMethod(1);
	}
}