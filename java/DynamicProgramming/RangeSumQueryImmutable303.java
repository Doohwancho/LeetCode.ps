/*
	Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
	
	Example:
	Given nums = [-2, 0, 3, -5, 2, -1]
	
	sumRange(0, 2) -> 1
	sumRange(2, 5) -> -1
	sumRange(0, 5) -> -3
	
	
	
	<문제>
	
	어레이와 숫자 i,j가 다음과 같이 주어진다. [-2, 0, 3, -5, 2, -1], i = 0, j = 2
	
	어레이 인덱스 i부터 j까지의 합을 반환하는 함수를 만들어라.
 */

package DynamicProgramming;

public class RangeSumQueryImmutable303 {

	/*
	 * //<문제풀이1>
	 * 
	 * //굳이 문제에서 함수를 두개로 나눠서 전역변수를 쓰는 불상사(?)가 발생했는데, 출제자의 의도를 파악하지 않고 문제를 푼 것 같다.
	 * 
	 * //Runtime: 277 ms, faster than 5.33% of Java online submissions for Range Sum
	 * Query - Immutable. //Memory Usage: 61.4 MB, less than 7.32% of Java online
	 * submissions for Range Sum Query - Immutable.
	 * 
	 * int[] container;
	 * 
	 * public NumArray(int[] nums) { container = new int[nums.length]; int idx = 0;
	 * for(int element : nums) container[idx++] = element; }
	 * 
	 * public int sumRange(int i, int j) { int sum = 0; for(int p = i; p <
	 * container.length; p++){ if(p > j) break; sum += container[p]; } return sum; }
	 */
	
	
	//<문제풀이2 by HelloWorld123456>
	
	//누적합을 담는 sum이라는 어레이를 다음과 같이 만듬.
	
	//nums = [-2, 0, 3, -5, 2, -1]
	//sum  = [-2,-2, 1, -4,-2, -3]
	
	//여기서 i부터 j번째의 함을 구하려고 sumRange()의 return문을 보면, sum[j]-sum[i]+nums[i]라고 해놨는데, 이걸 쉽게 말하자면 이렇다.
	
	//3번째부터 5번째까지의 합, 그러니까 3+(-5)+2의 합인 0을 구하고 싶다고 하자.
	
	//여기서 한 것을 나눠서 보면, 먼저 sum에서 5번째까지의 합 - 3번째 까지의 합을 했다.
	
	//따라서 [1,2,3,4,5]-[1,2,3]을 한 것이다. 계산하면 [4,5]만 남는다.
	
	//이 때, 문제에서 3번째부터 5번째까지의 합을 구하라고 한 것이기 때문에, 마지막에 3번째만 더하면 된다. 그게 nums[i]인 것이고.
	
	//똑똑하구만 기래
	
	//Runtime: 50 ms, faster than 96.42% of Java online submissions for Range Sum Query - Immutable.
	//Memory Usage: 40 MB, less than 100.00% of Java online submissions for Range Sum Query - Immutable.

	int[] nums;
	int[] sum;

	public void NumArray(int[] nums) {
	    if(nums==null||nums.length==0)
	    return;
		this.nums = nums;
		int len = nums.length;
		sum = new int[len];
		sum[0] = nums[0];
		for (int i = 1; i < len; i++)
			sum[i] = sum[i - 1] + nums[i];
	}

	public int sumRange(int i, int j) {
		return sum[j] - sum[i] + nums[i];
	}
}
