package algo210313;

public class Dijkstra {
	
	static int number = 6;
	static int INF = 1000000000;
	static int[][] a = {
			{0,2,5,1,INF,INF},
			{2,0,3,2,INF,INF},
			{5,3,0,3,1,5},
			{1,2,3,0,1,INF},
			{INF,INF,1,1,0,2},
			{INF,INF,5,INF,2,0}
	};
	static boolean visited[] = new boolean[number];
	static int distance[] = new int[number];
	
	static int getSmallIndex() {
		int min = INF;
		int index = 0;
		for (int i = 0; i < number; i++) {
			if(distance[i] < min && !visited[i]) {
				min = distance[i];
				index = i;
			}
		}
		return index;
	}
	 
	static void dijkstra(int start) {
		for (int i = 0; i < number; i++) {
			distance[i] = a[start][i];
		}
		visited[start] = true;
		
		for (int i = 0; i < number; i++) {
			int current = getSmallIndex();
			visited[current] = true;
			for (int j = 0; j < number; j++) {
				if(!visited[j]) {
					if(distance[current] + a[current][j] < distance[j]) {
						distance[j] = distance[current] + a[current][j];
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		dijkstra(0);
		for (int i = 0; i < number; i++) {
			System.out.print(distance[i]+" ");
		}

	}

}

