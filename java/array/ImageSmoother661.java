/*
	Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
	
	Example 1:
	Input:
	[[1,1,1],
	 [1,0,1],
	 [1,1,1]]
	Output:
	[[0, 0, 0],
	 [0, 0, 0],
	 [0, 0, 0]]
	Explanation:
	For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
	For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
	For the point (1,1): floor(8/9) = floor(0.88888889) = 0
	
	
	<문제>
	2차원 배열 이 다음과 같이 주어진다.
	
	[[1,1,1],
	 [1,0,1],
	 [1,1,1]]
	 
	 여기서 각 좌표마다 그 좌표를 포함한 주변 값들이 있다. 예를들어 행:0 열:1의 surrounding은 
	 [[1,1,1],
	  [1,0,1]]
	 이고, 행:1 열:1의 surrounding은
	 [[1,1,1],
	 [1,0,1],
	 [1,1,1]]
	 이고, 행:2 열:2의 srrounding은 
	 [[0,1],
	  [1,1]]
	 이다.
	 이 surrounding들의 값들의 평균을 소숫점은 버리고 새로운 2차원 배열에 넣어서 반환하라.
	 
	 
	 
	 <문제풀이>
	 
	 2중 for문으로 각 원소들을 돌면서, 각 원소들의 좌표에서 i-1~i+1까지(행), j-1~j+1까지(열)을 또다른 2중 for문으로 돌아 평균을 구하면 된다.
	
 */



package Array;

public class ImageSmoother661 
{
	
	public static int[][] imageSmoother(int[][] M) {
		int row = M.length, column = M[0].length;
        int[][] rst = new int[row][column];

        for(int i = 0; i < row; i++)
        {
        	for(int j = 0; j < column; j++)
        	{
        		int sum = 0;
        		int cnt = 0;
        		
        		//해당 좌표 주변의 값들의 평균을 구하는 부분
        		for(int p = i-1; p < i+2; p++)
        		{
        			for(int q = j-1; q < j+2; q++)
        			{
        				if(p >= 0 && p < row && q >= 0 && q < column) 
        				{
        					sum+=(M[p][q]);
        				}
        			}
        		}
        		rst[i][j] = (int)Math.floor(sum/cnt);
        	}
        }
        return rst;		
    }
	public static void main(String[] args) 
	{
		int[][] M = {
					 {1,1,1},
					 {1,0,1},
					 {1,1,1}
					};
		System.out.println(imageSmoother(M));
	}
}
