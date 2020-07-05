package array;

public class LongestSubarrOfOnesAfterDeletingOneElement1493 {

	
	//<문제풀이1>
	
	//sliding-window
	
	//O(n)
	
	//Runtime: 6 ms, faster than 21.37% of Java online submissions for Longest Subarray of 1's After Deleting One Element.
	//Memory Usage: 55.2 MB, less than 50.00% of Java online submissions for Longest Subarray of 1's After Deleting One Element.
    public int longestSubarray(int[] nums) {
        int max = 0;
        int zero = 0;
        for(int i = 1, last = 0; i < nums.length; i++){
            if(nums[i] == 0){
                nums[i] = nums[i-1] - last;
                last = nums[i];
                zero++;
            } else {
                nums[i] += nums[i-1];    
            }
            max = Math.max(max, nums[i]);
        }
        return zero > 0 ? max : max-1;
    }
    
    
    //<문제풀이 2 by lee215>
    
    //sliding window
    
    //얘는 문제풀이1과는 다르게, zero > 0 ? max : max-1처리를 안해도 되는게
    
    //애초에 max를 구할때 j-i를 하기 때문. j가 애초에 nums.length-1까지밖에 안가는것도 있고.
    
    //j는 쭉 가되, i는 특정 조건(0이 2번이상 나옴)이 되면, 오른쪽으로 다음 0까지 땡김.
    
    //Runtime: 4 ms, faster than 40.62% of Java online submissions for Longest Subarray of 1's After Deleting One Element.
    //Memory Usage: 55.5 MB, less than 50.00% of Java online submissions for Longest Subarray of 1's After Deleting One Element.
    
    public int longestSubarray(int[] nums) {
        int i = 0, k = 1, max = 0;
        for(int j = 0; j < nums.length; j++){
            if(nums[j] == 0) k--;
            while(k < 0){
                if(nums[i]==0){
                    k++;
                }
                i++;
            }
            max = Math.max(max, j-i);
        }
        return max;
    }
}
