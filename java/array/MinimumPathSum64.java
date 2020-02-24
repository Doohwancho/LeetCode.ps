package array;

public class MinimumPathSum64 {
	
	//<문제풀이1>
	
	//https://doohwancho.tistory.com/792?category=1055107
	
	//이 문제를 응용한 문제. 문제풀이는 위 링크 참조.
	
	//여기서 왼쪽 위쪽 더하는게 아니라, 왼쪽 위쪽 중 더 작은수를 더하는것임.
	
	//어짜피 해당 좌표(x,y)에 오는데 방법이 아무리 많아도, 그 중에 가장 짧은 루트를 쓸 것이기 때문.
	
	//크...이거지
	
	//Runtime: 1 ms, faster than 99.20% of Java online submissions for Minimum Path Sum.
	//Memory Usage: 41.9 MB, less than 87.84% of Java online submissions for Minimum Path Sum.
	
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        for(int i = 1; i < m; i++){
            grid[i][0] += grid[i-1][0];
        }
        
        for(int j = 1; j < n; j++){
            grid[0][j] += grid[0][j-1];
        }
        
        for(int p = 1; p < m; p++){
            for(int q = 1; q < n; q++){
                grid[p][q] += Math.min(grid[p-1][q], grid[p][q-1]);
            }
        }
        return grid[m-1][n-1];
    }
}
