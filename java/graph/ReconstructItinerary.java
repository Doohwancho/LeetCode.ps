package juneChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ReconstructItinerary {
	
	//<Trial01>
	
	//Input: [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
	//Output: ["JFK","KUL"]
	//Expected: ["JFK","NRT","JFK","KUL"]
	//Stdout: {NRT=[JFK], JFK=[KUL, NRT]}
	
	//일단 다 이어지는게 우선인데 사전적으로 먼저 튀나오는걸 먼저 갖다 붙이니 다 안이어지고 중간에 끊김
	
    public List<String> findItinerary(List<List<String>> tickets) {
        if(tickets.size() == 0) return new ArrayList<>();
        List<String> rst = new ArrayList<>();
        rst.add("JFK");
        Map<String,List<String>> m = new HashMap<>();
        
        for(List<String> list : tickets){
            if(m.containsKey(list.get(0))){
                List<String> tmp = m.get(list.get(0));
                tmp.add(list.get(1));
                m.put(list.get(0), tmp);
            } else {
                List<String> tmp = new ArrayList<>();
                tmp.add(list.get(1));
                m.put(list.get(0), tmp);
            }
        }
        
        String next;
        
        do{
            String key = rst.get(rst.size()-1);
            
            if(m.get(key).size() > 1){
                List<String> tmp = m.get(key);
                next = tmp.get(0);
                for(String s : tmp){
                    if(s.compareTo(next) < 0){
                        next = s;
                    }
                }
                tmp.remove(next);
                m.put(key, tmp);
                rst.add(next);
            } else {
                next = m.remove(key).get(0);
                rst.add(next);
            }
        } while(m.containsKey(next));
        
        return rst;
    }
	
    
    //<Trial02>
    
    //doubly linked list로 될까? 싶어서 만들다가 다시생각해보니까 각이 안나오는데?
    
    //binary tree로 일단 정렬해놓고 뽑아야되나
    
    class Solution {
        
        //[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        
        //JFK-1.SFO, 2.ATL
        //SFO-1.ATL
        //ATL-1.JFK, 2.SFO
        
        //JFK-SFO-ATL-JFK
        //           -SFO   
        //   -ATL-JFK
        //       -SFO
        
        class DoublyLinked{
            DoublyLinked prev;
            List<DoublyLinked> next;
            String node;
            
            DoublyLinked(String s){
                node = s;
                prev = null;
                next = null;
            }
            
            public void addNext(DoublyLinked nextNode){
                next = nextNode;
                nextNode.prev = this;
            }
            
            public void addPrev(DoublyLinked prevNode){
                prev = prevNode;
                prevNode.next = this;
            }
            
            public void deleteNode(DoublyLinked removeNode){
                removeNode.prev.next =removeNode.next.prev;
            }
        }
        
        public List<String> findItinerary(List<List<String>> tickets) {
            if(tickets.size() == 0) return new ArrayList<>();
            DoublyLinked dl = new DoublyLinked("JFK");
            for(List<String> list : tickets){
                
                dl.addNext(list.get(0));
            }
        }
    }
    
    
    //<Trial03>
    
    //아니 binary tree로 걍 냅다 이으면 어디까지 이어야 하는거지? 무한히 이어지는것도 있을텐데?
    
    //그리고 생각해보니까 list사이즈가 10000인데 {"JFK","ABC"}가 10000번째 있으면 list.remove(0)하고 list.add()를 10000번한다는거잖아?
    
    //시간초과하겠네? 시벌
    
    //그래프로 해야될거같은데
    

    class StringBinaryTree{
        
        String val;
        List<StringBinaryTree> children;
        Set<String> set;
        
        
        StringBinaryTree(String s){
            val = s;
            children = new LinkedList<>();
            set = new HashSet<>();
            set.add(s);
        }
        
        public void addChild(String key, String val){
            set.add(s);
            
            /*
            //if key is found
            
            StringBinaryTree child = new StringBinaryTree(s);
            int l = 0, r = children.size();
            while(l<r){ 
                int m = (l+r)/2;
                String mVal = children.get(m).val;
                if(s.compareTo(mVal) >= 0){ //add child according to lexiconical order
                    r = m;
                } else {
                    l = m+1;
                }
            }
            children.add(l, child);
            */
        }
        
        public void containsKey(String s){
            return set.contains(s);
        }
    }
    
    public List<String> findItinerary(List<List<String>> tickets) {
        if(tickets.size() == 0) return new ArrayList<>();
        StringBinaryTree tr = new StringBinaryTree("JFK");
        
        while(!tickets.isEmtpy()){
            List<String> tmp = tickets.remove(0);
            if(tr.containsKey(tmp.get(0))){
                tr.addChild(tmp.get(0), tmp.get(1));
            } else {
                tickets.add(tmp);
            }
        }
        
        //tr leftmost && size == tickets to List<String> and return
    }
    
    
    
    //<문제풀이1 by StefanPochmann>	
    
    //Hierholzer’s Algorithm
    
    //https://m.youtube.com/watch?v=qZrfK2iE4UA 
    
    //보면 얼추 설명해줌. 
    
    //일단 돌다가 막히는 부분이 나왔는데, 모든 노드를 다 돌지 않았으면,
    
    //막히기 전으로 돌아가서 다른 길로 돌아. 다시 막힐때까지.
    
    //다시 가다가 또 막혔는데 모든 노드를 다 안돌았으면, 위에방식 반복.
    
    //모든 노드를 돌고 막힐때까지.
    
    //모든 노드를 돌고 막혔으니까, starting point만 알면 모든 노드를 쭉 이어지게 돌 수 있지.
    
    //Eulerian path. Greedy DFS, building the route backwards when retreating.
    
    //targets = {'JFK': ['D', 'A'], 'A': ['C'], 'B': ['C'], 'C': ['JFK', 'D'], 'D': ['B', 'A']}
    //route = []	
	//stack = ['JFK']
    
	//First point at which we get stuck:
	//targets = {'JFK': ['D'], 'A': [], 'B': ['C'], 'C': ['JFK', 'D'], 'D': ['B']}
	//route = []
	//stack = ['JFK', 'A', 'C', 'D', 'A']
    
	//Update route:
	//targets = {'JFK': ['D'], 'A': [], 'B': ['C'], 'C': ['JFK'], 'D': ['B']}
	//route = ['A']
	//stack = ['JFK', 'A', 'C', 'D']
    
	//Search forward again until stuck:
	//targets = {'JFK': [], 'A': [], 'B': [], 'C': [], 'D': []}
	//route = ['A']
	//stack = ['JFK', 'A', 'C', 'D', 'B', 'C', 'JFK', 'D']
    
	//Update route:
	//targets = {'JFK': ['D'], 'A': [], 'B': [], 'C': ['JFK'], 'D': []}
	//route = ['A', 'D', 'JFK', 'C', 'B', 'D', 'C', 'A', 'JFK']
	//stack = []
    
	//Return route in reverse:
	//route = ['JFK', 'A', 'C', 'D', 'B', 'C', 'JFK', 'D', 'A']
    
    //Runtime: 5 ms
    //Memory Usage: 40 MB
    
    Map<String, PriorityQueue<String>> targets = new HashMap<>(); 
    List<String> route = new LinkedList();
    
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets)
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue()).add(ticket.get(1));
        visit("JFK");
        return route;
    }

    void visit(String airport) {
        while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
            visit(targets.get(airport).poll());
        route.add(0, airport);
    }
    
    
}
