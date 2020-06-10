package array;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class CircularArrayLoop457 {
	
	/*
	//<Trial01>
	
	//[2,-1,1,-2,-2]
	
	//여기에서 막힘.
	
	//0->2->3->1->0 반복인데,
	
	//문제에서 의도하는게, 오른쪽으로만 가면 무조건 양수만 나와야 되고, 왼쪽으로 가면 무조건 음수만 나와야 된다는 건가? 그래서
	
	//boolean pos = nums[0] > 0 ? true : false; 이거랑
	//if(pos != nums[i] > 0) return false; 이거
	
	//추가했는데, 
	
	//[3,1,2] 여기서 막힘.
	
	//true가 나와야 된다네? 근데 0->0->0->0이잖아
	
	//Input: [-1,2]
	//Output: false
	//Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.
	
	//문제에서 사이클 사이즈가 하나면 return false하라매?
	
	//1부터 시작해도 1->2->1->2 ...이잖아
	
	//Input: [-2,1,-1,-2,-2]
	//Output: false
	//Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.
	
	//이것도 1->2->1->2->... 안된데매
	
	//아
	
	//[3,1,2] 에서 1->2는 loop사이클의 사이즈가  2니까 okay고
	
	//[-2,1,-1,-2,-2]에서 1->2는 loop사이클의 사이즈가 2니까 되긴 하는데, 중간에 뒤로가는게 있어서 안된다는말이지?
	
	public boolean circularArrayLoop(int[] nums) {
        if(nums.length == 0) return false;
        Set<Integer> s = new HashSet<>();
        boolean pos = nums[0] > 0 ? true : false;

        for(int i = 0, j = 0; ; ){
            if(!s.add(i)){
                if(i == j || i+nums[i] == j) return false; //한곳에서 반복되거나(ex. [-1,2]), 두곳에서 반복되는 경우(ex. [-2,1,-1,-2,-2] 1->2->1->2->...) 예외처리
                if(s.size() > 1){
                    return true;
                } else {
                    return false;
                }
            }
            
            j = i;
            
            //i가 다음으로 넘어갈 때, nums의 범위에서 벗어날 경우에 대한 예외처리
            if(i+nums[i] >= nums.length){
                i = (i+nums[i])-(nums.length);
            } else if(i+nums[i] < 0){
                i = nums.length + (i+nums[i]);
            } else {
                i += nums[i];
            }
            if(pos != (nums[i] > 0)) return false;
        }
    }
	*/
	
	//<문제풀이1>
	
	//brute-force
	
	//Runtime: 80 ms, faster than 7.96% of Java online submissions for Circular Array Loop.
	//Memory Usage: 39.5 MB, less than 10.77% of Java online submissions for Circular Array Loop.
	
    public boolean circularArrayLoop(int[] nums) {
        int idx = 0;
        while(idx < nums.length){
            Set<Integer> s = new HashSet<>();
            boolean pos = nums[idx] > 0 ? true : false;
            
            for(int i = idx, j = 0, n = nums.length; ; ){
                if(!s.add(i)){
                    if(i == j) break; //x->x->x-> ...방지
                    return true;
                }
                j = i;
                
                i = (n+ (i+nums[i]) % n) % n; //i+nums[i]가 nums의 사이즈를 초과하든 마이너스 값이던, nums.length으로 나눈 나머지 양수값이 나옴. 
                
                if(pos != (nums[i] > 0)) break; //부호가 다른지 확인. 마이너스면 계속 마이너스여야 하고, 플러스면 계속 플러스인지 확인
            }
            idx++;
        }
        return false;
    }
}
