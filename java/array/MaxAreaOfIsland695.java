package Array;

public class MaxAreaOfIsland695 {
	
	
	//<문제풀이1>
	
	//dfs
	
	//Runtime: 2 ms, faster than 98.78% of Java online submissions for Max Area of Island.
	//Memory Usage: 39.7 MB, less than 96.30% of Java online submissions for Max Area of Island.
	
	private static int dfs(int[][] grid, int i, int j){
        int links = 1;
        
        //move down
        if(i < grid.length-1 && grid[i+1][j] == 1){
            grid[i][j] = 0;
            links += dfs(grid, i+1, j);
        }
        //move up
        if(i > 0 && grid[i-1][j] == 1){
            grid[i][j] = 0;
            links += dfs(grid, i-1, j);
        }
        //move right
        if(j < grid[0].length-1 && grid[i][j+1] == 1){
            grid[i][j] = 0;
            links += dfs(grid, i, j+1);
        }
        //move left
        if(j > 0 && grid[i][j-1] == 1){ 
            grid[i][j] = 0;
            links += dfs(grid, i, j-1);
        }
        //last area
        grid[i][j] = 0;
        
        return links;
    }
    
    public static int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }
    
    
    public static void main(String[] args) {
		int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
		System.out.println(maxAreaOfIsland(grid));
	}
}
