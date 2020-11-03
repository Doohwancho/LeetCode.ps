package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IsGraphBipartie785 {

	//<πÆ¡¶«Æ¿Ã1>
	
	//» 
	
	//Runtime: 5 ms, faster than 14.43% of Java online submissions for Is Graph Bipartite?.
	//Memory Usage: 39.7 MB, less than 6.19% of Java online submissions for Is Graph Bipartite?.
	
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> list = new ArrayList<>(n);
        for(int i = 0; i < n; i++) list.add(new ArrayList<>());
        for(int i = 0; i < n; i++){
            for(int j : graph[i]){
                list.get(i).add(j);
            }
        }
        int[] color = new int[n];
        
        for(int i = 0; i < n; i++){
            if(color[i] == 0){
                color[i] = 1;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                
                while(!q.isEmpty()){
                    int key = q.poll();
                    for(int otherGroup : list.get(key)){
                        if(color[otherGroup] == 0){
                            color[otherGroup] = color[key] == 1 ? 2 : 1;
                            q.add(otherGroup);
                        } else {
                            if(color[otherGroup] == color[key]) return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
