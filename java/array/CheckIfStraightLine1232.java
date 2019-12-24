/*
	You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.
	
	
	
	<문제>
	
	coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
	
	주어진 점들의 기울기가 일정하면 true, 일정하지 않다면 false를 반환하라.
 */

package Array;

public class CheckIfStraightLine1232 {
	
	//<문제풀이1>
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Check If It Is a Straight Line.
	//Memory Usage: 42.1 MB, less than 100.00% of Java online submissions for Check If It Is a Straight Line.
	
    public boolean checkStraightLine(int[][] coordinates) {
        double slope = (double)(coordinates[1][1]-coordinates[0][1]) / (double)(coordinates[1][0]-coordinates[0][0]);
        for(int i = 2; i < coordinates.length; i++){
            if(slope != (double)(coordinates[i][1]-coordinates[i-1][1]) / (double)(coordinates[i][0]-coordinates[i-1][0])) {
            	return false;
            }
        }
        return true;
    }
}
