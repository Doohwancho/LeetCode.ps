package DepthFirstSearch;

import java.util.List;

public class MaximumDepthOfNaryTree559 {
    
	//<문제풀이 by tankztc>
	
	//dfs(depth first search)는 깊이 우선 탐색이다.
	
	//깊이 우선 탐색은, 다음 branch로 넘어가기 전에 해당 branch를 완벽히 탐색하는 방법이다.
	
	//미로를 탐색할 때, 한 방향으로 갈 수 있을때 까지 계속 가다가 더 이상 갈 수 없게되면, 다시 가장 가까운 갈림길로 돌아와서 이곳으로부터 다른방향으로 다시 탐색하는 방법.
	
	//즉, 넓게 탐색하기 전, 깊게 탐색하는 것.
	
	//사용하는 경우, 모든 노드를 방문하고자 할 때, 쓰인다.
	
	//깊이 우선 탐색(dfs)가 너비 우선 탐색(bfs)보다 좀 더 간단하다.
	
	//단순 검색 속도는 bfs보다 느리다.
	
	//구현방법 2가지.
	
	//a. 순환 호출 이용
	
	//b. 명시적인 스택 사용
	
	//순환 호출이용시, 매 방문마다 root.visited = true로 표시해줌. 그리고 for(Node child: children) if(root.visited==false)로 방문하지 않은 접점을 찾음.
	
	//아래 예제의 경우, 단순히 가장 깊은 depth만 알면 되었기 때문에, 자식 노드로 갈 때, depth+1을 해서 최대 depth를 Math.max()로 가져감.

	//Runtime: 1 ms, faster than 98.07% of Java online submissions for Maximum Depth of N-ary Tree.
	//Memory Usage: 44.6 MB, less than 90.48% of Java online submissions for Maximum Depth of N-ary Tree.
	
	class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val,List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	};
	
	static int maxDepth = 0;
    
    private static void dfs(Node root, int depth){
        if(root == null) return;
        
        maxDepth = Math.max(maxDepth, depth);
        
        for(Node child : root.children){
            dfs(child, depth+1);
        }
    }
    
    public static int maxDepth(Node root) {
        dfs(root, 1);
        return maxDepth;
    }
}
