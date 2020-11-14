package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlightsWithinKStops787 {
	
	//<Trial01>
	
	//43 / 47 test cases passed.
	
	//Input:
	//5
	//[[0,1,5],[1,2,5],[0,3,2],[3,1,2],[1,4,1],[4,2,1]]
	//0
	//2
	//2
	//Output:
	//9
	//Expected:
	//7
	
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2]-b[2]);
        boolean[] visited = new boolean[n];
        
        for(int[] flight : flights){
            if(map.get(flight[0]) == null) map.put(flight[0], new HashMap<>());
            // if(map.get(flight[1]) == null) map.put(flight[1], new HashMap<>()); //undirected graph이기 때문
            map.get(flight[0]).put(flight[1], flight[2]);
            // map.get(flight[1]).put(flight[0], flight[2]);
        }
        
        pq.offer(new int[]{src, K+1, 0});
        
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int z = curr[2];
            
            if(x == dst) return z;
            if(visited[x] || y == 0) continue;
            visited[x] = true;
            
            if(map.containsKey(x)){
                for(int next : map.get(x).keySet()){
                    int cost = map.get(x).get(next);
                    pq.offer(new int[] {next, y-1, z+cost});
                }
            }
        }
        
        return -1;
    }
    
    
    
    //<Trial02>
    
    //accCost 추가
    
    //...를 해봤으나 생각해보니 필요없음. 
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2]-b[2]);
        boolean[] visited = new boolean[n];
        int[] accCost = new int[n];
        Arrays.fill(accCost, Integer.MAX_VALUE);
        accCost[0] = 0;
        
        for(int[] flight : flights){
            if(map.get(flight[0]) == null) map.put(flight[0], new HashMap<>());
            // if(map.get(flight[1]) == null) map.put(flight[1], new HashMap<>());
            map.get(flight[0]).put(flight[1], flight[2]);
            // map.get(flight[1]).put(flight[0], flight[2]);
        }
        
        pq.offer(new int[]{src, K+1, 0});
        
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int z = curr[2];
            
            if(x == dst) return accCost[x];
            if(visited[x] || y == 0) continue;
            visited[x] = true;
            
            if(map.containsKey(x)){
                for(int next : map.get(x).keySet()){
                    int cost = map.get(x).get(next);
                    if(z+cost < accCost[next]){
                        pq.offer(new int[] {next, y-1, z+cost});   
                        accCost[next] = z+cost;
                    }
                }
            }
        }
        
        return -1;
    }
    
    
    //<Trial03>
    
    //bellman-ford...failed
    
    //실패이유: 문제풀이2는 되는데 이건 안되는건, 문제풀이 2는 2state로 나눴기 때문.
    
    //얘는 한번 전진하면, (a->b), a가 c,d,e 등 전진할때까지 기다려야 하는데, 전진하고 dfs처럼 계~속감. a->b->c->d
    
    //그러다 중간에 한번 덮어쓰기 당하면, a->b에서 왔을때, ->c다음 ->f가야할 차례인데, 덮어쓰기 당해버려서 실패함.
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] kContainer = new int[n];
        kContainer[src] = K+1;
        
        int[] acc = new int[n];
        Arrays.fill(acc, Integer.MAX_VALUE);
        acc[0] = 0;
        
        int rst = Integer.MAX_VALUE;
        
        for(int i = 0; i < n-1; i++){
            for(int[] f : flights){
            	if(kContainer[f[0]] > 0){  
                    acc[f[1]] = acc[f[0]] + f[2];
                    kContainer[f[1]] = kContainer[f[0]]-1;
                    if(f[1] == dst) rst = Math.min(rst, acc[f[1]]);
                }
            }
        }
        
        return rst == Integer.MAX_VALUE ? -1 : rst;
    }
    
    
    //<문제풀이1>
    
    //Dijkstra
    
    //undirected graph라 boolean[] visited가 필요 없음.
    
    //Runtime: 15 ms, faster than 32.29% of Java online submissions for Cheapest Flights Within K Stops.
    //Memory Usage: 41 MB, less than 40.05% of Java online submissions for Cheapest Flights Within K Stops.
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int[] flight : flights){
            if(map.get(flight[0]) == null) map.put(flight[0], new HashMap<>());
            map.get(flight[0]).put(flight[1], flight[2]);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2]-b[2]);
        pq.offer(new int[]{src, K+1, 0});
        
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int z = curr[2];
            
            if(y == -1) continue;
            if(x == dst) return z;
            
            if(map.containsKey(x)){
                for(int next : map.get(x).keySet()){
                    int cost = map.get(x).get(next);
                    pq.offer(new int[] {next, y-1, z+cost});
                }
            }
        }
        return -1;
    }
    
    
    //<문제풀이2 by caraxin>
    
    //bellman-ford
    
    //얘는 dfs처럼 막가다가 덮어쓰기 당하지 않아서 됨.
    
    //Runtime: 4 ms, faster than 93.85% of Java online submissions for Cheapest Flights Within K Stops.
    //Memory Usage: 39.5 MB, less than 76.54% of Java online submissions for Cheapest Flights Within K Stops.
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Integer[] cur= new Integer[n];
        cur[src]=0;
        while(K-->-1){
            Integer[] next= Arrays.copyOf(cur, n);
            for (int[] f: flights){
                if(cur[f[0]] != null && (next[f[1]] == null || cur[f[0]] + f[2] <= next[f[1]])){
                    next[f[1]] = cur[f[0]] + f[2];
                }
            }
            cur = next;
        }
        return cur[dst]==null?-1:cur[dst];
    }
}
