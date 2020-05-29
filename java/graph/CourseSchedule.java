package mayChallenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CourseSchedule {
	
	//<Trial01>
	
	//queue부분에서 어떻게 처리할지 모르겠네
	
	//다녀간곳을 set에 넣고 set.contains()로 다녀간곳을 또 왔으면 return false 했는데
	
	//만약에, (1,2),(1,3),(3,4),(4,2),(2,5)
	
	//같이, 1->3->4->2->5 처럼 아무 문제 없는데, 2를 다시한번 들리는 경우에서 막힘
	
	//이건 int[]를 쓰고 거쳐간 곳을 1로 마크해도 동일한 문제 발생. 
	
	//아ㅏㅏㅏㅏㅏㅏㅏ
	
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> p = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++) {
        	p.add(new ArrayList<>());
        }
    	for(int[] pr : prerequisites) {
    		p.get(pr[0]).add(pr[1]);
    	}
    	int[] c = new int[numCourses];
        for(int i = 1; i < numCourses+1; i++){
        	c[i-1] = i;

            Queue<Integer> q = new LinkedList<>();
            q.add(i-1);
            
            while(!q.isEmpty()) {
            	int cur = q.poll();
            	for(int pr : p.get(cur)) {
            		if(c[pr] != i) {
            			c[pr] = i;
            		}
//            		if(c[pr] == c[cur]) //??
//            		if(c[pr] == 0) {
//            			c[pr] = c[cur] == i ? 2 : 1;
//            		} else {
//            			if(c[pr] == c[cur]) return false;
//            		}
            	}
            }
        }
        return true;
    }
    
    //<문제풀이1 by jeantimex>
    
    //trial01과 방식은 같은데, 차이점은
    
    //trial01같은 경우엔, (1,2),(1,3)같이 여러갈래로 뻣어나가는 경우에 둘다 동시처리함. 
    
    //근데 문제풀이1은 (1,2)따로, (1,3) 따로 처리함.
    
    ////(1,2),(1,3),(3,4),(4,2),(2,5)에서, trial01의 경우엔,
    
    //양갈래(1,2),(1,3)를 둘다 동시에 체크하기 때문에, 3으로 간 애가 (3->4),(4->2)처럼 2에 도착하면, 2에 한번 도착했기 때문에(1->2) return false하는데,
    
    //문제풀이1같은 경우, (1,2)따로, (1,3)따로 처리함.
    
    //(1->2) 하고 이상없네? 하면, stack[]을 다시 초기화 시키고
    
    //(1->3)을 돌때 stack[]은 (1->2)랑 별개도 돌아서, (3->4),(4->2)해도 문제없음.
    
    //Runtime: 3 ms
    //Memory Usage: 40.5 MB
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(numCourses);
        
        for (int i = 0; i < numCourses; i++) 
            adjList.add(i, new ArrayList<Integer>());
        
        for (int i = 0; i < prerequisites.length; i++)
            adjList.get(prerequisites[i][0]).add(prerequisites[i][1]);
        
        boolean[] visited = new boolean[numCourses];
        
        for (int u = 0; u < numCourses; u++)
            if (hasCycle(adjList, u, visited, new boolean[numCourses]))
                return false;
        
        return true;
    }
    
    boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, boolean[] stack) {
        if (visited[u]) //stack만쓰지 굳이 visited도 쓰는 이유는, 예를들어 (1,2),(2,3),(3,4), ... 에서, 1->2->3->4를 검증했고 이상없다면, visited[u] = true로 바뀌잖아? 근데 나중에 (8->9),(9->1)을 왔어. 근데 1 이후엔 이미 멀쩡하다는걸 검증했으니까 더 볼 필요가 없잖아. 그래서 쓰는거야.
            return false;
            
        if (stack[u])  //recursive시 한번 들렸으면 return false한다.
            return true;
            
        stack[u] = true;
            
        for (Integer v : adjList.get(u))
            if (hasCycle(adjList, v, visited, stack))
                return true;
        
        visited[u] = true;
        
        return false;
    }
	
	public static void main(String[] args) {
//		int a = 4;
//		int[][] p = {{0,1},{3,1},{1,3},{3,2}};
//		int a = 3;
//		int[][] p = {{1,0},{2,1}};
		int a = 8;
		int[][] p = {{1,0},{2,6},{1,7},{6,4},{7,0},{0,5}};
		System.out.println(canFinish(a,p));
	}
}
