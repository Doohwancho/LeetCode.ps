/*
	Given a matrix A, return the transpose of A.
	
	The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.
	
	 
	
	Example 1:
	
	Input: [[1,2,3],[4,5,6],[7,8,9]]
	Output: [[1,4,7],[2,5,8],[3,6,9]]
	Example 2:
	
	Input: [[1,2,3],[4,5,6]]
	Output: [[1,4],[2,5],[3,6]]
	
	
	
	<문제>
	
	행렬 A가 주어지면, 행렬 A를 transpose 시켜 반환하라.
	
	transpose란 좌상에서 우하로 내려오는 대각선을 기준으로 뒤집은 것을 의미한다.
	
	int[][] A = {{1,2,3},
				 {4,5,6},
				 {7,8,9}};
		
	int[][] output = {{1,4,7},
					  {2,5,8},
					  {3,6,9}};
					  
	A가 주어졌을 때, output은 이렇게 나와야 한다.
	
	
	
	<문제 풀이>
	
	step01) 답을 담을 rst변수를 선언한다. 이 때, row와 column의 길이를 A의 column과 row의 길이로 설정해 준다. 왜냐하면, A가 2x3인 경우, rst는 3x2의 형태로 반환되어야 하기 때문이다.
	
	step02) A를 for문으로 돌면서, row는 i, column은 j로 설정한다. 이 때, rst에 넣을 때는, row j, column i에 넣어준다.
	
 * 
 */

package Array;

public class TransposeMatrix867 {
	public static int[][] transpose(int[][] A)
	{
		//step01
		int[][] rst = new int[A[0].length][A.length];
		
		//step02
		for(int i = 0; i < A.length; i++)
		{
			for(int j = 0; j < A[0].length; j++)
			{
				rst[j][i] = A[i][j];
			}
		}
		return rst;
	}
	
	
	public static void main(String[] args) {
		int[][] A = {{1,2,3},
					 {4,5,6},
					 {7,8,9}};
		
		int[][] output = {{1,4,7},
						  {2,5,8},
						  {3,6,9}};
		
		System.out.println(transpose(A));
		}
		
	}
