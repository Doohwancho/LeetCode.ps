package dynamicProgramming;

import java.util.Arrays;

public class MinimumFallingPathSum931 {
	
	
	//<문제풀이1>
	
	//성능은 좋은데 뭔가 난잡해서 마음에 안듬.
	
	//Runtime: 1 ms, faster than 99.07% of Java online submissions for Minimum Falling Path Sum.
	//Memory Usage: 39.8 MB, less than 42.89% of Java online submissions for Minimum Falling Path Sum.

    int[][] A;
    int[][] mem;
    int constant = 987654321;
    
    private int dp(int a, int b){
        if(b < 0 || b >= A[0].length || a >= A.length) return 0;
        if(mem[a][b] != constant) return mem[a][b];
        for(int i = b; i < A[0].length; i++){
            int l = constant;
            int r = constant;
            if(i-1 >= 0){
                l = dp(a+1,i-1);
            }
            if(i+1 < A[0].length){
                r = dp(a+1, i+1);
            }
            mem[a][i] = Math.min(mem[a][i], A[a][i] + Math.min(dp(a+1,i), Math.min(l, r)));
        }
        return mem[a][b];
    }
    
    public int minFallingPathSum(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        this.A = A;
        mem = new int[m][n];
        for(int[] row : mem){
            Arrays.fill(row, constant);
        }
        dp(0,0);
        int rst = Integer.MAX_VALUE;
        for(int element : mem[0]){
            rst = Math.min(rst, element);
        }
        return rst;
    }
}
