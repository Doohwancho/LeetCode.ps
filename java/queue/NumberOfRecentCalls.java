package october;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfRecentCalls {

	//<문제풀이1>
	
	
	//Runtime: 18 ms
	//Memory Usage: 47.1 MB
	
    Queue<Integer> q;
    
    public RecentCounter() {
        q = new LinkedList<>();
    }
    
    public int ping(int t) {
        q.offer(t);
        while(q.peek() < t-3000){
            q.poll();
        }
        return q.size();
    }
}
