package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class NetworkDelayTime743 {
	
	//<문제풀이1 by alizhiyu46> 
	
	//약간 bfs쪽으로 꺾은 Dijkstra's algo
	
	//어씨 아리까리하네
	
	//왜 pq(a-b)넣었지?
	
	//테스트 케이스
	
	//[[1,2,1],[2,3,2],[1,3,4]]
	//3
	//1
	
	//에서, pq안쓰고 linkedlist쓰면,
	
	//3이 아니라 4가 나옴. 근데 문제가 How long will it take for all nodes to receive the signal? 잖아
	
	//1->3 가는데 4초걸리고
	
	//1->2->3가는데 1+2초해서 3초걸리면, 4초도 맞지 않나?
	
	//pq를 쓰면 가장 작은 초 위주로 먼저 돌긴 하는데,
	
	//작은 초가 걸린다고 해서 node끝까지 간다는 보장도 없고 좀 아리까리하네

	
	//Runtime: 31 ms, faster than 23.93% of Java online submissions for Network Delay Time.
	//Memory Usage: 43.9 MB, less than 5.98% of Java online submissions for Network Delay Time.
	
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
        for(int[] t : times){
            if(map.get(t[0]) == null){
                map.put(t[0], new HashMap<>());
            }
            map.get(t[0]).put(t[1], t[2]); 
        }
        
        boolean[] visited = new boolean[N+1];
        int rst = 0;
        
        Queue<int[]> q = new PriorityQueue<>((a,b) -> (a[0] - b[0])); //오름차순
        q.add(new int[]{0, K});    
    
        while(!q.isEmpty()){
            int[] pos = q.poll();
            int curr = pos[1];
            int accCost = pos[0];
            
            if(visited[curr] == true) continue;
            visited[curr] = true;
            
            rst = accCost;
            N--;
            if(N == 0) return rst;
            else if(N < 0) return -1;
            
            if(map.containsKey(curr)){
                for(int next : map.get(curr).keySet()){
                    int nextCost = map.get(curr).get(next);
                    q.offer(new int[] { accCost + nextCost, next });
                }
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
    	Queue<int[]> q = new PriorityQueue<>((a,b) -> (a[0] - b[0]));
    	
    	q.add(new int[] {100, 2});
    	q.add(new int[] {0, 1});
    	
    	
    	while(!q.isEmpty()) {
    		int[] tmp = q.poll();
    		System.out.println(tmp[0]+" "+tmp[1]);
    	}
	}
}
