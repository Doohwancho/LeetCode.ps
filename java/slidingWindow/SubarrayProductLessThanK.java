package september;

public class SubarrayProductLessThanK {

	//<문제풀이1>
	
	//sliding-window
	
	//Runtime: 7 ms
	//Memory Usage: 48.5 MB
	
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int rst = 0;
        int prod = 1;
        for(int i = 0, j = 0; i < nums.length; i++){
            prod *= nums[i];
            while(prod >= k && j <= i){
                prod /= nums[j];
                j++;
            }
            rst += (i-j+1);
        }
        return rst;
    }
}
