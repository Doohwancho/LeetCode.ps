/*
	Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums. If target exists, then return its index, otherwise return -1.
	
	
	Example 1:
	
	Input: nums = [-1,0,3,5,9,12], target = 9
	Output: 4
	Explanation: 9 exists in nums and its index is 4
	
	Example 2:
	
	Input: nums = [-1,0,3,5,9,12], target = 2
	Output: -1
	Explanation: 2 does not exist in nums so return -1

	
	
	
	<문제>
	
	오름차순 정렬이 이미 된 어레이 nums가 다음과 같이 주어진다.

	nums = [-1,0,3,5,9,12]
	
	여기서 target이 9일때, nums에서 해당 target의 인덱스를 반환하라.
	
	만약 nums에 target이 없다면, -1을 반환하라.
 */

package BinarySearch;

public class BinarySearch704 {

	/*
	//<문제풀이1>
	
	//그냥 for문 돌려서 같으면 해당 인덱스 반환하는 방법.
	
	//이 문제의 카테고리가 binary search인 만큼, 글쓴이의 의도를 기막히게 파악하지 못했다고 할 수 있음.
	
	//Runtime: 1 ms, faster than 9.53% of Java online submissions for Binary Search.
	//Memory Usage: 39.3 MB, less than 94.59% of Java online submissions for Binary Search.
	
	public static int search(int[] nums, int target) {
        for(int i= 0; i < nums.length; i++) if(nums[i] == target) return i;
        return -1;
    }
    */
	
	//<문제풀이2>
	
	//binary search에서, target이 nums에 없는 경우만 삼항연산자로 처리한 것.
	
	//binary search의 대략적 메커니즘은, l, r, m이 left, right, middle의 약자인데,
	
	//검색하는 리스트를 반으로 뚝 떼서, 내가 찾고자 하는 원소(target)이 오른쪽 반에 있으면, 왼쪽 반을 버리는 방법을 n번하는 것.
	
	//단, binary search를 하려면 리스트가 오름차순 정렬이 되있어야 한다는 조건이 있다. 
	
	//깔끔하구만.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Search.
	//Memory Usage: 39 MB, less than 100.00% of Java online submissions for Binary Search.
	
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length-1, m = 0;
		while(l < r) {
			m = (l+r)/2;
			if(nums[m] < target) {
				l = m+1;
			}
			else {
				r = m;
			}
		}
		return nums[l] == target ? l : -1;
    }
		
	
	public static void main(String[] args) {
		int[] nums = {-1,0,3,5,9,12};
		int target = 9;
		System.out.println(search(nums, target));
	}
}
