package Array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllDuplicatesInAnArray442 {
	
	/*
	//<문제풀이1>
	
	//set에 중복되는 값이 안들어가는 점을 이용한 풀이.
	
	//근데 개느림...하
	
	//Runtime: 19 ms, faster than 29.32% of Java online submissions for Find All Duplicates in an Array.
	//Memory Usage: 52.3 MB, less than 30.56% of Java online submissions for Find All Duplicates in an Array.
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        Set<Integer> filter = new HashSet<>();
        for(int i : nums){
            if(!filter.add(i)){
                rst.add(i);
            }
        }
        return rst;
    }
    */
	/*
	//<문제풀이2>
	
	//1 ≤ a[i] ≤ n (n = size of array) 라고 문제에서 그랬으니까,
	 
	//길이가 10인 리스트면, 안에 있는 값들의 최대값이 10이 최대.
	  
	//그러면 사이즈가 n인 리스트를 만들어서, 갯수파악해주면 됨. 
	  
	//문제는, 문제에서 Could you do it without extra space and in O(n) runtime?
	
	//라고 함. O(n)은 됬는데 without extra space가 안됨.
	
	//Runtime: 4 ms, faster than 100.00% of Java online submissions for Find All Duplicates in an Array.
	//Memory Usage: 46.8 MB, less than 100.00% of Java online submissions for Find All Duplicates in an Array.
	
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> rst = new ArrayList<>();
        int[] container = new int[nums.length+1];
        for(int i : nums){
            if(container[i]++ > 0){
                rst.add(i);
            }
        }
        return rst;
    }
    */
	
	//<문제풀이 by YuxinCao>
	
	//without extra space를 이리 푸네
	
	//똑똑허이
	
	//for문돌면서 값 x를 인덱스 삼아 nums[x]해서 나온 값을 음수로 바꿔버림.
	
	//그럼 다음에 똑같은 숫자 x가 또나왔으면, nums[x]는 이미 음수겠지? 
	
	//이 경우 if문에 걸려서 x가 res에 더해지게 됨.
	
	//절대값을 쓰는 이유는, for문이 중반부에 가면 초반부에 몇개 음수로 바꿔둔 것 때문에, -x를 x로 바꿔서 계산해야 하기 때문.

    // when find a number i, flip the number at position i-1 to negative. 
    // if the number at position i-1 is already negative, i is the number that occurs twice.
	
	//Runtime: 6 ms, faster than 91.50% of Java online submissions for Find All Duplicates in an Array.
	//Memory Usage: 47.2 MB, less than 94.44% of Java online submissions for Find All Duplicates in an Array.
	
    
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0) {
            	res.add(Math.abs(index+1));
            }
            nums[index] = -nums[index];
        }
        return res;
    }
}
