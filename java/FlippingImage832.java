/*
	Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
	
	To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
	
	To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
	
	Example 1:
	
	Input: [[1,1,0],[1,0,1],[0,0,0]]
	Output: [[1,0,0],[0,1,0],[1,1,1]]
	Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
	Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
	Example 2:
	
	Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
	Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
	Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
	Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
	
	
	
 */

package String;

public class FlippingImage832 {
	public static void flipAndInvertImage(int[][] A)
	{
		//step01 -- flip horizontally
		int vertical = A.length;
		int horizon = A[0].length;
		
		int[][] rst = new int[vertical][horizon]; //horizon, vertical 인가?
		
		
		for(int i = 0; i <= vertical-1 ; i++)
		{
			int flip = 0;
			for(int j = horizon-1; j >= 0 ; j--)
			{
				rst[i][flip] = Math.abs(A[i][j]-1);
				flip++;
			}	
		}
		System.out.println("finished");
		
		for(int q = 0; q <= rst.length-1 ; q++)
		{
			System.out.println(rst[q][0]);
//			for(int w = 0 ; w <= rst[0].length-1; w--)
//			{
//				System.out.println(rst[q][w]);
//			}	
		}
		
	}
	
	
	public static void main(String[] args) {
		int[][] A = {{1,1,0},
		             {1,0,1},
		             {0,0,0}};
		
		int[][] output = {{1,0,0},
						  {0,1,0},
						  {1,1,1}};
		
		int[][] B = {{1,1,0},
	             	 {1,0,1},
	             	 {0,0,0},
	             	 {0,0,1}};
		
		flipAndInvertImage(B);
	}
}
