package array;

public class RotateImage48 {
	/*
	//<Trial01>
	
	
    //(0,0) -> (0,2) -> (2,2) -> (2,0) -> (0,0)
    //(0,1) -> (1,2) -> (2,1) -> (1,0) -> (0,1)
    // matrix[i][j] -> matrix[j][n-1-i];

	private static void move(int[] coord, int n) {
		int x = coord[0];
		int y = coord[1];
		coord[2] = y;
		coord[3] = n-1-x;
		
		int a = coord[2];
		int b = coord[3];
		coord[0] = a;
		coord[1] = b;
		
		
	}
	
	public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n/2; i++){
            for(int j = i; j < n-1-i; j++){
            	int[] coord = new int[4];
            	System.out.println(matrix[i][j]+"  "+j);
            	coord[1] = j;
            	int tmp1 = -1;
            	int tmp2 = -1;
            	for(int q = 0; q < 4; q++) { //5
            		move(coord,n);
//            		tmp1 = matrix[coord[0]][coord[1]];
//            		matrix[coord[0]][coord[1]] = tmp2;
//            		tmp2 = matrix[coord[2]][coord[3]];
//            		matrix[coord[2]][coord[3]] = tmp1;
            	}
            	System.out.println();
            }
        }
    }
    */

	
	//<문제풀이1 by shpolsky>
	
	//현타오지네
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
	//Memory Usage: 38.5 MB, less than 5.77% of Java online submissions for Rotate Image.
	
    public void rotate(int[][] M) {
        for (int i = 0; i < (M.length+1)/2; i++) {
            for (int j = 0; j < M.length/2; j++) {
                int tmp = M[i][j];
                M[i][j] = M[M.length-j-1][i];
                M[M.length-j-1][i] = M[M.length-i-1][M.length-j-1];
                M[M.length-i-1][M.length-j-1] = M[j][M.length-i-1];
                M[j][M.length-i-1] = tmp;
            }
        }
    }
}
