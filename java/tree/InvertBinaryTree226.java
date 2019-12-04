/*
	Invert a binary tree.
	
	Example:
	
	Input:
	
	     4
	   /   \
	  2     7
	 / \   / \
	1   3 6   9
	
	Output:
	
	     4
	   /   \
	  7     2
	 / \   / \
	9   6 3   1
	
	Trivia:
	This problem was inspired by this original tweet by Max Howell:
	
	Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
	
	
	<문제>
	
	주어진 tree를 좌우반전으로 뒤집어라.
	
	주석 by Max Howell
	
	구글 엔지니어 90%가 니가 만든 프로그램쓰는데, 넌 binary tree를 뒤집는것도 모르냐? 
	
	ㅋㅋ
 */

package Tree;

public class InvertBinaryTree226 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	//<문제풀이1>
	
	//dfs를 하되, 매 노드에 들를때마다, 왼쪽 노드와 오른쪽 노드를 바꿔주는 방법.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Invert Binary Tree.
	//Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Invert Binary Tree.

	public TreeNode invertTree(TreeNode root) {
		TreeNode tmp = null;
		if (root == null) return tmp;

		tmp = root.left;
		root.left = root.right;
		root.right = tmp;

		invertTree(root.left);
		invertTree(root.right);

		return root;
	}
}
