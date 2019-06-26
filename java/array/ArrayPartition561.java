/*
	Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
	
	Example 1:
	Input: [1,4,3,2]
	
	Output: 4
	Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
	
	
	
	<문제>
	
	리스트가 주어지면, (a1,b1),(a2,b2)이렇게 둘끼리 짝짓고, 짝지은 각각의 그룹마다 min()을 적용했을 경우, 최대값을 구하라
	
	예를들어, [1,4,3,2]의 경우, 
	
	case1) [min(1,4),min(3,2)]= 3
	case2) [min(1,3),min(2,4)]= 3
	case3) [min(1,2),min(3,4)]= 4
	
	case3의 값이 가장 높으므로 4를 반환해 주면 된다.
	
	
	
	<문제 풀이>
	
	둘끼리 짝짓고 min을 적용했을 때, 가장 높은 값의 경우의 수가 나오려면, 먼저 주어진 리스트를 오름차순으로 정렬한 후, 앞에서부터 순차적으로  2개씩 min()을 적용시키면 된다.
	
	예를들어, [1,4,3,2]인 경우, .sort()를 이용해 오름차순으로 정렬하면, [1,2,3,4]가 되고, 앞에서부터 두개씩 min()을 적용하면 [min(1,2),min(3,4)]=4 가 된다.
	
	
 * 
 */


package Array;

import java.util.Arrays;


public class ArrayPartition561 {
	public static int arrayPairSum(int[] nums)
	{
		//step01 - 오름차순 정렬
		Arrays.sort(nums);
		
		//step02 - min의 값을 담을 변수 선언
		int sumMin = 0;
		
		//step03 - 2개씩 min값을 적용해서 sumMin 변수에 더함
		for(int idx = 1; idx < nums.length; idx+=2)
		{
			sumMin+=Math.min(nums[idx-1],nums[idx]);
		}
		
		return sumMin;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,4,3,2};
		System.out.println(arrayPairSum(nums));
	}
}
