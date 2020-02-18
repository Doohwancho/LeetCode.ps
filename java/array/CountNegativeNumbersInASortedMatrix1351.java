package array;

import java.util.Arrays;

public class CountNegativeNumbersInASortedMatrix1351 {
	
	/*
	//<문제풀이1>
	
	//brute-force
	
	//Runtime: 1 ms, faster than 62.06% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
	//Memory Usage: 41.6 MB, less than 100.00% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
	public int countNegatives(int[][] grid) {
        int rst = 0;
        
        for(int[] row : grid){
            for(int element : row){
                if(element < 0){
                    rst++;
                }
            }
        }
        
        return rst;
    }
	*/
	
	/*
	//<문제풀이2>
	
	//다 안세도 됨
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
	//Memory Usage: 41.5 MB, less than 100.00% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
	
    public int countNegatives(int[][] grid) {
        int rst = 0;
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] < 0){
                    rst += (grid[i].length - j);
                    break;
                }
            }    
        }
        
        return rst;
    }
    */
    
	/*
    //<문제풀이3 by ManualP>
    
    //힙스터를 위한 풀이
    
    //Arrays.deepToString(grid);를 하면 이렇게 변함.
	//String형식의 "[[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]"
    
    //그걸 .split("-")하면 아래같이 9개로 쪼개짐
    
	//[[4, 3, 2, 
	//1], [3, 2, 1, 
	//1], [1, 1, 
	//1, 
	//2], [
	//1, 
	//1, 
	//2, 
	//3]]
	
	//윾시 성능도 개구림
	
	//Runtime: 9 ms, faster than 5.53% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
	//Memory Usage: 41.7 MB, less than 100.00% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
    
    public int countNegatives(int[][] grid) {
        return Arrays.deepToString(grid).split("-").length - 1;
    }
    */
	
	//<문제풀이4 by myCafeBabe>
	
	//binary search
	
	//increasing order 반대 순서로 어쨌든 정렬되있으니까 그점을 이용한 풀이.
	
	//sample size가 말도안되게 크다고 할 때, 가장 성능이 좋은 풀이.
	
	//한줄 풀로 마이너스인 값은 미리 ans += (rows - i) * cols; 로 더해놓고,
	
	//그 윗줄들 나머지를 for문으로 차례차례 돌면서 binary search 이용하여 mid(마이너스)인 부분을 찾는 것
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
	//Memory Usage: 41.2 MB, less than 100.00% of Java online submissions for Count Negative Numbers in a Sorted Matrix.
    
    public int countNegatives(int[][] grid) {
        int ans = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int bound = rows;
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] < 0) {
                ans += (rows - i) * cols;
                bound = i;
                break;
            }
        }
        for (int i = 0; i < bound; i++) {
            ans += cols - binarySearch(grid[i]);
        }
        return ans;
    }
    
    public int binarySearch(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2; //(high+low)/2랑 같음. high+low가 Integer.MAX_VALUE넘어갈까봐 이렇게 바꿔서 쓴 것.
            if (arr[mid] >= 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if (arr[low] < 0) {
            return low;
        }
        if (arr[high] < 0) {
            return high;
        }
        return arr.length;
    }
    
	
}
