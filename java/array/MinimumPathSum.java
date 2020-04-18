package thirtyDayChallenge;

public class MinimumPathSum {
	
	//<문제풀이1>
	
	//greedy
	
	//첫줄은 i-1번을 계속 누적으로 더해주고
	
	//i,j가 (1,1)부터 loop돌때
	
	//본인 위치에 위에 있는놈이랑 왼쪽에 있는놈중에 더 작은애를 더해주는 방식으로
	
	//맨 마지막까지 더하면, 맨 마지막에 최소거리가 나온다.

	//61 / 61 test cases passed.
	//Status: Accepted
	//Runtime: 2 ms
	//Memory Usage: 42 MB
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        for(int i = 1; i < m; i++){
            grid[i][0] += grid[i-1][0];
        }
        
        for(int i = 1; i < n; i++){
            grid[0][i] += grid[0][i-1];
        }
        
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                grid[i][j] = grid[i][j]+Math.min(grid[i][j-1], grid[i-1][j]);
            }
        }
        return grid[m-1][n-1];
    }
}
