package algo211011_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class BOJ_Silver5_7785_회사에있는사람 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		
		ArrayList<String> list = new ArrayList<>();
		Map<String, String> map = new TreeMap<>();
		
		for (int idx = 0; idx < n; idx++) {
			String[] str = in.readLine().split(" ");
			
			map.put(str[0], str[1]);
		}
		
		Iterator<String> iter = map.keySet().iterator();
		
		while (iter.hasNext()) {
			String key = iter.next();
			if (map.get(key).equals("enter")) {
				list.add(key);
			}
		}
		
		int listSize = list.size();
		StringBuilder sb = new StringBuilder();
		for (int idx = listSize - 1; idx >= 0; idx--) {
			sb.append(list.get(idx) + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
