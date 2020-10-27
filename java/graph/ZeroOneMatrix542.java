package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZeroOneMatrix542 {
	
	
	//<문제풀이1>
	
	//bfs
	
	//Runtime: 12 ms, faster than 80.04% of Java online submissions for 01 Matrix.
	//Memory Usage: 42 MB, less than 6.98% of Java online submissions for 01 Matrix.
	
	private void bfs(int[][] matrix, int[][] cp, int i, int j){
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
	                if(matrix[coord[0]-1][coord[1]] == 0){
	                    cp[i][j] = cnt;
	                    return;
	                }
	                int[] tmp = new int[2];
	                tmp[0] = coord[0]-1;
	                tmp[1] = coord[1];
	                next.add(tmp);
	            }
	            if(coord[1] > 0){
	                if(matrix[coord[0]][coord[1]-1] == 0){
	                    cp[i][j] = cnt;
	                    return;
	                }
	                int[] tmp = new int[2];
	                tmp[0] = coord[0];
	                tmp[1] = coord[1]-1;
	                next.add(tmp);
	            }
	            if(coord[0] < matrix.length-1){
	                if(matrix[coord[0]+1][coord[1]] == 0){
	                    cp[i][j] = cnt;
	                    return;
	                }
	                int[] tmp = new int[2];
	                tmp[0] = coord[0]+1;
	                tmp[1] = coord[1];
	                next.add(tmp);
	            }
	            if(coord[1] < matrix[0].length-1){
	                if(matrix[coord[0]][coord[1]+1] == 0){
	                    cp[i][j] = cnt;
	                    return;
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
	}
	
	public int[][] updateMatrix(int[][] matrix) {
	    int m = matrix.length;
	    int n = matrix[0].length;
	    int[][] cp = new int[m][n];
	    
	    for(int i = 0; i < m; i++){
	        for(int j = 0; j < n; j++){
	            cp[i][j] = matrix[i][j];
	        }
	    }
	    for(int i = 0; i < m; i++){
	        for(int j = 0; j < n; j++){
	            if(matrix[i][j] == 1){
	                bfs(matrix, cp, i, j);                    
	            }
	        }
	    }
	    return cp;
	}
    
    
    //<문제풀이2 by shawngao>
    
    //좀 신박해 보여서 가져와봤더니 성능이 더 구리네
	
	//0 0 0 
	//0 2147483647 0 
	//2147483647 2147483647 2147483647 
	
	//0 0 0 
	//0 1 0 
	//2147483647 2147483647 2147483647 
	
	//0 0 0 
	//0 1 0 
	//1 2147483647 2147483647 
	
	//0 0 0 
	//0 1 0 
	//1 2147483647 1 
	
	//0 0 0 
	//0 1 0 
	//1 2 1 
    
    //Runtime: 14 ms, faster than 63.29% of Java online submissions for 01 Matrix.
    //Memory Usage: 40.6 MB, less than 6.98% of Java online submissions for 01 Matrix.
    
    public static int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
                else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n || 
                    matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) { //기존것+1보다 더 크면(== 1을 Integer.MAX_VALUE로 설정한 것), 이라는 조건을 둔게, 위에서 +1했는데, 옆에애가 또 +1하면 안되잖아. 중복 방지하려고.
                	continue;
                }
                queue.add(new int[] {r, c});
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
            }
        }
        
        return matrix;
    }
    
    public static void main(String[] args) {
		int[][] matrix = new int[3][3];
		int[] r1 = new int[3];
		int[] r2 = new int[3];
		int[] r3 = new int[3];
		
		r1[0] = 0;
		r1[1] = 0;
		r1[2] = 0;
		
		r2[0] = 0;
		r2[1] = 1;
		r2[2] = 0;
		
		r3[0] = 1;
		r3[1] = 1;
		r3[2] = 1;
		
		matrix[0] = r1;
		matrix[1] = r2;
		matrix[2] = r3;
		
		System.out.println(updateMatrix(matrix));
	}
}
