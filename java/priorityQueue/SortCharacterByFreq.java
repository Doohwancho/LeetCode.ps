package mayChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharacterByFreq {
	
	//<문제풀이1 by tankztc>
	
	//포인트1) <Map<Character, Intger>>가 아닌, <Map.Entry<Character, Integer>>이다.
	
	//포인트2) lambda ((a, b) -> (b.getValue() - a.getValue())
	
	//포인트3) pq.addAll(map); 이 아닌, pq.addAll(entrySet());
	
	//포인트4) 그냥 for(Map.Entry<Character, Integer> ele : pq)로 돌면, 랜덤으로 돌아짐. 반드시 poll()로 빼주면서 돌아야 정렬한 순서대로 나옴
	
	//Runtime: 34 ms
	//Memory Usage: 52.9 MB
	
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        
        pq.addAll(map.entrySet());
        
        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();  //여기서도 Map.Entry<> 주의
            for (int i = 0; i < entry.getValue(); ++i) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
    
}
