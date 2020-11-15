package graph;

import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumPathSum64 {

	//<Time Limit Exceeded>
	
	//Dijkstra

	//27 / 61 test cases passed.
	
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][]{{1,0},{0, 1}};
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        pq.offer(new int[] {grid[0][0], 0, 0});
        
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int acc = curr[0];
            int x = curr[1];
            int y = curr[2];
            
            if(x == m-1 && y == n-1) return acc;
            
            for(int[] dir : dirs){
                int newX = x+dir[0];
                int newY = y+dir[1];
                if(newX >= m || newY >= n) continue;
                pq.offer(new int[] {acc+grid[newX][newY], newX, newY});
            }
        }
        
        return 0;
    }
    
    //<문제풀이1>
    
    //Dijkstra
    
    //Runtime: 439 ms, faster than 5.26% of Java online submissions for Minimum Path Sum.
    //Memory Usage: 52.3 MB, less than 5.03% of Java online submissions for Minimum Path Sum.
    
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] matrix = new int[m][n];
        for(int[] mat : matrix){
            Arrays.fill(mat, Integer.MAX_VALUE);
        }
        matrix[0][0] = grid[0][0];
        int[][] dirs = new int[][]{{1,0},{0, 1}};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {grid[0][0], 0, 0});
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int acc = curr[0];
            int x = curr[1];
            int y = curr[2];
            
            for(int[] dir : dirs){
                int newX = x+dir[0];
                int newY = y+dir[1];
                if(newX >= m || newY >= n) continue;
                if(acc+grid[newX][newY] < matrix[newX][newY]){
                    q.offer(new int[] {acc+grid[newX][newY], newX, newY});
                    matrix[newX][newY] = acc+grid[newX][newY];
                }
            }
        }
        
        return matrix[m-1][n-1] == Integer.MAX_VALUE ? 0 : matrix[m-1][n-1];
    }
    
}
