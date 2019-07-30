/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].


<문제>

리스트 안의 숫자와 target 숫자가 주어진다.

리스트 안의 숫자 2개를 합해서 target숫자를 만들 수 있다.

그 두 수의 인덱스를 리스트에 담아 [0,1] 이런식으로 반환하라.

 */

package Array;

import java.util.Map;
import java.util.HashMap;


public class TwoSum1 {
	
	/*
		<문제 풀이>
		
		주어진 nums을 for문으로 돌면서 딕셔너리에 키값을 해당 값, 벨류를 해당 값의 인덱스로 넣는다.
		
		그리고 다시 nums를 for문으로 도는데, 이 때 target - 해당 값이 아까 넣은 딕셔너리에 있으면 인덱스를 리스트에 담아 반환한다.
		
		
		Runtime: 2 ms, faster than 98.82% of Java online submissions for Two Sum.
		Memory Usage: 37.3 MB, less than 99.56% of Java online submissions for Two Sum.
	 */
	
	public static int[] twoSum(int[] nums, int target) {
		
        Map<Integer, Integer> container = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < nums.length; i++) 
        { 
        	container.put(nums[i], i);
		}
        
        for(int j = 0; j < nums.length; j++)
        {
        	if(container.containsKey(target - nums[j]) && j != container.get(target - nums[j])) // target이 6이고 리스트 안 element가 3일때, 6-3은 3이므로 본인이 중복적용 되는 것을 방지
        	{
        		return new int[] {j, container.get(target - nums[j])};
        	}
        }
        return null;
    }
	public static void main(String[] args) {
//		int[] nums = {2, 7, 11, 15};
//		int target = 9;
		
		int[] nums = {3, 2, 4};
		int target = 6;
		
		
		System.out.println(twoSum(nums, target));
	}
}






