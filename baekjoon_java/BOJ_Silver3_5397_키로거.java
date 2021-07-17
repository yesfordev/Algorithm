package algo210718;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BOJ_Silver3_5397_키로거 {

	static int TC;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    
	    TC = Integer.parseInt(in.readLine());
	    
	    for (int t = 1; t <= TC; t++) {
		    List<Character> list = new LinkedList<Character>();
		    ListIterator<Character> lit = list.listIterator();
		    
	    	String str = in.readLine();
	    	int strSize = str.length();
	    	
	    	for (int idx = 0; idx < strSize; idx++) {
	    		
	    		char ch = str.charAt(idx);
				
	    		if(ch == '<') {
	    			if(!lit.hasPrevious()) continue;
	    			lit.previous();
	    		} else if(ch == '>') {
	    			if(!lit.hasNext()) continue;
	    			lit.next();
	    		} else if(ch == '-') {
	    			if(!lit.hasPrevious()) continue;
	    			lit.previous();
	    			lit.remove();
	    		} else {
	    			lit.add(ch);
	    		}
			}
	    	
	    	for(Character word : list) {
	    		bw.write(word);
	    	}
	    	bw.write("\n");
		}
	    
	    bw.flush();
	    bw.close();
	    in.close();
	}
}
