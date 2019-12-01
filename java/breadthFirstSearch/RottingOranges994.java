/*
	In a given grid, each cell can have one of three values:
	
	the value 0 representing an empty cell;
	the value 1 representing a fresh orange;
	the value 2 representing a rotten orange.
	Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
	
	Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
	
	 
	
	Example 1:
	
	
	
	Input: [[2,1,1],[1,1,0],[0,1,1]]
	Output: 4
	Example 2:
	
	Input: [[2,1,1],[0,1,1],[1,0,1]]
	Output: -1
	Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
	Example 3:
	
	Input: [[0,2]]
	Output: 0
	Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
	 
	
	Note:
	
	1 <= grid.length <= 10
	1 <= grid[0].length <= 10
	grid[i][j] is only 0, 1, or 2.
	
	
	
	
	<문제>
	
	아무것도 없는건 0, 멀쩡한 토마토는 1, 썩은 토마토는 2가 2차원 배열로 주어짐.
	
	[[2,1,1],
	 [1,1,0],
	 [0,1,1]]
	 
	
	썩은 토마토 옆에있으면 멀쩡한 토마토가 썩는다. 그림참조. https://leetcode.com/problems/rotting-oranges/
	
	점점 썩어나갈 때, 몇 턴 후에 모든 토마토가 썩을까?
	
	단, 옆에 썩은토마토가 없어서 생존한 토마토가 하나라도 있으면 -1반환. 
	
	그리고 애초에 주어진 토마토가 모두 썩어있었으면 0반환.
	
 */

package BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges994 {
	
	
	//<문제풀이1>
	
	//bfs
	
	//보기엔 지랄맞은데 알고보면 별거아님
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Rotting Oranges.
	//Memory Usage: 39.9 MB, less than 75.00% of Java online submissions for Rotting Oranges.

	public int orangesRotting(int[][] grid) {
		boolean apple = false;                        //애초에 썩은사과 뿐이라면(==멀쩡한 사과가 하나도 없다면) 0 반환 하기 위한 변수 

		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 2) {                  //썩은사과가 있다면,
					queue.add(new int[] { i, j });      //썩은사과의 좌표를 queue에 넣음.
				} else if (!apple && grid[i][j] == 1) { //멀쩡한 사과가 한개라도 있는지 확인. 다썩었으면 바로 0반환해야 하니깐.
					apple = true;
				}
			}
		}
		if (apple) {                 
			int cnt = 0;

			while (!queue.isEmpty()) {   //queue에 썩은사과의 좌표를 넣고 bfs방식으로 돌림.
				int size = queue.size();
				while (size-- > 0) {
					int[] pos = queue.poll(); //썩은 토마토의 좌표를 하나씩 뽑음
					int x = pos[0];           //x,y라고 변수선언 했는데 이해할때 x는 row의미, y는 column을 의미함.
					int y = pos[1];

					//썩은 토마토 좌표기준, 동서남북으로 멀쩡한 토마토가 있는지 확인, 
					if ((x - 1) >= 0 && grid[x - 1][y] == 1) { 
						grid[x - 1][y] = 2;                    //있다면 썩을사과로 바꾸고
						queue.add(new int[] { x - 1, y });     //새롭게 썩은 사과의 좌표를 queue에 넣음
					}
					if ((x + 1) < grid.length && grid[x + 1][y] == 1) {
						grid[x + 1][y] = 2;
						queue.add(new int[] { x + 1, y });
					}
					if ((y - 1) >= 0 && grid[x][y - 1] == 1) {
						grid[x][y - 1] = 2;
						queue.add(new int[] { x, y - 1 });
					}
					if ((y + 1) < grid[0].length && grid[x][y + 1] == 1) {
						grid[x][y + 1] = 2;
						queue.add(new int[] { x, y + 1 });
					}
				}
				if (!queue.isEmpty())  //매 턴마다 카운트를 1씩 더해줌
					cnt++;
			}
			
			//살아남은 토마토가 하나라도 있다면 -1반환
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					if (grid[i][j] == 1) {               
						return -1;
					}
				}
			}
			return cnt; //모두 다 썩은걸 확인했으면 cnt반환
		} else {
			return 0;         //멀쩡한 사과가 한개도 없으면 0 바로 반환.
		}
	}

}
