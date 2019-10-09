	/*	
	You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
	
	The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
	
	Example 1:
	Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
	Output: [-1,3,-1]
	Explanation:
	    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
	    For number 1 in the first array, the next greater number for it in the second array is 3.
	    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
	Example 2:
	Input: nums1 = [2,4], nums2 = [1,2,3,4].
	Output: [3,-1]
	Explanation:
	    For number 2 in the first array, the next greater number for it in the second array is 3.
	    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.





	<문제>
	
	nums1과 nums2가 다음과 같이 주어진다.
	
	nums1 = [4,1,2], nums2 = [1,3,4,2]
	
	각 어레이는 중복된 값이 없고, nums1는 nums2에 종속되는 어레이다.
	
	리스트에 다음의 조건에 부합되는 수를 담아서 반환하라.
	
	조건은, nums1에 4가 있다고 치면, nums1은 nums2에 종속되니까, nums2에도 4가 무조건 있는데, nums2에 있는 4의 오른쪽 숫자들 중, 가장 첫번째로 4보다 큰 수 가있다면, 그 수를 어레이에 담고, 없다면 -1을 담아라.
	
	nums1에 있는 모든 원소를 위의 방식대로 하고 반환하라.
 */

package Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NextGreaterElementI496 {
	
	//<문제풀이1>
	
	//2중 for문으로 모든 숫자를 순회하는 방법보다, nums2에 각 원소들이 몇번 인덱스에 있는지 key:value로 dictionary(a.k.a map)에 담아 놓고,
	
	//nums1을 loop돌면서, 해당 원소가 nums2에 있는 위치에 오른쪽(+1)부터 loop돌면서, 더 큰값이 있으면 넣고 없다면 -1을 넣는 방법.
	
	//성능이 괜찮다.
	
	//Runtime: 2 ms, faster than 97.01% of Java online submissions for Next Greater Element I.
	//Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for Next Greater
	
	public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] rst = new int[nums1.length];
        int rst_idx = 0;
        
		Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums2.length; i++) map.put(nums2[i], i);
        
        for(int j : nums1) {
        									
        	int idx = (int)map.get(j)+1;
        	if(idx >= nums2.length) rst[rst_idx++] = -1;
        	else {

            	for(int k = idx; k < nums2.length; k++) {
            		if(nums2[k] > j) {
            			rst[rst_idx++] = nums2[k];
            			break;
            		}
            		if(k == nums2.length-1) rst[rst_idx++] = -1;
            	}
        	}        	
        }
		
        return rst;
    }
	
	public static void main(String[] args) {
		int[] nums1 = {4,1,2};
		int[] nums2 = {1,3,4,2};
		System.out.println(nextGreaterElement(nums1, nums2));
	}
}




