package thirtyDayChallenge;

public class JumpGame {
	
	//<문제풀이1>
	
	//75 / 75 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 41.1 MB
	
    public boolean canJump(int[] nums) {
        for(int i = 0, maxJump = 0; i < nums.length; i++){
            maxJump = Math.max(maxJump, nums[i] + i);   //현재 위치로 부터 최대 점프할 수 있는 인덱스 매 loop마다 업데이트
            if(i < nums.length-1 && maxJump == i) return false; //만약 i가 마지막이 아닌데, 최대로 점프할 수 있는 인덱스가 현재 위치인 i라면, 더이상 갈 수 없다는 뜻이므로 return false
        }
        return true; //끝까지 갔으면 return true
    }
}
