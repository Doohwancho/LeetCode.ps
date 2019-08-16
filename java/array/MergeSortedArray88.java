/*
	Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
	
	Note:
	
	The number of elements initialized in nums1 and nums2 are m and n respectively.
	You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
	Example:
	
	Input:
	nums1 = [1,2,3,0,0,0], m = 3
	nums2 = [2,5,6],       n = 3
	
	Output: [1,2,2,3,5,6]
	
	
	
	<문제>
	
	어레이 nums1와 nums2를 merge하라.
	
	두 어레이 nums1와 nums2가 다음과 같이 주어진다.
	
	nums1 = [1,2,3,0,0,0];
	nums2 = [2,5,6]; 
	
	m과 n도 다음과 같이 주어진다.
	
	m = 3;
	n = 3;
	
	여기서 m은 nums1에서 초기화된 값들의 갯수이다. 3이니까 맨 처음 1,2,3이 초기화 된 값이고, 나머지 0들은 초기화를 안해준 값이다.
	
	바로 이 곳에(0들에) nums2를 merge 시키되, 오름차순이어야 한다.
	
	따라서 [1,2,2,3,5,6]이 나와야 한다.
 */

package Array;

import java.util.Arrays;

public class MergeSortedArray88 {
	
	/*
	<문제풀이>
	
	먼저 nums2가 empty array라면, nums1로 합병할게 없으므로, 아무것도 하지 않는다.
	
	nums1을 for문으로 돌면서 0이 있는 값이 초기화가 안된 자리이므로 nums2의 값을 넣어준다.
	
	단, 맨 처음 인덱스는 m부터 시작한다. 그 이유는 m이 num1에서 초기화된 값들의 갯수이기 때문이다.
	
	(보통 리스트 만들때 여유공간을 남겨두면 값을 앞에서부터 채워나가기 때문에 초기화가 안된 값 0들이 맨 뒤에 위치함)
	
	num1에 값을 모두 넣었으면 내장함수 Arrays.sort()를 이용하여 오름차순정렬.
	
	좀 느린게 단점.
	
	Runtime: 1 ms, faster than 10.87% of Java online submissions for Merge Sorted Array.
	Memory Usage: 36.2 MB, less than 100.00% of Java online submissions for Merge Sorted Array.
	*/
	
//	public static void merge(int[] nums1, int m, int[] nums2, int n) {
//        
//		if(nums2.length == 0) return;
//        
//        for(int i = m, j = 0; i < nums1.length; i++)
//        {
//            if(nums1[i] == 0)
//            {
//                nums1[i] = nums2[j++];
//            }
//        }
//        Arrays.sort(nums1);
//    }
	
	/*
	<문제풀이 by caikeke>

	nums1의 인덱스를 m-1번째인 i와, nums1의 마지막 인덱스인 k로 구분.
	
	nums1와 nums2의 값을 비교하여 더 큰값을 nums1의 마지막 인덱스부터 채워나가면서,
	
	k를 한칸씩 왼쪽으로 당겨줌. nums1의 값을 썼으면 i--을 하고 nums2의 값을 썼다면 j--을 해줌
	
	만약 첫번째 while문이 다 끝났는데도, nums2의 값들이 nums1의 값들보다 작아서 안들어갔을 수도 있으므로
	
	nums2의 값들을 넣어줌
	
	Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Sorted Array.
	Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Merge Sorted Array.
	 */
	
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
        
		int i = m-1, j = n-1, k = m+n-1;
		
		while(i >= 0 && j >= 0)
		{
			nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
		}
		while(j >= 0)
		{
			nums1[k--] = nums2[j--];
		}
    }
	
	public static void main(String[] args) {
//		int[] nums1 = {1,2,3,0,0,0};
//		int m = 3;
//		int[] nums2 = {2,5,6};
//		int n = 3;
		
		int[] nums1 = {-1,0,0,3,3,3,0,0,0};
		int m = 6;
		int[] nums2 = {1,2,2};
		int n = 3;
		
		merge(nums1, m, nums2, n);
	}
}
