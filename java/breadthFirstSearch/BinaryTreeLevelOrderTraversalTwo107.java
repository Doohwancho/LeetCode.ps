/*
	Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
	
	For example:
	Given binary tree [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its bottom-up level order traversal as:
	[
	  [15,7],
	  [9,20],
	  [3]
	]
	
	
	
	<문제>
	
	주어진 트리에 가장 밑에층부터 층층이 값을 넣어 반환하라. 
	
		3
	   / \
	  9  20
	    /  \
	   15   7

	요런식으로.
	[
	  [15,7],
	  [9,20],
	  [3]
	]
 */
package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversalTwo107 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	//<문제풀이1>
	
	//bfs방식.
	
	//매 loop마다 값을 tmp라는 리스트에 넣고, 해당 층(level, depth)의 loop이 끝나면 tmp를 rst라는 리스트에 넣음.
	
	//tmp는 일반적으로 맨 뒤 인덱스에 하나하나 쌓는것이라 arraylist로 선언
	
	//rst는 순서를 가장 밑에층부터 거꾸로 하라고 했고, 시작을 맨 윗층부터 하기 때문에, tmp삽입시 index 0에 넣어야 함.
	
	//리스트에서 index0에 넣는게 arraylist보다 linkedlist가 구조상 더 빠르므로 rst는 linkedlist로 선언.
	
	//Runtime: 1 ms, faster than 96.03% of Java online submissions for Binary Tree Level Order Traversal II.
	//Memory Usage: 36.4 MB, less than 100.00% of Java online submissions for Binary Tree Level Order Traversal II.

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		if (root == null)
			return new ArrayList<>();

		List<List<Integer>> rst = new LinkedList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			List<Integer> tmp = new ArrayList<>();
			int size = queue.size();
			while (size-- > 0) {
				TreeNode currNode = queue.poll();
				tmp.add(currNode.val);
				if (currNode.left != null)
					queue.add(currNode.left);
				if (currNode.right != null)
					queue.add(currNode.right);
			}
			rst.add(0, tmp);
		}
		return rst;
	}
}
