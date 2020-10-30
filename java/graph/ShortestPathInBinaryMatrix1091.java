package graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix1091 {
	
	//<Trial01>
	
	//yet
	
	private static void draw(int[][] g) {
		for(int i = 0; i < g.length; i++) {
			for(int j = 0; j < g[0].length; j++) {
				System.out.print(g[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        int[][] dir = new int[][]{{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
        int rst = 0;
        
        // int[][] cp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    grid[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        grid[0][0] = 0;
        // cp[0][0] = 1;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0});
        
        int cnt = 0;
        
        while(!q.isEmpty() && cnt < 3){
            int size = q.size();
            
            while(size-- > 0){
                int[] coord = q.poll();
                int x = coord[0];
                int y = coord[1];
                
                visited[x][y] = true;
                
                for(int[] d : dir){
                    int dx = x+d[0];
                    int dy = y+d[1];
                    if(dx < 0 || dy < 0 || dx == m || dy == n || visited[dx][dy]){
                        continue;
                    }
                    grid[dx][dy] = Math.min(grid[dx][dy], grid[x][y]+1);
                    
                    q.add(new int[] {dx, dy});
                }
                visited[x][y] = false;
                draw(grid);
            }
            cnt++;
        }
        
        return grid[m-1][n-1];
    }
	
	public static void main(String[] args) {
		int[][] grid = new int[][] {{0, 1},{1, 0}};
		
		System.out.println(shortestPathBinaryMatrix(grid));
	}
}
