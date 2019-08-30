/*
	Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
	
	Example 1:
	Input: [3, 2, 1]
	
	Output: 1
	
	Explanation: The third maximum is 1.
	Example 2:
	Input: [1, 2]
	
	Output: 2
	
	Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
	Example 3:
	Input: [2, 2, 3, 1]
	
	Output: 1
	
	Explanation: Note that the third maximum here means the third maximum distinct number.
	Both numbers with value 2 are both considered as second maximum.
 */

package Array;

import java.util.TreeSet;

public class ThirdMaxNum414 {
/*
	//<Trial01>

	//int[] nums = {1,2,-2147483648}; 처럼 Min_Value가 들어가면 실패함..

	//너무한거 아니냐 저런숫자 실전에서 안나올거같은데


	public static int thirdMax(int[] nums) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
                
        for(int i = 0; i < nums.length; i++)
        {
        	if(nums[i] > first)
    		{
        		third = second;
        		second = first;
    			first = nums[i];
    		}
        	else if(nums[i] != first && nums[i] > second)
    		{
        		third = second;
        		second = nums[i];
    		}
        	else if(nums[i] != first && nums[i] != second && nums[i] >= third)
    		{
    			third = nums[i];
    		}
        }
        
		return third != Integer.MIN_VALUE ? third : first;
    }
	*/
	
	//<문제풀이 by lsy1991121>
	
	//위의 로직과 같으나, 형변환 이용. long형의 min_value가 -9223372036854775808이고, leetcode에서 나오는 예제가
	
	//int의 min_value인 2147483648까지밖에 안나오므로, 이를 이용한 방법. 천젠가?
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Third Maximum Number.
	//Memory Usage: 37.5 MB, less than 100.00% of Java online submissions for Third Maximum Number.
	
	public static int thirdMax(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
        
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;
        
        for (int num : nums) {
            long longNum = (long) num;
            if (longNum > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (longNum > max2 && longNum != max1) {
                max3 = max2;
                max2 = num;
            } else if (longNum > max3 && longNum != max1 && longNum != max2) {
                max3 = num;
            }
        }
        
        return (int)(max3 == Long.MIN_VALUE? max1 : max3);
	}
	
	/*
	//<문제풀이 by StefanPochmann>
	
	//TreeSet을 이용하여 set으로 중복을 없애면서 오름차순으로 집어넣는 것을 이용
	
	//Runtime: 5 ms, faster than 29.21% of Java online submissions for Third Maximum Number.
	//Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for Third Maximum Number.

	public static int thirdMax(int[] nums) {
		TreeSet<Integer> set = new TreeSet<>();
		for(int num : nums)
			if(set.add(num) && set.size() > 3)
				set.pollFirst();
		
		return set.size() > 2 ? set.pollFirst() : set.pollLast();
	}
	*/
	public static void main(String[] args) {
		//int[] nums = {2,2,3,1};
		//int[] nums = {1,2,2,5,3,5};
		int[] nums = {1,2,-2147483648};
		System.out.println(thirdMax(nums));
	}
}
