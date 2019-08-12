/*
	Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
	
	You may assume no duplicates in the array.
	
	Example 1:
	
	Input: [1,3,5,6], 5
	Output: 2
	Example 2:
	
	Input: [1,3,5,6], 2
	Output: 1
	Example 3:
	
	Input: [1,3,5,6], 7
	Output: 4
	Example 4:
	
	Input: [1,3,5,6], 0
	Output: 0
	
	
	
	
<문제>

리스트와 타깃이 다음과 같이 주어진다. 

list = [1,3,5,6]
target = 5

리스트 안에 타깃이 있으면 해당 인덱스를 반환하고,

만약 없다면, 리스트의 원소들이 오름차순으로 증가한다고 가정할 때, 타깃이 원래 있었어야할 인덱스를 반환하라.

예를들어 위의 예시인 경우, target이  list에 있으므로 5의 인덱스인 2를 반환한다.

만약 target이 2였다면, 1과 3사이에 있었어야 하므로, 인덱스 1을 반환하고,

만약 target이 7이었다면, 가장 마지막에 있었어야 함으로, 리스트의 마지막 인덱스를 반환한다.



 */

package Array;

public class SearchInsertPosition35 {
	
//	<문제 풀이>
//	
//	for문으로 돌면서 target보다 같거나 큰 수가 나오면 해당 인덱스 반환
//	
//	만약 for문이 끝날 때 까지 return으로 반환이 되지 않는다는 듯은, list안에 target보다 큰 숫자가 없다는 말과 같음
//	
//	따라서 리스트에 맨 뒤에 위치해야 함으로, 리스트의 길이를 반환
//	
//	
//	Runtime: 0 ms, faster than 100.00% of Java online submissions for Search Insert Position.
//	Memory Usage: 38.7 MB, less than 100.00% of Java online submissions for Search Insert Position.
	
	public static int searchInsert(int[] nums, int target) {

		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] >= target)
			{
				return i;
			}
		}
		return nums.length;
    }
	
	
	public static void main(String[] args) {
		int[] nums = {1,3,5,6};
		int target = 2;
		
//		int[] nums = {1,3,5,6};
//		int target = 7;
//		
//		int[] nums = {1,3,5,6};
//		int target = 2;
		
		System.out.println(searchInsert(nums, target));
		
		
	}
}
