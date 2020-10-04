package october;

import java.util.PriorityQueue;

public class RemoveCoveredIntervals {
	
	//<Trial01>
	
	//iterate하면서 i>j면, j를 null로 바꾸고, i<j면 i=j 후 j=null시키고 다시 처음부터 도는 방법.
	
	//마지막에 null이 없는 애들이 가장 큰 범위니까 걔네들만 뽑는 방법
	
	//인데 괴랄한 input나오면 안되네. 몇개 안나오는 애들은 패스하는데..
	
    public int removeCoveredIntervals(int[][] intervals) {
        int len = intervals.length;
        
        for(int i = 0; i < len; i++){
            int[] I = intervals[i];
            int j = i+1;
            
            if(I != null){
                while(j < len && intervals[j] != null){
                    int[] J = intervals[j];
                    if(i == j){
                        j++;
                        continue;
                    }
                    else if(I[0] <= J[0] && J[1] <= I[1]){
                        intervals[j] = null;
                    } else if(J[0] <= I[0] && I[1] <= J[1]){
                        I[0] = J[0];
                        I[1] = J[1];
                        intervals[j] = null;
                        j = i-1;
                    }
                    j++;
                }
            }
        }
        
        
        int rst = 0;
        for(int[] inter : intervals){
            if(inter != null){
                rst++;
            }
        }
        return rst;
    }
    
    
    
    //<Trial02>
    
    //어씨 거의 다 왔는데 마지막 while문에서 뇌절함
    
    //이게 안되는 이유는 [1,2] -> [1,4]일 때,
    
    //왼쪽애가 똑같은애가 나오면 else if문에 안걸려서 암것도 안하네..
    
    public int removeCoveredIntervals(int[][] intervals) {
        if(intervals.length == 0) return 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        
        for(int[] inter : intervals){
            pq.offer(inter);
        }
        
        int rst = 0;
        int[] prev = null;
        
        while(pq.size() > 0){
            int[] curr = pq.poll();
            
            if(prev == null){
                prev = curr;
            }
            else if(curr[1] > prev[1] && curr[0] > prev[0]){
                prev = curr;
                rst++;
            } 
        }
        return rst;
    }
    
    
    //<문제풀이1 by lee215>
    
    //왼쪽 애가 오름차순으로 정렬되서 튀어나옴.
    
    //이때 pq.poll()시 오른쪽 애가 무조건 이전애의 왼쪽애보다 커야 함.
    
    //curr[1]이 같기라도 하면, curr[0]이 무조건 작기 때문에 이전애에 포함되기 때문.
    
    //그래서 r = Math.max(curr[1], r)해주는 것.
    
    //if(curr[1] > r && curr[0] > l) 요 if문에 걸리면 새로운 범위에 애가 나온다는 말이니까,
    
    //left bound를 업데이트 시켜줌
    
    //Runtime: 4 ms
    //Memory Usage: 39.8 MB
    
    public int removeCoveredIntervals(int[][] intervals) {
        if(intervals.length == 0) return 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        
        for(int[] inter : intervals){
            pq.offer(inter);
        }
        
        int rst = 0;
        int l = -1, r = -1;
        
        while(pq.size() > 0){
            int[] curr = pq.poll();
            
            if(curr[1] > r && curr[0] > l){
                l = curr[0];
                rst++;
            } 
            r = Math.max(curr[1], r);
        }
        return rst;
    }
}
