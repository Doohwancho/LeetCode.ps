/*
	Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
	
	
	
	For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
	
	Two binary trees are considered leaf-similar if their leaf value sequence is the same.
	
	Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
	
	 
	
	Note:
	
	Both of the given trees will have between 1 and 100 nodes.
	
	
	
	
	<문제>
	
	tree의 가장 끝단에 있는 노드를 leaf라고 할 때, tree1과 tree2의 leaf의 값들이, 가장 왼쪽값부터 순서대로 일치하는지 여부를 반환하라.
 */

package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees872 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	//<문제풀이>
	
	//depth-first-search(in order traversal) 방법 사용.
	
	//pre/in/post order차이는 아래 링크 참조
	
	//https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
	
	//3가지 방법중 in-order사용한 이유는, 루트 순회 순서가 가장 왼쪽부터 차례대로 방문하기 때문.
	
	//leaf를 구별하는 방법은 해당 노드에 자식이 없는지 확인. 자식이 없는지 확인하려면, left child와 right child가 없어야 하기 때문에,
	
	//if(root.left == null && root.right == null)로 조건을 주었음.
	
	//심플한 방법이긴 하지만 성능이 좋음.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Leaf-Similar Trees.
	//Memory Usage: 34.9 MB, less than 100.00% of Java online submissions for Leaf-Similar Trees.

	private void dfs(TreeNode root, List<Integer> list) {
		if (root == null)
			return;
		dfs(root.left, list);
		if (root.left == null && root.right == null)
			list.add(root.val);
		dfs(root.right, list);
	}

	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		dfs(root1, list1);
		dfs(root2, list2);
		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i) != list2.get(i))
				return false;
		}
		return true;
	}
}
