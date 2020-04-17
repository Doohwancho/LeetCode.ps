package thirtyDayChallenge;

public class NumberOfIsland {
	
	//<문제풀이1>
	
	//backtracking
	
	//땅이 걸릴때마다 backtracking으로 1을 2로 바꾸고 rst+1해주는 방법.
	
	//우하좌상으로 한칸씩 1이 있는지 보면서, 있으면 그 칸으로 이동하고 1을 2로 바꾼 후, 다시 그 칸 기준으로 우하좌상에 1이 있는지 확인을 반복.
	
	//loop돌면 어짜피 왼쪽 위에서 순서대로 오른쪽 아래로 가는데, 왜 굳이 3번째 if랑 4번째 if인 j>0나 i>0쓸까?
	
	//그건 만약에 섬이
	
	//0010
	//0010
	//0110 
	
	//요딴식으로 나오면, 밑으로 가다가 왼쪽으로 꺾어야 되잖아. 그래서 그래
	
	//47 / 47 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
	//Memory Usage: 42.3 MB
    private void move(char[][] grid, int i, int j){
        grid[i][j] = '2';
        if(j < grid[0].length-1 && grid[i][j+1]=='1'){
            move(grid, i, j+1);
        }
        if(i < grid.length-1 && grid[i+1][j]=='1'){
            move(grid, i+1, j);
        }
        if(j > 0 && grid[i][j-1]=='1'){
            move(grid, i, j-1);
        }
        if(i > 0 && grid[i-1][j]=='1'){
            move(grid, i-1, j);
        }
    }
    
    public int numIslands(char[][] grid) {
        if(grid.length==0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int rst = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j]=='1'){
                    move(grid, i, j);
                    rst++;
                }
            }
        }
        return rst;
    }
}
