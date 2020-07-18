package julyChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseScheduleII {
	
	//<Trial01>
	
	//아 모르겠다 dfs할라그랬는데 헤깔리네

	private List<Integer> helper(Map<Integer, List<Integer>> map, List<Integer> list, int numCourses, int pre) {
		if (!map.containsKey(pre))
			return null;
		List<Integer> tmp = new ArrayList<>(list);

		for (int nextCourse : map.get(pre)) {
			tmp.add(nextCourse);
			if (map.containsKey(nextCourse) && map.get(nextCourse).contains(pre))
				return null;
			if (tmp.size() == numCourses)
				return tmp;
			tmp = helper(map, tmp, numCourses, nextCourse);
		}
		return null;
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> map = new HashMap<>(); // prerequisite : courses

		for (int[] pr : prerequisites) {
			if (map.containsKey(pr[1])) {
				List<Integer> tmp = map.get(pr[1]);
				tmp.add(pr[0]);
				map.put(pr[1], tmp);
			} else {
				List<Integer> tmp = new ArrayList<>();
				tmp.add(pr[0]);
				map.put(pr[1], tmp);
			}
		}
		if (prerequisites == null || prerequisites.length == 0) {
			int[] rst = new int[numCourses];

			for (int i = 0; i < numCourses; i++) {
				rst[i] = i;
			}
			return rst;

		} else {
			for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
				List<Integer> tmp = new ArrayList<>();
				tmp.add(entry.getKey());

				List<Integer> temp = helper(map, tmp, numCourses, entry.getKey());

				if (temp != null && temp.size() != 0) {
					int[] rst = new int[numCourses];
					int i = 0;
					while (i < numCourses) {
						rst[i] = temp.get(i);
						i++;
					}
					return rst;
				}
			}
		}
		return new int[0];
	}

    
    
    //<문제풀이1 by jianwu>
    
	//queue
	
    //Runtime: 4 ms
	//Memory Usage: 45.9 MB
    
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
    	int[] result = new int[numCourses];
    	int[] preCnt = new int[numCourses]; //prerequisites 필요한 클래스들의 갯수
    	List<Integer>[] nextSet = new List[numCourses]; //i는 prerequisites들. value는 i를 take하면 들을 수 있는 클래스들.
    	for(int i=0; i<numCourses; i++)
    		nextSet[i] = new ArrayList<>();

    	for(int[] p : prerequisites) {
    		preCnt[p[0]]++;  
    		nextSet[p[1]].add(p[0]);
    	}

    	Queue<Integer> sourceNode = new LinkedList<Integer>(); 
    	for(int i=0; i<numCourses; i++)
    		if(preCnt[i] == 0)
    			sourceNode.add(i); //prerequisites필요 없는 클래스들을 더해줌. 얘내는 조건이 없어서 얘내부터 시작할거.
    	
    	for(int i=0; i<numCourses; i++) {
    		if(sourceNode.isEmpty()) //모두 prerequisites이 필요하면, 그래프를 어떻게 돌아도 이전 노드에 돌아오기 때문에 return null.
    			return new int[0];
    		int n = sourceNode.poll(); //prerequisites필요 없는 클래스부터 시작
    		result[i] = n; //n부터 시작
    		for(int next : nextSet[n]) { //n을 들으면 들을 수 있는 클래스들이 next
    			preCnt[next]--; //n을 들으면 들을 수 있는 모든 클래스의 경우의 수를 하나씩 까고 0이되면 다음 node에 더해줌. [1,0],[2,0]이면, 1이랑 2 다 다음노드에 더해줌. 어짜피 [1,2]처럼 서로 관여하지 않아서 1,2,거치고 그 다음 가는거. 
    			if(preCnt[next] == 0) //그중에 하나가 0이되면, 
    				sourceNode.add(next); //node에 다음타자 더해줌.
    		}
    	}
    	return result;
    }
}
