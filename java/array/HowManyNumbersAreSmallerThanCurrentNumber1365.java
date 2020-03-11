package array;

import java.util.Arrays;

public class HowManyNumbersAreSmallerThanCurrentNumber1365 {
	
	/*
	//<문제풀이1>
	
	//Runtime: 4 ms, faster than 63.66% of Java online submissions for How Many Numbers Are Smaller Than the Current Number.
	//Memory Usage: 41.4 MB, less than 100.00% of Java online submissions for How Many Numbers Are Smaller Than the Current Number.
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] container = new int[nums.length];
        
        for(int i = 0; i < nums.length; i++){
            container[i] = nums[i];
        }
        
        Arrays.sort(container);
        
        for(int j = 0; j < nums.length; j++){
            for(int k = 0; k < nums.length; k++){
                if(nums[j] == container[k]){
                    nums[j] = k;
                    break;
                }
            }
        }
        return nums;
    }
    */
	
    //<문제풀이2>
    
    //Runtime: 1 ms, faster than 99.67% of Java online submissions for How Many Numbers Are Smaller Than the Current Number.
    //Memory Usage: 41.6 MB, less than 100.00% of Java online submissions for How Many Numbers Are Smaller Than the Current Number.
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] container = new int[101];
        
        for(int num : nums){
            container[num]++;
        }
        
        for(int i = 1; i < 101; i++){ //누적합을 더해주는게 포인트.
            container[i] = container[i] + container[i-1];
        }
        
        for(int i = 0; i < nums.length; i++){
            nums[i] = nums[i] > 0 ? container[nums[i]-1] : 0; //3항 연산자 쓴 이유는 nums[i]가 0일때, container[-1]하면 array out of bound error 뜨기 때문
        }
        
        return nums;
    }
}
