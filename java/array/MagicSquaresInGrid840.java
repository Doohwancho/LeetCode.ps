/*
	A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.
	
	Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
	
	 
	
	Example 1:
	
	Input: [[4,3,8,4],
	        [9,5,1,9],
	        [2,7,6,2]]
	Output: 1
	Explanation: 
	The following subgrid is a 3 x 3 magic square:
	438
	951
	276
	
	while this one is not:
	384
	519
	762
	
	In total, there is only one magic square inside the given grid.
	
	
	<문제>
	
	magic square이란, 3x3짜리 2차원 배열에서, 가로,세로,양 대각선에 위치한 값들의 합이 모두 같은 것을 뜻한다.
	
	예를들어, 밑의 2차원 배열은 가로, 세로, 대각선들의 합이 모두 15이므로 magic square이다.
	
	438
	951
	276
	
	이와같이, 인풋 2차원 배열이 다음과 같이 주어졌을 때, 주어진 2차원 배열 안 3x3 subarray들 중, magic square이 몇개있는지 구하라.
	
 */

package Array;

public class MagicSquaresInGrid840 {
	
	/*
	//<문제 풀이 - trial01>
	
	//all elements are distinct**에서 막힘
	
	//[[5,5,5],[5,5,5],[5,5,5]]
	
	//일때 0으로 나와야 하는데 1로나옴
	
	//또한 가로로 늘어난 3x3밖에 적용이 안됨 
	
	public static int numMagicSquaresInside(int[][] grid) {
        
		int rst = 0;
		//int row  = grid.length;
		int column = grid[0].length;
		
		for(int i = 0; i < column-2; i++)
		{
			int width = grid[0][i]+grid[0][i+1]+grid[0][i+2];
			int height = grid[0][i]+grid[1][i]+grid[2][i];
			int diagnal = grid[0][i]+grid[1][i+1]+grid[2][i+2];
			int reverseDiagnal = grid[0][i+2]+grid[1][i+1]+grid[2][i];
			
			if(width + height == diagnal + reverseDiagnal)
			{
				rst++;
			}
		}
		
		return rst;
    }
	*/
	
	//<문제풀이 by wangzi6147>
	
	//위의 해결책에 개선버전
	
	//가로만 가는게 아니라 세로로도 적용(2중 for문이용)
	
	//또한 record변수를 이용해 1미만, 9초과하거나 중복되는 값이 나오면 다음것으로 넘어감
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Magic Squares In Grid.
	//Memory Usage: 34.8 MB, less than 100.00% of Java online submissions for Magic Squares In Grid.
	
	private static boolean magicSqr(int[][] grid, int row, int column)
	{
		int[] record = new int[10];
		
		for(int i = row; i < row+3; i++)
		{
			for(int j = column; j < column+3; j++)
			{
				if(grid[i][j] > 9 || grid[i][j] < 1 || record[grid[i][j]] == 1) 
					return false;
				record[grid[i][j]]=1;
			}
		}
		
		int diagnalSum1 = grid[row][column] + grid[row+1][column+1] + grid[row+2][column+2];
		int diagnalSum2 = grid[row][column+2] + grid[row+1][column+1] + grid[row+2][column];
		
		if(diagnalSum1 != diagnalSum2) return false;
		
		int widthSum = grid[row][column] + grid[row][column+1] + grid[row][column+2];
		int heightSum = grid[row][column] + grid[row+1][column] + grid[row+2][column];
		
		if(widthSum != heightSum) return false;
		
		return true;
	}
	
	public static int numMagicSquaresInside(int[][] grid) {
		
		int rst = 0;
		int row = grid.length-2;
		int column = grid[0].length-2;
		
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < column; j++)
			{
				if(magicSqr(grid,i,j))
					rst++;
			}
		}
		
		return rst;
	}
	

	public static void main(String[] args) {
		int[][] grid = {{4,3,8,4},
		                {9,5,1,9},
		                {2,7,6,2}};
		
		System.out.println(numMagicSquaresInside(grid));
	}
}
