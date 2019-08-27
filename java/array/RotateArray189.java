/*
	Given an array, rotate the array to the right by k steps, where k is non-negative.
	
	Example 1:
	
	Input: [1,2,3,4,5,6,7] and k = 3
	Output: [5,6,7,1,2,3,4]
	Explanation:
	rotate 1 steps to the right: [7,1,2,3,4,5,6]
	rotate 2 steps to the right: [6,7,1,2,3,4,5]
	rotate 3 steps to the right: [5,6,7,1,2,3,4]
	Example 2:
	
	Input: [-1,-100,3,99] and k = 2
	Output: [3,99,-1,-100]
	Explanation: 
	rotate 1 steps to the right: [99,-1,-100,3]
	rotate 2 steps to the right: [3,99,-1,-100]
	
	
	
	
	<문제>
	
	리스트와 k가 [1,2,3,4,5,6,7] and k = 3 이렇게 주어지면,
	
	k번만큼 리스트를 오른쪽으로 땡기는데, 맨 오른쪽에 있던 애는 맨 왼쪽으로 간다.
	
	double linked list와 같은 개념이다.
 */

package Array;

import java.util.Arrays;
import java.util.LinkedList;

public class RotateArray189 {
	
	/*
	//<문제풀이>
	
	//rotate 시키려면, 맨 마지막 숫자를 따로 빼 놓고, 0번째부터 n-1번까지 숫자들을 오른쪽으로 한칸씩 이동한 다음,
	
	//따로 빼둔 숫자를 0번째에 넣으면 된다.
	
	//이짓을 k번 반복하면 된다.
	
	//linkedlist가 list처음이나 중간에 값을 삽입하는게 arraylist보다 빠르다길래 한번 써봤는데, 많이 느리다.
	
	//linkedlist없이 처리하는게 불필요하게 linkedlist에 값을 넣고, 또 nums로 옮기는 짓을 안해도 되서 더 빠를 것 같다.
	
	//Runtime: 198 ms, faster than 5.29% of Java online submissions for Rotate Array.
	//Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for Rotate Array.
	
	public static void rotate(int[] nums, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        int len = nums.length;
        
        for(int i = 0; i < len; i++)
        {
        	list.add(nums[i]);
        }
        
        for(int j = 0; j < k; j++)
        {
        	int tmp = list.get(len-1);
        	list.removeLast();
        	list.push(tmp);  //insert at index(0)
        }
        
        for(int q = 0; q < len; q++)
        {
        	nums[q] = list.get(q);
        }
       
    }
    */
	
	/*
	//<문제풀이>
	
	//오져따리
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Array.
	//Memory Usage: 37 MB, less than 100.00% of Java online submissions for Rotate Array.
	
	//뒤에서 k번째까지 따로 빼둔다.
	
	//그리고 system.arraycopy로 남은 숫자들을 k번째부터 맨 끝으로 밀어준다.
	
	//그리고 아까 빼둔 숫자들을 처음부터 차례대로 넣어준다.
	
	//유효성 검사를 할 때, nums의 원소가 1개면, 몇번이고 rotate시켜도 똑같으므로, return해준다.
	
	//또한, k가 nums의 길이보다 더 클 경우, nums의 길이만큼 rotate시키면 원래대로이므로, k에서 nums의 길이만큼 나눈 값의 나머지를 계산해준다.
	
	public static void rotate(int[] nums, int k) {
		if(nums.length == 1) return;
        else if(k > nums.length) k %= nums.length;

		int len = nums.length-1;
		int[] container = new int[k];
		int idx = k-1;
		
		for(int i = len; i > len-k; i--)
		{
			container[idx--] = nums[i];
		}

		System.arraycopy(nums, 0, nums, k, len-k+1);
		
		for(int j = 0; j < k; j++)
		{
			nums[j] = container[j];
		}		
	}
	*/
	
	//<문제풀이 by caikehe>
	
	//신기한 방법
	
	//step1) 리스트 전체 뒤집는다
	
	//step2) 0~k번까지 뒤집는다
	
	//step3) k~끝까지 뒤집는다
	
	//int[] nums = {1,2,3,4,5,6,7};
	//int k = 3;
	
	//1  2  3  4  5  6  7
	//7  6  5  4  3  2  1  - step1
	//5  6  7  4  3  2  1  - step2 
	//5  6  7  1  2  3  4  - step3

	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Array.
	//Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for Rotate Array.
	
	public static void rotate(int[] nums, int k) {
	    k %= nums.length;
	    reverse(nums, 0, nums.length-1);  // reverse the whole array
	    reverse(nums, 0, k-1);  // reverse the first part
	    reverse(nums, k, nums.length-1);  // reverse the second part
	}

	public static void reverse(int[] nums, int l, int r) {
	    while (l < r) {
	        int tmp = nums[l];
	        nums[l++] = nums[r];
	        nums[r--] = tmp;
	    }
	}

	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5,6,7};
		int k = 3;
		
		rotate(nums, k);
	}
}
