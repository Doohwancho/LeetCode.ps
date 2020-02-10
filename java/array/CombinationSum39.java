package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum39 {
	
	//<Trial01>
	
	/*
    private void recursive(int[] candidates, int i, int target){
        //?
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> rst = new ArrayList<>();
        Arrays.sort(candidates);
        
//        Set<Integer> container = new HashSet<>();
//        for(int element : candidates){
//            container.add(element);
//        }
        
        for(int i = candidates.length-1; i >= 0; i--){
            if(candidates[i] == target){
                List<Integer> temp = new ArrayList<>();
                int num = target % candidates[i];
                while(num-->0){
                    temp.add(candidates[i]);
                }
                rst.add(temp);
            }
            else if(candidates[i] < target){
                int cnt = target/candidates[i];
                while(cnt-- > 0){
                    if(target - (candidates[i] * cnt)){ //근데 이게 candidates의 원소 중 하나로 딱 떨어지면 괜찮은데, [2,2,2,3,3,5] = 17이러면 어쩌지?
                        //재귀로 돌리는법?
                        recursive(candidates, i, )
                    }
                }
            }
        }
    }
    */
	
	//<문제풀이1 by shpolsky>
	
	//Runtime: 6 ms, faster than 31.46% of Java online submissions for Combination Sum.
	//Memory Usage: 41.6 MB, less than 5.19% of Java online submissions for Combination Sum.
	
	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // sort candidates to try them in asc order
        Arrays.sort(candidates); 
        // dp[t] stores all combinations that add up to t
        List<List<Integer>>[] dp = new ArrayList[target+1];  
        
        // build up dp
        for(int t=0; t<=target; t++) {
            // initialize
            dp[t] = new ArrayList();
            // initialize
            List<List<Integer>> combList = new ArrayList();
            
            // for each t, find possible combinations
            for(int j=0; j<candidates.length && candidates[j] <= t; j++) {
                if(candidates[j] == t) {
                    combList.add(Arrays.asList(candidates[j])); // itself can form a list
                } else {
                    for(List<Integer> prevlist: dp[t-candidates[j]]) { // here use our dp definition
                        // i thought it makes more sense to compare with the last element
                        // only add to list when the candidates[j] >= the last element
                        // so the list remains ascending order, can prevent duplicate (ex. has [2 3 3], no [3 2 3])
                        // equal is needed since we can choose the same element many times   
                        if(candidates[j] >= prevlist.get(prevlist.size()-1)){       // -> 이래야 if t==4, dp[4] == [2,2], if t==6, dp[6] = [2,2](from 4)+[2] and so on 
                            List temp = new ArrayList(prevlist); // temp is needed since cannot edit prevlist inside for each loop
                            temp.add(candidates[j]); 
                            combList.add(temp);
                        }
                    }
                }
            }
            dp[t] = combList;
        }
        return dp[target];
    }    
	
	public static void main(String[] args) {
		System.out.println(combinationSum(new int[] {2,3,5}, 8));
	}
}
