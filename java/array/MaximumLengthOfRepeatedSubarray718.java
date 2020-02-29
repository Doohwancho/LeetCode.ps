package array;

public class MaximumLengthOfRepeatedSubarray718 {
	
	/*
	//<Trial01>
	
	//Brute-force
	
	//로 하려는데 경우의 수가 너~~~~무 많아서 안됌.
	
	public int findLength(int[] A, int[] B) {
        int rst = 0;
        for(int i = 0; i < A.length; i++){
            for(int j = 0, i_ = i, sub = 0; i_ < A.length && j < B.length; j++){
                if(A[i_] == B[j]){
                    i_++;
                    sub++;
                } else{
                    i_ = i;
                    sub = 0;
                }
                rst = Math.max(rst, sub);
            }
        }
        return rst;
    }
    */
	/*
	
	//<문제풀이1 by alexander>
	
	//Your input
	//[1,2,3,2,1]
	//[3,2,1,4,7]
	
	//stdout
	
	//0 0 1 0 0 0 
	//0 1 0 0 0 0 
	//3 0 0 0 0 0 
	//0 2 0 0 0 0 
	//0 0 1 0 0 0 
	//0 0 0 0 0 0 
	
	//Output : 3
	
	//이런방법이...!
	
	//Runtime: 55 ms, faster than 29.85% of Java online submissions for Maximum Length of Repeated Subarray.
	//Memory Usage: 54 MB, less than 25.00% of Java online submissions for Maximum Length of Repeated Subarray.
	
    public int findLength(int[] a, int[] b) {
        int m = a.length, n = b.length;
        if (m == 0 || n == 0) return 0;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = m - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--)
                max = Math.max(max, dp[i][j] = a[i] == b[j] ? 1 + dp[i + 1][j + 1] : 0);
        return max;        
    }
    */
    
    //<문제풀이2 by alexander>
    
    //문제풀이1의 2차원을 1차원으로 줄인 형태. 20ms빠르고 13.1MB메모리 덜씀
    
    //원리는 똑같음. dp는 for문으로 보면 아래와 같이 나옴.
    
	//Your input
	//[1,2,3,2,1]
	//[3,2,1,4,7]
    
	//0 0 1 0 0 0 
	//0 2 0 0 0 0 
	//3 0 0 0 0 0 
	//0 1 0 0 0 0 
	//0 0 1 0 0 0 
    
    //1에서 2가는건 알겠는데 1이 왜 다시 0이되지? 보니까 j가 문제풀이1과는 다르게 증가하는 방식이네
    
    //똑똑하네 이 친구
    
    //Runtime: 35 ms, faster than 88.91% of Java online submissions for Maximum Length of Repeated Subarray.
    //Memory Usage: 40.9 MB, less than 80.00% of Java online submissions for Maximum Length of Repeated Subarray.
    
    public int findLength(int[] a, int[] b) {
        int m = a.length, n = b.length;
        if (m == 0 || n == 0) return 0;
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = m - 1; i >= 0; i--)
            for (int j = 0; j < n; j++)
                max = Math.max(max, dp[j] = a[i] == b[j] ? 1 + dp[j + 1] : 0);
        return max;        
    }
}
