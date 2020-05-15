package mayChallenge;

public class MaximumSumCircularSubArray {
	
	/*
	//<Trial01>
	
	//brute-force
	
	//흐미..
    public int maxSubarraySumCircular(int[] A) {
        int[] c = new int[A.length*2];
        c[0] = A[0];
        int len = A.length;
        
        int rst = Integer.MIN_VALUE;
        
        
        for(int i = 1, j = 0; i < A.length*2; i++){
            if(i < A.length){
                c[i] = c[i-1]+A[i];    
            } else {
                c[i] = c[i-1]+A[j];
                j++;
            }
        }
        
        for(int j = 0, p = -1; j < c.length; j++, p = j-len){
            while(p >= 0 && p < j){
                rst = Math.max(rst, c[j]-c[p]);
                p++;
            }
            if(j < A.length){
                rst = Math.max(rst, c[j]);
            }
        }
        return rst;
    }
    */
    
    
	//<문제풀이1 by logan138>
	
	//EXPLANATION:-
	//EX 1:-
	//   A = [1, -2, 3, -2]
	//   max = 3   (This means max subarray sum for normal array)
	//   min = -2  (This means min subarray sum for normal array)
	//   sum = 0   (total array sum)
	//   Maximum Sum = 3 (max)
	
	//EX2:-
	//  A = [5, -3, 5]
	//  max = 7
	//  min  = -3
	//  sum = 7
	//  maximum subarray sum = 7 - (-3) = 10
	  
	//EX3:-
	//   A = [3, -1, 2, -1]
	//   max = 4
	//   min = -1
	//   sum = 3
	//   maximum subarray sum  = sum - min = 3 - (-1) = 4
	
	//EX4:-
	//   A = [-2, -3, -1]
	//   max = -1
	//   min = -6
	//   sum = -6
	//   In this example, if we do (sum - min) then result is 0. but there is no subarray with sum 0.
	//   So, in this case we return max value. that is -1.
	
	//From above examples, 
	//we can understand that 
	//maximum sum is either max (we get using kadane's algo) or (sum - min).
	//There is a special case, if sum == min,
	//then maximum sum is max.
	
	//Let's develop an algorithm to solve this problem.
	//1. Find maximum subarray sum using kadane's algorithm (max) 
	//2. Find minimum subarray sum using kadane's algorithm (min)
	//3. Find total sum of the array (sum)
	//4. Now, if sum == min return max
	//5. Otherwise, return maximum ( max, sum - min )
	
	//Runtime: 4 ms
	//Memory Usage: 45.4 MB
    
    public int maxSubarraySumCircular(int[] A) {
        if(A.length == 0) return 0;
        int sum = A[0];
        int maxSoFar = A[0];
        int maxTotal = A[0];
        int minTotal = A[0];
        int minSoFar = A[0];
        for(int i = 1; i < A.length; i++){
            int num = A[i];
            maxSoFar = Math.max(num, maxSoFar + num);
            maxTotal = Math.max(maxSoFar, maxTotal);
            
            minSoFar = Math.min(num, minSoFar + num);
            minTotal = Math.min(minTotal, minSoFar);
            
            sum += num;
        }
        if(sum == minSoFar) return maxTotal;
        return Math.max(sum - minTotal, maxTotal);
    }
}
