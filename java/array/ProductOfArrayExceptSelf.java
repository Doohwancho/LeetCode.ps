package thirtyDayChallenge;

public class ProductOfArrayExceptSelf {
	
	/*
	//<Trial01>
	
	//Note: Please solve it without division and in O(n).
	
	//제기랄?

	//18 / 18 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 47 MB
	public int[] productExceptSelf(int[] nums) {
        int zeroes = 0;
        int total = 1;
        for(int n : nums){
            if(n==0){
                zeroes++; //0갯수 몇개인지 세. 0갯수에 따라서 이 array의 생사가 갈리니까.
            }else{
                total *= n; //0이 아니면 다 곱해
            }
        }
        if(zeroes > 1){  //0이 2개라고 치자? [0,2,0,4,5] 그럼 결국 0 자신을 제외한 다른곳에 0이 존재하기 때문에 어떤수를 곱해도 0이겠지? 그럼 다 0을 넣으면 돼.
            for(int i = 0; i < nums.length; i++){
                nums[i] = 0;
            }
        } else if(zeroes == 1){ //0이 한개밖에 없다고 치자? [1,2,0,4,5] 이건 0의 자리엔 나머지 다 곱한 total이 들어가고, 나머지 자리엔 다 0이겠지? 
            for(int i = 0; i < nums.length; i++){
                if(nums[i]==0){
                    nums[i] = total;
                } else {
                    nums[i] = 0;
                }
            }
        } else{ //0이 아예 없다고 치자? 그럼 [1,2,3]에서 total이 6이겠지? 그리고 6은 1x2x3이라서, 임의의 수 2라고 치면, 6/2하면, 1x3만 남잖아. 나머지 수들의 곱. 그래 그거여
            for(int i = 0; i < nums.length; i++){
                nums[i] = total / nums[i];
            }
        }
        return nums;
    }
    */
	
	//<문제풀이1 by xcv58>
	
	//{2,3,4,5}넣었다고 치면
	
	//첫 for문의 결과가 이렇게 나옴.
	
	//{1 2 6 24}
	
	//맨 처음은 1이고, 나머지는 i-1번째까지 곱한 것.
	
	//그러니까 {1, index0, index0 * index1, index0 * index1 * index2}
	
	//근데 두번째 for문, 역순으로 돌땐, 맨 마지막부터 숫자를 하나씩 tmp에 누적으로 쌓으면서 곱해감.
	
	//1 2 6 24 
	//1 2 30 24 
	//1 40 30 24 
	//60 40 30 24 
	
	// {1, index0, index0 * index1, index0 * index1 * index2} 이거였잖아?
	
	//index0 * index1 * index2 요부분. 첫번째. i가 nums.length-1일땐 1만 곱함. tmp가 1이잖아.
	
	//근데 tmp가 nums에 맨 마지막 숫자부터 누적으로 쌓인다그랬지?
	
	//tmp가 1*index3됨. 그리고 다음인  index0 * index1으로 넘어가면, 
	
	// index0 * index1 * index3이 됨. 2번째만 빼고 다 곱한거지.
	
	//그러면 tmp는 index2 * index3이 됨. 누적으로 쌓인다그랬잖아?
	
	//그럼 다음거인 index0을 돌땐, index0 * index2 * index3 이 됨. 1번째만 빼고 다곱한거지. 
	
	//이런식이야.

	//18 / 18 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 47.9 MB
	public static int[] productExceptSelf(int[] nums) {
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
	
	public static void main(String[] args) {
		int[] test = {2,3,4,5};
		System.out.println(productExceptSelf(test));
	}
}
