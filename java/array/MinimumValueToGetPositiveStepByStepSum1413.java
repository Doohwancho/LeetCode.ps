package array;

public class MinimumValueToGetPositiveStepByStepSum1413 {

	//<문제풀이1>
	
	//만약 누적으로 더했을때 중간에 -가 한번이라도 튀어나오면, 그 값보다 최소 1더 큰 값이 필요하겠지?
	
	//그래서 Math.min()으로 rst에 가장 작은 누적숫자를 구해줘
	
	//rst가 마이너스 값이면, 절대값 씌우고 +1해서 반환하면 되구
	
	//만약 양수면, 문제에서 최소 starvalue달라고 했으니까, 1주면 돼.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Value to Get Positive Step by Step Sum.
	//Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Minimum Value to Get Positive Step by Step Sum.
    public int minStartValue(int[] nums) {
        int rst = nums[0];
        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
            rst = Math.min(rst, nums[i]);
        }
        return rst < 0 ? Math.abs(rst)+1 : 1;
    }
}
