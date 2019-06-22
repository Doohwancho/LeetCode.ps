
/*
	Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
	
	You may return any answer array that satisfies this condition.
	
	 
	
	Example 1:
	
	Input: [3,1,2,4]
	Output: [2,4,3,1]
	The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 
 
 
 	<문제>
 	리스트 A가 주어지면, A에 들어있는 정수들 중, 모든 짝수를 순서상 먼저 배치하고, 홀수를 짝수 다음으로 빼라.
 	
 	예를들어, [3,1,2,4]가 주어졌다면, 짝수인 2,4를 먼저 배치하고, 홀수인 3,1를 나중에 배치하여, [2,4,3,1]을 반환한다.
 	
 	단, 홀수와 짝수의 순서제약은 없다. [4,2,3,1], [2,4,1,3], [4,2,1,3] 도 가능하다.
 	
 	
 
 
	<문제풀이 - trial01>
	List<Integer> 즉, array로 하는것은 풀었으나, int[]로 바꾸는 것에서 막힘
	
	step01 - odd와 even 숫자가 들어갈 array를 생성
	step02 - A를 for문으로 돌면서, 2로 나누었을 때, 나머지가 1이면(홀수면) odd, 0이면(짝수면) even에 insert
	step03 - 짝수가 들어있는 even 어레이에 홀수가 들어있는 odd array를 .addall()로 합침

 */

package Array;

import java.util.List;
import java.util.ArrayList;

public class SortArrayByParity905ver1 {
	public static List<Integer> sortArrayByParity(int[] A)
	{
		//step01
		ArrayList<Integer> odd = new ArrayList<Integer>(); //ArrayList는 List 인터페이스를 구현한 클래스이다. 설정된 저장 용량(capacity)보다 많은 데이터가 들어오면 자동으로 용량이 늘어난다.
		ArrayList<Integer> even = new ArrayList<Integer>();
		
		//step02
		for(int i = 0 ; i < A.length ; i++)
		{
			if(A[i]%2 == 1)
			{
				odd.add((int)A[i]);
			}
			else
			{
				even.add(A[i]);
			}
		}
		
		//step03
		List<Integer> rst = new ArrayList<Integer>(even);
		rst.addAll(odd);
		
		return rst;
	}
	
	
	public static void main(String[] args) 
	{
		int[] A = {3,1,2,4};
		System.out.println(sortArrayByParity(A));
	}
}
