/*

Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6
 

Example 2:

Input: [1,2,3,4]
Output: 24



<문제>

주어진 리스트 안의 3값을 곱했을 때 가장 큰 값을 반환하라.

 */


package Array;

import java.util.Arrays;

public class MaximumProductofThreeNum628 {
	/*
	
//	<문제풀이>
//
//	오름차순으로 정렬해서 가장 뒤엣것 3개가 가장 큰 숫자니까 곱하면 되나, {-4,-3,-2,-1,60} 같은 경우, 가장 뒤엣거 곱한게 120인데, -4,-3,60을 곱하면 720이 나와 더 커져버린다.
//	
//	이 경우, 맨 뒤엣것을 곱한것과 가장 앞에 음수 주개와 맨 뒤 가장 큰수를 곱한것과 비교하여 더 큰것을 반환한다.
//	
//	
//	Runtime: 15 ms, faster than 39.23% of Java online submissions for Maximum Product of Three Numbers.
//	Memory Usage: 38.9 MB, less than 99.30% of Java online submissions for Maximum Product of Three Numbers.
	 
	public static int maximumProduct(int[] nums) {
		Arrays.sort(nums);
		int rst = 1;
		int negativeMax = nums[0]*nums[1]*nums[nums.length-1];
		for(int i = nums.length-1; i >= nums.length-3; i--) rst *= nums[i];
		
		return Math.max(negativeMax, rst);
    }
	*/
	
	/*
	
	<문제풀이 by dreamchase>
	
	가장 큰 숫자 1,2,3과 작은숫자 4,5를 간단한 if~else문으로 뽑은 후, 1*2*3 과 1*4*5중 더 큰것을 반환.
	
	Arrays.sort()를 사용하지 않고, nums[n]을 사용하지 않기 때문에 빠르다.
	
	
	Runtime: 2 ms, faster than 99.75% of Java online submissions for Maximum Product of Three Numbers.
	Memory Usage: 38.6 MB, less than 99.30% of Java online submissions for Maximum Product of Three Numbers.
	
	*/
	
	public static int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
       for (int n : nums) {
           if (n > max1) {
               max3 = max2;
               max2 = max1;
               max1 = n;
           } else if (n > max2) {
               max3 = max2;
               max2 = n;
           } else if (n > max3) {
               max3 = n;
           }

           if (n < min1) {
               min2 = min1;
               min1 = n;
           } else if (n < min2) {
               min2 = n;
           }
       }
       return Math.max(max1*max2*max3, max1*min1*min2);
   }
	
	public static void main(String[] args) {
		//int[] nums = {1,2,3,4};
		//int[] nums = {-1,-2,1,2,3};
		int[] nums = {-4,-3,-2,-1,60};
		System.out.println(maximumProduct(nums));
	}
}
