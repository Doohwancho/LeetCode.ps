/*
	A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:
	
	-2: turn left 90 degrees
	-1: turn right 90 degrees
	1 <= x <= 9: move forward x units
	Some of the grid squares are obstacles. 
	
	The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
	
	If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)
	
	Return the square of the maximum Euclidean distance that the robot will be from the origin.
	
	 
	
	Example 1:
	
	Input: commands = [4,-1,3], obstacles = []
	Output: 25
	Explanation: robot will go to (3, 4)
	Example 2:
	
	Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
	Output: 65
	Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
	 
	
	Note:
	
	0 <= commands.length <= 10000
	0 <= obstacles.length <= 10000
	-30000 <= obstacle[i][0] <= 30000
	-30000 <= obstacle[i][1] <= 30000
	The answer is guaranteed to be less than 2 ^ 31.
	
	
	
	<문제>
	
	
	
	
	
	
	
 */

package Greedy;

import java.util.HashSet;
import java.util.Set;

public class WalkingRobotSimulation874 {
	
	/*
	//<Trial01>
	
	//Obstacles없을때 유효한 메소드
	
	public static int robotSim(int[] commands, int[][] obstacles) {
		int[] position = {0,0}; //현재의 x,y좌표
		int xyDirection = 1; //position의 x값을 바꿀지 y값을 바꿀지 결정
		//int[] Direction = {1,2,3,4}; //west, north, east, south
		int currDir = 2; //시작은 북쪽으로 이동
		
		for(int move : commands) {
			if(move < 0) { //방향전환을 한다면
				xyDirection = Math.abs(xyDirection-1); //좌로 트나 우로트나 y축에서 움직이던게 x축으로 바뀌니 xyDirection변경
				if(move == -1) { //우로틀면
					currDir++;   //currDir하나 더한다. 그러면 int[] Direction의 숫자대로 방향전환
					if(currDir > 4) { //4가 south. 5에서 한번더 우로틀면 west이므로
						currDir = 1; //west인 1로 픽스
					}
				}
				else {
					currDir--;
					if(currDir < 1) {
						currDir = 4;
					}
				}
			}
			else {
				if(currDir == 2 || currDir == 3) { //north, east로 갈때만 플러스, 나머진 마이너스
					position[xyDirection] += move;
				}
				else {
					position[xyDirection] -= move;
				}
			}
		}
		
		return position[0]*position[0]+position[1]*position[1];
	}
	*/
	
	
	//<문제풀이 by caraxin>
	
	//Runtime: 30 ms, faster than 62.96% of Java online submissions for Walking Robot Simulation.
	//Memory Usage: 50.1 MB, less than 100.00% of Java online submissions for Walking Robot Simulation.
	
    public static int robotSim(int[] commands, int[][] obstacles) {
        //obstacle을 .contains로 찾아야 하기 때문에 set에 담아둠. String형태로.
    	Set<String> set= new HashSet<>();
        for (int[] o: obstacles) set.add(o[0]+","+o[1]);
        
        //방향. k로 방향을 정한다. k가 0이면 [0,1], 1이면 [1,0], 2이면 [0,-1], 3이면[-1,0].
        //각각 north, east, south, west
        int[] d= new int[]{0,1,0,-1,0};
        
        //x,y축 + k값은 방향 + res는 결과값
        int x=0, y=0, k=0, res=0;
        for (int c: commands){
            if (c==-1) k++;        //우측이면 k+1. 시계방향으로 돌리는것과 같다고 보면 됨. 방향도 시계방향으로 한칸감.
            else if (c==-2) k+=3;  //좌측이면 k+3. 3이 초과되서 4이상 숫자가 되면 어짜피 else구문에 %=4에서 다시 0부터 3까지 숫자로 됨.
            else{
                k%=4;
                for (int i=0; i<c; i++){     //해당칸에 4칸움직이라고했으면 for문으로 한칸씩 움직임. obstacle로 막힐 수 있기 때문.
                    int X=x+d[k], Y=y+d[k+1];         //좌표를 [0,1],[1,0] ... 이런식으로 묶은게 아니라 {0,1,0,-1,0} 이런식으로 해서 단순히 k+1해서 xy구할수있게 만든게 똑똑허네
                    if (set.contains(X+","+Y)) break; //obstacle을 .contains로 찾고, 찾았으면 한칸 더 움직이기 전에 멈춘다.
                    x=X;
                    y=Y;
                }
            }
            res=Math.max(res,x*x+y*y); //Return the square of the maximum Euclidean distance that the robot will be from the origin. 
            //원점으로부터 가장많이 떨어진 곳의 제곱값을 반환해야하기 때문에 Math.max()함
        }
        
        return res;
    }
	
	
	public static void main(String[] args) {
		int[] commands = {4,-1,4,-2,4};
		int[][] obstacles = {{2,4}};
		System.out.println(robotSim(commands, obstacles));
	}
}
