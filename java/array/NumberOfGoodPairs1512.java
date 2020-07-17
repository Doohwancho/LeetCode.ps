package array;

import java.util.Arrays;

public class NumberOfGoodPairs1512 {
	
	//<문제풀이1>	
	
	//EZ
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Number of Good Pairs.
	//Memory Usage: 37 MB, less than 100.00% of Java online submissions for Number of Good Pairs.
	
    public int numIdenticalPairs(int[] nums) {
        Arrays.sort(nums);
        int rst = 0;
        int stack = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i-1]){
                stack++;
            } else {
                rst += (stack * (stack+1))/2;
                stack = 0;
            }
        }
        return stack > 0 ? rst + (stack * (stack+1))/2 : rst;
    }
}
