package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SmallestStringWithSwaps1202 {

	/*
	//<Trial01>
	
	//서로 이어진것도 잇고
	//중복도 없애고
	//서로 이어진 부분들 loop돌면서 sorting도 했는데
	//비슷하겐 sorting되는데 안되네 
	
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        
        List<Set<Integer>> pathContainer = new ArrayList<>();
        Set<Integer> filter = new HashSet<>();
        boolean interval = false;
        
        for(int i = 0; i < pairs.size(); i++) {
        	int first = pairs.get(i).get(0);
            int second = pairs.get(i).get(1);
            
            if(filter.contains(first) || filter.contains(second)){
                for(Set<Integer> path : pathContainer){
                    if(path.contains(first) || path.contains(second)){
                        path.add(first);
                        path.add(second);
                    }
                }
            } else {
                filter.add(first);
                filter.add(second);
                Set<Integer> path = new HashSet<>();
                path.add(first);
                path.add(second);
                pathContainer.add(path);
            }
            
        }
        
        char[] ch = s.toCharArray();
        
        //step02. 끊어진 구간마다 최적의 sorting한다.
        for(Set<Integer> set : pathContainer) {
            List<Integer> tmp = new ArrayList<>(set);
            Collections.sort(tmp);
        	for(int i = 0; i < tmp.size()-1; i++) {
        		for(int j = i+1; j < tmp.size(); j++) {
        			if(ch[tmp.get(i)] > ch[tmp.get(j)]) {
        				swap(ch, tmp.get(i), tmp.get(j));
        			}
        		}
        	}
        }
        
        return String.valueOf(ch);
    }
    
	private void swap(char[] ch, int a, int b) {
		char tmp = ch[a];
		ch[a] = ch[b];
		ch[b] = tmp;
	}
	*/
	
	//<문제풀이1 by tiandiao123>
	
	//"dcab"
	//[[0,3],[1,2]]
	
	//의 경우,
	
	//parent는 
	
	//0 1 2 3에서 
	
	//3 1 2 3 
	//3 2 2 3
	
	//와 같이 됨
	
	//map은
	
	//{2=[1, 2], 3=[0, 3]}
	
	//1st for: [c]    index:1
	//1st for: [a, c] index:2
	//2nd for: a
	//2nd for: ac
	//1st for: [d]    index:0
	//1st for: [b, d] index:3
	//2nd for: bac
	//2nd for: bacd
	
	//추접스럽게 .compareTo()같은거 안쓰고 PriorityQueue씀. 레게노.
	
	//Runtime: 41 ms, faster than 82.10% of Java online submissions for Smallest String With Swaps.
	//Memory Usage: 89.5 MB, less than 100.00% of Java online submissions for Smallest String With Swaps.
	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int[] parent = new int[s.length()];
        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }
        
        for(List<Integer> ele: pairs){
            int a = ele.get(0);
            int b = ele.get(1);
            int pa = findParent(parent, a);
            int pb = findParent(parent, b);
            if(pa!=pb){
                parent[pa] = pb;
            }
        }
        
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0;i<parent.length;i++){
            int p = findParent(parent, i);
            if(map.containsKey(p)==false){
                map.put(p, new ArrayList<>());
            }
            map.get(p).add(i);
        }
        char[] array = new char[s.length()];
        for(Integer key: map.keySet()){
            PriorityQueue<Character> minheap = new PriorityQueue<>();
            for(Integer index: map.get(key)){
                minheap.add(s.charAt(index));
            }
            
            for(int index: map.get(key)){
                array[index] = minheap.poll();
            }
        }
        
        return new String(array);
    
    }
    
    public int findParent(int[] parent, int index){
        while(index!=parent[index]){
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }
}
