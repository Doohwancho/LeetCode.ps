package mayChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContiguousArrayMayChallenge {
	
	//<문제풀이1>
	
	//Runtime: 42 ms
	//Memory Usage: 49.4 MB
	
	public static int findMaxLength(int[] nums) {
		int rst = 0;
		int[] sum = new int[nums.length];
		Map<Integer, List<Integer>> map = new HashMap<>();
		
		List<Integer> temp = new ArrayList<>(); //0,-1을 미리 더해줘야, [0,1,0,1,0,1]같은 케이스 pass가능. [0,0,0,0,0,0,0,1,1,1]같이 뭉쳐있는곳엔 필요없음.
        temp.add(-1);
        map.put(0, temp);
        
        for(int i = 0; i < nums.length; i++) {
        	if(nums[i] == 0) nums[i] = -1;
        	sum[i] = (i > 0 ? sum[i-1] : 0) + nums[i];
        	if(map.containsKey(sum[i])) {
        		List<Integer> tmp = map.get(sum[i]);
        		tmp.add(i);
        		map.put(sum[i], tmp);
        	} else {
        		List<Integer> tmp = new ArrayList<>();
        		tmp.add(i);
        		map.put(sum[i], tmp);
        	}
        }
        
        for(Map.Entry<Integer, List<Integer>> m : map.entrySet()) {
        	rst = Math.max(rst, Math.abs(m.getValue().get(m.getValue().size()-1) - m.getValue().get(0))); //젤 마지막에 등장했던 인덱스와 젤 첨 등장했던 인덱스의 차이
        }
		
		return rst;
    }

	
	
	//<문제풀이2 by shawngao>
	
	//문제풀이 1에서는 같은 sum이 등장한 인덱스를 모두 arraylist에 담아놨다가, 젤 마지막꺼랑 젤 처음꺼를 빼는 식으로 했는데
	
	//최장길이 구하는거니까 어짜피 중간것들은 필요없잖아?
	
	//그래서 Math.max()로 최대치 매 loop마다 업데이트 시키고 list에 안담는 방법
	
	//22ms더 빠름
	
	//Runtime: 20 ms
	//Memory Usage: 49.2 MB
	
    public int findMaxLength(int[] nums) {
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
}
