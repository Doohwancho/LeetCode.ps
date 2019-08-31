/*
	Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
	
	We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).
	
	Example 1:
	Input: [4,2,3]
	Output: True
	Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
	Example 2:
	Input: [4,2,1]
	Output: False
	Explanation: You can't get a non-decreasing array by modify at most one element.
 */

package Array;

import java.util.Arrays;

public class NonDecreasingArr665 {
	
	/*
	//<Trial01>
	
	//중간에 확 뛰는 값을 cnt해서 2번 이상 스파크 튀면 false반환하려고 했으나,
	
	//{2,3,3,2,4}같이 3,3에서 두번 스파크 튀긴 하는데 3번째 인덱스의 2만 빼면 오름차순 정렬되는 것을 예외처리 못해줌
	
	//int[] nums = {2,3,3,2,4}; 에서 막힘
	public static boolean checkPossibility(int[] nums) {
        int cnt = 0;
		
        for(int i = 1; i < nums.length; i++)
        {
        	if(nums[i] < nums[i-1] || cnt > 0)
    		{
    			if(i > 1 && nums[i] < nums[i-2])
    			{
    				cnt++;
    				if(i < nums.length-1 && nums[i+1] < nums[i-1])
    				{
    					continue;
    				}
    				else
    				{
    					return false;
    				}
    			}
    		}

        }
        
        return cnt > 1 ? false : true;
    }
    
    */
	
	/*
    //<Trial02>
	
	//원소 하나씩 빼고, 나머지를 오름차순 정렬한 후, 정말로 오름차순 정렬이 되있는지 확인하면 되는 줄 알았는데,
	
	//1,2,3,10,5 이런건 10하나만 빼고 나머지 오름차순 정렬비교 가능하나,
	
	//5,4,3,2,1 이런것 까지도 true떠서 실패
	
	public static boolean checkPossibility(int[] nums) {
		if(nums.length == 1) return true;
		
		for(int i = 0; i < nums.length; i++)
		{
			int[] clone = nums.clone();
			clone[i] = Integer.MIN_VALUE;
			Arrays.sort(clone);
			
			for(int j = 2; j < clone.length; j++)
			{
				if(clone[j-1] > clone[j])
				{
					break;
				}
				else if(j == clone.length-1)
				{
					return true;
				}
				else
				{
					continue;
				}
			}
		}
		return false;
	}
	*/
	
	//<문제풀이 by shawngao>
	
	//Trial01과 기본적인 로직은 같으나,
	
	//아까 막혔던 {2,3,3,2,4}같은 곳에서 2를 3으로 smoothing해줌
	
	//else문은 없어도 돌아는 감. else문 없이 돌리면 2ms나오고,
	
	//Runtime: 2 ms, faster than 11.18% of Java online submissions for Non-decreasing Array.
	//Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Non-decreasing Array.
	
	//else문 있이 돌리면 1ms나옴
	
	//Runtime: 1 ms, faster than 99.48% of Java online submissions for Non-decreasing Array.
	//Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Non-decreasing Array.
	
	//else문의 역할은 i번째를 smoothing하는것. if문의 역할이 i+1번째를 smoothing 하는것 처럼.
	
	//예를들어, {2,3,3,2,4}는 if문에 걸려서 {2,3,3,3,4}로 index3번째가 smoothing 되었다면,
	
	//{2,2,3,2,4}의 경우는, else문에 걸려서 {2,2,2,2,3}로 index 2번째가 smoothing됨.
	
	public static boolean checkPossibility(int[] nums) {
        int n = nums.length, count = 0;

        for (int i = 0; i + 1 < n; i++) {
            if (nums[i] > nums[i + 1]) {
                count++;
                if (i > 0 && nums[i + 1] < nums[i - 1]) nums[i + 1] = nums[i];
                else nums[i] = nums[i + 1];
            }
        }

        return count <= 1;
    }
	

	public static void main(String[] args) {
		//int[] nums = {1,5,4,6,7,10,8,9};
		//int[] nums = {3,2,3,2,4};
		//int[] nums = {3,4,2,3};
		//int[] nums = {2,3,3,2,4}; // {2,2,3,3,4}
		//int[] nums = {4,2,3};
		int[] nums = {4,2,1};
		System.out.println(checkPossibility(nums));
	}
}
