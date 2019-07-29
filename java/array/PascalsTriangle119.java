/*
<문제>

Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.

In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 3
Output: [1,3,3,1]



<문제>

숫자 n이 주어지면, n번째의 파스칼 삼각형 숫자를 리스트에 담아 반환하라.

 */


package Array;

import java.util.List;
import java.util.ArrayList;


public class PascalsTriangle119 {
	/*
<문제 풀이 by yfcheng>

n차마다 1씩 더해준다.

n = 1  ->  [1]
n = 2  ->  [1, 1]
n = 3  ->  [1, 1, 1]
n = 4  ->  [1, 1, 1, 1]
n = 5  ->  [1, 1, 1, 1, 1]
...

1씩 더해주면서, 양 끝을 제외한 중간 숫자들은, 그 전 index에 위치한 숫자 와 해당숫자를 더해준다.
1  
1 1 
1 2 1 
1 3 3 1 
1 4 6 4 1 


Runtime: 1 ms, faster than 89.04% of Java online submissions for Pascal's Triangle II.
Memory Usage: 33.6 MB, less than 5.27% of Java online submissions for Pascal's Triangle II.

	 */

	public static List<Integer> getRow(int rowIndex)
	{
		List<Integer> rst = new ArrayList<>();
		
		for(int i = 0; i <= rowIndex; i++)
		{
			rst.add(1);

			for(int j = i-1; j > 0; j--)
			{
				rst.set(j, rst.get(j-1)+rst.get(j));
			}
		}
		return rst;
    }
	public static void main(String[] args) 
	{
		int rowIndex = 3;
		System.out.println(getRow(rowIndex));
	}
}
