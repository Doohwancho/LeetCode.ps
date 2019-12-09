/*
	Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
	
	Example 1:
	Given tree s:
	
	     3
	    / \
	   4   5
	  / \
	 1   2
	Given tree t:
	   4 
	  / \
	 1   2
	Return true, because t has the same structure and node values with a subtree of s.
	Example 2:
	Given tree s:
	
	     3
	    / \
	   4   5
	  / \
	 1   2
	    /
	   0
	Given tree t:
	   4
	  / \
	 1   2
	Return false.
	
	
	
	<문제>
	
	트리 s,t가 다음과 같이 주어진다.
	
		Given tree s:
	
	     3
	    / \
	   4   5
	  / \
	 1   2
	Given tree t:
	   4 
	  / \
	 1   2
	 
	 
	t가 s의 subtree(종속되는지) 여부를 반환하라.
	
	만약에 다음처럼 
	
		Given tree s:
	
	     3
	    / \
	   4   5
	  / \
	 1   2
	    /
	   0
	Given tree t:
	   4
	  / \
	 1   2
	 
	 0이 삐져나왔다면, s의 subtree인 node(4)가 t랑 완전히 일치하지 않으므로, false를 반환한다.
 */

package Tree;

public class SubtreeOfAnotherTree572 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	//<문제풀이1>
	
	//isSubtree()는 s를 돌면서 s의 노드가 t의 root와 같은지 확인해주는 함수. 
	
	//compare()은 s의 노드중 하나가 t의 root가 같다면 작동함. 
	
	//compare()의 역할은 s의 subtree(선택한 node를 root삼아)와 t를 비교하여
	
	//하나라도 노드의 값이 다르다면, false를 반환해주는 함수.
	
	//지렸다.
	
	//Runtime: 6 ms, faster than 89.74% of Java online submissions for Subtree of Another Tree.
	//Memory Usage: 39.6 MB, less than 100.00% of Java online submissions for Subtree of Another Tree.
    
	private boolean compare(TreeNode s, TreeNode t){
        if(s == null && t == null) return true;
        if((s == null && t != null) || (s != null && t == null) || s.val != t.val) return false;
        return compare(s.left, t.left) && compare(s.right, t.right);
    }
    
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null) return false;
        if(s.val == t.val && compare(s,t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
}
