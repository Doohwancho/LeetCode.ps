package julyChallenge;

public class IslandPerimeter {
	
	
	//<Trial01.
	
	//Input:
//	[[0,1,0,0],
//	 [1,1,1,0],
//	 [0,1,0,0],
//	 [1,1,0,0]]

//	Output: 16
	
	//이렇게 서로 한칸씩 붙어있는건 잘 구해지는데,
	
	//[1,1]
	//[1,1]
	
	//같이 두칸이상씩 따닥따닥 붙어있는건 안되네

    private int helper(int[][] grid, int i, int j, boolean flag){
        grid[i][j] = 2;
        
        int four = flag ? 3 : 4;
        int adder = 0;
        
        if(i < grid.length-1 && grid[i+1][j] == 1){
            four--;
            adder += helper(grid, i+1, j, true);
        }
        if(j < grid[0].length-1 && grid[i][j+1] == 1){
            four--;
            adder += helper(grid, i, j+1, true);
        }
        if(i > 0 && grid[i-1][j] == 1){
            four--;
            adder += helper(grid, i-1, j, true);
        }
        if(j > 0 && grid[i][j-1] == 1){
            four--;
            adder += helper(grid, i, j-1, true);
        }
        System.out.println("i: "+i+" j: "+j+" four: "+four+" adder: "+adder);
        return four + adder;
    }
    
    public int islandPerimeter(int[][] grid) {
        int rst = 0;
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    rst = Math.max(rst, helper(grid, i, j, false)); 
                }
            }
        }
        return rst;
    }
    
    
    
    
    //<문제풀이1>
    
    //dfs
    
    //four+adder반환하기 전에, 4방향으로 칸이 체크되어있으면 four을 하나씩 까는 방식으로 바꿔서
    
    //돌아가게는 만들었는데, 썩 맘에드는 방식은 아니네
    
    //time: O(mn) 아닌가? 
    //space: O(mn) 
    
    //Runtime: 11 ms
    //Memory Usage: 58.3 MB
    
    
    private int helper(int[][] grid, int i, int j){
        grid[i][j] = 2;
        
        int four = 4;
        int adder = 0;
        
        if(i < grid.length-1 && grid[i+1][j] == 1){
            adder += helper(grid, i+1, j);
        }
        if(j < grid[0].length-1 && grid[i][j+1] == 1){
            adder += helper(grid, i, j+1);
        }
        if(i > 0 && grid[i-1][j] == 1){
            adder += helper(grid, i-1, j);
        }
        if(j > 0 && grid[i][j-1] == 1){
            adder += helper(grid, i, j-1);
        }
        
        if(i < grid.length-1 && grid[i+1][j] > 0){
            four--;
        }
        if(j < grid[0].length-1 && grid[i][j+1] > 0){
            four--;
        }
        if(i > 0 && grid[i-1][j] > 0){
            four--;
        }
        if(j > 0 && grid[i][j-1] > 0){
            four--;
        }
        
        return four + adder;
    }
    
    public int islandPerimeter(int[][] grid) {
        int rst = 0;
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    rst = Math.max(rst, helper(grid, i, j)); 
                }
            }
        }
        return rst;
    }
    
    
    //<문제풀이2 by diaa>
    
    //얜 안붙어있을때만 +1함
    
    //직관적인 방법
    
    //Runtime: 8 ms
    //Memory Usage: 58.4 MB
    
    public int islandPerimeter(int[][] grid) {
    	if(grid == null || grid.length == 0|| grid[0].length == 0)
    		return 0;
    	int perimeter = 0;
    	int n = grid.length;
    	int m = grid[0].length;
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < m; j++) {
        		if(grid[i][j] == 1) {
        			if(i == 0 || grid[i - 1][j] == 0)
        				perimeter++;
        			if(i == n -1 || grid[i + 1][j] == 0)
        				perimeter++;
        			if(j == 0 || grid[i][j - 1] == 0)
        				perimeter++;
        			if(j == m - 1|| grid[i][j + 1] == 0)
        				perimeter ++;
        		}
        	}
        }
        return perimeter;
    }
    
    
    //<문제풀이3 by LHearen>
    
    //가장 이상적인 방법
    
    //총 4각형의 갯수랑 걔내들이 몇번 붙어있는지만 알면 되잖아?
    
    //몇번 붙어있는지는 2중for문 돌면서 바로 오른쪽애랑 바로 밑애애만 붙어있는지 봄
    
    //어짜피 2중 for문이 왼쪽 위에서 오른쪽 아래방향으로 가니까.
    
    //Runtime: 7 ms
    //Memory Usage: 58 MB
    
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int R = grid.length, C = grid[0].length;
        int cellCount = 0, neighborCount = 0;
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] == 1) {
                    cellCount++;
                    // right, down
                    if (c < C-1 && grid[r][c+1] == 1) neighborCount++;
                    if (r < R-1 && grid[r+1][c] == 1) neighborCount++;
                }
            }
        }
        return cellCount * 4 - neighborCount * 2;
    }
}
