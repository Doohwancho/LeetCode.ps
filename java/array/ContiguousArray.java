package thirtyDayChallenge;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {

	/*
	//<Trial01>
	
	// find the maximum length of a contiguous subarray with equal number of 0 and 1.
	
	//이라길래, 000111이나 00001111, 111000 같이 쪼로록 붙어있는것만 되는줄 알았는데
	
	//010101도 되고 100011도 되는거였네. 걍 1이랑 0숫자만 같으면 장땡이네
	
    public int findMaxLength(int[] nums) {
        Stack st = new Stack();
        int rst = 0;
        
        for(int i = 0, j = 0;i < nums.length; i++){
            if(st.isEmpty()){
                st.push(nums[i]);
            } else{
                if((int)st.peek()!=nums[i]){
                    j+=2;
                    st.pop();
                } else {
                    st.push(nums[i]);
                    j = 0;
                }
            }
            rst = Math.max(rst, j);
        }
        return rst;
    }
    */
	
	//<문제풀이 by shawngao>
	
	//0을 -1로 바꿔. 그럼 -1나오고 1나오면 +-제로니까 계산하기 더 편혀
	
	//{0,0,1,0,0,1,1}; 이걸 testcase로 넣으면
	
	//sumToIndex: {0=-1, -1=0} sum:  -1   max: 0 
	//sumToIndex: {0=-1, -1=0, -2=1} sum:  -2   max: 0 
	//sumToIndex: {0=-1, -1=0, -2=1} sum:  -1   max: 2 
	//sumToIndex: {0=-1, -1=0, -2=1} sum:  -2   max: 2 
	//sumToIndex: {0=-1, -1=0, -2=1, -3=4} sum:  -3   max: 2 
	//sumToIndex: {0=-1, -1=0, -2=1, -3=4} sum:  -2   max: 4 
	//sumToIndex: {0=-1, -1=0, -2=1, -3=4} sum:  -1   max: 6 
	
	//매 iterate마다 map과 max가 이런식으로 나옴.
	
	//0이 나오면 sum이 하나씩 깎이고 1이나오면 다시 +1됨. 
	
	//sum이 오르락 내리락 할 때, 이전에 한번 나왔다면, 0과 1이 페어가 됬다는 말이므로, max를 업데이트 시켜줌 
	
	//현제 for loop이 가르키는 인덱스 i와 맨 처음 똑같은 sum이 나왔던 곳의 차로.
	
	//맨 처음이어야 하는 이유는 'maximum' contiguous subarray여야 하니깐.
	

	//555 / 555 test cases passed.
	//Status: Accepted
	//Runtime: 19 ms
	//Memory Usage: 49.4 MB
	
    public static int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) nums[i] = -1;
        }
        
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int sum = 0, max = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumToIndex.containsKey(sum)) {
                max = Math.max(max, i - sumToIndex.get(sum));
            }
            else {
                sumToIndex.put(sum, i);
            }
        }
        
        return max;
    }
    
    public static void main(String[] args) {
		int[] test = {0,0,1,0,0,1,1};
		System.out.println(findMaxLength(test));
	}
	
	
}
