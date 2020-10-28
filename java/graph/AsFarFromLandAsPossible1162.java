package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AsFarFromLandAsPossible1162 {
	
	//<Trial01 - Time Limit Exceeded>
	
	//bfs

    private int bfs(int[][] matrix, int i, int j){
	    Queue<List<int[]>> q = new LinkedList<>();
	    List<int[]> list = new ArrayList<>();
	    int[] temp = new int[2];
	    temp[0] = i;
	    temp[1] = j;
	    list.add(temp);
	    q.add(list);

	    int cnt = 1;

	    while(!q.isEmpty()){
	        List<int[]> row = q.poll();
	        List<int[]> next = new ArrayList<>();

	        for(int[] coord : row){
	            if(coord[0] > 0){
	                if(matrix[coord[0]-1][coord[1]] == 1){
	                    return cnt;
	                }
	                int[] tmp = new int[2];
	                tmp[0] = coord[0]-1;
	                tmp[1] = coord[1];
	                next.add(tmp);
	            }
	            if(coord[1] > 0){
	                if(matrix[coord[0]][coord[1]-1] == 1){
	                    return cnt;
	                }
	                int[] tmp = new int[2];
	                tmp[0] = coord[0];
	                tmp[1] = coord[1]-1;
	                next.add(tmp);
	            }
	            if(coord[0] < matrix.length-1){
	                if(matrix[coord[0]+1][coord[1]] == 1){
	                    return cnt;
	                }
	                int[] tmp = new int[2];
	                tmp[0] = coord[0]+1;
	                tmp[1] = coord[1];
	                next.add(tmp);
	            }
	            if(coord[1] < matrix[0].length-1){
	                if(matrix[coord[0]][coord[1]+1] == 1){
	                    return cnt;
	                }
	                int[] tmp = new int[2];
	                tmp[0] = coord[0];
	                tmp[1] = coord[1]+1;
	                next.add(tmp);
	            }
	        }
	        if(next.size() > 0){
	            q.add(next);
	            cnt++;
	        }
	    }
        return 0;
	}
    
    public int maxDistance(int[][] matrix) {
	    int m = matrix.length;
	    int n = matrix[0].length;
        int rst = 0;
        int ones = 0;
        
        for(int i = 0; i < m; i++){
	        for(int j = 0; j < n; j++){
                if(matrix[i][j] == 1){
                    ones++;
                }
	        }
	    }

        if(ones == 0 || ones == m*n) return -1;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    rst = Math.max(rst, bfs(matrix, i, j));     
                }
            }
        }
        
	    return rst;
    }
    
    
    //<문제풀이1 by wangzi6147>
    
    //bfs
    
    //Runtime: 12 ms, faster than 82.10% of Java online submissions for As Far from Land as Possible.
    //Memory Usage: 39.8 MB, less than 5.18% of Java online submissions for As Far from Land as Possible.
    
    public int maxDistance(int[][] matrix) {
	    int m = matrix.length;
	    int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        
        Queue<int[]> q = new LinkedList<>();
        int rst = 0;
        
        for(int i = 0; i < m; i++){
	        for(int j = 0; j < n; j++){
                if(matrix[i][j] == 1){
                    visited[i][j] = true;
                    q.add(new int[] {i, j});
                }
	        }
	    }

        if(q.size() == 0 || q.size() == m*n) return -1;
        
        int[][] dir = new int[4][2];
        dir[0] = new int[] {0, 1};
        dir[1] = new int[] {1, 0};
        dir[2] = new int[] {0, -1};
        dir[3] = new int[] {-1, 0};
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0){
                int[] coord = q.poll();
                int x = coord[0];
                int y = coord[1];
                
                for(int[] d : dir){
                    int newX = x+d[0];
                    int newY = y+d[1];
                    if(newX < 0 || newY < 0 || newX == m || newY == n || visited[newX][newY]){
                        continue;
                    }
                    rst = Math.max(rst, matrix[x][y]);
                    matrix[newX][newY] = matrix[x][y]+1;
                    q.add(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
                size--;
            }
        }
	    return rst;
    }
}
