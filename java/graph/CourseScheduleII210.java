package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseScheduleII210 {
	
	//<문제풀이1 by tankztc>
	
	//와
	
	//생각을 뒤집어서, a를 들으려면 b,c,d를 들어야 한다. 이런식으로 간게 아니라 거꾸로
	
	//b를 들어야 a를 들을 수 있음. c를 들어야 a를 들을 수 있음. d를 들어야 a를 들을 수 있음. 이런식으로 바꾼 뒤, map에 넣으면,
	
	//int[] p 에서 0인 애들, map에 key에 존재하지 않는 애들, 아무것도 안들어도 바로 들을 수 있는 애들을 집어서, 거기서부터
	
	//한칸씩 bfs로 내려가면서, 체크하면 됨.
	
	//numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
	
	//이거의 경우, 난 어떻게 생각했냐면
	
	//0 -> 
	//1 -> 0
	//2 -> 0
	//3 -> 1, 2
	
	//그래서 0부터 시작하면, 1이랑 2를 구해야 하는데, 만약 2가 1을 prerequisite한다고 했을 때, 2을 1보다 어떻게 먼저 넣을 수 있지 고민했었음.
	
	//근데 정렬방식을 tankztc처럼 거꾸로 넣으면,
	
	//0 -> 1,2
	//1 -> 3
	//2 -> 3
	//3
	
	//이리되고, 아무 선행과목이 없는 0부터 시작하면, 다음에 1이랑 2를 보는데, 넘어갈때 1이랑 2의 p[1], p[2]를 각각 하나씩 까줌.
	
	//선행해야하는 0을 이미 지나왔으니까. 그 후, p[1]이나 p[2]중에 0인애가 있으면, 더이상 선행할애가 없다는 말이니까,
	
	//q에 더해주고 결국 rst[i] = p[n]이 되게 됨.
	
	//만약 iscycle이면, return new int[0]
	
	//Runtime: 5 ms, faster than 70.73% of Java online submissions for Course Schedule II.
	//Memory Usage: 39.7 MB, less than 5.12% of Java online submissions for Course Schedule II.
	
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] p = new int[numCourses];
        
        for(int[] pr : prerequisites){
            if(!map.containsKey(pr[1])){
                map.put(pr[1], new ArrayList<>());
            }
            map.get(pr[1]).add(pr[0]);
            p[pr[0]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        int[] rst = new int[numCourses];
        int idx = 0;
        int cnt = 0;
        
        for(int i = 0; i < numCourses; i++){
            if(p[i] == 0){
                q.offer(i);
            }
        }
        
        while(!q.isEmpty()){
            int key = q.poll();
            
            if(map.containsKey(key)){
                for(int child : map.get(key)){
                    if(--p[child] == 0){
                        q.offer(child);
                    }
                }
            }
            rst[idx++] = key;
            cnt++;
        }
        
        return cnt == numCourses ? rst : new int[0];
    }
}
