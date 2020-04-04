package array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualToK560 {
	/*
	//<Trial01 - time limit exceeded>
	
	//brute-force
	
	public int subarraySum(int[] nums, int k) {
        int rst = 0;
        for(int i = 0; i < nums.length; i++){ //i < nums.length-1안하고 i < nums.length한 이유는, 마지막 element하나도 카운트 해야하기 때문
            for(int j = i+1; j < nums.length+1; j++){ //j < nums.length+1한 이유도 마지막 element하나 카운트 하기 위해. 걍 j < nums.length하면 j-for문 밑에 애들이 스킵됨.
                int sum = 0;
                for(int p = i; p < j; p++){
                    sum += nums[p];
                }
                if(sum == k){
                    rst++;
                }
            }
        }
        return rst;
    }
	*/
	/*
	//<문제풀이1 by Leetcode Solution>
	
	//누적 합을 더해주고, 2중for문으로 여태껏 누적합중에 빼서 k인 숫자가 나오면 +1해주는 방식
	
	//[-1,1,-2,2,3] 인데, k = 3이라고 하면,
	
	//누적합은 [0,-1,0,-2,0,3]이 되고,
	
	//0번째, 2번째, 4번째 총 3번의 합이 k가 되는 식.
	
	//여기서 0번째는 nums[i]본인을 뜻함. 애초에 container[0]은 0이라 nums[i]+0=nums[i]이기 때문
	
	//Runtime: 205 ms, faster than 25.24% of Java online submissions for Subarray Sum Equals K.
	//Memory Usage: 41.3 MB, less than 5.43% of Java online submissions for Subarray Sum Equals K.
	public int subarraySum(int[] nums, int k) {
        int rst = 0;
        
        int[] container = new int[nums.length+1];
        for(int i = 1; i < container.length; i++){
            container[i] = container[i-1] + nums[i-1];
        }
        
        for(int i = 1; i < container.length; i++){
            for(int j = 0; j < i; j++){
                if(container[i]-container[j] == k){
                    rst++;
                }
            }
        }
        return rst;
    }
	*/
	
	//<문제풀이2 by shawngao>
	
	//누적합을 map.key에 계속 더해주면서(예전에 같은 누적합이 있었다면, 해당 누적합의 갯수+1만 해줌),
	
	//누적합-k가 map에 있는지 확인.
	
	//있으면 몇개 쌓여있는지 보고, 그 갯수만큼 rst에 +해줌
	
	//Runtime: 11 ms, faster than 95.95% of Java online submissions for Subarray Sum Equals K.
	//Memory Usage: 40.4 MB, less than 33.69% of Java online submissions for Subarray Sum Equals K.
    public int subarraySum(int[] nums, int k) {
        int rst = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum-k)){
                rst += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return rst;
    }
}
