package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FindTheCityWithSmallestNumberOfNeighborsAtThresholdDistance1334 {

	//<Trial01>
	
	//yet
	
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[] rst = new int[n];
        
        for(int i = 0; i < n; i++){
            double[] tmp = new double[n];
            tmp[i] = 0.1;
            
            for(int j = 0; j < edges.length; j++){
                boolean flag = true;
                
                if(tmp[edges[j][0]] > 0){
                    double next = Math.floor(tmp[edges[j][0]]) + edges[j][2];
                    if(next <= distanceThreshold){
                        tmp[edges[j][1]] = next;
                        flag = false;
                    }
                }
                else if(tmp[edges[j][1]] > 0){
                    double next = Math.floor(tmp[edges[j][0]]) + edges[j][2];
                    if(next <= distanceThreshold){
                        tmp[edges[j][0]] = next;
                        flag = false;
                    }
                }
                if(flag){ //if same as prev, break;
                    break;
                }
                rst[i]++;
                
            }
        }
        
        System.out.print("rst");
        
        //pick least with largest num
        int idx = n-1;
        for(int q = 0; q < n; q++){
            System.out.print(rst[q]+" ");
        }
        
        return rst[idx];
    }
    
    
    
    //<Trial02>
    
    //48 / 52 test cases passed.
    
    //8
    //[[3,5,9558],[1,2,1079],[1,3,8040],[0,1,9258],[4,7,7558],[5,6,8196],[3,4,7284],[1,5,6327],[0,4,5966],[3,6,8575],[2,5,8604],[1,7,7782],[4,6,2857],[3,7,2336],[0,6,6],[5,7,2870],[4,5,5055],[0,7,2904],[1,6,2458],[0,5,3399],[6,7,2202],[0,2,3996],[0,3,7495],[1,4,2262],[2,6,1390]]
    //7937
    
    //까비~아깝쏘잉
    
    //Queue<Integer> q = new LinkedList<>(); 에서
    
    //PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b-a);
    
    //로 바꾸면
    
    //7 6 6 5 7 7 7 7   이게
    
    //7 6 7 7 7 7 7 7  이렇게 됨
    
    
    
    
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        
        for(int[] edge : edges){
            if(map.get(edge[0]) == null){
                map.put(edge[0], new HashMap<>());
            }
            if(map.get(edge[1]) == null){
                map.put(edge[1], new HashMap<>());
            }
            map.get(edge[0]).put(edge[1], edge[2]);
            map.get(edge[1]).put(edge[0], edge[2]);
        }
        
        int[] container = new int[n];
        
        for(int i = 0; i < n; i++){
            Queue<Integer> q = new LinkedList<>();
            q.offer(i);
            
            int[] acc = new int[n];
            boolean[] visited = new boolean[n];
            visited[i] = true;
            int cnt = 0;
            
            while(!q.isEmpty()){
                int from = q.poll();

                if(map.get(from) != null){
                    for(int to : map.get(from).keySet()){
                        int cost = map.get(from).get(to);
                        if(!visited[to] && acc[from]+cost <= distanceThreshold){
                            visited[to] = true;
                            acc[to] = acc[from]+cost;
                            q.offer(to);
                            cnt++;
                        }
                    }
                }
            }
            container[i] = cnt;
        }
        
        int rst = 0;
        for(int i = 0, j = Integer.MAX_VALUE; i < container.length; i++){
            if(container[i] <= j){
                j = container[i];
                rst = i;
            }
        }
        
        return rst;
    }
    
    
    
    //<Trial03>
    
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        
        for(int[] edge : edges){
            if(map.get(edge[0]) == null){
                map.put(edge[0], new HashMap<>());
            }
            if(map.get(edge[1]) == null){
                map.put(edge[1], new HashMap<>());
            }
            map.get(edge[0]).put(edge[1], edge[2]);
            map.get(edge[1]).put(edge[0], edge[2]);
        }
        
        int[] container = new int[n];
        
        for(int i = 0; i < n; i++){
            // Queue<Integer> q = new LinkedList<>();
            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1]-a[1]);
            q.offer(new int[]{i, distanceThreshold});
            
            boolean[] visited = new boolean[n];
            visited[i] = true;
            int cnt = 0;
            
            while(!q.isEmpty()){
                int[] from = q.poll();

                if(map.get(from[0]) != null){
                    for(int to : map.get(from[0]).keySet()){
                        int cost = map.get(from[0]).get(to);
                        if(!visited[to] && from[1]-cost >= 0){
                            visited[to] = true;
                            q.offer(new int[]{to, from[1]-cost});
                            cnt++;
                        }
                    }
                }
            }
            container[i] = cnt;
        }
        
        int rst = 0;
        for(int i = 0, j = Integer.MAX_VALUE; i < container.length; i++){
            if(container[i] <= j){
                j = container[i];
                rst = i;
            }
        }
        
        return rst;
    }
    
    
    //<문제풀이1>
    
    //Dijkstra
    
    //쟁점이 2개인덧.
    
    //쟁점1. Queue쓰면 안되고 PriorityQueue써야 하는 이유
    
    //쟁점2. visited[true]의 처리를 어떻게 할 것인가?
    
    //먼저 쟁점1의 경우, 아래 테스트 케이스를 넣으면,
    
	//23
	//[[1,3,6289],[0,14,8478],[10,18,2195],[21,22,9476],[3,17,4172],[17,21,60],[13,21,1981],[20,22,3303],[9,22,9220],[0,21,6194],[14,21,7898],[4,13,8930],[10,15,3780],[14,20,4386],[0,3,618],[3,8,2749],[4,11,9083],[4,9,8239],[14,19,3820],[4,5,6725],[10,13,4485],[8,12,6901],[5,17,6530],[5,21,4457],[1,16,8094],[13,22,1602],[8,17,2124],[0,1,3059],[13,17,9168],[2,16,261],[11,19,7438],[10,20,735],[4,6,8003],[18,19,3512],[4,20,6455],[2,11,8826],[17,19,5510],[4,14,3990],[3,7,1541],[17,20,1264],[0,15,699],[12,20,6925],[5,19,7239],[7,11,3094],[8,11,2839],[7,18,9754],[4,18,8225],[3,16,3113],[8,15,8352],[0,16,6305],[10,16,8550],[2,12,5908],[11,12,3229],[8,18,3813],[6,13,5894],[12,14,9602],[1,17,1057],[0,9,5588],[18,21,849],[1,6,7512],[7,15,725],[3,19,2897],[2,7,2420],[7,10,4679],[0,8,7687],[14,22,706],[3,21,9558],[6,11,8210],[3,18,1063],[5,22,9265],[10,12,6493],[8,20,1508],[0,18,6553],[10,22,8385],[7,19,9379],[2,14,4937],[3,10,6462],[12,17,4390],[6,15,5771],[3,15,7711],[0,10,805],[12,22,9048],[1,8,7818]...
	//6423
    
    //container: 23 23 22 23 23 23 23 23 23 23 22 22 23 23 23 23 22 23 23 22 23 23 23
    
    //이쁘게 잘 들어가다가 19번째에서 22가 떠서 틀린 답이 나옴.
    
    //왜 그럴까?
    
    //PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1]-a[1]); 이렇게 해놓고,
    
    //매번 from[1]-cost 을 넣으면, 내림차순으로 들어가니까, 가장 여유분이 많이남은애 순서대로 들어갈거아냐
    
    //만약에
    
    //가 있는데, a->c할때 threshold를 다 써버렸어. 근데, b->c할땐 여유분이 많아서 b->c->d까지 가능하단 말이야?
    
    //그런데 a->c이미 갔기 때문에 visited[c] = true가 되어서, b->c할때 이미 간곳이라 못가면, d를 결국 못가겠지?
    
    //그래서 가장 여유분이 있는 노드 우선으로 가기 때문에 PriorityQueue가 필요한거야.
    
    
    
    //쟁점2. visited[true]의 처리를 어떻게 할 것인가?
    
    //아래 처럼 안하고, 만약에
    
    /*
    boolean[] visited = new boolean[n];
    visited[i] = true;
    int cnt = 0;
    
    while(!q.isEmpty()){
        int[] from = q.poll();
        
        // if(visited[from[0]]){
        //     continue;
        // } else {
        //     visited[from[0]] = true;
        //     cnt++;
        // }

        if(map.get(from[0]) != null){
            for(int to : map.get(from[0]).keySet()){
                int cost = map.get(from[0]).get(to);
                if(!visited[to] && from[1]-cost >= 0){
                    q.offer(new int[]{to, from[1]-cost});
                    visited[to] = true;
                    cnt++;
                }
            }
        }
    }
    */
    
    //이것처럼, 맨 처음 visited[i]를 true표시한 다음, q의 children을 돌때 조건만족하면 visited[to] = true하는 방식으로 하면 뭐가 문제일까?
    
    //이것도 꽤 많이 패스하긴 하는데 말이야.
    
    //49 / 52 test cases passed.
    
    //이것도 쟁점1과 비슷한 이유야.
    
    //priority queue잖아. 넣은 순서대로 나오는게 아니라, 여유가 많은 순서대로 나온단 말이야?
    
    //만약 visited[to] = true를 미리 먹인 애가, PriorityQueue에 후순위에 있는데,
    
    //늦게 들어온 여유있는 선순위 애가 해당 visited[to]를 돌아서 그 너머에 d를 가려는데 못가는 상황이 나올 수도 있잖아? 쟁점1처럼
    
    //     a->c->d
    //     b->c
    
    //그래서 미리 visited[to] = true해주는게 아니라, 실제로 도착했을때 visited[from[0]] = true해주는거야.

    
    //Runtime: 98 ms, faster than 21.13% of Java online submissions for Find the City With the Smallest Number of Neighbors at a Threshold Distance.
    //Memory Usage: 40.7 MB, less than 7.75% of Java online submissions for Find the City With the Smallest Number of Neighbors at a Threshold Distance.
    
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        
        for(int[] edge : edges){
            if(map.get(edge[0]) == null){
                map.put(edge[0], new HashMap<>());
            }
            if(map.get(edge[1]) == null){
                map.put(edge[1], new HashMap<>());
            }
            map.get(edge[0]).put(edge[1], edge[2]);
            map.get(edge[1]).put(edge[0], edge[2]);
        }
        
        int[] container = new int[n];
        
        for(int i = 0; i < n; i++){
            // Queue<Integer> q = new LinkedList<>();
            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1]-a[1]);
            q.offer(new int[]{i, distanceThreshold});
            
            boolean[] visited = new boolean[n];
            int cnt = 0;
            
            while(!q.isEmpty()){
                int[] from = q.poll();
                
                if(visited[from[0]]){
                    continue;
                } else {
                    visited[from[0]] = true;
                    cnt++;
                }

                if(map.get(from[0]) != null){
                    for(int to : map.get(from[0]).keySet()){
                        int cost = map.get(from[0]).get(to);
                        if(!visited[to] && from[1]-cost >= 0){
                            q.offer(new int[]{to, from[1]-cost});
                        }
                    }
                }
            }
            container[i] = cnt;
        }
        
        int rst = 0;
        for(int i = 0, j = Integer.MAX_VALUE; i < container.length; i++){
            if(container[i] <= j){
                j = container[i];
                rst = i;
            }
        }
        
        return rst;
    }
}
