
/*
<문제 풀이 by Shevchenko_7>

ver2의 방식과 유사하다. 다만 map을 한개 선언해 주는데, 이때 value값을 arrayList를 넣고 값을 넣을때마다 해당 값의 인덱스를 arraylist에 넣는다.

최빈수가 나오면 최빈수에 해당하는 key의 최빈수-1인덱스의 값(가장 오른쪽의값)에다가 0번째 값(가장 왼쪽의 값)을 빼준 것을 반환한다.

Runtime: 20 ms, faster than 64.25% of Java online submissions for Degree of an Array.
Memory Usage: 42.3 MB, less than 31.09% of Java online submissions for Degree of an Array.

 */


package Array;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class DegreeOfArray697ver3 {
	public static int findShortestSubArray(int[] nums)
	{
		int max = 0;
		Map<Integer,List<Integer>> map = new HashMap<>();
		
		for(int i = 0; i < nums.length; i++)
		{
			if(!map.containsKey(nums[i]))
			{
				map.put(nums[i], new ArrayList<>());
			}
			map.get(nums[i]).add(i);
			max = Math.max(max, map.get(nums[i]).size());
		}
		
		if(max == 1 || max == nums.length) return max;
		
		int min = nums.length;
		
		for(int j : map.keySet())
		{
			if(map.get(j).size()==max)
			{
				min = Math.min(min, map.get(j).get(max-1)-map.get(j).get(0)+1);
			}
		}
		return min;
    }
	
	
	public static void main(String[] args) 
	{
		int[] nums = {1,2,6,6,2,6,2,3,3,3};
		//int[] nums = {1, 2, 2, 3, 1};
		//int[] nums = {1,2,3,3,3,3,1};
		System.out.println(findShortestSubArray(nums));
	}

}
