package HashTable;

public class IslandPerimeter463 {
	
	/*
	//<Trial01>
	
	//가로세로 1의 갯수를 구해서 합치고x2하면 될줄알았는데, 위의 에시처럼 옴폭 파진 부분은 처리못함
	
	
	public static int islandPerimeter(int[][] grid) {
		int width = grid[0].length;
		int height = grid.length;
		int widthOnes = 0;
		int heightOnes = 0;
		
		for(int i = 0; i < height; i++)
		{
			int cnt = 0;
			for(int j = 0; j < width; j++)
			{
				if(grid[i][j] == 1) cnt++;
			}
			widthOnes = Math.max(widthOnes, cnt);
		}
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				if(grid[i][j] == 1)
				{
					heightOnes++;
					break;
				}
			}
		}
		
		return 2 * (widthOnes + heightOnes);
    }
    */
	
	
	/*
	//<문제풀이1>
	
	//box가 몇개있는지 구하고, 4면이니까 4를 곱해줘.
	
	//그리고 겹치는 부분을 빼는데, 두번씩 빼줘. 
	
	
	//Runtime: 9 ms, faster than 34.55% of Java online submissions for Island Perimeter.
	//Memory Usage: 58.7 MB, less than 97.92% of Java online submissions for Island Perimeter.
	
	public static int islandPerimeter(int[][] grid) {
		int width = grid[0].length;
		int height = grid.length;
		int totalBox = 0;
		int contiguous = 0;
		
		//step01 - count the number of total boxes
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				if(grid[i][j] == 1) totalBox++;
			}
		}
		
		//step02 - count contiguous points
		
		//horizontal
		if(width > 1)
		{
			for(int i = 0; i < height; i++)
			{
				for(int j = 1; j < width; j++)
				{
					if(grid[i][j-1] == 1 && grid[i][j] == 1)
					{
						contiguous++;
					}
				}
			}
		}
		if(height > 1)
		{
			for(int j = 1; j < height; j++)
			{
				for(int i = 0; i < width; i++)
				{
					if(grid[j][i] == 1 && grid[j-1][i] == 1)
					{
						contiguous++;
					}
				}
			}
		}
		
		return totalBox * 4 - contiguous * 2;
	}
	*/
	
	/*
	
	//<문제풀이2 by LHearen>
	
	//DFS(Depth First Search) - 깊이 우선 탐색
	
	//하나의 정점으로부터 시작하여 차례대로 모든 정점들을 한 번씩 방문하는 것

	//미로를 탐색할 때 한 방향으로 갈 수 있을 때까지 계속 가다가 더 이상 갈 수 없게 되면 다시 가장 가까운 갈림길로 돌아와서 이곳으로부터 다른 방향으로 다시 탐색을 진행하는 방법과 유사하다.
	//https://gmlwjd9405.github.io/2018/08/14/algorithm-dfs.html
	
	//Runtime: 10 ms, faster than 29.35% of Java online submissions for Island Perimeter.
	//Memory Usage: 59.4 MB, less than 97.92% of Java online submissions for Island Perimeter.
	

	public static int width, height, count = 0;
	public static final int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static int islandPerimeter(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		
		height = grid.length;
		width = grid[0].length;
		
		for(int i = 0; i < height; ++i)
		{
			for(int j = 0; j < width; ++j)
			{
				if(grid[i][j] == 1)
				{
					dfs(grid, i, j);
				}
			}
		}
		
		return count;
	}
	
	private static void dfs(int[][] grid, int i, int j)
	{
		if(i == -1 || i == height || j == -1 || j == width || grid[i][j] != 1) return;
		grid[i][j] = 2;
		
		if(i == 0 || grid[i-1][j] == 0) count++;
		if(j == 0 || grid[i][j-1] == 0) count++;
		if(i == height-1 || grid[i+1][j] == 0) count++;
		if(j == width-1 || grid[i][j+1] == 0) count++;
		
		for(int[] d : dir) dfs(grid, i+d[0],j+d[1]);
	}
	*/
	

	//<문제풀이3 by LHearen>
	
	//문제풀이1과 원리는 같으나, for문 하나로 해결.
	
	//아주 칭찬해~
	
	//Runtime: 6 ms, faster than 100.00% of Java online submissions for Island Perimeter.
	//Memory Usage: 58.9 MB, less than 97.92% of Java online submissions for Island Perimeter.
	
	
    public static int islandPerimeter(int[][] grid) {
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
	
	
	
	public static void main(String[] args) {
		int[][] grid = {{0,1,0,0},
		                {1,1,1,0},
		                {0,1,0,0},
		                {1,1,0,0}};
		//int[][] grid = {{1,1}};
		//int[][] grid = {{1},{1}};
		
		System.out.println(islandPerimeter(grid));
	}
}
