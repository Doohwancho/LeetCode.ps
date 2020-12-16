package dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset368 {

	//<Trial01>
	
	//정신차려 이친구야~
	
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        for(int i = 1, max = 0; i < len; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] % nums[j] == 0){
                    max = Math.max(max, dp[j]);    
                }
            }
            dp[i] = max+1;
            max = 0;
        }
        
        int rst = 0;
        for(int ele : dp){
            rst = Math.max(rst, ele);
        }
        return rst;
    }
    
    
    //<Trial02>
    
    //[5,9,18,54,108,540,90,180,360,720]
    
    //에서, 9->18->"54"  얘에 막혀서 90->180->360->720 못감
    
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        ArrayList<Integer>[] dp = new ArrayList[len];
        
        for(int i = 0; i < len; i++){
            dp[i] = new ArrayList<Integer>();
            dp[i].add(nums[i]);
            for(int j = 0; j < i; j++){
                if(nums[i] % dp[j].get(dp[j].size()-1) == 0){
                    dp[j].add(nums[i]);
                }
            }
        }
        
        int idx = 0;
        int size = 0;
        for(int p = 0; p < len; p++){
            if(dp[p].size() > size){
                size = dp[p].size();
                idx = p;
            }
        }
        
        return dp[idx];
    }
    
    
    //<문제풀이1 by gooner656>
    
    //Trial01에서 좀 더 생각했으면 풀 수 있었던 문제.
    
    //아쉽다.
    
    //Runtime: 28 ms, faster than 14.36% of Java online submissions for Largest Divisible Subset.
    //Memory Usage: 40.1 MB, less than 6.24% of Java online submissions for Largest Divisible Subset.
    
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] dp = new int[len];
        int max = 1;
        
        for(int i = 0; i < len; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] % nums[j] == 0){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        
        List<Integer> rst = new ArrayList<>();
        int prev = -1;
        for(int i = len-1; i >= 0; i--){
            if(dp[i] == max && (prev == -1 || prev % nums[i] == 0)){
                rst.add(nums[i]);
                prev = nums[i];
                max--;
            }
        }
        return rst;
    }
}
