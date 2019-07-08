/*
	Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
	
	Find all the elements of [1, n] inclusive that do not appear in this array.
	
	Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
	
	Example:
	
	Input:
	[4,3,2,7,8,2,3,1]
	
	Output:
	[5,6]
	
	
	
	<문제>
	
	리스트가 [4,3,2,7,8,2,3,1]이렇게 주어진다. 1부터 n까지의 숫자이다. 여기서 2나 3같은 수처럼 중복되는 수가 있을 수 있다.
	
	리스트 안에 빈 숫자를 반환하면 된다. 위의 경우 5와 6이 비었기 때문에(1,2,3,4,7,8이 나옴) [5,6]을 반환해 주면 된다.
	
	
	
	<문제풀이>
	
	for문을 돌면서, 해당 숫자의 자리에 맞게끔 자리를 바꿔준다. 먼저 두 인덱스의 자리를 바꿔주는 swap()함수를 만든 후,
	while문을 돌면서, idx1과 idx2의 숫자가 해당숫자가 둘다 맞지 않으면 두 숫자를 swap해 주는 함수를 만든다.
	예를들어,  {4,3,2,7,8,2,3,1} 이걸로 시작했다고 치면, idx1이 0이고 idx2가 3(4-1)일때, 4와 7을 swap한다.
	그러면  {7,3,2,4,8,2,3,1}이 되는데, 이때 idx1이 0이고 idx2가 6(7-1)이 되서 7과 3을 swap하면
	 {3,3,2,4,8,2,7,1}이 된다. 이런식으로 자기 자리를 찾다보면 {1,2,3,4,2,3,7,8}이 되어, 원래 자리수가 아닌 2,3의 idx+1만 뽑아내면 된다.
	
 * 
 */



package Array;

import java.util.List;
import java.util.ArrayList;

public class FindAllNumbersDisappeared448 {
	public static void swap(int[] A, int idx1, int idx2)
	{
		int tmp = A[idx1];
		A[idx1] = A[idx2];
		A[idx2] = tmp;
	}
	
	public static List<Integer> findDisappearedNumbers(int[] nums)
	{
		List<Integer> rst = new ArrayList();
		
		for(int i = 0; i < nums.length; i++)
		{			
			while(nums[i] != i+1 && nums[nums[i]-1] != nums[i])
			{
				swap(nums, i, nums[i]-1);
			}
		}
		
		for(int j = 0; j < nums.length; j++)
		{
			if(nums[j] != j+1) rst.add(j+1);
		}

        return rst;
    }
	
	
	public static void main(String[] args)
	{
		int[] nums = {4,3,2,7,8,2,3,1};
		System.out.println(findDisappearedNumbers(nums));
		
	}
}
