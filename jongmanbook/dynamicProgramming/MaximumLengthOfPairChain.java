package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MaximumLengthOfPairChain {

	//<Trial01>
	
	//dp
	
	//{1=[4], 2=[3, 6], 3=[4], 4=[5], 6=[7], 8=[9]}
	//begin: 6 key: 6 nextVal: 7 nextKey: 8
	//0 0 0 0 0 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0  
	//begin: 1 key: 1 nextVal: 4 nextKey: 6
	//0 3 0 0 0 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
	//begin: 4 key: 4 nextVal: 5 nextKey: 6
	//0 3 0 0 3 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
	//begin: 4 key: 6 nextVal: 7 nextKey: 8
	//0 3 0 0 3 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
	//begin: 1 key: 2 nextVal: 3 nextKey: 4
	//0 4 0 0 3 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
	//begin: 1 key: 2 nextVal: 6 nextKey: 8
	//0 4 0 0 3 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
	//begin: 1 key: 3 nextVal: 4 nextKey: 6
	//0 4 0 0 3 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
	//begin: 1 key: 4 nextVal: 5 nextKey: 6
	//0 4 0 0 3 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
	//begin: 1 key: 6 nextVal: 7 nextKey: 8
	//0 4 0 0 3 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
	//4
	
	//testcase¿¡ À½¼ö³ª¿Í¼­ mem[À½¼ö]ÇÏ¸é ¿¡·¯¶ä
	
	//°³²ÜÀë¸ôÄ«ÀÎ°¡?
	
    static int[] mem = new int[10000001];
    static TreeMap<Integer,List<Integer>> tm = new TreeMap<>();
    
    public static int dp(int begin) {
    	if(mem[begin] != 0) return mem[begin];
    	
        for (Entry<Integer, List<Integer>> entry : tm.entrySet()) {
        	if(entry.getKey() >= begin) {
        		for(Integer nextVal : entry.getValue()) {
	        		Integer nextKey = tm.higherKey(nextVal);
		        	if(nextKey == null) {
		        		return mem[begin] = Math.max(mem[begin], 1);
		        	}
		        	mem[begin] = Math.max(mem[begin], dp(nextKey)+1);
	        	}	
        	}
        }
        return mem[begin];
    }
    
    public static int findLongestChain(int[][] pairs) {
        for(int[] p : pairs){
        	if(!tm.containsKey(p[0])){
                tm.put(p[0], new ArrayList<>());        		
        	}
        	tm.get(p[0]).add(p[1]);
        }
        
        dp(tm.firstKey());

        return mem[tm.firstKey()];
    }
    
    public static void main(String[] args) {
//    	int[][] pairs = {{1,2},{2,3},{3,4}};
//    	int[][] pairs = {{-10,-8},{8,9},{-5,0},{6,-4},{1,7},{9,10},{-4,7},{}};
    	int[][] pairs = {{1,4},{2,3},{2,6},{3,4},{4,5},{6,7},{8,9}};
    	System.out.println(findLongestChain(pairs));
	}
}
