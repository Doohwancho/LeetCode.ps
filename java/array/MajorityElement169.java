/*
	Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
	
	You may assume that the array is non-empty and the majority element always exist in the array.
	
	Example 1:
	
	Input: [3,2,3]
	Output: 3
	Example 2:
	
	Input: [2,2,1,1,1,2,2]
	Output: 2
	
	
	
	<문제>
	
	주어진 리스트에서 가장 빈도수가 많은 수를 반환하라
	
	
	
	<문제 풀이>
	
	리스트의 가장 첫번째 수를 maxNum, 수를 세기위해 1의값을 가지는 cnt변수 선언.
	
	만약 maxNum이 나오면 cnt를 하나씩 더해주고, 아니라면 cnt를 하나씩 빼준다.
	
	만약 maxNum이 8이고, 리스트 안에서 그 다음으로 많이 나온숫자가 1이라고 가정하자.
	
	{1,1,1,8,8,8,8}에서, 3번째 8의 인덱스(인덱스5)에서 maxNum은 1이고 cnt는 0이된다.
	
	그러면 다음 차례에선 else문에 걸려서 maxNum이 8이되어 8을 반환한다.
 * 
 */

package Array;

public class MajorityElement169 {
	
	public static int majorityElement(int[] nums) {
        
		int cnt = 1;
		int maxNum = nums[0];
		
		for(int i = 1; i < nums.length; i++)
		{
			if(nums[i] == maxNum)
			{
				cnt++;
			}
			else if(cnt > 0)
			{
				cnt--;
			}
			else
			{
				maxNum=nums[i];
				cnt = 1;
			}
		}
		return maxNum;
    }
	
	public static void main(String[] args) {
		int[] nums = {8,1,8,1,7,1,8,8};
		System.out.println(majorityElement(nums));
	}
}
