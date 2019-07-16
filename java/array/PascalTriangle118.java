/*
	
	Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
	
	Example:
	
	Input: 5
	Output:
	[
	     [1],
	    [1,1],
	   [1,2,1],
	  [1,3,3,1],
	 [1,4,6,4,1]
	]
	
	
	<문제>
	
	숫자가 주어지면, 숫자만큼의 층이 있는 파스칼 삼각형을 만들라.
	
	예를들어, 4를 입력하면, 5층의 파스칼 삼각형을 다음과 같이 만들면 된다.
	
	Input: 4
	Output:
	[
	     [1],
	    [1,1],
	   [1,2,1],
	  [1,3,3,1]
	]
	
 */


package Array;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class PascalTriangle118 {
	
	/*
	 * 
	<문제풀이 by jeantimex>
	
	
	먼저 반환할 리스트안에 리스트를 rst 변수로 선언해 준다. List<List<Integer>> rst = new ArrayList<List<Integer>>();
	
	2중 for문으로 돌면서, 먼저 해당층의 리스트를 List<Integer> list = new ArrayList<Integer>(Arrays.asList(1));
	
	요렇게 선언하는데, new ArrayList<>();은 new ArrayList<List<Integer>>();와 같은 말이다.
	
	Arrays.asList(1)은 값이 1이 담겨진 리스트를 하나 만들어준다. [1]
	
	그 이전층의 i-1번째와 i번째를 더해준 값을 넣어주고, 맨 마지막엔 1을 if(i > 0) list.add(1); 이렇식으로 넣어주고 리스트가 완성됬으면 다시 rst에 넣어준다.
	
	
	Runtime: 1 ms, faster than 21.27% of Java online submissions for Pascal's Triangle.
	Memory Usage: 34.1 MB, less than 5.30% of Java online submissions for Pascal's Triangle.
	
	 */
	/*
	public static List<List<Integer>> generate(int numRows) 
	{
		List<List<Integer>> rst = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < numRows; i++)
        {
            List<Integer> list = new ArrayList<Integer>(Arrays.asList(1));
            
            for(int j = 1; j < i; j++)
            {
                list.add(rst.get(i-1).get(j-1) + rst.get(i-1).get(j));
            }
            
            if(i > 0) list.add(1);
            
            rst.add(list);
        }
        return rst;
    }
	*/
	
	/*
		<문제풀이 by caikehe>
		
		dynamic programming방식으로 푼 예제.
		
		row = [1]을 만들어 놓고, 층이 추가될때마다 .add(1)을 해준다.
		
		그러면서 양쪽 맨 끝을 제외한 알맹이들은 역순으로 돌면서 (i-1)+(i+1)을 해주며 바꿔나간다.
		
		실행 순서는 대략 이렇게 된다
		
		n = 5;
		
		[1]
		[1, 1]
		[1, 1, 1]
		[1, 2, 1]
		[1, 2, 1, 1]
		[1, 2, 3, 1]
		[1, 3, 3, 1]
		[1, 3, 3, 1, 1]
		[1, 3, 3, 4, 1]
		[1, 3, 6, 4, 1]
		[1, 4, 6, 4, 1]
		
		
		output : [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
		
		Runtime: 0 ms, faster than 100.00% of Java online submissions for Pascal's Triangle.
		Memory Usage: 34 MB, less than 5.30% of Java online submissions for Pascal's Triangle.
		
	 */
	
	public static List<List<Integer>> generate(int numRows) 
	{
		List<Integer> row = new ArrayList<>();
		List<List<Integer>> rst = new ArrayList<>();
		
		for(int i = 0; i < numRows; i++)
		{
			row.add(1);
			
			for(int j = row.size()-2; j > 0; j--)
			{
				row.set(j,  row.get(j) + row.get(j-1));
			}
			rst.add(new ArrayList<>(row));
		}
		return rst;
	}
	
		

	public static void main(String[] args) 
	{
		int numRows = 5;
		System.out.println(generate(numRows));
	}
}
