/*
	Given an array of integers nums, write a method that returns the "pivot" index of this array.
	
	We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.
	
	If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
	
	Example 1:
	
	Input: 
	nums = [1, 7, 3, 6, 5, 6]
	Output: 3
	Explanation: 
	The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
	Also, 3 is the first index where this occurs.
	 
	
	Example 2:
	
	Input: 
	nums = [1, 2, 3]
	Output: -1
	Explanation: 
	There is no index that satisfies the conditions in the problem statement.
 */
package Array;

public class FindPivotIndex724 {
	
	/*
	
	//<문제풀이 - trial01>
	
	//int[] nums = {-1,-1,-1,-1,0,1}; 에서 막힘
	
	//방법 : left와 right를 index에 가장 왼쪽, 오른쪽 값을 넣어놓고, 크기를 비교해 가면서 index를 하나 하나 옮기는 방법
	
	//문제점 : int[] nums = {-1,-1,-1,-1,0,1}; 에서 막힘
	
	//-부호에서 left로 갈까 right로 갈까 판단을 못함
	
	public static int pivotIndex(int[] nums) {
		
		if(nums.length == 0) return -1;
		
		int left = 0;
		int right = nums.length-1;
		
		int leftSum = nums[left];
		int rightSum = nums[right];
		
		while(left+1 != right-1 && left < right)
		{
			if(Math.abs(leftSum) > Math.abs(rightSum))
			{
				rightSum += nums[--right];
			}
			else if(Math.abs(leftSum) < Math.abs(rightSum))
			{
				leftSum += nums[++left];
			}
			else
			{
				leftSum += nums[++left];
				rightSum += nums[--right];
			}
		}
		
		return (leftSum == rightSum && left < right) ? left+1 : -1; 
    }
	
	*/
	
	/*
	//<문제풀이 by shine123glow>
	
	//for문 돌면서 해당 i번째 인덱스 -1번째까지의 합과, i+1쨰~마지막 인덱스 까지의 합을 비교
	
	//단순무식방법
	
//	Runtime: 211 ms, faster than 7.17% of Java online submissions for Find Pivot Index.
//	Memory Usage: 53.8 MB, less than 5.08% of Java online submissions for Find Pivot Index.
	
	public static int getSum(int[] nums, int left, int right)
	{
		int sum = 0;
		
		for(int j = left; j < right; j++)
		{
			sum += nums[j];
		}
		
		return sum;
	}
	
	public static int pivotIndex(int[] nums) {
        
		if(nums.length < 3) return -1;
        
        int start = 0;
        int end = nums.length;
		
		for(int i = 0; i < nums.length; i++)
		{
			int leftSum = getSum(nums, start, i);
			int rightSum = getSum(nums, i+1, end);
			if(leftSum == rightSum) return i;
		}
		return -1;
	}
	*/
	
	/*
	//<문제풀이 by alexander>
	
	//리스트 {1, 7, 3, 6, 5, 6} 에 모든 원소를 더한 값이 total이라고 치자.
	
	//for문을 돌면서 해당 자릿수의 값을 total에서 빼주면 왼쪽 오른쪽 값이 같아야 한다.
	
	//따라서 index 0부터 해당 값을 하나하나 더해주면서 total에서 j번째 값을 뺐을 때, 여태껏 더해왔던 값 sum(sum of left)가 sum of right와 같으므로, x2를 해준 값과 같다면 j를 반환한다.
	
//	Runtime: 2 ms, faster than 55.70% of Java online submissions for Find Pivot Index.
//	Memory Usage: 38.6 MB, less than 100.00% of Java online submissions for Find Pivot Index.
	
	
	public static int pivotIndex(int[] nums) {
		
		int total = 0;
		int sum = 0;
		
		for(int i = 0; i < nums.length; i++)
		{
			total += nums[i];
		}

		for(int j = 0; j < nums.length; sum += nums[j++])
		{
			if((total - nums[j]) == sum * 2) return j;
		}
		
		return -1;
	}
	*/
	
	//<문제풀이 by alexander>
	
	//위의 풀이를 조금 다듬은 것
	
	//for문을 정의할 때, for(int i = 0; i < nums.length; i++) 이렇게 쓰는것 보다, for(int i : nums)이렇게 쓰는게 1ms 빠름
	
//	Runtime: 1 ms, faster than 100.00% of Java online submissions for Find Pivot Index.
//	Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Find Pivot Index.
	
	public static int pivotIndex(int[] nums) {
        int total = 0, sum = 0;
		for(int i : nums) total += i;
		for(int j = 0; j < nums.length; sum += nums[j++])
			if(total - nums[j] == sum * 2) return j;
		return -1;
	}
	
	
	public static void main(String[] args) {
		int[] nums = {1, 7, 3, 6, 5, 6};
		//int[] nums = {1,2,3};
		//int[] nums = {-1,-1,-1,-1,-1,-1};
		//int[] nums = {-1,-1,-1,-1,-1,0};
		//int[] nums = {-1,-1,-1,-1,0,1};
		//int[] nums = {-1,-1,-1,0,1,1};
		
		
		System.out.println(pivotIndex(nums));
	}
}
