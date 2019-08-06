/*
	In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. 
	
	There is at least one empty seat, and at least one person sitting.
	
	Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 
	
	Return that maximum distance to closest person.
	
	Example 1:
	
	Input: [1,0,0,0,1,0,1]
	Output: 2
	Explanation: 
	If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
	If Alex sits in any other open seat, the closest person has distance 1.
	Thus, the maximum distance to the closest person is 2.
	Example 2:
	
	Input: [1,0,0,0]
	Output: 3
	Explanation: 
	If Alex sits in the last seat, the closest person is 3 seats away.
	This is the maximum distance possible, so the answer is 3.
 
	
	
	
	<문제>
	
	히키코모리 문제.
	
	리스트 {1,1,0,0,0,1,0}에서 1은 사람이 앉은 곳, 0은 공석을 의미한다.
	
	여기서 사람들과 최대한 멀리 앉으려고 할 때,
	
	앉았을 때 가장 가까운 사람과 거리는?

*/

package Array;

public class MaximizeDistanceToClosestPerson849 {
	
	/*
	//<Trial01>
	
	//1일때 -0.3, 0일때 +1을 하여
	
	//0과1의 조합이 최대인 것을 찾아 가장 가까운 사람과 거리를 반환하려고 했으나...
	
	//막힘...
	
    public static int maxDistToClosest(int[] seats) {
        float subZero = 0;
    	float maxZero = 0;

        for(int i : seats)
        { 
        	if(i == 0) 
    		{
        		subZero++; 
        		if(i == seats[seats.length-1])
        			maxZero = Math.max(maxZero, subZero);
    		}
        	else
        	{	
        		if(subZero % 1 > 0.5 && subZero > 0) // 막힌부분 -> 10001에서 1000 > 10001으로 인식
        		{
        			subZero -= 0.3f;
        			maxZero = Math.max(maxZero, subZero); 
        			subZero = -0.3f;
        			continue;
        		}
        		subZero -= 0.3f;
        		maxZero = Math.max(maxZero, subZero);
        		
        		subZero = -0.3f;
        	}
        	
        	//System.out.println(subZero +"    "+maxZero);
        }
        
    	if(maxZero % 1 > 0.5)
    	{
    		return (int)maxZero;
    	}
    	else if(((int)maxZero) % 2 == 0)
    	{
    		return ((int)maxZero) / 2;
    	}
    	else
    	{
    		return ((int)maxZero) / 2 + 1;
    	}
    }
	 */
	
	//<문제풀이 by lee215>
	
	//result = max(distance at the beginning, distance in the middle / 2, distance at the end)
	
	 public static int maxDistToClosest(int[] seats) {
	        int res = 0, n = seats.length, last = -1;
	        for (int i = 0; i < n; ++i) {
	            if (seats[i] == 1) {
	                res = last < 0 ? i : Math.max(res, (i - last) / 2);
	                last = i;
	            }
	        }
	        res = Math.max(res, n - last - 1);
	        return res;
	    }
	
	
	
	public static void main(String[] args) {
		//양끝에 1이 있으면 
		//{1,0,0,0,0,1}       n/2   -> 2
		//{1,0,0,0,0,0,1}     (n+n%2)/2 -> 3
		//{1,0,0,0,0,0,0,1}   n/2   -> 3
		//{1,0,0,0,0,0,0,0,1} (n+n%2)/2 -> 4
		
		//한쪽만 1이 있으면
		//{1,0,0,0}     n -> 3
		//{0,0,0,0,1}   n -> 4
		//{0,0,0,0,0,1} n -> 5
		
		//int[] seats = {1,0,0,0,1,0,1};
		//int[] seats = {1,0,0,0}; //자리 택하고(자리에 0을 지우고) 남은 0수의 +1
		//int[] seats = {1,0,0,1};
		//int[] seats = {0,1,1,1,0,0,1,0,0};
		//int[] seats = {0,0,1};
		//int[] seats = {0,0,0,0,1,0,1};
		//int[] seats = {0,0,0,1,1,0,1,1,0,0,0,1,0,0,1,0,0};
		//int[] seats = {0,0,0,1,0,0,0,1};
		int[] seats = {1,1,0,0,0,1,0};

		
		
		System.out.println(maxDistToClosest(seats));
	}
}
