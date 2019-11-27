package BreadthFirstSearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaximumDepthOfNaryTree559 {
    
	class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	};
	
	/*
	//<문제풀이1>
	
	//bfs
	
	//Runtime: 2 ms, faster than 27.92% of Java online submissions for Maximum Depth of N-ary Tree.
	//Memory Usage: 40.1 MB, less than 100.00% of Java online submissions for Maximum Depth of N-ary Tree.
	
	public int maxDepth(Node root) {
        if(root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int depth = 1;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                Node currNode = queue.poll();
                for(Node child : currNode.children) //다른문제는 left child, right child라고 명시하는데 여긴 리스트만들어서 children 다 박아넣는듯 
                	queue.add(child); 
            }
            if(!queue.isEmpty()) depth++;
        }
        return depth;
    }
	*/
	
	//<문제풀이2 by tankztc>
	
	//dfs
	
	//depth는 파라미터로 값을 유지시켜주며, depth의 최대값을 maxDepth로 갱신시켜 주는 방법.
	
	//tree가 어떤식으로 나오느냐에 따라 dfs가 더 빠를수도 있고, bfs가 더 빠를 수도 있음.
	
	//tree사이즈가 작고 양쪽이 얼추 even하면 dfs가 더 빠른데,
	
	//만약에 tree왼쪽에 20000 node몰빵됬고 오른쪽에 10 node밖에 없는데 최소 depth를 구하라는 식의 문제면 bfs가 더 빠를수도 있음.
	
	//근데 재귀를 사용한다는 점에서 dfs가 조금 더 간지남.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of N-ary Tree.
	//Memory Usage: 39.1 MB, less than 100.00% of Java online submissions for Maximum Depth of N-ary Tree.
	
    int maxDepth = 0; 
    
    public int maxDepth(Node root) {
        dfs(root, 1);
        return maxDepth;
    }
    
    void dfs(Node root, int depth) {
        if (root == null) return;
        
        maxDepth = Math.max(maxDepth, depth);
        for (Node node : root.children) {
            dfs(node, depth + 1);
        }
    }
}
