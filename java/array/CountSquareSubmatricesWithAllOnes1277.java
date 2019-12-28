package Array;

public class CountSquareSubmatricesWithAllOnes1277 {
	
	/*
	//<Trial01>
	
	
    // 10101
    // 10011
    // 01011
    // 10011
	
	//일 때, 2x2짜리까지 인식하지 2x3을 인식 못해서 실패.
	
	
	
	//규칙1: n-side면, 1**2+2**2+3**2+...+n**2
    //1 - 1
    //2 - 5(4+1)
    //3 - 14(9+4+1)
    //4 - 30(16+9+4+1)
	
	//규칙2: 가장 큰 정사각형을 찾고, 해당 정사각형 내의 작은 정사각형의 모든 경우의 수를 더해준 후, 정사각형을 지움.
    
    private boolean check(int[][] matrix, int i, int j, int maxSide){
        boolean flag = true;
        loop:
        for(int p = i; p < i+maxSide; p++){
            for(int q = j; q < j+maxSide; q++){
                if(matrix[p][q] != 1){
                    flag = false;
                    break loop;
                }
            }
        }
        return flag;
    }
    
    private void modify(int[][] matrix, int i, int j, int maxSide){
        for(int p = i; p < i+maxSide; p++){
            for(int q = j; q < j+maxSide; q++){
                matrix[p][q] = 0;
            }
        }
    }
    
    private int adder(int num){
        int sum = 0;
        for(int i = 1; i <= num; i++){
            sum += i*i;
        }
        return sum;
    }
    
    private int rest(int[][] matrix){
        int rst = 0;
        for(int[] row: matrix){
            for(int element : row){
                if(element == 1){
                    rst++;
                }
            }
        }
        return rst;
    }

    public int countSquares(int[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        int maxSide = Math.min(row, column)-1;
        int rst = 0;
        
        while(maxSide > 0){
            int change = maxSide;
            loop:
            for(int i = 0; i < row - maxSide; i++){
                for(int j = 0; j < column - maxSide; j++){
                    if(check(matrix, i, j, maxSide+1)){                   
                        modify(matrix, i, j, maxSide+1);
                        rst += adder(maxSide+1);
                        maxSide--;
                        break loop;
                    }
                }
            }
            if(change == maxSide){
                maxSide--;       
            }
        }
        
        return rst+rest(matrix);
    }
    */
	
	
	//<문제풀이 by manrajsingh007>
	
//	[
//	  [0,1,1,1],
//	  [1,1,1,1],
//	  [0,1,1,1]
//	]
	

//		0 1 1 1 
//		1 1 2 2 
//		0 1 2 3
	
	//합을 더함.
	
	//똑똑허이
	
	//Runtime: 7 ms, faster than 95.14% of Java online submissions for Count Square Submatrices with All Ones.
	//Memory Usage: 59 MB, less than 100.00% of Java online submissions for Count Square Submatrices with All Ones.
    
    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int count = 0;
        
        for(int i = 0; i < n; i++) if(matrix[i][0] == 1) dp[i][0] = 1;
        for(int j = 0; j < m; j++) if(matrix[0][j] == 1) dp[0][j] = 1;
		
        // dp[i][j] will tell you the max side of the square with bottom right corner at (i, j).
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(matrix[i][j] == 1){
                    int min = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    dp[i][j] = min;
                }
            }
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) count += dp[i][j];
        }
        
        return count;
    }
}
