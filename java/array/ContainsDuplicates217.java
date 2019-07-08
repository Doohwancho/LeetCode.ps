/*
	Given an array of integers, find if the array contains any duplicates.
	
	Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
	
	Example 1:
	
	Input: [1,2,3,1]
	Output: true
	Example 2:
	
	Input: [1,2,3,4]
	Output: false
	Example 3:
	
	Input: [1,1,1,3,3,4,3,2,4,2]
	Output: true
	
	
	<문제>
	
	리스트 안에 중복되는 값이 있는지 판단하여, 중복되는 값이 있으면 true를 없으면 false를 반환하라.
	
	
	<문제풀이 - trial01>
	
	ArrayList를 선언하여, 어레이 안에 값이 없으면 넣고, 있으면 true를 반환하는 로직
	
	단 미친듯한 input을 넣었을 경우, 어레이에 수많은 인풋을 넣기 힘들기 때문에 time-exceed이 뜸
	
	
 */


//package Array;
//
//import java.util.List;
//import java.util.ArrayList;
//
//
//public class ContainsDuplicates217 {
//	
//	public static boolean containsDuplicate(int[] nums)
//	{
//        List<Integer> rst = new ArrayList<>();
//        
//        for(int i = 0; i < nums.length; i++)
//        {
//        	if(!rst.contains(nums[i]))
//        	{
//        		rst.add(nums[i]);
//        	}
//        	else
//        	{
//        		return true;
//        	}
//        }
//        return false;
//    }
//	
//	public static void main(String[] args)
//	{
//		int[] nums = {1,2,3,1};
//		System.out.println(containsDuplicate(nums));
//	}
//}

/*
 * <문제풀이 by LHearen>
 * 
 * 중복되는 값은 set에 들어갈 수 없음을 이용하여, !set.add(n) -- set에 안들어 가면 중복된다는 말이므로, true를 반환
 * 
 * if (!set.add(n)) 이렇게만 해도, if문이 돌면서 set에 들어가는지 확인을 할 때, 값을 넣어준다.
 * 
 * Runtime: 6 ms, faster than 80.71% of Java online submissions for Contains Duplicate.
	Memory Usage: 44.7 MB, less than 39.83% of Java online submissions for Contains Duplicate.
 */



package Array;

import java.util.Set;
import java.util.HashSet;


public class ContainsDuplicates217 {
	
	public static boolean containsDuplicate(int[] nums)
	{
		if (nums == null || nums.length < 2) return false;
        Set<Integer> set = new HashSet<>();
        for (int n: nums) 
        {
            if (!set.add(n))
        	{
            	return true;
        	}
        }
        return false;
    }
	
	public static void main(String[] args)
	{
		int[] nums = {1,2,3,1};
		System.out.println(containsDuplicate(nums));
	}
}
