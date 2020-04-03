package thirtyDayChallenge;

public class MaximumSubarray {
	
	/*
	//<문제풀이1>
	
	//202 / 202 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 39.8 MB
	
	public int maxSubArray(int[] nums) {
        int rst = nums[0];
        int max = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < 0){
                if(max < 0){
                    max = Math.max(max, nums[i]); //계속 -만 나오면 그중에 그나마 젤 큰거를 고른다.
                }
                else if(max + nums[i] >= 0){ //nums[i]가 아무리 마이너스여도, 여태껏 쌓아올린 것들과 합했을때 +면, 가져간다.
                    max += nums[i];
                }
                else{ //여태껏 쌓아올린 max보다 nums[i]의 -값이 더 크면, 끌고가봐야 -니까 0으로 리셋시켜준다.
                    max = 0;  
                }
                
            } else {
                if(max > 0){ 
                    max += nums[i];
                } else {
                    max = nums[i]; //max가 마이너스 값일 경우, nums[i]로 새롭게 리셋시켜준다
                }
            }
            rst = Math.max(rst, max);
        }
        return rst;
    }
    */
	
	
	//<문제풀이2 by shabeeb>
	
	//문제풀이1을 걍 졸라 간단하게 압축한 것

	//202 / 202 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 39.8 MB
	
    public int maxSubArray(int[] nums) {
        int currentWindowSum = nums[0];
        int largestSum = nums[0];
        for(int i=1; i<nums.length ; i++){
            currentWindowSum = Math.max(currentWindowSum+nums[i],nums[i]);
            largestSum = Math.max(largestSum,currentWindowSum);
        }
        return largestSum;
    }
}
