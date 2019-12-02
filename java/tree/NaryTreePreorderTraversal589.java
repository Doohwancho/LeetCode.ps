package Tree;

import java.util.ArrayList;
import java.util.List;

public class NaryTreePreorderTraversal589 {
	
	
	// Definition for a Node.
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
	
	//pre-order-traversal
	
	//Runtime: 1 ms, faster than 98.81% of Java online submissions for N-ary Tree Preorder Traversal.
	//Memory Usage: 40.1 MB, less than 100.00% of Java online submissions for N-ary Tree Preorder Traversal.
	
    List<Integer> rst = new ArrayList<>(); //전역변수에 때려넣음
    
    public List<Integer> preorder(Node root) {
        if(root == null) return new ArrayList<>(0);
        rst.add(root.val);
        for(Node child : root.children) preorder(child);
        return rst;
    }
}
