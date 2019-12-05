/*
	Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
	
	Example :
	
	Input: root = [4,2,6,1,3,null,null]
	Output: 1
	Explanation:
	Note that root is a TreeNode object, not an array.
	
	The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
	
	          4
	        /   \
	      2      6
	     / \    
	    1   3  
	
	while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
	
	
	
	
	<문제>
	
	트리가 주어지면, 트리의 모든 노드들의 값들을 비교하여 그중 값의 차이가 가장 작은 수를 반환하라.
 */

package Tree;

import java.util.ArrayList;
import java.util.List;

public class MinimumDistanceBetweenBSTNodes783 {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	/*
	//<Trial01>
	
	//'서로 붙어있는 노드들 중에서' 차이가 가장 작은걸 구하라는줄 착각함.

	public int minDiffInBST(TreeNode root) {
		if (root == null) return 0;
		
		int minDiff = Integer.MAX_VALUE;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				TreeNode currNode = queue.poll();
				if (currNode.left != null) {
					minDiff = Math.min(minDiff, Math.abs(currNode.val - currNode.left.val));
					queue.add(currNode.left);
				}
				if (currNode.right != null) {
					minDiff = Math.min(minDiff, Math.abs(currNode.val - currNode.right.val));
					queue.add(currNode.right);
				}
			}
		}
		return minDiff;
	}
	*/
	
	
	//<문제풀이1>
	
	//bst tree를 inorder로 돌면서 리스트에 담으면, 오름차순으로 담기니까, 오름차순으로 담긴 값들의 차이중 최솟값을 구해 반환하면 됨.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Distance Between BST Nodes.
	//Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Minimum Distance Between BST Nodes.
    static List<Integer> list = new ArrayList<>();
    
    private static void inOrder(TreeNode root){
        if(root == null) return;
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
    public int minDiffInBST(TreeNode root) {
        if(root == null) return 0;
        inOrder(root);
        int minDiff = Integer.MAX_VALUE;
        for(int i = 1; i < list.size(); i++){
            minDiff = Math.min(minDiff, list.get(i)-list.get(i-1));
        }
        return minDiff;
    }
    
    
}
