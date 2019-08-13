/*
	In a given integer array nums, there is always exactly one largest element.
	
	Find whether the largest element in the array is at least twice as much as every other number in the array.
	
	If it is, return the index of the largest element, otherwise return -1.
	
	Example 1:
	
	Input: nums = [3, 6, 1, 0]
	Output: 1
	Explanation: 6 is the largest integer, and for every other number in the array x,
	6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
	 
	
	Example 2:
	
	Input: nums = [1, 2, 3, 4]
	Output: -1
	Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
	
	
	
	<문제>
	
	리스트가 다음과 같이 주어진다.
	
	nums = [3, 6, 1, 0]
	
	이 리스트 안에서 가장 큰 수를 보면 6인데, 가장큰 수 6이 나머지 수 각각의 곱하기 2보다 크거나 같으면 가장 큰수의 인덱스를 반환하고, 아니면 -1을 반환한다.
	
	위 경우, 3*2, 1*2, 0*2가 6,2,0인데, 가장 큰 수 6이, 6,2,0보다 크거나 같으므로, 6의 인덱스인 1을 반환한다.

 */

package Array;

public class LargestNumAtLeastTwiceOfOthers747 {
	
	/*
	<문제풀이>
	
	먼저 for문을 돌면서 리스트 안에 원소들 중, 가장 큰 숫자와 그 숫자의 인덱스를 찾아 largestNum, largestNumIndex에 저장한다.
	
	다시한번 for문을 돌면서, largestNum이 아니면서 2배이상이면, 문제에서 물어보는 조건에 부합하지 않으니까 -1을 반환하고,
	
	for문을 모두 돌았는데도 조건이 부합한다면, largestNum의 인덱스인 largestNumIndex를 반환한다.
	
	Runtime: 0 ms, faster than 100.00% of Java online submissions for Largest Number At Least Twice of Others.
	Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Largest Number At Least Twice of Others.
	*/
	public static int dominantIndex(int[] nums) {
        //step1 - find largest number
		int largestNum = Integer.MIN_VALUE;
		int largestNumIndex = -1;
		
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] > largestNum)
				{
					largestNum = nums[i];
					largestNumIndex = i;
				}
		}
		
		//step2 - check if not greater than any element in the list
		for(int j = 0; j < nums.length; j++)
		{
			if(nums[j] != largestNum && !(largestNum >= nums[j] * 2)) return -1;
		}
		
		
		return largestNumIndex;
    }
	
	
	public static void main(String[] args) {
		int[] nums = {3, 6, 1, 0};
		//nums = {1, 2, 3, 4};
		
		System.out.println(dominantIndex(nums));
	}
}
