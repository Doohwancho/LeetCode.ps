package Tree;

import java.util.ArrayList;
import java.util.List;

public class NaryTreePostorderTraversal590 {
	
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
	
	//<문제풀이>
	
	//post-order-traversal
	
	//root = [1,null,3,2,4,null,5,6] 일 때,  
	
	//예상 순서는 6->5->4->2->3->1 X
	//실제 순서는 5->6->3->2->4->1 O
	
	//level order traversal이랑 비슷하게 층별로 반환하되, post-order-traversal은 아래에서 위로 반환하는구나.
	
	//Runtime: 1 ms, faster than 98.71% of Java online submissions for N-ary Tree Postorder Traversal.
	//Memory Usage: 39.3 MB, less than 100.00% of Java online submissions for N-ary Tree Postorder Traversal.
	
    List<Integer> rst = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if(root == null) return new ArrayList<>(0);
        for(Node child : root.children) postorder(child);
        rst.add(root.val);
        return rst;
    }
}
