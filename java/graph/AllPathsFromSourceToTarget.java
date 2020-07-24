package julyChallenge;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {
	
	
	//<문제풀이1 by stevenlli>
	
	//dfs
	
	//Runtime: 2 ms
	//Memory Usage: 40.9 MB
	
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> path = new ArrayList<>();

		path.add(0);
		dfsSearch(graph, 0, res, path);

		return res;
	}

	private void dfsSearch(int[][] graph, int node, List<List<Integer>> res, List<Integer> path) {
		if (node == graph.length - 1) { //끝까지 간다음 누적된 path를 rst에 더해줌
			res.add(new ArrayList<Integer>(path));
			return;
		}

		for (int nextNode : graph[node]) {
			path.add(nextNode); //가는길에 하나 집고
			dfsSearch(graph, nextNode, res, path); //해당노드로 이어서 감. 끝까지 간 다음 path를 rst에 넣겠지
			path.remove(path.size() - 1); //다시 돌아와서 위에 방금 추가한걸 지워. [[1,2], [3], [3], []] 에서 1타고 끝까지 가서 rst에 넣었으면, 1 지우고 [0,2]부터 다시 시작해야지.
		}
	}

}
