package september;

public class UniquePathsIII {

	//<문제풀이1>
	
	//dfs
	
	//다른애들이 한걸보니 boolean[][] visited안쓰고, 기존의 grid에서 한번 들르면 -2라던지 다른숫자로 마크했다가, 4방향 동서남북 다 돌면 다시 원래숫자로 돌리는 방법씀.
	
	//그거 빼고 동일.
	
	//Runtime: 1 ms
	//Memory Usage: 36.6 MB
	
    int rst = 0;
    
    private void dfs(int[][] grid, boolean[][] visited, int x, int y, int cnt, int t){
        if(x < 0 || x == grid.length || y < 0 || y == grid[0].length || visited[x][y] == true || grid[x][y] == -1) return;
        
        cnt++;
        
        if(grid[x][y] == 2 && cnt == t){
            rst++;
        }
            
        visited[x][y] = true;
        
        dfs(grid, visited, x+1, y, cnt, t);
        dfs(grid, visited, x, y+1, cnt, t);
        dfs(grid, visited, x-1, y, cnt, t);
        dfs(grid, visited, x, y-1, cnt, t);
        
        visited[x][y] = false;
    }
    
    public int uniquePathsIII(int[][] grid) {
        int p = grid.length;
        int q = grid[0].length;
        int t = 0;
        int x = 0;
        int y = 0;
        
        for(int i = 0; i < p; i++){
            for(int j = 0; j < q; j++){
                if(grid[i][j] == 1){
                    x = i;
                    y = j;
                } else if(grid[i][j] == -1){
                    t++;
                }
            }
        }
        t = p*q-t;
        
        dfs(grid, new boolean[p][q], x, y, 0, t);
        return rst;
    }
}
