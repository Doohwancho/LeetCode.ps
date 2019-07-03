/*
	Runtime: 1 ms, faster than 100.00% of Java online submissions for Reshape the Matrix.
	Memory Usage: 39.2 MB
	
	Runtime이 2ms 더 빠른 방법
	
	List를 따로 선언하고, 거기에 값을 모두 담은 다음에 다시 rst에 값을 옮기는것이 아니라,
	
	그냥 nums를 for문으로 돌면서 rst에 유기적으로 담는 방법.
 */

package Array;

public class ReshapeTheMatrix556ver2 {
	public int[][] matrixReshape(int[][] nums, int r, int c) 
	{
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) return nums;
        
        int[][] result = new int[r][c];
        int row = 0, col = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[row][col] = nums[i][j];
                col++;
                if (col == c) {
                    col = 0;
                    row++;
                }
            }
        }
        return result;
    }

}
