package array;

import java.util.Map;
import java.util.TreeMap;

public class DivideArrayInSetsOfKConsecutiveNumbers1296 {
	
	/*
	//<Trial01 - Memory Limit Exceeded>
 
	//인풋 에바참치자너
	
    public boolean isPossibleDivide(int[] nums, int k) {
        if(nums.length % k != 0) return false;
        Arrays.sort(nums);
        
        int[] container = new int[nums[nums.length-1]+1];
        
        for(int ele : nums){
            container[ele]++;
        }
        
        for(int prev = -1, len = nums.length; len > 0; len -= k, prev = -1){
            for(int idx = 0, limit = 0; idx < container.length; idx++){
                if(container[idx] > 0){
                    if(prev == -1){
                        prev = idx;
                        container[idx]--;
                        limit++;
                    } else {
                        if(idx == prev+1){
                            prev = idx;
                            container[idx]--;
                            limit++;
                        }
                        else if(prev == idx){
                            continue;
                        } else { 
                            return false;
                        }
                    }
                } 
                if(limit == k){
                    break;
                }
            }
        }
        return true;
    }
    */
	
	//<문제풀이1 by lee215>
	
	//Runtime: 191 ms, faster than 16.75% of Java online submissions for Divide Array in Sets of K Consecutive Numbers.
	//Memory Usage: 55.1 MB, less than 100.00% of Java online submissions for Divide Array in Sets of K Consecutive Numbers.
    public boolean isPossibleDivide(int[] A, int k) {
        Map<Integer, Integer> c = new TreeMap<>(); //Arrays.sort()를 treemap으로 대체
        for (int i : A) c.put(i, c.getOrDefault(i, 0)+1); //빈도수 카운트
        for (int it : c.keySet())
            if (c.get(it) > 0)
                for (int i = k - 1; i >= 0; --i) {
                    if (c.getOrDefault(it + i, 0) < c.get(it)) return false; //빈도수가 1,1,1,1,2같은건 되는데 2,1,1,1,1은 5개 날리면 맨 앞에 1만 덩그러니 남아서 false반환이잖아. 그래서 맨 앞에꺼가 맨 뒤엣것보다 무조건 같거나 작아야 하지.
                    c.put(it + i, c.get(it + i) - c.get(it)); //만약에 3,3,3,3,5면 같은거 3번 돌아야 되잖아. 그럼 야예 맨 처음에 나온거 3씩만 빼주면 0,0,0,0,2가되서 iteration 줄이고 바로 다음으로 넘어갈 수 있지
                }
        return true;
    }
	
	
	
}
