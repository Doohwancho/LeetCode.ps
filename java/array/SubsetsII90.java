package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class SubsetsII90 {
	
	/*
	//<Trial01>
	
	//건너뛰는게 구현이 안되있네 아이고
	
	//Input : [1,2,3]
	//Output : [[],[1],[1,2],[1,2,3],[2],[2,3],[3]]
	//Expected : [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
	
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        Stack<List<Integer>> rst = new Stack<>();
        rst.add(new ArrayList<>());
        
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums[i]);
            rst.add(tmp);
            
            for(int j = i+1; j < nums.length; j++){
                List<Integer> tmpCopied = new ArrayList<>(rst.lastElement());
                tmpCopied.add(nums[j]);
                rst.add(tmpCopied);
            }
        }
        return rst;
    }
    */
	
	
	//<문제풀이1 by cbmbbz>
	
	//nums[i]가 nums[i-1]이랑 같으면 맨 끝에만 한번 더하고, 다른숫자면 기존에 거에 다 더하는 로직
	
	//i: 1    int[]: {1,1,2,3}
	//
	//1 
	//1 1 
	//----------------------------------
	//i: 2    int[]: {1,1,2,3}
	//
	//1 
	//1 1 
	//2 
	//1 2 
	//1 1 2 
	//----------------------------------
	//i: 3    int[]: {1,1,2,3}
	//
	//1 
	//1 1 
	//2 
	//1 2 
	//1 1 2 
	//3 
	//1 3 
	//1 1 3 
	//2 3 
	//1 2 3 
	//1 1 2 3 
	//----------------------------------
	//[[], [1], [1, 1], [2], [1, 2], [1, 1, 2], [3], [1, 3], [1, 1, 3], [2, 3], [1, 2, 3], [1, 1, 2, 3]]
	
	//Runtime: 1 ms, faster than 99.76% of Java online submissions for Subsets II.
	//Memory Usage: 39.4 MB, less than 5.88% of Java online submissions for Subsets II.
	public static List<List<Integer>> subsetsWithDup(int[] num) {
	    Arrays.sort(num);
	    List<List<Integer>> ans = new ArrayList<List<Integer>>();
	    
	    int len = num.length;
	    if (len == 0) return ans; 
	    
	    ans.add(new ArrayList<Integer>()); // first, need to add the subset of num[0]
	    ans.add(new ArrayList<Integer>());
	    ans.get(1).add(num[0]);
	    
	    int nprev = 1; // this is the number of lists that the previous number was added in.
	                 // if the current number is same as the prev one, it'll be only added in the 
	                // lists that has the prev number.
	                
	    for (int i = 1; i < len ; ++i){
	        int size = ans.size();
	        if (num[i]!=num[i-1])   // if different
	            nprev = size;        // this means add num[i] to all lists in ans;
	        for (int j = size-nprev; j < size; ++j){
	            List<Integer> l = new ArrayList<Integer>(ans.get(j));
	            l.add(num[i]);
	            ans.add(l);
	        }
	    }
	    return ans;
	}
	
	public static void main(String[] args) {
		int[] test = {1,1,2,3};
		System.out.println(subsetsWithDup(test));
	}


}

