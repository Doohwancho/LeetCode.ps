/*
	Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
	
	Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
	
	Example 1:
	Input: [1, 2, 2, 3, 1]
	Output: 2
	Explanation: 
	The input array has a degree of 2 because both elements 1 and 2 appear twice.
	Of the subarrays that have the same degree:
	[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
	The shortest length is 2. So return 2.
	Example 2:
	Input: [1,2,2,3,1,4,2]
	Output: 6
	
	
	
	<문제>
	
	리스트가 [1, 2, 2, 3, 1]같이 주어지면, 리스트 안의 원소들 중, 가장 많이 등장하는 원소들이 있을텐데(위의 경우 1과 2가 각각 2번씩, 가장 많은 빈도수로 나타난다),
	
	각 원소들의 subarray길이가 가장 짧은 값을 반환하라.(1의 subarray는 5이고, 2의 subarray는 2니까, 더 짧은 2반환)
	
	
	<문제풀이 - trial01>
	
	먼저 가장 많은 빈도수로 등장하는 단어를 추출하고, 2pointer로 양쪽 끝에서 left와 right의 값을 점점 줄여나가며 subarray를 구하려고했으나, 
	
	빈도수가 동일한 숫자가 두개 이상 있을 때 에러남.
	
 */

package Array;

public class DegreeOfArray697 {
	public static int findShortestSubArray(int[] nums)
	{
        int maxNum = nums[0];
        int cnt = 1;
        
        for(int i = 1; i < nums.length; i++)
        {
        	if(nums[i]==maxNum)
        	{
        		cnt++;
        	}
        	else if(nums[i]!=maxNum && cnt > 0)
        	{	
        		cnt--;
        	}
        	else
        	{
        		maxNum=nums[i];
        		cnt=1;
        	}
        }
        
        int l = 0, r = nums.length-1;
        
        while(l<r)
        {
        	if(nums[l]==maxNum && nums[r]==maxNum)
        	{
        		return r-l+1;
        	}
        	else if(nums[r]==maxNum)
        	{
        		l++;
        	}
        	else
        	{
        		r--;
        	}
        }
        return 0;
    }
	
	
	public static void main(String[] args) 
	{
		//int[] nums = {1,2,6,6,2,6,2,3,3,3};
		int[] nums = {1, 2, 2, 3, 1};
		//int[] nums = {1,2,3,3,3,3,1};
		System.out.println(findShortestSubArray(nums));
	}
}
