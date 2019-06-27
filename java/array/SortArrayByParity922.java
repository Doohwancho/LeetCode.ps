/*
	Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
	
	Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
	
	You may return any answer array that satisfies this condition.
	
	 
	
	Example 1:
	
	Input: [4,2,5,7]
	Output: [4,5,2,7]
	Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
	
	
	
	
	<문제>
	
	리스트가 다음과 같이 주어지면, [4,2,5,7] 홀수 인덱스에 있으면 홀수 숫자로, 짝수 인덱스의 있으면 짝수 숫자로 재배치를 하라
	
	
	
	<문제풀이>

	
	step01) A와 동일한 크기를 가진 int[] rst를 선언
	
	step02) rst에 넣을 때, 짝수 인덱스와 홀수 인덱스를 구별해 주기 위한 odd, even 변수 선언
	
	step03) for문을 돌면서, 짝수이면(2로 나누었을 때, 나머지가 0이면) rst에 even(짝수)인덱스에 값을 넣어주고, even 인덱스를 +2. 왜냐면 +1하면 odd와 겹치고 홀수가 되니까. 짝수도 같은 로직으로 처리
	
 */

package Array;

public class SortArrayByParity922 {
	public static int[] sortArrayByParityII(int[] A)
	{
		//step01 & step02
		int[] rst = new int[A.length];
		int odd = 1;
		int even = 0;
		
		//step03
		for(int idx = 0; idx < A.length; idx++)
		{
			if(A[idx] % 2 == 0)
			{
				rst[even] = A[idx];
				even += 2;
			}
			else
			{
				rst[odd] = A[idx];
				odd += 2;
			}
		}
		
		return rst;
	}
	
	
	public static void main(String[] args) {
		int[] A = {4,2,5,7};
		System.out.println(sortArrayByParityII(A));
		
	}
}
