package graph;

public class NumberOfEnclaves1020 {
	
	//<문제풀이1>
	
	//dfs
	
	//Runtime: 4 ms, faster than 75.36% of Java online submissions for Number of Enclaves.
	//Memory Usage: 47.4 MB, less than 7.31% of Java online submissions for Number of Enclaves.
	
	private void dfs(int[][] A, int i, int j){
        if(i < 0 || j < 0 || i == A.length || j == A[0].length || A[i][j] == 0) return;
        A[i][j] = 0;
        dfs(A, i-1, j);
        dfs(A, i, j-1);
        dfs(A, i+1, j);
        dfs(A, i, j+1);
    }
	
    public int numEnclaves(int[][] A) {
        //1. mark edges 1's to '0' including inner lands that are connected to edges
        int rst = 0;
        int m = A.length;
        int n = A[0].length;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || j == 0 || i == m-1 || j == n-1){
                    dfs(A, i, j);
                }
            }
        }
        
        //2. if A[i][j] == 1, rst+1
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(A[i][j] == 1){
                    rst++;
                }
            }
        }
        return rst;
    }
}
