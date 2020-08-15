package augustChallenge;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
	
	//<문제풀이1 by tankztc>
	
	//overlay를 찾을때마다(== 원래의 범위를 침범하는 애를 찾을때마다) +1
	
	//Runtime: 3 ms
	//Memory Usage: 39.2 MB
	
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>(){ 
            public int compare(int[] a, int[] b){
                return a[1] - b[1]; //end 기준으로 정렬
            }
        });
        
        int rst = 0;
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] < end){ //새로운 애의 시작이 기존 end보다 작다는 말은,기존 범위안에 overlay한다는 말. 고로 불필요. +1.
                rst++;
            } else{
                end = intervals[i][1]; //시작이 기존 end나 그 이후부터 시작이면 end 범위를 늘림.
            }
        }
        
        return rst;
    }


}
