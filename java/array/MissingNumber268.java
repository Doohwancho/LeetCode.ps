/*
	Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
	
	Example 1:
	
	Input: [3,0,1]
	Output: 2
	Example 2:
	
	Input: [9,6,4,2,3,5,7,0,1]
	Output: 8
	
	<문제>
	
	리스트가 다음과 같이 주어진다. [3,0,1]
	
	리스트 안의 숫자들은 0부터 시작해서 1씩 순차적으로 늘어나는 값들이다.
	
	그런데 자세히 보면 중간에 값 하나가 빠졌다.
	
	그 빠진 값을 구하라.
	
	
	<문제풀이>
	
	주어진 리스트의 길이+1의 rst라는 새로운 int[]를 만든다.
	
	[3,0,1]의 rst는 [0,0,0,0]이다.
	
	for문으로 [3,0,1]을 돌면서, 해당 값의 인덱스에 값을 넣는다. 단, 0을 넣을때는 정수중 값 아무거나로 대신해서 넣는다.(나는 1로 넣음)
	
	그러면 [1,1,0,3]이 되는데, 이걸 for문으로 돌면서 0인 값이 있으면 해당 인덱스를 뽑아내면 된다.
	
	Runtime: 1 ms, faster than 41.50% of Java online submissions for Missing Number.
	Memory Usage: 38.5 MB, less than 99.62% of Java online submissions for Missing Number.
 */

package Array;

public class MissingNumber268 {
	
	public static int missingNumber(int[] nums) {
		int[] rst = new int[nums.length+1];
        
        for(int i = 0; i < nums.length; i++)
        {
        	if(nums[i]==0)
        	{
        		rst[nums[i]] = 1;
        	}
        	else
        	{
        		rst[nums[i]] = nums[i];
        	}
        }

        for(int j = 0; j < rst.length; j++)
        {
        	if(rst[j]==0) return j;
        }
        return 0;
    }
	
	public static void main(String[] args) {
		int[] nums = {3,0,1};
		System.out.println(missingNumber(nums));
	}
}
