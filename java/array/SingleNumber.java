/*
	Given a non-empty array of integers, every element appears twice except for one. Find that single one.
	
	Note:
	
	Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	
	Example 1:
	
	Input: [2,2,1]
	Output: 1
	Example 2:
	
	Input: [4,1,2,1,2]
	Output: 4
	
	
	
	<문제>
	
	리스트 안에는 숫자들이 있는데, 그중 한 숫자만 딱 한번 등장하고, 나머지 숫자들은 두번씩 등장한다고 할 때,
	
	한번만 등장하는 애를 뽑아라.
 */

package thirtyDayChallenge;

import java.util.Arrays;

public class SingleNumber {
	
	/*
	//<문제풀이1>
	
	//먼저 정렬해 주면 
	
	//[3,1,2,3,2] 이게 [1,2,2,3,3] 으로 된다.
	
	//인덱스 0부터 시작해서 2씩 증가해 가면서, if(nums[i]!=nums[i+1]) return nums[i]로 unique한 숫자를 뽑을 수 있다.
	
	//근데 만약에 [1,1,2,2,5]처럼 멘 뒤에 있는 경우엔, if(i == nums.length-1)로 예외처리 해준다.
	
	//return nums[0]은 list size가 1일때인 경우 처리.
	
	//Runtime: 3 ms
	//Memory Usage: 40.7 MB
    public int singleNumber(int[] nums) {
    	Arrays.sort(nums);
        for(int i = 0; i < nums.length; i+=2){
            if(i == nums.length-1) return nums[nums.length-1];
            if(nums[i]!=nums[i+1]) return nums[i];
        }
        return nums[0];
    }
    */
	
    //<문제풀이2>
    
    //지려버렸따
	
	//xor연산은 bitmask연산 중 하나로, 지랑 같은거 나오면 false, 다른거 나오면 true뜸.
	
	//예를들어, 
	
	//true ^ true = false
	
	//true ^ false = true
	
	//1010 ^ 1010 = 0000
	
	//1010 ^ 1010 ^ 1010 = 1010
	
	//모든 수를 xor연산하면, duplicate나올 때 다시 0이 되므로, unique한 수를 뽑을 수 있음
    
    //Runtime: 0 ms
    //Memory Usage: 41 MB
    public int singleNumber(int[] nums) {
        int xor = 0;
        for(int n : nums) xor ^= n;
        return xor;
    }
}
