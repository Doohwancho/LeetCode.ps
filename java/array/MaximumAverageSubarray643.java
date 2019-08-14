/*
	Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.
	
	Example 1:
	
	Input: [1,12,-5,-6,50,3], k = 4
	Output: 12.75	
	Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
	
	
	
	<문제>
	
	리스트와 k가 다음과 같이 주어진다.
	
	int[] nums = {1,12,-5,-6,50,3};
	int k = 4; 
	
	리스트를 k길이의 subarray로 끊으면, {1,12,-5,-6} , {12,-5,-6,50}, {-5,-6,50,3}으로 나눌 수 있다.
	
	이 때, 원소들의 합이 가장 큰 리스트의 평균을 반환하라.
 */

package Array;

public class MaximumAverageSubarray643 {
	
	/*
		k길이 만큼의 합을 뜻하는 subArray변수와, 가장 큰 합을 나타내는 maximum변수를 선언.
		
		처음 k번째까지 원소의 합은 일단 maximum변수에 넣어놓고, for문을 돌면서 maximum update
		
		여기서 포인트는 2중 for문을 써서 traillingIndex ~ traillingIndex+k 번까지 매번 합연산을 하는게 아니라
		
		이미 합해진 값에서 맨 왼쪽값 하나만 빼고, 오른쪽 값 하나만 더해서 연산하여, 불필요한 연산 제거

		Runtime: 3 ms, faster than 96.87% of Java online submissions for Maximum Average Subarray I.
		Memory Usage: 42.1 MB, less than 76.92% of Java online submissions for Maximum Average Subarray I.
	 */
	
	public static double findMaxAverage(int[] nums, int k) {
		int subArray = 0;
		int traillingIndex = 0;
		
		for(int i = 0; i < k; i++)
		{
			subArray += nums[i];
		}
		
		int maximum = subArray;
		
		for(int j = k; j < nums.length; j++)
		{
			
			subArray = subArray + nums[j] - nums[traillingIndex++];
			maximum = Math.max(maximum, subArray); 
		}
		
		return (double)maximum / k;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,12,-5,-6,50,3};
		int k = 4; 
		System.out.println(findMaxAverage(nums, k));
		
		}
}
