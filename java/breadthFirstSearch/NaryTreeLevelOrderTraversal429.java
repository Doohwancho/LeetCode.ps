package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class NaryTreeLevelOrderTraversal429 {

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
	
	//<문제풀이1>
	
	//bfs - level order traversal
	
	//Runtime: 2 ms, faster than 86.40% of Java online submissions for N-ary Tree Level Order Traversal.
	//Memory Usage: 41.3 MB, less than 100.00% of Java online submissions for N-ary Tree Level Order Traversal.
	
	public List<List<Integer>> levelOrder(Node root) {
		//유효성 검사
		if(root == null) return new ArrayList<>();
        
		//결과값 반환할 어레이리스트 선언
        List<List<Integer>> rst = new ArrayList<>();
        
        //bfs에 필요한 큐선언
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            //각 level(층)에 담을 어레이시트스 선언
            List<Integer> list = new ArrayList<>();
            while(size-- > 0){
            	//각 층에 있는 노드들을 하나씩 빼서 위에 선언한 어레이리스트에 담기
                Node currNode = queue.poll();
                list.add(currNode.val);
                
                //해당 노드의 자식들이 있다면 큐에 넣기
                for(Node child : currNode.children){
                    queue.add(child);
                }
            }
            //해당 층에 loop가 끝났다면, 다음층 넘어가기 전에 rst에 해당층 노드들 어레이리스트에 담아놓은거 옮기기
            rst.add(list);
        }
        return rst;
    }
}
