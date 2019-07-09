
/*
<문제 풀이 by diaa>

hashmap을 세개를 만들어서, 하나는 count전용, 하나는 left index전용, 나머지 하나는 right index전용으로 선언

만약 countMap에 없으면 처음등장한다는 말이므로 leftMap에 넣는다. 그리고 매번 rightMap을 업데이트 해준다.

max frequency를 구한 후, max frequency와 같다면 rightMap-leftMap한 차이를 반환.

Runtime: 23 ms, faster than 58.06% of Java online submissions for Degree of an Array.
Memory Usage: 40 MB, less than 98.60% of Java online submissions for Degree of an Array.

 */


package Array;

import java.util.HashMap;

public class DegreeOfArray697ver2 {
	public static int findShortestSubArray(int[] nums)
	{
        HashMap<Integer,Integer> countMap = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> leftMap = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> rightMap  = new HashMap<Integer,Integer>();
        
        int max = 0;
        
        for(int i = 0; i < nums.length; i++)
        {
        	int num = nums[i];
        	int cnt = 1;
        	
        	if(countMap.containsKey(nums[i]))
        	{
        		cnt += countMap.get(nums[i]);
        		countMap.put(nums[i],cnt);
        	}
        	else
        	{
        		leftMap.put(nums[i],i);
        	}
        	countMap.put(nums[i],cnt);
        	rightMap.put(nums[i],i);
        	max = Math.max(max, cnt);
        }
        
        int min = nums.length;
        
        for(int j : leftMap.keySet())
        {
        	if(countMap.get(j)==max)
        	{
        		min = Math.min(min, rightMap.get(j)-leftMap.get(j)+1);
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
