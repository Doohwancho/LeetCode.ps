/*
	A binary tree is univalued if every node in the tree has the same value.
	
	Return true if and only if the given tree is univalued.
	
	 
	
	Example 1:
	
	
	Input: [1,1,1,1,1,null,1]
	Output: true
	Example 2:
	
	
	Input: [2,2,2,5,2]
	Output: false
	
	
	
	<문제>
	
	tree의 모든값이 똑같으면 true, 아니면 false를 반환하라.
 */
package Tree;

public class UnivaluedBinaryTree965 {

	public class TreeNode {
	  int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
	}
	// <문제풀이1>

	// 크...이거지

	// Runtime: 0 ms, faster than 100.00% of Java online submissions for Univalued Binary Tree.
	// Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Univalued Binary Tree.
	
	private boolean dfs(TreeNode root, int uniValue) {
		if (root == null) return true;  //uniValue와 다른 값이 나타난 모든 경우의 수(root.left가 null이라던가..)를 true반환하게끔 함 
		if (root.val != uniValue) return false; //오직 노드의 값이 uniValue와 다를때만 false반환.
		return dfs(root.left, uniValue) && dfs(root.right, uniValue); //false가 한번이라도 나오면 and연산자에 걸려서 false반환.
	}

	public boolean isUnivalTree(TreeNode root) {
		if (root == null) return false; //유효성검사
		return dfs(root, root.val);
	}
}
