/*
	Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
	
	Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
	
	Example 1:
	
	Given nums = [1,1,2],
	
	Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
	
	It doesn't matter what you leave beyond the returned length.
	Example 2:
	
	Given nums = [0,0,1,1,1,2,2,3,3,4],
	
	Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
	
	It doesn't matter what values are set beyond the returned length.
	Clarification:
	
	Confused why the returned value is an integer but your answer is an array?
	
	Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
	
	Internally you can think of this:
	
	// nums is passed in by reference. (i.e., without making a copy)
	int len = removeDuplicates(nums);
	
	// any modification to nums in your function would be known by the caller.
	// using the length returned by your function, it prints the first len elements.
	for (int i = 0; i < len; i++) {
	    print(nums[i]);
	}
	
	
 */
package Array;

public class RemoveDuplicatesFromSortedArray26 {
	
	/*
	//<문제 풀이>
	
	//가장 단순 무식한 방법
	
	//2중 반복문을 사용하여, i번째의 수가 i-1번째 수보다 크지 않으면, 나머지 숫자들 중 i와 가장 가까운 수를(오름차순 정렬이 되있으니까) i번째에 넣어주는 방식.
	
	//i번째에 넣은 숫자라 해당 리스트에 가장 큰 숫자일 때까지 반복
	
	//최대 (n-1)!까지 반복하기 때문에 느림
	
	//Runtime: 14 ms, faster than 6.21% of Java online submissions for Remove Duplicates from Sorted Array.
	//Memory Usage: 40.1 MB, less than 82.44% of Java online submissions for Remove Duplicates from Sorted Array.
	
    public static int removeDuplicates(int[] nums) {
    	
    	if(nums.length == 0) return 0;
        if(nums.length == 1) return nums.length;
        
        int lngth = 1;
        int maxNum = nums[nums.length-1];
        int i = 1;

        while(nums[i-1] != maxNum)
        {
            if(!(nums[i] > nums[i-1]))
            {
                int j = i+1;
                while(j < nums.length)
                {
                    if(nums[j] > nums[i-1])
                    {
                        nums[i] = nums[j];
                        lngth++;
                        break;
                    }
                    j++;
                }
            }
            else
            {
                lngth++;
            }
            i++;
        }
                 
        return lngth; 
    }
    */
    //<문제풀이 by StefanPochmann>
    
    //인덱스를 두개로 본다.
    
    //하나는 for문을 도는 인덱스(j), 다른 하나는 값을 순차적으로 넣기 위한 인덱스(i)
	
	//j가 i보다 더 크면 j를 넣어주고 i+1을 해주는 방식
	
	//i == 0 || 대신 i = 1을하고 if(nums.length) == 1 return 1;을 할 수도 있지만, 걸리는 속도도 같고
	
	//무엇보다 밑의 방식이 더 범용적이고 깔끔함
    
    public static int removeDuplicates(int[] nums) {

        int i = 0;
        
        for(int j : nums)
        {
            if(i == 0 || j > nums[i-1])
            {
                nums[i++] = j;
            }
        }
        return i;
    }
    
	public static void main(String[] args) {
		 int[] nums = {0,0,1,1,1,2,2,3,3,4};
		 System.out.println(removeDuplicates(nums));

	}
}
