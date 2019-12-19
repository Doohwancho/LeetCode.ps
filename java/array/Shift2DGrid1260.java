package Array;

import java.util.LinkedList;
import java.util.List;

public class Shift2DGrid1260 {
	
	/*
	//<Trial01>
	
	//뭔생각으로 짰는지 원
	
	//Input:[[1,2,3],[4,5,6],[7,8,9]] 1
	//Output:[[-1,1,3],[5,4,6],[8,7,9]]
	//Expected:[[9,1,2],[3,4,5],[6,7,8]]
	
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> rst = new ArrayList<>();
        
        for(int m = 0; m < k; m++){
            for(int i = 0; i < grid.length; i++){
                int shift = -1;
                for(int j = 1; j < grid[0].length; j++){
                    if(j < grid[0].length-1){
                        if(shift != -1 && j == 1){
                            grid[i][j-1] = shift;
                            shift = -1;
                        }
                        int tmp = 0;
                        tmp = grid[i][j];
                        grid[i][j] = grid[i][j-1];
                        grid[i][j-1] = tmp;
                    }
                    else if(i != grid.length-1){
                        shift = grid[i][j];
                    }
                }
                if(i == grid.length-1){
                    grid[0][0] = shift;
                }
            }
        }
        
        for(int[] row:grid){
            List<Integer> list = new ArrayList<>();
            for(int element:row){
                list.add(element);
            }
            rst.add(list);
        }
        return rst;
    }
	*/
	/*
	//<Trial02>
	
	//한번 돌리는건 되는데, k번(여러번)돌리는건 안됨. 정신차리자 친구야.
	
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> rst = new ArrayList<>();
        int row = grid.length-1;
        int column = grid[0].length-1;
        
        for(int m = 0; m < k; m++){
            for(int i = 0; i <= row; i++){
                List<Integer> list = new ArrayList<>();
                
                if(i == 0){
                    list.add(grid[row][column]); 
                }
                else{
                    list.add(grid[i-1][column]);   
                }; 
    
                for(int j = 0; j < column; j++){
                    list.add(grid[i][j]);
                };
                rst.add(list);
            }
        }
        return rst;
    }
    */
	
	/*
	//<Trial03>
	
	//아니 형 이런 인풋은 좀 아니잖아
	
	//Input : [[1],[2],[3],[4],[7],[6],[5]] , 23
	//Output : [[-1],[-1],[-1],[-1],[-1],[-1],[-1]]
	//Expected : [[6],[5],[1],[2],[3],[4],[7]]
	
	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        List<List<Integer>> rst = new ArrayList<>();
        // if(grid.length < 2 && grid[0].length < 2) return rst;
        
        int row = grid.length-1;
        int column = grid[0].length-1;
        
        for(int m = 0; m < k; m++){
            int index = 0;
            int push1 = grid[index++][0];
            
            for(int i = 0; i <= row; i++){
                //맨 앞에넣을 뒤에꺼 미리 빼두고 
                int frontKeeper = grid[i][0];
                int backKeeper = i > 0 ? push1 : grid[row][column];
                int push2;
                
                //오른쪽으로 한칸씩 밀고
                for(int j = 1; j < column+1; j++){
                    if(j == 1){
                        push1 = grid[i][j];
                        grid[i][j] = grid[i][j-1];        
                    } else {
                        push2 = grid[i][j];
                        grid[i][j] = push1;
                        push1 = push2;
                    }
                }
                //맨 앞에 삽입
                grid[i][0] = backKeeper;
            }
        }
        for(int[] p : grid){
            List<Integer> list = new ArrayList<>();
                for(int q : p){
                    list.add(q);
                }
                rst.add(list);
            }
        return rst;    
	}
	*/
 
	//<문제풀이 by JatinYadav>
	
	//아.. 이리 쉬운걸
	
	//Runtime: 4 ms, faster than 99.13% of Java online submissions for Shift 2D Grid.
	//Memory Usage: 47 MB, less than 100.00% of Java online submissions for Shift 2D Grid.
	
	public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
	    int row = grid.length;
	    int column = grid[0].length;  
	    int mod = row * column;
	    int[][] temp = new int[row][column];
	    k = k % mod; //2x2에서 4번 오른쪽으로 움직이면 제자리에 오니까, 불필요한 k제거
	      
	    for (int i = 0; i < row; i++) {
	        for (int j = 0; j < column; j++) {
	            int p = j + k; // column
	            int r = p / column; // row
	            if (p < column) {
	                temp[i][p] = grid[i][j];
	            } else {
	                temp[(i + r) % row][p % column] = grid[i][j];
	            }
	        }
	    }
	    
		// temp to list
	    List<List<Integer>> result = new LinkedList<>();
	    for (int[] t : temp) {
	        LinkedList<Integer> c = new LinkedList<>();
	        for (int i : t) {
	            c.add(i);
	        }
	        result.add(c);
	    }
	    return result;
	}
}
