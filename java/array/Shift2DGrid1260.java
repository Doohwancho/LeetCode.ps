package Array;

import java.util.ArrayList;
import java.util.List;

public class Shift2DGrid1260 {
	
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
	
	//<Trial02>
	
	//그리드를 먼저 바꾸고 다 바꾸면 어레이에 옮겨야 하는데 그냥 첫방에 어레이에 옮겨서 불상사남.
	
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
}
