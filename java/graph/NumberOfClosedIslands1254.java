package graph;

public class NumberOfClosedIslands1254 {

	//<문제풀이1>
	
	//dfs
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Number of Closed Islands.
	//Memory Usage: 39.4 MB, less than 6.63% of Java online submissions for Number of Closed Islands.
	
    private void dfs(int[][] grid, int i, int j){
        if(i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == 1) return;
        grid[i][j] = 1;
        dfs(grid, i+1, j);
        dfs(grid, i, j+1);
        dfs(grid, i-1, j);
        dfs(grid, i, j-1);
    }
    
    public int closedIsland(int[][] grid) {
        int rst = 0;
        int m = grid.length;
        int n = grid[0].length;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0 && (i == 0 || j == 0 || i == m-1 || j == n-1)){
                    dfs(grid,i,j);
                }
            }
        }
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    dfs(grid,i,j);
                    rst++;
                }
            }
        }
        
        return rst;
    }
}
