package array;

public class MaximumSideLengthOfSquareWithSumLessEqualToThreshold1292 {
	
	//<문제풀이1 by motorix>
	
	//mat = [[1,1,3,2,4,3,2],
	//       [1,1,3,2,4,3,2],
	//       [1,1,3,2,4,3,2]], 
	//threshold = 4
	
	//prefixSum
	
	//0 0 0  0  0  0  0  0 
	//0 1 2  5  7 11 14 16 
	//0 2 4 10 14 22 28 32 
	//0 3 6 15 21 33 42 48 
	
	//k: 2 i: 1 j: 1 sum: 15
	//k: 2 i: 1 j: 2 sum: 18
	//k: 2 i: 1 j: 3 sum: 27
	//k: 2 i: 1 j: 4 sum: 27
	//k: 2 i: 1 j: 5 sum: 27
	//k: 1 i: 1 j: 1 sum: 4  -->return(<=threshold)
	
	//Runtime: 58 ms, faster than 23.96% of Java online submissions for Maximum Side Length of a Square with Sum Less than or Equal to Threshold.
	//Memory Usage: 52.7 MB, less than 100.00% of Java online submissions for Maximum Side Length of a Square with Sum Less than or Equal to Threshold.
	
    public static int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        // Find prefix sum
        int[][] prefixSum = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                sum += mat[i-1][j-1];
                prefixSum[i][j] = prefixSum[i-1][j] + sum;
            }
        }
        
        // Start from the largest side length
        for(int k = Math.min(m, n)-1; k > 0; k--) {
            for(int i = 1; i+k <= m; i++) {
                for(int j = 1; j+k <= n; j++) {
                    if(prefixSum[i+k][j+k] - prefixSum[i-1][j+k] - prefixSum[i+k][j-1] + prefixSum[i-1][j-1] <= threshold) {
                        return k+1;
                    }
                }
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
    	int[][] mat = {{1,1,3,2,4,3,2},{1,1,3,2,4,3,2},{1,1,3,2,4,3,2}};
    	int threshold = 4;
    	System.out.println(maxSideLength(mat, threshold));
	}
}
