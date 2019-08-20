/*
	Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
	
	Example 1:
	
	Input: nums = [1,2,3,1], k = 3
	Output: true
	Example 2:
	
	Input: nums = [1,0,1,1], k = 1
	Output: true
	Example 3:
	
	Input: nums = [1,2,3,1,2,3], k = 2
	Output: false
	
	
	
	
	<문제>
	
	리스트가 nums = [1,2,3,1] 이렇게 주어지면, 중복되는 원소들 중 해당 원소들의 인덱스의 차이가 최대 k면 true를 반환하라.
	
	그런데 nums = [1,0,1,1], k = 1의 경우, false가 나와야 하는데 true가 나옴.
	
	문제설명이 모호하게 서술되었음.
	
	두 중복되는 element가 얼마나 멀든, 그 차이가 k이하면 true를 반환하는걸로 짜면 됨...
	
 */


package Array;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateII219 {


	/*
//	<문제풀이>
//	
//	딕셔너리를 이용하여 매 loop마다 hashmap에 원소와 해당 원소의 인덱스를 넣고, 같은 원소가 나오면 인덱스의 차이가 k보다 작거나 같은지 비교하는 것
//	
//	Runtime: 8 ms, faster than 79.73% of Java online submissions for Contains Duplicate II.
//	Memory Usage: 44 MB, less than 31.58% of Java online submissions for Contains Duplicate II.
	
	
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++)
        {
        	int number = nums[i];
        	if(map.containsKey(number) && i - map.get(number) <= k)
        	{
        		return true;
        	}
        	map.put(number, i);
        }
        return false;		
    }
	*/
	
	
//	<문제풀이 by southpenguin>
//	
//	sliding window방법 사용
//	
//	설명링크 : http://codingdojang.com/scode/439?langby=java
//	
//	Runtime: 6 ms, faster than 96.78% of Java online submissions for Contains Duplicate II.
//	Memory Usage: 41.1 MB, less than 100.00% of Java online submissions for Contains Duplicate II.
	
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();
		
		for(int i = 0; i <nums.length; i++)
		{	
			if(i > k) set.remove(nums[i-1-k]); //k번째 안에 드는것만 keep
			if(!set.add(nums[i])) return true; //k번째 안에 있으면서 중복되는 원소가 있으면 true반환. **if(!set.add(nums[i]))를 하면 매 loop마다 set에 nums[i]가 add됨
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		int[] nums = {1,0,1,1};
		int k = 1;
		
		System.out.println(containsNearbyDuplicate(nums, k));
	}
}
