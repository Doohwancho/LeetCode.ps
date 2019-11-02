package Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TwoCityScheduling1029 {
	
	//<Trial01>
	
	//cost a,b간의 차이를 이용해서 뭘 해야할 것 같았지만 만들어내진 못함. 

	/*
	//diff가 큰것부터 작은숫자를 넣는데, 그게 size만큼 차면, 나머지는 큰숫자를 넣는다.
	
	public static int twoCitySchedCost(int[][] costs) {
		
		//abs(a-b)차이가 가장 큰 숫자중 더 작은숫자를 costs[0].length()/2개까지 더하고, 나머지 숫자들은 더 큰숫자를 더해줌
		
		//첫번째 loop, int[costs[0].length()/2] 에 cost가장 큰거 순서대로 넣음
		
		//두번째 loop abs(a-b) in int[], 더 작은숫자 더함. else 큰숫자 더함.
		
		//근데 이 경우는 어떡하지? (10,30) (5,25) 상관없구나.
		
//		int size = costs[0].length/2;
//		
//		int[] container = new int[size];
//		
//		for(int[] cost : costs) {
//			int diff = Math.abs(cost[0]-cost[1]);
//			if(diff > container[0]) {
//				
//			}
//		}

		
		
		int rst = 0;
		int size = costs.length/2;
		int cnt = 0;
		int left = 0;
		int right = 0;
		
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
		for(int[] cost : costs) {
			int diff = Math.abs(cost[0]-cost[1]);
			pq.offer(diff);
			System.out.println(diff);
		}
		
		System.out.println("-----------------1st--------------------");
		
		for (int i = 0; i < costs.length; ++i) {
            int gap = pq.poll();
            System.out.println(gap);
            System.out.println("second   "+i);
            
            for(int[] cost : costs) {
            	int diff = Math.abs(cost[0]-cost[1]);
            	if(gap == diff) {
            		if(cnt < size) {
            			System.out.println("cnt < size     "+cost[0]+"   "+cost[1]);
                		if(cost[0] < cost[1]) {
                			rst += cost[0];
                			left++;
                		}
                		else {
                			rst += cost[1];
                			right++;
                		}
                		cnt++;
                		break;
            		}
            		else {
            			System.out.println("cnt > size     "+cost[0]+"   "+cost[1]);
            			if(left < size) {
            				if(cost[0] < cost[1]) {
            					rst += cost[0];
            					left++;
            				}
            			}
            			else if(right < size) {
            				if(cost[0] > cost[1]) {
            					rst += cost[1];
            					right++;
            				}
            			}
            		}
            		break;
            	}
            }
            System.out.println("loop ended"+"   "+rst);
		}
		
		//259           *
		//      54      *
		//      667      
		//184
		//      118     *
		//577
		 
		//259           2
		//      54      3
		//      667     4
		//      139     6
		//      118     1
		//      469     5
			
		
		return rst;
    }
    */
	
	
	
	//<문제풀이 by tankztc>
	
	//각 cost의 델타값을 기준으로 어레이를 재정렬 한다.
	
	//예시) [[10,20],[30,200],[400,50],[30,20]] --> delta [-10, -170, 350, 10] 
	
	//--> sort [-170, -10, 10, 350] (==[[30,200],[10,20],[30,20],[400,50]])
	
	//이런 마법같은 방법이..!
	
	
	public static int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> {
            return (a[0] - a[1]) - (b[0] - b[1]);
        });
        
        for(int[] cost : costs) {
        	System.out.println(cost[0]+"  "+cost[1]);
        }
        
        int res = 0;
        for (int i = 0;i<costs.length;i++) {
            if (i < costs.length/2) {
                res += costs[i][0];
            } else 
                res += costs[i][1];
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		int[][] costs = {{259,770},{448,54},{926,667},{184,139},{840,118},{577,469}};
		System.out.println(twoCitySchedCost(costs));
	}
}
