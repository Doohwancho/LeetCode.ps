/*
	Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
	
	Example 1:
	Input: [1,3,5,4,7]
	Output: 3
	Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
	Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
	Example 2:
	Input: [2,2,2,2,2]
	Output: 1
	Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
	
	
	
	<문제>
	
	리스트가 다음과 같이 [1,3,5,4,7] 주어지면, 연속적으로 증가하는 sub-array의 최대길이를 구하라.
	
	이 경우는 [1,3,5]와 [4,7]이 연속적으로 증가하는 sub-array인데, 그 중 [1,3,5]가 가장 긴 sub-array이므로, 그 길이인 3을 반환한다.
 */

package Array;

public class LongestContinuousIncreasingSubseq674 {
	
	/*
	 * <문제풀이>
	 * 
	 * for문을 돌면서 앞에 숫자가 뒤에 숫자보다 더 크면(증가하면) consq변수를 1씩 늘리고, 작거나 같으면 consq변수를 1로 다시 초기화 한다.
	 * 
	 * 가장 큰 consq를 구하기 위해서, 증가할 때마다 max()함수를 써서 rst변수에 최대 sub-array 길이를 업데이트 한다.
	 * 
	 * Runtime: 1 ms, faster than 99.74% of Java online submissions for Longest Continuous Increasing Subsequence.
	   Memory Usage: 37.6 MB, less than 100.00% of Java online submissions for Longest Continuous Increasing Subsequence.
	 */
	public static int findLengthOfLCIS(int[] nums) {
		int prev = Integer.MIN_VALUE;
		int consq = 0;
		int rst = 0;
		
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] > prev)
			{
				prev = nums[i];
				consq++;
				rst = Integer.max(rst, consq);
			}
			else
			{
				prev = nums[i];
				consq = 1;
			}
		}
        return rst;
    }
	public static void main(String[] args) {
		int[] nums = {1,3,5,4,7};
		System.out.println(findLengthOfLCIS(nums));
	}
}
