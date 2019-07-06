/*
	Given an array A of integers, return true if and only if we can partition the array into three non-empty parts with equal sums.
	
	Formally, we can partition the array if we can find indexes i+1 < j with (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])
	
	 
	
	Example 1:
	
	Input: [0,2,1,-6,6,-7,9,1,2,0,1]
	Output: true
	Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
	Example 2:
	
	Input: [0,2,1,-6,6,7,9,-1,2,0,1]
	Output: false
	Example 3:
	
	Input: [3,3,6,5,-2,2,5,1,-9,4]
	Output: true
	Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4 
	
	
	
	
	<문제>
	
	리스트가 int[] A = {3,3,6,5,-2,2,5,1,-9,4}; 다음과 같이 주어지면, 합이 같게 세 등분하라
	
	
	
	<문제풀이>
	
	먼저 리스트에 있는 모든 숫자의 합을 구한 후, 3으로 나눠서 나머지가 있는지 확인한다.
	
	나머지가 있다면, 합이 같게 3등분 할 수 없기 때문이다.
	
	딱 맞아 떨어진다면, 총 합을 3으로 나눈 후, for문으로 돌면서 합이 같아질때까지 비교해 보면 된다.
	
	
	Runtime: 38 ms, faster than 13.03% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
	Memory Usage: 50.1 MB, less than 86.92% of Java online submissions for Partition Array Into Three Parts With Equal Sum.
 */
package Array;

import java.util.stream.IntStream;

public class PartitionArrayThreeEqualSum1013 {
	
	public static boolean canThreePartsEqualSum(int[] A) 
		{
	        int summation = IntStream.of(A).sum();
	        
	        if(summation % 3 == 0)
	        {
	        	summation /= 3;
	        	int partSum = 0;
	        	int identifier = 0;
	        	
	        	for(int i = 0; i < A.length; i++)
	        	{
	        		partSum += A[i];
	        		if(summation == partSum) 
	        		{
	        			identifier++;
	        			partSum = 0;
	        		}
	        	}
	        	return identifier == 3;
	        }
	        else
	        {
	        	return false;
	        }
	    }

	public static void main(String[] args) {
		int[] A = {3,3,6,5,-2,2,5,1,-9,4};
		//int[] A = {0,2,1,-6,6,7,9,-1,2,0,1};
		
		System.out.println(canThreePartsEqualSum(A));
	}
}
