package julyChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    
    
    //<문제풀이1 by mo10>
	
	//무-난
    
    //Runtime: 17 ms
    //Memory Usage: 48.3 MB
    
    public int[] topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        int[] rst = new int[k];

        for (int pos = bucket.length - 1, j = 0; pos >= 0 && j < k; pos--) {
            if (bucket[pos] != null) {
                List<Integer> tmp = bucket[pos];
                for(int i = 0; i < tmp.size(); i++, j++){
                    rst[j] = tmp.get(i);
                }
            }
        }
        return rst;
    }
    
    
    //<문제풀이2 by logan138>
    
    //priority queue
    
    //trial때 pq썼는데(int[2]해서 int[0]은 num으로 int[1]은 num의 빈도수로)
    
    //그땐 넣을때 걍 for(int n : nums)로 넣었더니 순서가 엉망으로 들어갔음.
    
    //이친구는 map.keySet()으로 각 element당 한번씩만 빈도수 기준으로 넣어서 잘드감.
    
    //Runtime: 23 ms
    //Memory Usage: 47.9 MB
    
    class Freq{
        int num, freq;
        Freq(int num, int freq){
            this.num = num;
            this.freq = freq;
        }
    }
    
    public int[] topKFrequent(int[] nums, int k) {
        
        Map<Integer, Integer> m = new HashMap<>();
        for(int n : nums){
            m.put(n, m.getOrDefault(n, 0)+1);
        }
        PriorityQueue<Freq> pq = new PriorityQueue<>(m.size(), (a,b)-> b.freq - a.freq); //빈도수 큰게 앞으로(뒤에 들어온 b.freq가 앞에있는 a.freq보다 더 크면, 양수니까, 앞으로 땡김)
        
        for(int key : m.keySet()){
            pq.offer(new Freq(key, m.get(key)));
        }
        
        int[] rst = new int[k];
        int idx = 0;
        while(k > 0){
            rst[idx++] = pq.poll().num;
            k--;
        }
        return rst;
    }
}
