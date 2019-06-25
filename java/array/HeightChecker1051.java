/*
 * 
	Students are asked to stand in non-decreasing order of heights for an annual photo.
	
	Return the minimum number of students not standing in the right positions.  (This is the number of students that must move in order for all students to be standing in non-decreasing order of height.)
	
	 
	
	Example 1:
	
	Input: [1,1,4,2,1,3]
	Output: 3
	Explanation: 
	Students with heights 4, 3 and the last 1 are not standing in the right positions.
	
	
	
	
	<문제>
	
	int[] 리스트가 주어진다. [1,1,4,2,1,3]를 예시로 든다. 여기서 각 숫자는 학생의 키를 의미한다. 학생들은 키가 작아지지 않게 순서대로 서있어야 한다.
	
	순서대로 서있게 정렬한 것과, 처음 주어진 학생들의 array를 비교하여, 본인 순서에 맞지 않게 서있는 학생의 수를 반환하라.
	
	예를들어, [1,1,4,2,1,3]을 키순으로 새우면, [1,1,1,2,3,4]가 된다.
	
	여기서 3번째, 5번째, 6번째 값인(4, 1, 3)이 본인 순서에 맞지 않게 서 있으므로, 3을 반환한다.
	
 */


package Array;

import java.util.Arrays;

public class HeightChecker1051 {
	public static int heightChecker(int[] heights)
	{
//		trial01 
		
//		문제 이해를 못하고, 단순히 전 학생이 그 다음 학생보다 키가 크면 +1하게끔 함
		
		
//		int cnt = 0;
//		
//		for(int idx = 1; idx < heights.length; idx++)
//		{
//			if(heights[idx-1] > heights[idx])
//			{
//				continue;
//			}
//			else
//			{
//				cnt++;
//			}
//		}
//		return heights.length-cnt;
		
		
		//solution by kylewzk
		
		int[] org = new int[heights.length];
        System.arraycopy(heights, 0, org, 0, heights.length);
        //System.arraycopy(복사하고 싶은 array, 복사할 array에 어느 index부터 담을 것인지, 복사한 값을 담을 array, 담을 array에 어느 index에 담을 것인지, 총 몇개를 복사할 것인지);
        Arrays.sort(heights);
        
//        for(int element: org) { System.out.print(element); }
//        System.out.println();
//        for(int element: heights) { System.out.print(element); }
//        System.out.println();
		
        int res = 0;
        for(int i = 0; i < heights.length; i++)  if(org[i] != heights[i]) res++;
        return res;
	}
	
	
	public static void main(String[] args) 
	{
		int[] heights = {1,1,4,2,1,3};
		System.out.println(heightChecker(heights));
	}

}
