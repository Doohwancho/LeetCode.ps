package array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LeastNumberOfUniqueIntegersAfterKRemovals1481 {
	
	//<문제풀이1 by mandaliak>
	
	//map에 {숫자:빈도수}로 넣고, priorityqueue로 빈도수 대로 오름차순 정렬 후,
	
	//k-빈도수씩 하는 방법
	
	//Runtime: 99 ms, faster than 77.87% of Java online submissions for Least Number of Unique Integers after K Removals.
	//Memory Usage: 49.8 MB, less than 50.00% of Java online submissions for Least Number of Unique Integers after K Removals.
	
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        for(int a : arr){
            m.put(a, m.getOrDefault(a, 0)+1);
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue()); //value(빈도)기준 오름차순 정렬
        pq.addAll(m.entrySet()); //그냥 pq.addAll(map)이 아니라, pq.addAll(map.entrySet())이네
        
        while(!pq.isEmpty() && k-- > 0){
            Map.Entry<Integer, Integer> entry = pq.remove(); //.remove()로 빼야 정렬시킨대로 이쁘게 나온다. 그리고 Map.Entry<>타입으로 받음. 
            int ev = entry.getValue();
            if(ev > 1){
                entry.setValue(ev-1);
                pq.add(entry);
            }
        }
        return pq.size();
    }
}
