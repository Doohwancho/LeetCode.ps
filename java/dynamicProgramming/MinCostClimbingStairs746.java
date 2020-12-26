package dynamicProgramming;

public class MinCostClimbingStairs746 {
	
	//<문제풀이1>
	
	//dp
	
	//Runtime: 1 ms, faster than 72.84% of Java online submissions for Min Cost Climbing Stairs.
	//Memory Usage: 38 MB, less than 99.97% of Java online submissions for Min Cost Climbing Stairs.

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] mem = new int[n+1];
        
        for(int i = 0; i < cost.length; i++){
            mem[i] = cost[i];
        }
        
        for(int i = 2; i <= n; i++){
            mem[i] = Math.min(mem[i-1], mem[i-2])+mem[i];
        }
        return mem[n];
    }
}
