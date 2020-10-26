package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FindEventualSafeStates802 {

	
	//<Trial01 - Time Limit Exceeded>
	
	//[[1,2],[2,3],[5],[0],[5],[],[]] 이건 되는디
	
	//[[],[0,2,3,4],[3],[4],[]]  
	
	//[[0],[2,3,4],[3,4],[0,4],[]] 에서 막힘
	
    private boolean dfs(List<List<Integer>> bundle, boolean[] visited, boolean[] isCycle, int idx, int p){
        
        if(visited[idx]){
            isCycle[idx] = true;
            return true;
        }
        
        for(int i = idx; i < bundle.size(); i++){
            visited[i] = true;
            for(int ele : bundle.get(i)){
                if(dfs(bundle, visited, isCycle, ele, i)){
                    isCycle[i] = true;
                }; 
            }  
            visited[i] = false;
            if(idx < p) return false;
        }
        return false;
    }
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> bundle = new ArrayList<>();
        for(int i = 0; i < graph.length; i++){
            List<Integer> tmp = new LinkedList<>();
            for(int ele : graph[i]){
                tmp.add(ele);
            }
            bundle.add(tmp);
        }
        
        boolean[] visited = new boolean[graph.length];
        boolean[] isCycle = new boolean[graph.length];
        
        dfs(bundle, visited, isCycle, 0, 0);
        
        List<Integer> rst = new ArrayList<>();
        for(int i = 0; i < isCycle.length; i++){
            if(!isCycle[i]){
                rst.add(i);
            }
        }
        return rst;
    }
    
    
    //<문제풀이1 by kevincongcc>
    
    //0 = 아직 안들른 깨끗한 노드
    //1 = 이미 한번 들른곳. 
    //2 = 지금 들르고 있는 중인 곳
    
    //color[start]가 2가 뜬다는건, isCycle이라는 것이므로, return false해서 res.add(i)하지 않음.
    
    //돌다가 color[start]가 1이 떴다는건, 예전에 i에 들른적은 있지만 isCycle이 아니었다는 뜻. 그러므로 패스하고 다음거 iterate함. 
    
    //Runtime: 4 ms, faster than 99.23% of Java online submissions for Find Eventual Safe States.
    //Memory Usage: 49.1 MB, less than 11.56% of Java online submissions for Find Eventual Safe States.
    
    public boolean dfs(int[][] graph, int start, int[] color){
        if(color[start] != 0) return color[start] == 1;
        color[start] = 2;
        for(int i = 0; i < graph[start].length; i++){
            if(!dfs(graph, graph[start][i], color)){
                return false;
            };
        }
        color[start] = 1;
        
        return true;
    }
    
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        if(graph == null || graph.length == 0)  return res;
        
        int nodeCount = graph.length;
        int[] color = new int[nodeCount];
        
        for(int i = 0;i < nodeCount;i++){
            if(dfs(graph, i, color))    res.add(i);
        }
        
        return res;
    }
}
