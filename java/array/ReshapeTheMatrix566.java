/*
	In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.
	
	You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.
	
	The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.
	
	If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
	
	Example 1:
	Input: 
	nums = 
	[[1,2],
	 [3,4]]
	r = 1, c = 4
	Output: 
	[[1,2,3,4]]
	Explanation:
	The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
	Example 2:
	Input: 
	nums = 
	[[1,2],
	 [3,4]]
	r = 2, c = 4
	Output: 
	[[1,2],
	 [3,4]]
	Explanation:
	There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
	
	
	
	<문제>
	
	matrix와 r와 c가 주어진다.
	
	r은 row, c는 column을 의미한다.
	
	다음과 같이 예시가 주어졌다고 하자.
	
	int[][] nums = {{1,2},
					{3,4}};
	int r = 1, c = 4;
	
	원래 matrix는 2x2이나, 1x4로 바꿔서 {{1,2,3,4}}을 반환하면 된다.
	
	단, 다음과 같이 reshape이 불가능하게 나왔을 경우, 원본을 반환한다.
	
	nums = 	[[1,2],
			 [3,4]]
	r = 2, c = 4
	
	
	
	<문제 풀이>
	
	step01) nums를 reshape했을 때, rxc로 변형이 가능한지 확인.
	
	step02) 리스트를 만들어 nums에 있는 모든 값들을 대입
	
	step03) rxc의 사이즈의 int[][] rst를 만들어 리스트에 대입한 값을 for문으로 돌면서 차례대로 입력
	
	
	Runtime: 3 ms, faster than 17.88% of Java online submissions for Reshape the Matrix.
	Memory Usage: 38.4 MB,
 * 
 */

package Array;

import java.util.List;
import java.util.ArrayList;

public class ReshapeTheMatrix566 {
	
	public static int[][] matrixReshape(int[][] nums, int r, int c) 
	{
		//step01
        if(nums.length*nums[0].length == r*c) 
        {
        	int[][] rst = new int[r][c];
        	List bucket = new ArrayList();
        	int idx = 0;
        	
        	for(int i = 0; i < nums.length; i++)
        	{
        		for(int j = 0; j < nums[0].length; j++)
        		{
        			bucket.add(nums[i][j]);
        		}
        	}
        	
        	for(int p = 0; p < r; p++)
        	{
        		for(int q = 0; q < c; q++)
        		{
        			rst[p][q]=(int)bucket.get(idx); //bucket.get()을 하면 object type으로 나옴. 이걸 int type으로 변환해야함.
        			idx++;
        		}
        	}
        	
        	return rst;
        }
        else
        {
        	return nums;
        }
    }
	public static void main(String[] args) 
	{
		int[][] nums = {{1,2},
						{3,4}};
		int r = 1, c = 4;
		System.out.println(matrixReshape(nums, r, c));
	}
}
