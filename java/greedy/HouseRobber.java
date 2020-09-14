package september;

public class HouseRobber {
	
	//<문제풀이1 by jianchao-li>
	
	//pre는 i-2까지 최댓값
	
	//cur은 i-1까지 최댓값
	
	//temp는 (i-2 + i)랑 (i-1)을 비교한 최댓값
	
	//temp가 새롭게 갱신되면, 다음번째로 넘어가야 하니까, 

	//pre = cur; cur = temp;해서 뒤로 한칸씩 밀어줌
	
	//Runtime: 0 ms
	//Memory Usage: 37.1 MB
	
    public int rob(int[] nums) {
        int pre = 0, cur = 0;
        for (int num : nums) {
            final int temp = Integer.max(pre + num, cur);
            pre = cur;
            cur = temp;
        }
        return cur;
    }
}
