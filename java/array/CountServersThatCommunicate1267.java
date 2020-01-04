/*
	You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.
	
	Return the number of servers that communicate with any other server.
	
	 
	
	Example 1:
	
	
	
	Input: grid = [[1,0],[0,1]]
	Output: 0
	Explanation: No servers can communicate with others.
	Example 2:
	
	
	
	Input: grid = [[1,0],[1,1]]
	Output: 3
	Explanation: All three servers can communicate with at least one other server.
	Example 3:
	
	
	
	Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
	Output: 4
	Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.
	
	
	
	
	<문제>
	
	grid = [[1,0,0],[1,1,0],[0,0,1]
	
	여기서 1은 서버가 있음을 나타내고, 0은 없음을 나타낸다.

	그림으로 그려보면
	
	o x x
	o o x
	x x o
	
	행과 열에 서버 혼자만 있는 것(오른쪽 아래것)을 외톨이 서버라고 할 때,
	
	외토리 서버가 아닌 총 서버의 갯수를 반환하라.
	
 */

package Array;

import java.util.ArrayList;
import java.util.List;

public class CountServersThatCommunicate1267 {
	
	/*
	//<문제풀이1>
	
	//2중 for문 돌면서 총 갯수, 해당 행과 열에 컴퓨터 갯수 파악 후
	
	//다시 2중 for문 돌면서 해당 행과 열에 컴퓨터 갯수가 1개인것 중에서 grid[i][j] == 1인것의 갯수(server that does NOT communicate) 파악해서
	
	//총 서버 갯수 - 혼자인 서버갯수 반환.
	
	//Runtime: 4 ms, faster than 57.58% of Java online submissions for Count Servers that Communicate.
	//Memory Usage: 54.9 MB, less than 100.00% of Java online submissions for Count Servers that Communicate.
	
	public int countServers(int[][] grid) {
        int[] rows = new int [grid.length];
        int[] columns = new int [grid[0].length];
        int total = 0;
        int loner = 0;
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]==1){
                    rows[i]++;
                    columns[j]++;
                    total++;
                }
            }
        }
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]==1 && rows[i]==1 && columns[j]==1){
                    loner++;
                }
            }
        }
        
        return total - loner;
    }
    */
	
	
	//<문제풀이2>
	
	//같은 로직, 성능개선
	
	//Runtime: 3 ms, faster than 96.56% of Java online submissions for Count Servers that Communicate.
	//Memory Usage: 52.7 MB, less than 100.00% of Java online submissions for Count Servers that Communicate.
	public int countServers(int[][] grid) {
        List<int[]> xys = new ArrayList<>();
        int[] rows = new int [grid.length];
        int[] columns = new int [grid[0].length];
        int loner = 0;
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]==1){
                    rows[i]++;
                    columns[j]++;
                    int[] xy = new int[2];
                    xy[0] = i;
                    xy[1] = j;
                    xys.add(xy);
                }
            }
        }
        
        for(int[] xy : xys){
            if(rows[xy[0]] == 1 && columns[xy[1]] == 1){
                loner++;
            }
        }

        return xys.size() - loner;
    }
}
