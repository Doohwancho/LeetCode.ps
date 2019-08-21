/*
	Given an array A of integers, return true if and only if it is a valid mountain array.
	
	Recall that A is a mountain array if and only if:
	
	A.length >= 3
	There exists some i with 0 < i < A.length - 1 such that:
	A[0] < A[1] < ... A[i-1] < A[i]
	A[i] > A[i+1] > ... > A[A.length - 1]
	 
	
	Example 1:
	
	Input: [2,1]
	Output: false
	Example 2:
	
	Input: [3,5,5]
	Output: false
	Example 3:
	
	Input: [0,3,2,1]
	Output: true
	
	
	
	<문제>
	
	array가 주어지면, mountain array인지 판단하라.
	
	mountain array란, 산모양처럼, 어레이 중간 어딘가에 가장 큰 수가 있고, 그 수를 기점으로 양 옆에 수들이 감소하는 array다.
	
	예를들어, [0,1,2,3,2,1,0]이나, [0,3,6,10,8,2,1]이 있다. 
	
	만약 양 옆에 수들이 감소하지 않고, 그 이전 수보다 같거나 크다면, mountain array가 아니다.
	
	예를들어, [0,3,2,2,1]에서 2와 2가 같기 때문에 mountain array가 성립되지 않는다.
	
 */

package Array;

public class ValidMountainArray941 {
	
	/*
//	<문제풀이>
//	
//	가장 큰 수의 인덱스를 구하고, 양옆으로 for문을 돌면서 수가 감소하는지 파악하는법
	
//	메모리는 많이 안쓰나 느림
//	
//	Runtime: 2 ms, faster than 32.87% of Java online submissions for Valid Mountain Array.
//	Memory Usage: 38.9 MB, less than 100.00% of Java online submissions for Valid Mountain Array.
	
	public static boolean validMountainArray(int[] A) {
        //step01 - 유효성 검사1
		if(A.length < 3) return false;
		
		//step02 - find the peak's index
        int largestNum = Integer.MIN_VALUE;
        int largestIndex = 0;
        
        for(int i = 0; i < A.length; i++)
        {
        	if(A[i] > largestNum)
    		{
        		largestNum = A[i];
    			largestIndex = i;
    		}
        }
        //유효성검사2
        if(largestNum == A[0] || largestNum == A[A.length-1]) return false;
        
        //step03 - use two-pointer method to check if it decreases on both ends
        int left = largestNum;
        int right = largestNum;
        
        for(int j = largestIndex-1; j >= 0; j--)
        {
        	if(A[j]>= left)
        	{
        		return false;
        	}
        	left = A[j];
        }
        for(int j = largestIndex+1; j < A.length; j++)
        {
        	if(A[j]>= right)
        	{
        		return false;
        	}
        	right = A[j];
        }
        return true;
    }
    */
	
	/*
//	<문제풀이2>
//	
//	먼저 peak를 찾고 peak를 기준으로 양쪽으로 체크하는게 아니라 for문으로 two pointer를 한번에 하면 더 빨라지지 않을까? 라고 해서
//			
//	만들어 봤는데 걸리는 시간은 동일..
//	
//	Runtime: 2 ms, faster than 32.87% of Java online submissions for Valid Mountain Array.
//	Memory Usage: 39.3 MB, less than 100.00% of Java online submissions for Valid Mountain Array.
	
	public static boolean validMountainArray(int[] A) {
		if(A.length < 3) return false;
		
		int left = A[0];
		int right = A[A.length-1];
		int leftpeak = -1;
		int rightpeak = -1;
		
		for(int i = 1, j = A.length-2; (i < A.length && j >= 0) && (leftpeak < 0 || rightpeak < 0); ) 
		{
			if(leftpeak < 0)
				if(A[i] <= left) leftpeak = i-1;
				else left = A[i++];
			
			if(rightpeak < 0)
			{
				if(A[j] <= right) rightpeak = j+1;
				else right = A[j--];
			}
		}
		return leftpeak == rightpeak;
	}
	*/
	
	//<문제풀이 by lee215>
	
	//문제푸는 방식은 위와 같으나, 위엣거보다 더 깔끔하고 영리하게 씀..
	
	//불필요한 값들의 비교는 다 빼고 오로지 인덱스 값만 취급
	
	//유효성 검사(A.length < 3)가 return문 코드안에 녹아있음
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Valid Mountain Array.
	//Memory Usage: 39 MB, less than 100.00% of Java online submissions for Valid Mountain Array.
	
	
	public static boolean validMountainArray(int[] A) {
        int n = A.length, i = 0, j = n - 1;
        while (i + 1 < n && A[i] < A[i + 1]) i++;
        while (j > 0 && A[j - 1] > A[j]) j--;
        return i > 0 && i == j && j < n - 1;
    }


	public static void main(String[] args) {
		//int[] A = {0,1,3,2,1,0};
		//int[] A = {3,5,5};
		//int[] A = {0,1,2,3,4,5,6,7,8,9};
		int[] A = {0,1,3,0};
		
		
		System.out.println(validMountainArray(A));
	}
}
