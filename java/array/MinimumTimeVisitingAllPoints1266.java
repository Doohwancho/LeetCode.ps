/*
	On a plane there are n points with integer coordinates points[i] = [xi, yi]. Your task is to find the minimum time in seconds to visit all points.
	
	You can move according to the next rules:
	
	In one second always you can either move vertically, horizontally by one unit or diagonally (it means to move one unit vertically and one unit horizontally in one second).
	You have to visit the points in the same order as they appear in the array.
	 
	
	Example 1:
	
	
	Input: points = [[1,1],[3,4],[-1,0]]
	Output: 7
	Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]   
	Time from [1,1] to [3,4] = 3 seconds 
	Time from [3,4] to [-1,0] = 4 seconds
	Total time = 7 seconds
	Example 2:
	
	Input: points = [[3,2],[-2,2]]
	Output: 5
	
	
	
	
	<문제>
	
	x,y좌표가 int[]에 담겨저 다음과 같이 주어진다.
	
	points = [[1,1],[3,4],[-1,0]]
	
	(1,1)에서 (3,4)까지, (3,4)에서 (-1,0)까지의 최단거리의 총 합을 반환하라.
	
	동서남북 + 대각선 포함 한칸 움직일때 +1으로 간주한다.
 */

package Array;

public class MinimumTimeVisitingAllPoints1266 {
	
	
	//<문제풀이1>
	
	//x의 차와 y의 차중 더 값이 큰 값이 최단거리라고 딱보면 알수있음.  
	
	//Runtime: 1 ms, faster than 84.90% of Java online submissions for Minimum Time Visiting All Points.
	//Memory Usage: 42.1 MB, less than 100.00% of Java online submissions for Minimum Time Visiting All Points.
	
    public int minTimeToVisitAllPoints(int[][] points) {
        if(points.length < 2) return 0;
        int rst = 0;
        for(int i = 1; i < points.length; i++){
            rst += Math.max(Math.abs(points[i][1]-points[i-1][1]), Math.abs(points[i][0]-points[i-1][0]));
        }
        return rst;
    }
}
