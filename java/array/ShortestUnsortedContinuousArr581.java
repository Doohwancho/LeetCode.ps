/*
	Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
	
	You need to find the shortest such subarray and output its length.
	
	Example 1:
	Input: [2, 6, 4, 8, 10, 9, 15]
	Output: 5
	Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
	
	
	
	
	
	
	<문제>
	
	리스트가 [2, 6, 4, 8, 10, 9, 15] 이렇게 주어지면, 이 리스트 안에 얼마만큼의 길이의 sub-array를 오름차순 정렬해야 전체 리스트가 오름차순 정렬되는지 구하라.
	
	위 경우, [6, 4, 8, 10, 9] 이 다섯개 원소를 오름차순 정렬하면, 전체 리스트가 이쁘게 오름차순 정렬되므로, 5를 반환한다.
 */

package Array;

import java.util.Arrays;

public class ShortestUnsortedContinuousArr581 {
	
	/*
	//<문제>
	
	//메모리는 좋으나 속도가 평범
	
	//Runtime: 7 ms, faster than 56.89% of Java online submissions for Shortest Unsorted Continuous Subarray.
	//Memory Usage: 39.7 MB, less than 100.00% of Java online submissions for Shortest Unsorted Continuous Subarray.
	
	
	//nums와 똑같은데 오름차순 정렬된 compare이라는 리스트를 만들어, 2-pointer로 양 끝단에 다른 원소를 찾아내 그 차이만큼 반환하는 방법.
	
	
	//2  6  4  8  10  9  15   - nums
	//2  4  6  8  9  10  15   - compare
	//   o            o       - 다른 양 끝단 index확인
	//양끝단 차이 반환
	

	public static int findUnsortedSubarray(int[] nums) {
		//step1 - create identical && sorted array
		int len = nums.length;
		int[] compare = new int[len];
		System.arraycopy(nums, 0, compare, 0, len); // int[] compare = nums.clone();와 동일
        Arrays.sort(compare);
        
        //step2 - find the differences at the both ends
        int l = 0, r = len-1;
        
        while(l < len)
        {
        	if(nums[l] != compare[l]) break;
        	else l++;
        }
        while(r >= 0)
        {
        	if(nums[r] != compare[r]) break;
        	else r--;
        }
        return r-l+1 < 1 ? 0 : r-l+1;
    }
	*/
	
	//<문제풀이 by compton_scatter
	
	//subarray의 가장 오른쪽 숫자른, 그 이후 나오는 숫자보다 무조건 작아야 한다.
	
	//subarray의 가장 왼쪽 숫자는, 그 이전에 나오는 숫자보다 무조건 커야한다.
	
	//이 두 극단을 찾는 방법.
	
	//Runtime: 2 ms, faster than 92.42% of Java online submissions for Shortest Unsorted Continuous Subarray.
	//Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Shortest Unsorted Continuous Subarray.
	
	public static int findUnsortedSubarray(int[] nums) {
		int len = nums.length, max = nums[0], min = nums[len-1], l = -1, r = -2;
		
		for(int i = 1; i < len; i++)
		{
			max = Math.max(max, nums[i]);
			if(nums[i] < max) r = i; //find the last element which is smaller than the last seen max from 
			
			min = Math.min(min, nums[len-1-i]);
			if(nums[len-1-i] > min) l = len-1-i; //find the last element which is bigger than the last seen min from 
		}
		return r-l+1;
	}


	public static void main(String[] args) {
		int[] nums = {2, 6, 4, 8, 10, 9, 15};
		System.out.println(findUnsortedSubarray(nums));
	}
}
