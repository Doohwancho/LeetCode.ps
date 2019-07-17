/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.



<문제>

리스트가 다음과 같이 주어지면, [-2,1,-3,4,-1,2,1,-5,4]

리스트 안의 subarray들 중에서 가장 큰 합을 반환하라.

위의 경우에는 subarray [4,-1,2,1]의 합이 가장 크므로 6을 반환하면 된다.


 */

package Array;

import java.util.stream.IntStream;

public class MaximumSubarray53 {
	
	/*
//	<문제풀이 - trial01>
//	Time Limit Exceeded(when too many inputs)
//	
//	2중 for문으로 subarray를 돌면서 subarray들의 합을 Math.max()로 찾아내는 방법

 * 	문제점 : many duplicates
 * 
 * 	{-2,1,3}을 다음과 방식으로 풀면, 컴퓨터는 연산을 {-2},{-2,1},{-2,1,3} 총 6번 숫자를 훑어야함.
 * 
 * 	근데 그냥 오른쪽으로 갈 때 숫자를 한번씩만 더해주면 3번 숫자를 훑음.
 * 
 * 	다음에 소개된 방식이 dynamic programming으로 푼 것


	public static int maxSubArray(int[] nums) {
		
		if(nums.length < 2) return nums[0];
		
		int sum = Integer.MIN_VALUE;
				
        for(int i = 0; i < nums.length; i++)
        {
        	for(int j = i; j < nums.length; j++)
        	{
        		int[] newNums = new int[j-i+1];
        		
                System.arraycopy(nums, i, newNums, 0, j-i+1);

        		sum = Math.max(sum, IntStream.of(newNums).sum());
        	}
        	
        }
		return sum;
    }
	*/
	
	//문제풀이 - by AlexTheGreat
	
	//Kadane's Algorithm
	//https://www.youtube.com/watch?time_continue=7&v=2MmGzdiKR9Y
	
	//for문으로 돌면서, 각 원소들을 더해나가면서 최대합을 기록, 더한 합이 -가 되는 순간 합을 해나가는것을 멈추고 다시 합을 시작.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Subarray.
	//Memory Usage: 38.8 MB, less than 25.09% of Java online submissions for Maximum Subarray.
	
	public static int maxSubArray(int[] nums) {
	    int max = Integer.MIN_VALUE, sum = 0;
	    
	    for(int i = 0; i < nums.length; i++)
	    {
	    	if(sum < 0) sum = nums[i];
	    	else sum += nums[i];
	    	if(sum > max) max = sum;
	    }
	    return max;
	}
	
	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
		//int[] nums = {-2,-1};
		
		
		System.out.println(maxSubArray(nums));
	}
}
