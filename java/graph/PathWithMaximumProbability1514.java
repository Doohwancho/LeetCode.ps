package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PathWithMaximumProbability1514 {
	
	
	//<Trial01>
	
	//5
	//[[2,3],[1,2],[3,4],[1,3],[1,4],[0,1],[2,4],[0,4],[0,2]]
	//[0.06,0.26,0.49,0.25,0.2,0.64,0.23,0.21,0.77]
	//0
	//3
	
	//이건 패스 하는디..

    private void draw(double[] a){
        for(double a_ : a){
            System.out.print(a_+" ");
        }
        System.out.println();
    }
    
    private void draw(boolean[] a){
        for(boolean a_ : a){
            System.out.print(a_+" ");
        }
        System.out.println();
    }
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            double cost = succProb[i];
            
            if(map.get(from) == null){
                map.put(from, new HashMap<>());
            }
            map.get(from).put(to, cost);
        }
        
        int curr = start;
        // double next = Integer.MIN_VALUE;
        
        boolean[] visited = new boolean[n];
        
        double[] prob = new double[n];
        Arrays.fill(prob, -100);
        prob[curr] = 1;
        
        for(int i = 0; i < n; i++){
            visited[curr] = true;
            
            double nextProb = Integer.MIN_VALUE; 
            // int next = curr;
            
            if(map.containsKey(curr)){
                // System.out.println("a: "+curr);
                for(int child : map.get(curr).keySet()){
                    double cost = map.get(curr).get(child);    
                    prob[child] = Math.max(prob[child], prob[curr] * cost);
                    // System.out.println("b: "+child+" cost: "+cost+" prob[curr] * cost: "+ (prob[curr] * cost));
                    
                    // if(!visited[child] && map.get(curr).get(child) > nextProb){
                    //     nextProb = map.get(curr).get(child);
                    //     next = child;
                    // }
                }
            }
            
            // prob[next] = prob[curr] * nextProb;
            // curr = next;
            
            for(int j = 0; j < n; j++){
                if(!visited[j] && prob[j] > nextProb){
                    nextProb = prob[j];
                    curr = j;
                }
            }
            
            // System.out.println("curr: "+curr);
            // draw(visited);
            // draw(prob);
            
        }
        
        return prob[end] == -100 ? 0 : prob[end]; 
    }
    
    
    //<Trial02>
    
    //undirected graph라고 해서 단방향인줄 알았는데 양방도 치나보네
    
    //5
	//[[1,4],[2,4],[0,4],[0,3],[0,2],[2,3]]
	//[0.37,0.17,0.93,0.23,0.39,0.04]
	//3
	//4
    
    //이거 안됬는데 from-> to에서 to->from도 추가하니 돌아감.
    
    //근데 
    
	//10
	//[[0,3],[1,7],[1,2],[0,9]]
	//[0.31,0.9,0.86,0.36]
	//2
	//3
    
    //에서 막히네?
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            double cost = succProb[i];
            
            if(map.get(from) == null){
                map.put(from, new HashMap<>());
            }
            map.get(from).put(to, cost);
            
            if(map.get(to) == null){
                map.put(to, new HashMap<>());
            }
            map.get(to).put(from, cost);    
        }
        
        int curr = start;
        
        boolean[] visited = new boolean[n];
        
        double[] prob = new double[n];
        Arrays.fill(prob, -100);
        prob[curr] = 1;
        
        for(int i = 0; i < n; i++){
            visited[curr] = true;
            double nextProb = Integer.MIN_VALUE; 
            
            if(map.containsKey(curr)){
                // System.out.println("a: "+curr);
                for(int child : map.get(curr).keySet()){
                    double cost = map.get(curr).get(child);    
                    prob[child] = Math.max(prob[child], prob[curr] * cost);
                }
            }
            for(int j = 0; j < n; j++){
                if(!visited[j] && prob[j] > nextProb){
                    nextProb = prob[j];
                    curr = j;
                }
            }
        }
        
        return prob[end] == -100 ? 0 : prob[end]; 
    }
    
    
    
    //<문제풀이1>
    
    //Dijkstra's Algo
    
    //어씨 쥰내느린거 실화냐?
    
    //Runtime: 1464 ms, faster than 5.18% of Java online submissions for Path with Maximum Probability.
    //Memory Usage: 51.5 MB, less than 5.18% of Java online submissions for Path with Maximum Probability.
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        
        for(int i = 0; i < edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            double cost = succProb[i];
            
            if(map.get(from) == null){
                map.put(from, new HashMap<>());
            }
            map.get(from).put(to, cost);
            
            if(map.get(to) == null){
                map.put(to, new HashMap<>());
            }
            map.get(to).put(from, cost);    
        }
        
        int curr = start;
        
        boolean[] visited = new boolean[n];
        
        double[] prob = new double[n];
        Arrays.fill(prob, -100);
        prob[curr] = 1;
        
        for(int i = 0; i < n; i++){
            visited[curr] = true;
            double nextProb = Integer.MIN_VALUE; 
            
            if(map.containsKey(curr)){
                for(int child : map.get(curr).keySet()){
                    double cost = map.get(curr).get(child);    
                    prob[child] = Math.max(prob[child], prob[curr] * cost);
                }
            }
            for(int j = 0; j < n; j++){
                if(!visited[j] && prob[j] > nextProb){
                    nextProb = prob[j];
                    curr = j;
                }
            }
        }
        
        return prob[end] < 0 ? 0 : prob[end]; 
    }
    
}
