/*
	Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
	
	The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
	
	Note:
	
	Your returned answers (both index1 and index2) are not zero-based.
	You may assume that each input would have exactly one solution and you may not use the same element twice.
	Example:
	
	Input: numbers = [2,7,11,15], target = 9
	Output: [1,2]
	Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
	
	
	
	<문제>
	
	오름차순으로 정렬된 리스트가 [2,7,11,15] 처럼 주어지면, 둘이 더해서 target을 만들 수 있는 것의 index를 반환하라.
	
	단, 여기서 index는 1번째가 0이 아닌 1, 2번째가 1이아닌 2이다.
	
	
	
	<문제풀이>
	
	2중 for문으로, i는 index0부터 순차적으로, j는 i의 바로 다음 것에서 부터 순차적으로 돌면서, i번째와 j번째의 합이 target이 되면 해당 값 반환.
	
	Runtime: 81 ms, faster than 7.74% of Java online submissions for Two Sum II - Input array is sorted.
	Memory Usage: 36.4 MB, less than 99.91% of Java online submissions for Two Sum II - Input array is sorted.
 */

package Array;

public class TwoSumIISortedInputArray167 {
	
	public static int[] twoSum(int[] numbers, int target)
	{
        for(int i = 0; i < numbers.length; i++)
        {
        	for(int j = i+1; j < numbers.length; j++)
        	{
        		if(numbers[i]+numbers[j]==target) return new int[] {i+1,j+1};
        	}
        }
        return null;
    }
	
	public static void main(String[] args) 
	{
		int[] numbers = {2,7,11,15};
		int target = 9;
		System.out.println(twoSum(numbers, target));
		
	}
}
