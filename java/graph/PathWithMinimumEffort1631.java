package graph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathWithMinimumEffort1631 {
	
	//<Trial01>
	
	//[[1,10,6,7,9,10,4,9]]
	
	//다익스트라 짤때 가장 효율적인 방법만 가게했는데, 
	
	//위 testcase처럼 일자무식하게 효율은 개나주고 일자방향으로 가라그러면 안먹힘;
	
	
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] board = new int[m][n]; 
        for(int[] row : board){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        board[0][0] = 0;
        
        int[][] dirs = new int[][]{{1,0},{0,-1},{-1,0},{0,1}};
        
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        pq.offer(new int[]{0,0,Integer.MAX_VALUE});
        
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int acc = curr[2];
            
            if(x == m-1 && y == n-1) return board[m-1][n-1];
            visited[x][y] = true;
            
            for(int[] dir : dirs){
                int newX = x + dir[0];
                int newY = y + dir[1];
                
                if(newX < 0 || newY < 0 || newX >= m || newY >= n || visited[newX][newY]) continue; //이걸 넣어야 하는가?

                int diff = Math.abs(heights[newX][newY] - heights[x][y]);
                if(diff <= board[newX][newY]){
                    board[newX][newY] = Math.min(board[newX][newY], diff);
                    pq.offer(new int[] {newX, newY, board[newX][newY]});
                }
            }
        }
        return 0;
    }

    
    //<문제풀이1>
    
    //Dijkstra
    
    //Runtime: 78 ms, faster than 44.25% of Java online submissions for Path With Minimum Effort.
    //Memory Usage: 38.9 MB, less than 99.66% of Java online submissions for Path With Minimum Effort.
    
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] board = new int[m][n]; 
        for(int[] row : board){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        board[0][0] = 0;
        
        int[][] dirs = new int[][]{{1,0},{0,-1},{-1,0},{0,1}};
        
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
        pq.offer(new int[]{0,0,Integer.MAX_VALUE});
        
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int acc = curr[2];
            
            if(x == m-1 && y == n-1) return board[m-1][n-1];
            visited[x][y] = true;
            
            for(int[] dir : dirs){
                int newX = x + dir[0];
                int newY = y + dir[1];
                
                if(newX < 0 || newY < 0 || newX >= m || newY >= n || visited[newX][newY]) continue; 

                int diff = Math.abs(heights[newX][newY] - heights[x][y]);
                board[newX][newY] = Math.max(board[x][y], Math.min(board[newX][newY], diff));
                pq.offer(new int[] {newX, newY, board[newX][newY]});
            }
        }
        return 0;
    }
}
