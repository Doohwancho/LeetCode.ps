package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TimeNeededToInformAllEmployees1376 {

	//<Trial01>
	
	//생각: 트리만들고 root to leaf iterate할때 time더해서 math.max(leaves)하면 되지 않을까?
	
	//근데 이거 오버킬같은데
	
    public Trie{
        int id = -1;
        int time = 0;
        int acc = 0;
        Trie l;
        Trie r; //자식이 여러갤 수도 있기에 List<Trie>로 해야됨 할꺼면
        
        public Trie(int n, int t){
            id = n;
            time = t;
        }
    }
    
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Trie root = new Trie(headID, informTime[headID]);
        
        //...
    }
    
    //<Trial02>
    
    //bfs
    
//    11
//    4
//    [5,9,6,10,-1,8,9,1,9,3,4]
//    [0,213,0,253,686,170,975,0,261,309,337]
    
//    686
//    337   1023
//    253   1276
//    309   1585
//    975   2560
//    170   2730
//    0   2730
//    0   2730
    
    
    //국어문제였네 ㅋㅋ
    
    //5번노드에서 170을 더하면 안됨. 6번노드에서 975초가 끝나기 전에 8번노드(261초)+5번노드(170초)가 끝나기 때문
    
    //약간 비동기처리라고 이해하면 됨

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Queue<List<Integer>> q = new LinkedList<>();
        List<Integer> tmp = new ArrayList<>();
        tmp.add(headID);
        q.add(tmp);
        
        int rst = informTime[headID];
        while(!q.isEmpty()) {
            List<Integer> p = q.poll();
            List<Integer> row = new ArrayList<>();
            int largestRow = 0;
            
            for(int ele : p){
                for(int i = 0; i < manager.length; i++){
                    if(manager[i] == ele){
                        row.add(i);
                        largestRow = Math.max(informTime[i], largestRow);
                    }
                }
            }
            if(row.size() > 0) q.add(row);
            rst += largestRow;
        };
        
        return rst;
    }
    
    
    //<문제풀이1>
    
    //오예~
    
    //root to leaf backward
    
    //Runtime: 856 ms, faster than 5.11% of Java online submissions for Time Needed to Inform All Employees.
    //Memory Usage: 54 MB, less than 5.73% of Java online submissions for Time Needed to Inform All Employees.
    
    class Node{
        int id = -1;
        int time = 0;
        
        public Node(int id){
            this.id = id;
        }
        
        public Node(int id, int time){
            this.id = id;
            this.time = time;
        }
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Queue<Node> q = new LinkedList<>();
        int rst = 0;
        
        for(int i = 0; i < informTime.length; i++){
            if(informTime[i] == 0){
                Node nd = new Node(i);
                q.add(nd);
            }
        }
        
        while(!q.isEmpty()){
            Node p = q.poll();
            
            int m = manager[p.id];
            if(m != -1){
                q.add(new Node(m, p.time+informTime[m]));
            } else {
                rst = Math.max(rst, p.time);
            }
        }
        
        return rst;
    }
    
    
    //<문제풀이2 by lee215>
    
    //dfs(recursive)
    
    //root에서 시작하긴 하지만, recursive로 leaf찍고 올라오는 구조여서 가능하구나
    
    //똑똑허이
    
    //Runtime: 96 ms, faster than 73.05% of Java online submissions for Time Needed to Inform All Employees.
    //Memory Usage: 67.3 MB, less than 5.73% of Java online submissions for Time Needed to Inform All Employees.
    
    private int dfs(Map<Integer, List<Integer>> map, int[] informTime, int curr){
        
        int max = 0;
        List<Integer> tmp = map.get(curr);
        if(tmp == null) return max;
        
        for(int i = 0; i < tmp.size(); i++){
            max = Math.max(max, dfs(map, informTime, tmp.get(i)));    
        }
        
        return max + informTime[curr];
    }
    
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int i = 0; i < manager.length; i++){
            int m = manager[i];
            if(m != -1){
                if(!map.containsKey(m)){
                    map.put(m, new ArrayList<>());
                }
                map.get(m).add(i);
            }
        }
        
        return dfs(map, informTime, headID);
    }
}
