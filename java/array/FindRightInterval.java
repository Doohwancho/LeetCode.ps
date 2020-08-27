package augustChallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FindRightInterval {

	// <문제풀이1>

	// TreeMap

	// 어우 편하다

	// 2줄로 줄이는거 가능하네.

	// map.ceilingKey()가 같거나 더 큰거니까

	// Integer idx = map.ceilingKey(is[i][1]);
	// res[i] = idx == null ? -1 : map.get(idx);

	// Runtime: 25 ms
	// Memory Usage: 47.5 MB

	public int[] findRightInterval(int[][] intervals) {
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		for (int i = 0; i < intervals.length; i++) {
			tm.put(intervals[i][0], i);
		}
		int[] rst = new int[intervals.length];
		for (int j = 0; j < intervals.length; j++) {
			if (tm.get(intervals[j][1]) != null) {
				rst[j] = tm.get(intervals[j][1]);
			} else if (tm.higherKey(intervals[j][1]) != null) {
				rst[j] = tm.higherEntry(intervals[j][1]).getValue();
			} else {
				rst[j] = -1;
			}
		}
		return rst;
	}

	
	
	//<문제풀이2 by yavinci>
	
	//binary search
	
	//이게 성능이 더 좋네
	
	//Runtime: 18 ms
	//Memory Usage: 47.6 MB
	
	public int[] findRightInterval(int[][] intervals) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            list.add(intervals[i][0]);
            map.put(intervals[i][0], i);
        }
        Collections.sort(list);
        
        int[] rst = new int[intervals.length];
        
        for(int i = 0; i < intervals.length; i++){
            int end = intervals[i][1];
            int t = binarySearch(list, end);
            if(t < end){
                rst[i] = -1;
            } else {
                rst[i] = map.get(t);
            }
        }
        return rst;
    }
    
    private int binarySearch(List<Integer> list, int target){
        int left = 0, right = list.size()-1;
        while(left < right){
            int middle = (left+right)/2;
            if(list.get(middle) < target){
                left = middle+1;
            } else {
                right = middle;
            }
        }
        return list.get(left);
    }
}
