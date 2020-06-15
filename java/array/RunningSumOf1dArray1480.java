package array;

public class RunningSumOf1dArray1480 {
	
	//<문제풀이1>
	
	//i-1를 계속 누적으로 더해주면 됨.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Running Sum of 1d Array.
	//Memory Usage: 40.5 MB, less than 50.00% of Java online submissions for Running Sum of 1d Array.
    public int[] runningSum(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
        }
        return nums;
    }
}
