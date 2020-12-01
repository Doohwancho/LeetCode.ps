package dynamicProgramming;

import java.util.LinkedList;
import java.util.List;

public class MinimumCostTreeFromLeafValues1130 {

	//<Trial01>
	
	//31 / 103 test cases passed.
	
	//[15,13,5,3,15]
	
	//이게 무조건 양끝단에서 시작하지 않아도 되는구나.
	
	//중간부터 시작해도 되네 
	
    public int mctFromLeafValues(int[] arr) {
        int rst = 0;
        int len = arr.length;
        
        for(int i = 0, j = len-1; i < j; ){
            int l = arr[i] * arr[i+1];
            int r = arr[j-1] * arr[j];
            
            if(l < r){
                rst += l;
                i++;
                arr[i] = Math.max(arr[i], arr[i-1]);
            } else {
                rst += r;
                j--;
                arr[j] = Math.max(arr[j], arr[j+1]);
            }
        }
        
        return rst;
    }
    
    
    
    //<문제풀이1>
    
    
    //Runtime: 3 ms, faster than 45.43% of Java online submissions for Minimum Cost Tree From Leaf Values.
    //Memory Usage: 36.6 MB, less than 56.10% of Java online submissions for Minimum Cost Tree From Leaf Values.
    
    public int mctFromLeafValues(int[] arr) {
        int rst = 0;
        int len = arr.length;
        
        List<Integer> list = new LinkedList<>();
        for(int ar : arr) {
        	list.add(ar);
        }
        
        while(list.size() > 1) {
        	int idx = 0;
        	int max = Integer.MAX_VALUE;
        	for(int i = 0; i < list.size()-1; i++) {
        		int mult = list.get(i) * list.get(i+1);
        		if(max > mult) {
        			max = mult;
        			idx = i;
        		}
        	}
        	list.set(idx, Math.max(list.get(idx), list.get(idx+1)));
        	list.remove(idx+1);
        	rst += max;
        }
        
        return rst;
    }
}
