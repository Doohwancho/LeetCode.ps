package september;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
	
	//<Trial01>
	
	//범위설정하는 거라길래 
	
	//Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
	
	//이면, 일자로 0부터 9까지 쭉 그리면
	
	//0,1,1,1,1,1,1,1,1,1
	
	//이고, 0으로 구분된 애를 뽑게했는데 [[1,8]]반환해버림 쓰바
	
	//구분을 시켜야 되는데 망...

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals[intervals.length-1][1];
        int[] canvas = new int[len+1];
        
        for(int[] interval : intervals){
            for(int i = interval[0]; i <= interval[1]; i++){
                canvas[i] = 1;
            }
        }
        for(int i = newInterval[0]; i <= newInterval[1]; i++){
            canvas[i] = 1;
        }
        
        List<int[]> container = new ArrayList<>();
        int[] tmp = new int[2];
        for(int i = 0, j = 0; i < len; i++){
            if(canvas[i] == 0){
                if(tmp[j] != 0){
                    container.add(tmp); 
                    tmp = new int[2];
                    j = 0;
                }
            } else {
                if(j == 0){
                    tmp[j] = i;
                    j++;
                } else {
                    tmp[j] = i;
                }
            }
        }
        if(tmp[1] != 0){
            container.add(tmp);
        }
        
        int[][] rst = new int[container.size()][2];
        for(int i = 0; i < container.size(); i++){
            rst[i] = container.get(i);
        }
        return rst;
    }
    
    
    //<문제풀이1 by lkjhlkjhasdf1>
    
    //와 똑똑하다
    
    //intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    
    //이걸 예로들면,
    
    //if문에 걸리는건 [1,2]. 이 경우엔 [1,2] 걍 넣으면 됨
    
    //else if문에 걸리는건 [12,16]. 이 경우엔 얘보다 new Interval자체가 작은게 명백하니까,
    
    //new Interval넣으면 됨. 근데 그러면 다음 iterate할때 비교대상이 없어지니까 [12,16]을 newInterval에 넣고 계속 돌림.
    
    //else에 해당하는 애들이 [3,5],[6,7],[8,10]인 애들.
    
    //[4,8]이랑 애매하게 엉겨있는 애들. 이런애들은 범위를 합쳐줘야 하는데,
    
    //왼쪽 맨 끝은 Math.min(i번째 애 [0], newInterval[0]) 해주고,
    
    //오른쪽 끝단은 Math.max(i번째 애[1], newInterval[1])해주다가
    
    //else if에 newInterval보다 더 오른쪽에 있는애가 나타아면 그때 넣어줌.
    
    //Runtime: 1 ms
    //Memory Usage: 41.9 MB
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList();
        for (int[] in : intervals) {
            if (in[1] < newInterval[0]) {
                list.add(in);
            } else if (newInterval[1] < in[0]) {
                list.add(newInterval);
                newInterval = in;
            } else {
                newInterval[0] = Math.min(newInterval[0], in[0]);
                newInterval[1] = Math.max(newInterval[1], in[1]);
            }
        }
        list.add(newInterval);

        return list.toArray(new int[list.size()][]);
    }
}
