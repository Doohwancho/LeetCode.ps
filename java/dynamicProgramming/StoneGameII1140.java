package dynamicProgramming;

public class StoneGameII1140 {

	
	//<Trial01>
	
	//음... 모르것다
	
    int[][] mem;
    int[] piles;
    
    private int dp(int idx, int id, int M, int n){
        if(idx >= mem.length) return 0;
        if(mem[idx][id] != 0) return mem[idx][id];
        for(int i = idx, acc = 0; i < Math.min(n, idx+M); i++){
            acc += piles[i];
            mem[idx][id] = Math.max(mem[idx][id], acc + dp(i+1, id^1, 2*(i+1), n));  
        }
        return mem[idx][id];
    }
    
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        mem = new int[n][2];
        this.piles = piles;
        return dp(0, 1, 2, n);
    }
    
    
    //<문제풀이1 by Seaview>
    
    //0~i까지 m만큼 가져간 것 + (i+1 부터 나머지의 총합 - 상대방이 최적으로 take한 것)
    
    //Runtime: 3 ms, faster than 63.20% of Java online submissions for Stone Game II.
    //Memory Usage: 38.1 MB, less than 69.96% of Java online submissions for Stone Game II.
    
    public int stoneGameII(int[] piles) {
        int[] presum =  Arrays.copyOf(piles, piles.length);
        for (int i = presum.length - 2; i >= 0; i--) presum[i] += presum[i + 1];
        return dfs(presum, 1, 0, new int[piles.length][piles.length]);
    }
    private int dfs(int[] presum, int m, int p, int[][] memo) {
        if (p + 2 * m >= presum.length) { // last player takes all
            return presum[p];
        }

        if (memo[p][m] > 0) return memo[p][m];
        int res = 0, take = 0;
        for (int i = 1; i <= 2 * m; i++) {
            // current take
            take = presum[p] - presum[p + i];
            // take max of current + what lefts from other player max take
            res = Math.max(res, take + presum[p + i] - dfs(presum, Math.max(i, m), p + i, memo));
        }
        memo[p][m] = res;
        return res;
    }
}
