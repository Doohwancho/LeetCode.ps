package Array;

public class ProductOfArrayExceptSelf238 {
	
	/*
	//<Trial01>
	
	//brute-force
	
	//Time limit exceeded
	
	//Note: Please solve it without division and in O(n).
	
	//not O(n)
	
    public int[] productExceptSelf(int[] nums) {
        int[] rst = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            int tmp = 1;
            for(int j = 0; j < nums.length; j++){
                if(j != i){
                    tmp *= nums[j];
                }
            }
            rst[i] = tmp;
        }
        return rst;
    }
    */
    
	/*
    //<Trial02>
	
	//Note: Please solve it without division and in O(n).
	
	//O(n)이긴 한데, division써서 탈락.
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
	//Memory Usage: 41.5 MB, less than 100.00% of Java online submissions for Product of Array Except Self.
    
	public int[] productExceptSelf(int[] nums) {
        int[] rst = new int[nums.length];
        int num = 0;
        int zero = 0;
        
        for(int i = 0; i < nums.length; i++){ //0을 제외한 총 곱인 num과 0의 갯수인 zero를 구함
            if(nums[i] != 0){
                if(num == 0){
                    num = nums[i];
                } else {
                    num *= nums[i];
                }
            } else {
                zero++;
            }
        }
        
        for(int j = 0; j < nums.length; j++){
            if(zero > 1){  //0의 갯수가 2개면, nums[i]를 제외한 모든 숫자를 어떻게든 곱해도 0이기 때문에, 모든 숫자를 0으로 함.
                rst[j] = 0;
            }
            else if(zero == 1){ //0의 갯수가 1개면, nums[i]가 0인건 나머지 곱한 것, 0이 아닌건 0으로 넣어줌.
                if(nums[j] != 0){
                    rst[j] = 0;
                }
                else{
                    rst[j] = num;
                }
            }
            else{
                rst[j] = num/nums[j]; //0의 갯수가 하나도 없으면, 정상적으로 총곱/nums[i]를 해줌
            }
        }
        return rst;
    }
    */
	
	//<문제풀이1 by xcv58>
	
	//Note: Please solve it without division and in O(n).
	
	//두 조건 모두 충족.
	
	//Your input : [1,2,3,4]
	//stdout : 1 1 2 6 
	//Output : [24,12,8,6]
	//Expected :[24,12,8,6]

	//result에 [1,1,2,6]을 먼저 만들고, 
	
	//result = [1, 1*nums[0], 1*nums[0]*nums[1], 1*nums[0]*nums[1]*nums[2]];
	
	//result = [(1)*nums[1]*nums[2]*nums[3], (1*nums[0])*nums[2]*nums[3], (1*nums[0]*nums[1])*nums[3], (1*nums[0]*nums[1]*nums[2])*1
	
	//[24,12,8,6]이됨.
	
	//천재인가
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
	//Memory Usage: 41.3 MB, less than 100.00% of Java online submissions for Product of Array Except Self.

	public int[] productExceptSelf(int[] nums) {
	    int[] result = new int[nums.length];
	    for (int i = 0, tmp = 1; i < nums.length; i++) {
	        result[i] = tmp;
	        tmp *= nums[i];
	    }
	    for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
	        result[i] *= tmp;
	        tmp *= nums[i];
	    }
	    return result;
	}
}
