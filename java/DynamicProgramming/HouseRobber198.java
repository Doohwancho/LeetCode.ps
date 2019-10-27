/*
	You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
	
	Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
	
	Example 1:
	
	Input: [1,2,3,1]
	Output: 4
	Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
	             Total amount you can rob = 1 + 3 = 4.
	Example 2:
	
	Input: [2,7,9,3,1]
	Output: 12
	Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
	             Total amount you can rob = 2 + 9 + 1 = 12.
	             
	             
	             
	
	
	<문제>
	
	넌 프로 도둑이다.
	
	[2,7,9,3,1]가 주어지는데, 이는 각 집마다 있는 돈을 뜻한다.
	
	넌 돈을 최대한 많이 훔치고 싶다.
	
	그런데 바로 2연속으로 털어가면 경찰한테 걸리기 때문에 무조건 한집 털면 최소 한번은 쉬어주어야 한다.
	
	이 때, 너가 털수있는 최대금액을 반환하라.
 
 */

package DynamicProgramming;

public class HouseRobber198 {
	/*
	//<Trial01>
	
	//개어거지로라도 하려고 했는데 모르겠다...
	
	public static int rob(int[] nums) {
		if(nums.length == 0) return 0;
        else if(nums.length == 1) return nums[0];
        else if(nums.length == 2){
            if(nums[0]>nums[1]) return nums[0];
            else return nums[1];
        }
        int rst = 0;
		int lastIdx = 0;
		
		for(int i = 1; i < nums.length-1;) {
			if(nums[i] > nums[i-1]+nums[i+1]) {
				rst += nums[i];
				i+=3;
			}
			else {
				rst += nums[i-1]+nums[i+1];
				i+=4;
			}
			lastIdx = i;
		}
		if(nums.length-1-lastIdx == 0) {
			if(nums[nums.length-1] > nums[nums.length-2]) rst += nums[nums.length-1];
			else rst += nums[nums.length-2];
		}
		else if(nums.length-1-lastIdx == 1 || nums.length-1-lastIdx == -1) rst += nums[nums.length-1];
		return rst;
		
    }
	*/
	
	//<문제풀이1 by jianchao-li>
	
	//pre는 i-2까지의 최대 누적합계
	//cur은 i-1까지의 최대 누적합계
	//매 loop마다 cur에서 pre로 값이 옮겨가고, 현재까지 회대합계가 cur로 갱신됨.
	
	//천잰가
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for House Robber.
	//Memory Usage: 34 MB, less than 100.00% of Java online submissions for House Robber.
	
	public static int rob(int[] nums) {
        int pre = 0, cur = 0;
        for (int num : nums) {
            final int temp = Integer.max(pre + num, cur);
            pre = cur;
            cur = temp;
        }
        return cur;
    }
	
	
	public static void main(String[] args) {
		int[] nums = {1,2,1,1};
		//		int[] nums = {2,7,9};
		//		int[] nums = {2,7,9,3,1};
//		int[] nums = {2,7,9,3,1,4};
		System.out.println(rob(nums));
	}
}
