package thirtyDayChallenge;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsToK {
	
	//<문제풀이1 by shawngao>
	
    //80 / 80 test cases passed.
	//Status: Accepted
	//Runtime: 12 ms
	//Memory Usage: 40.3 MB
    public int subarraySum(int[] nums, int k) { //
    	int sum = 0;
    	int rst = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1); //k가 7인데 nums[i]가 7일수도 있잖아!
        
        for(int n : nums){
        	sum += n;
            if(map.containsKey(sum-k)){ 
            	//왜 k-n이 아니라 sum-k일까? 
            	//sum[i,j] = k라면, sum[0,i-1]이랑 sum[0,j]까지만 알면 되겠네?
            	//sum[0,j] - sum[0,i-1] = sum[i,j] = k이니까.
            	
            	//예를들어 1,2,3,4,3,2,1, k = 10, index가 5번째일 때(4 오른쪽 3을 가르킬 때),
            	//sum = 13, sum(13)-k(10) = 3이 map에 있으므로 rst+1하는데,
            	//여기서 k는 subarray안에 뭐가들었든(3,4,3) 총 합을 뜻하고, 
            	//sum-k는 subarray에 포함되지 않는 왼쪽 숫자들의 총 합(1,2)을 뜻함.
            	
            	rst += map.get(sum-k);
            	//왜 rst++; 안하고 ,rst+= map.get(sum-k)함?
            	//Input: [0,0,0,0,0,0,0,0,0,0], k = 0
            	//의 경우, 1+2+3+...+8+9+10 더해서 55가 되야 하자너
            	//누적으로 더해주는겨
            	//0,0,0,1,4,2...
            	//일때, 3번째 0을 카운트 해야한다면, 이전에 0들도 카운트 해줘야 하자너
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
            //왜 그냥 map.put(sum-k, 1) 안하고 누적으로 +1해줌?
            //만약 마이너스 값이 있다고 치자.
            //int[] sum = {1,3,....7,3,4}
            //인데, 3을 카운트 해줘야 하면, 왼쪽에 있는거 하나도 누적합 3, 오른쪽에 있는 3도 누적합 3이니까, 같은걸로 치잖아
            //그러니까 이전에 같은 수 나왔으면 +1해줘야지
        }
        return rst; 
        
    }
}
