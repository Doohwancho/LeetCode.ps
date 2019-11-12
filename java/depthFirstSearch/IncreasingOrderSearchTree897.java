package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class IncreasingOrderSearchTree897 {
	
	 public class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
	 
	/*
	 *  
	 *  <문제풀이 by lee215>
	 *  <문제해설 by Rooch>
	 	We can understand the solution by making the tree on paper.
		Example:
		
		                                                        5
		                                               3               6
		                                     2                 4                  8
		                           1                                       7                  9 
		
		The idea is to go to the left part of the tree.
		
		res = self.increasingBST(root.left, root)
		# Here we are traversing left part of the tree and here the tail/next node is our current node.
		So we get 1 2 3,
		when we get to 4 we can see that for all the right nodes tail/next pointer is basically the parent of the parent. Example - for node-4 tail will be 5.
		Otherwise tail is the parent.
		Therefor
		
		if not root: return tail
		#returns tail is its parent if None Node was a left Child 
		#returns tail is its parent's parent if None Node was a right child 
		The statement root.left means as we are creating a right-skewed tree or singly linked list we just need to go to the right direction. SO we are just putting a None to the left part of the tree/List.
		
		 root.right = self.increasingBST(root.right, tail)
		# returns parent's parent if right child else left child
		
		 return res
		# we return the leftmost node as root
		
	 */
	 
    public TreeNode increasingBST(TreeNode root) {
        return increasingBST(root, null);
    }

    public TreeNode increasingBST(TreeNode root, TreeNode tail) {
        if (root == null) return tail;
        TreeNode res = increasingBST(root.left, root);
        root.left = null;
        root.right = increasingBST(root.right, tail);
        return res;
    }
}
