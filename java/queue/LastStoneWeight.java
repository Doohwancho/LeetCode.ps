package thirtyDayChallenge;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {
	
	/*
	//<Trial01>
	
	//머리깨지것네
	
    public int lastStoneWeight(int[] stones) {
        int[] c = new int[1001];
        for(int i : stones) c[i]++;
        int rst = 0;
        int i = 1000;
        while(i > 0){
            if(c[i] > 0){
                if(c[i] % 2 == 1){
                    if(rst == 0){
                        rst = i;
                        i--;
                    } else {
                        if(rst-i >= i){
                            rst = rst-i;
                            c[i]--;
                            if(c[i] == 0) i--;
                        } else {
                            c[rst-i]++;
                            rst = 0;
                            i--;
                        }
                    }
                }
                else {
                    if(rst == 0){
                        i--;
                        continue;
                    } else {
                        if(rst-i > i){
                            rst = rst-i;
                            c[i]--;
                        } else {
                            c[rst-i]++;
                            rst = i;
                            i--;
                        }
                    }
                }
            } else {
                i--;
            }
        }
        return rst; 
    }
    */
	
    
    //<문제풀이1 by Olsh>
    
    //똑똑허이
	
	//priority queue를 이용해 역순으로 숫자를 넣고, 큰수부터 두개씩 뽑아 서로 다르면 그 차이를 priority queue에 집어넣는 방식
	
	//70 / 70 test cases passed.
	//Status: Accepted
	//Runtime: 1 ms
    //Memory Usage: 37.1 MB	
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(b-a)); //same as Comparator.reverseOrder()
        for(int i : stones) pq.add(i);
        while(pq.size() > 1){
            int i1= pq.poll();
            int i2= pq.poll();
            if(i1!=i2) pq.add(Math.abs(i1-i2));
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
