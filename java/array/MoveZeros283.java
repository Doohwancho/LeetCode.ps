/*
	Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
	
	Example:
	
	Input: [0,1,0,3,12]
	Output: [1,3,12,0,0]
	
	
	
	<문제>
	
	리스트가 [0,1,0,3,12]와 같이 주어지면, 0을 맨 뒤로 빼라
	
	
	
	<문제 풀이>
	
	0 이외의 수가 나오면, 그 이전의 0과 바꿔주는 식으로 돌린다.
	
	Runtime: 0 ms, faster than 100.00% of Java online submissions for Move Zeroes.
	Memory Usage: 36.4 MB, less than 99.96% of Java online submissions for Move Zeroes.
 */

package Array;

public class MoveZeros283 {
	public static void moveZeroes(int[] nums) 
	{
		int zero = 0, l = 0, r = nums.length;
	    while (l < r) {
	        if (nums[l] != 0) {
	            int tmp = nums[zero];
	            nums[zero++] = nums[l];
	            nums[l] = tmp;
	        }
	        l++;
	    }
    }
	
	public static void main(String[] args) 
	{
		int[] nums = {0,1,0,3,12};
		moveZeroes(nums);
		for(int element : nums) System.out.println(element);
	}
}
