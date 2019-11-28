/*
	In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
	
	Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
	
	We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
	
	Return true if and only if the nodes corresponding to the values x and y are cousins.
	
	
	
	<문제>
	
	tree에서 부모 노드가 다르나 같은층(depth)에 있는걸 서로 조카관계에 있다고 할 때,

	x,y가 조카관계에 있는지 여부를 반환하라.
	
 */

package BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

public class CousinsInBinaryTree993 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	//<문제풀이1>
	
	//bfs방법에 조건 두개 추가한것.
	
	//조건1. 부모가 달라야한다(==각 노드 돌 때, node.left랑 node.right가 x,y인지 확인하여 맞으면 false반환. 부모가 같으니까.)
	
	//조건2. x,y가 같은층이여야 한다.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Cousins in Binary Tree.
	//Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Cousins in Binary Tree.

	public boolean isCousins(TreeNode root, int x, int y) {
		// 유효성 검사
		if (root == null)
			return false;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int depth = 0;
		int depthX = 0;
		int depthY = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				TreeNode currNode = queue.poll();
				if (currNode.left != null)
					queue.add(currNode.left);
				if (currNode.right != null)
					queue.add(currNode.right);

				if ((currNode.left != null && currNode.right != null)						//조건1
						&& ((currNode.left.val == x && currNode.right.val == y)
								|| (currNode.left.val == y && currNode.right.val == x))) { 
					return false;
				}
				if (currNode.val == x) //x층 update
					depthX = depth;
				if (currNode.val == y) //y층 update
					depthY = depth;
			}
			depth++; //한층 내려갈때마다 depth+1
		}
		return depthX == depthY ? true : false; //조건2 with 3항연산자
	}
}
