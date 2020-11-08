package graph;

import java.util.Arrays;
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
	
	
	
	//201108 -- 추가
	
	//N을 한개씩 까면서 돈다는건, 모든 노드를 한번씩 다 돈다는 말
	
	//처음 출발한 노드 K부터 가장 멀리있는 노드는 필연적으로 가장 나중... 이 아니네?
	
	//Queue가 LinkedList가 아니라 PriorityQueue잖아. 그럼 순서 노상관하고 가장 accumulated cost가 낮은애 먼저 처리한다는건데
	
	//PQ -> LinkedList로 바꾸면
	
	//[[1,2,1],[2,3,2],[1,3,4]]
	//3
	//1
	
	//이 testcase에서 막힘.
	
	//아 
	
	//How long will it take for all nodes to receive the signal?
	
	//1->2->3으로 가든, 1->3으로 가든, 3으로 가는건 똑같은데,
	
	//1->2->3으로 가면 총 3초가 걸리고, 1->3으로 가면 총 4초가 걸리잖아?
	
	//어짜피 일루가나 절로가나 도착지가 똑같다면, 당연히 cost가 낮은 쪽으로 가니까 3초인 1->2->3으로 가야겠지.
	
	//그래서 현재 선택지에서 가장 cost가 낮은애를 우선적으로 가야하니까, priority queue를 쓴거고.
	
	//어씨 수학문제가 아니라 국어문제였네
	
	
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
    
    
    //다익스트라(Dijkstra's algorithm) 알고리즘
    
    //shortest path from one node to every other node
    
    //큰틀은 약간 greedy랑 비슷한 개념인듯. 매 iterate마다 여러개의 available한 노드중, 가장 cost가 작은 노드를 선택하고, visited[i]에 = false삽입해서 다음에 또 못오게 함.
    
    //문제풀이1과 원리는 같은데 19ms차이나 나는걸 보면 pq가 어지간히 느린듯
    
    //Runtime: 12 ms, faster than 81.98% of Java online submissions for Network Delay Time.
    //Memory Usage: 43.8 MB, less than 5.98% of Java online submissions for Network Delay Time.
    
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap();
        for(int[] time : times){
            map.putIfAbsent(time[0], new HashMap());
            map.get(time[0]).put(time[1], time[2]);
        }
        boolean[] visited = new boolean[N + 1];
        int[] dis = new int[N + 1];
        Arrays.fill(dis, 101);  //N이 100까지라고 문제에서 나옴. 그니까, 101~무한 사이 아무숫자나 가능.
        dis[K] = 0;
        
        int nextVertex = K;
        
        for(int j = 0; j < N; j++){
            if(map.containsKey(nextVertex)){
                for(int dst : map.get(nextVertex).keySet()){
                    dis[dst] = Math.min(dis[dst], dis[nextVertex] + map.get(nextVertex).get(dst)); //Math.min()인걸 보면 brute-force인덧.
                }
            }
            visited[nextVertex] = true;
            int minEdge = 101;
            for(int i = 0; i < dis.length; i++){ 
                 if(!visited[i] && dis[i] < minEdge){ //pq(a[0]-b[0])의 역활
                     nextVertex = i;
                     minEdge = dis[i];
                 }
            }
        }
        int res = 0; 
        for(int i = 1; i <= N; i++){
            if(dis[i] == 101) return -1;
            res = Math.max(res, dis[i]);
        }
        return res;
    }
}
