	/*
	Given an array nums and a value val, remove all instances of that value in-place and return the new length.

	Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
	
	The order of elements can be changed. It doesn't matter what you leave beyond the new length.
	
	Example 1:
	
	Given nums = [3,2,2,3], val = 3,
	
	Your function should return length = 2, with the first two elements of nums being 2.
	
	It doesn't matter what you leave beyond the returned length.
	Example 2:
	
	Given nums = [0,1,2,2,3,0,4,2], val = 2,
	
	Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
	
	Note that the order of those five elements can be arbitrary.
	
	It doesn't matter what values are set beyond the returned length.
	Clarification:
	
	Confused why the returned value is an integer but your answer is an array?
	
	Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
	
	Internally you can think of this:
	
	// nums is passed in by reference. (i.e., without making a copy)
	int len = removeElement(nums, val);
	
	// any modification to nums in your function would be known by the caller.
	// using the length returned by your function, it prints the first len elements.
	for (int i = 0; i < len; i++) {
	    print(nums[i]);
	}
	
	
	
	
	
	<문제>
	
	리스트인 nums에 제거하고싶은 값 val이 주어지면, in-place 방식을 이용하여 해당 값을 모두 지우고, 지운 후 리스트의 길이를 반환하라.
	
	예를들어, nums = {3,2,2,3}, val = 3 이면, 3을 모두 삭제하고난 nums는 {2,2}가 되고, 따라서 위 길이인 2를 반환하면 된다.
	
	 */

package Array;

public class RemoveElement27 {
	
	/*
		//문제풀이
		
		문제에서 in-place 방식으로, 새로운 리스트를 만들지 않고 특정값을 지우라고 하였다.
		
		근데 예제에 보면 다음과 같이 써져있다.
		
		It doesn't matter what values are set beyond the returned length.
		
		이말은 즉, 특정 값을 삭제하고 나고 남은 공간에 아무숫자나 들어가도 된다는 말이다.
		
		이 때문에 새로운 리스트를 만들지 않고 리스트의 길이를 어떻게 바꿔야 하나 걱정할 필요가 없다.
		
		밑에 방식은, 인덱스를 i와 j 두개를 쓰는데, for문으로 돌면서 val과 다르면 인덱스 i에 있는것을 인덱스 j위치에 넣고, j를 +1하여 한칸 당긴다.
		
		이 말은, j는 nums[i]와 val이 다른 횟수를 의미한다.
		
		따라서 반환값도 j로 해준다.
		
		동작방식은 for문을 돌다가 val이 나오면, 그 다음 val이 아닌값으로 덮어씌우는 형태이다.
		
		따라서 nums에 {1,2,3,4}을 넣고, val가 2라면, nums는 {1,3,4,4} 가 된다.
		
		val인 2가 다음 val이 아닌 3으로 덮어씌워지게 되고, 지워져서 남은 빈 공간은 nums의 가장 마지막 값으로 채워지게 된다.
		
		Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Element.
		Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Remove Element.

	*/
	public static int removeElement(int[] nums, int val) {
		if(nums == null) return -1;
        if(nums.length == 0) return 0;
        int j = 0;
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] != val)
            {
                nums[j++] = nums[i];
            }
        }
        for(int k : nums) { System.out.print(k+" ");}
        System.out.println();
        return j;
    }
	
	
	public static void main(String[] args) {
//		int[] nums = {3,2,2,3};
//		int val = 3;
		int[] nums = {1,2,3,4};
		int val = 2;
		
		System.out.println(removeElement(nums, val));
		
	}
	
}
