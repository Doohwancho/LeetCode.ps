package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankTeamsByVotes1366 {
	
	/*
	//<Trial01>
	
	//votes = ["ABC","ACB","ABC","ACB","ACB"]
	
	//이건 되는데, 
	
	//한번 등장한 알파벳이 재등장 하지 않는건 안됨.
	
	//그리고 tie시 다음거에서 측정하는 기능이 없음
	
    public static String rankTeams(String[] votes) {
    	String rst = "";
        for(int i = 0; i < votes[0].length(); i++){
        	int[] container = new int[26];
            for(String str : votes){
            	container[str.charAt(i)-65]++;
            }
            int maxIdx = 0;
            int best = 0;
            //if tie -> check next
            for(int j = 0; j < 26; j++) {
            	if(container[j] > best) {
            		best = container[j];
            		maxIdx = j;
            	}
            }
            //check taken
            rst += String.valueOf((char)(maxIdx+65)); 
        }
        return rst;
    }
    */
	
	public static String rankTeams(String[] votes) {
		//"WXYZ","XYZW"
	      Map<Character, int[]> map = new HashMap<>();
	      int l = votes[0].length();
	      for(String vote : votes){
	        for(int i = 0; i < l; i++){
	          char c = vote.charAt(i);
	          map.putIfAbsent(c, new int[l]);
	          map.get(c)[i]++;
	        }
	      }
	      
	    // {"WXYZ","XYZW"};
		//  1 0 0 1 
		//  1 1 0 0 
		//  0 1 1 0 
		//  0 0 1 1
	      for(Character row : map.keySet()){
	    	  System.out.println(row);
	    	  
	    	  for(int ele : map.get(row)) {
	    		  System.out.print(ele+" ");
	    	  }
	    	  System.out.println();
	      }
	      
	      List<Character> list = new ArrayList<>(map.keySet());
	      Collections.sort(list, (a,b) -> {
	        for(int i = 0; i < l; i++){
	          if(map.get(a)[i] != map.get(b)[i]){
	            return map.get(b)[i] - map.get(a)[i];
	          }
	        }
	        return a - b;
	      });
	      
	      StringBuilder sb = new StringBuilder();
	      for(char c : list){
	        sb.append(c);
	      }
	      return sb.toString();//XWYZ
	    }
	
    
    
    
    public static void main(String[] args) {
//		String[] votes = {"ABC","ACB","ABC","ACB","ACB"};
    	String[] votes = {"WXYZ","XYZW"};
    	
		System.out.println(rankTeams(votes));
	}
}
