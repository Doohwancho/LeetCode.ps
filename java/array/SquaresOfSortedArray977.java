/*
	Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
	
	 
	
	Example 1:
	
	Input: [-4,-1,0,3,10]
	Output: [0,1,9,16,100]
	Example 2:
	
	Input: [-7,-3,2,3,11]
	Output: [4,9,9,49,121]
	
	
	
	<문제>
	
	리스트가 다음과 같이 주어지면 [-4,-1,0,3,10], 각 원소의 제곱을 한 값을 오름차순으로 정렬하여 반환하라.
	
	
	<문제풀이 >
	
	step01 - 답이 들어갈 arrayList 생성
	step02 - 문제에서 주어진 리스트를 for문으로 돌면서, 각 원소의 제곱을 한 값을 방금 만든 array에 입력
	step03 - 해당 array를 Collections.sort()로 오름차순 정렬
	step04 - ArrayList를 int[]로 변환
	
 */

package Array;

import java.util.ArrayList;
import java.util.Collections;

public class SquaresOfSortedArray977 {
	public static int[] sortedSquares(int[] A)
	{
		//step01 - 답안을 담을 array 생성
		ArrayList<Integer> arraylist = new ArrayList<Integer>();
		
		//step02 - for문으로 돌면서, array에 해당 integer의 제곱을 넣음 
		for(int element: A)
		{
			arraylist.add(element*element);
		}
		
		//step03 - Collections.sort()로 오름차순 정렬
		Collections.sort(arraylist);
		
		//step04 - ArrayList to int[]
		int[] ret = new int[arraylist.size()];
		
	    for (int i=0; i < ret.length; i++)
	    {
	        ret[i] = arraylist.get(i).intValue();
	    }
	    
		return ret;
	}
	
	
	public static void main(String[] args) {
		int[] A = {-4,-1,0,3,10};
		System.out.println(sortedSquares(A));
	}
}
