/*
	Given a binary array, find the maximum number of consecutive 1s in this array.
	
	Example 1:
	Input: [1,1,0,1,1,1]
	Output: 3
	Explanation: The first two digits or the last three digits are consecutive 1s.
	    The maximum number of consecutive 1s is 3.
	    
	    
	<문제>
	
	리스트 안에 1이 최대 몇번 반복되는지 반환하라
	
	
	
	<문제풀이>
	
	1이 연속적으로 나오는걸 세는 consq변수를 활용과, consq변수들 중 가장 큰 값을 정하는 if(consq > maximum) 활용.
	
	Runtime: 2 ms, faster than 99.75% of Java online submissions for Max Consecutive Ones.
	Memory Usage: 39.3 MB, less than 95.89% of Java online submissions for Max Consecutive Ones.
 */

package Array;

public class MaxConsecutiveOnes485 {
	
	public static int findMaxConsecutiveOnes(int[] nums) 
	{
        int consq = 0;
		int maximum = 0;
        
        for(int element : nums)
        {
        	if(element == 1)
        	{
        		consq += element;
        	}
        	else
        	{
        		consq = 0;
        	}
        	
        	if(consq > maximum) 
        	{
        		maximum = consq;
        	}
        }
		return maximum;
    }
	
	public static void main(String[] args) 
	{
		int[] nums = {1,1,0,1,1,1};
		System.out.println(findMaxConsecutiveOnes(nums));
	}
}
