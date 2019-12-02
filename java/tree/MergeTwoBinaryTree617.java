/*
	Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
	
	You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
	
	Example 1:
	
	Input: 
		Tree 1                     Tree 2                  
	          1                         2                             
	         / \                       / \                            
	        3   2                     1   3                        
	       /                           \   \                      
	      5                             4   7                  
	Output: 
	Merged tree:
		     3
		    / \
		   4   5
		  / \   \ 
		 5   4   7
		 
		 
		 
	<문제>
	
	tree1과 tree2을 합쳐라.
 */

package Tree;

public class MergeTwoBinaryTree617 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
    /*
	// <Trial01>

	// merge(t1.left, t2.left, rst.left);

	// 이부분에서 null pointer exception에러남.

	private void merge(TreeNode t1, TreeNode t2, TreeNode rst) {
		if (t1 == null && t2 == null)
			return;

		if (t1 != null && t2 != null) {
			rst.val = t1.val + t2.val;
		} else if (t1 == null) {
			rst.val = t2.val;
		} else if (t2 == null) {
			rst.val = t1.val;
		}
		if ((t1 != null && t1.left != null) || (t2 != null && t2.left != null)) {
			TreeNode left = new TreeNode(0);
			rst.left = left;
			merge(t1.left, t2.left, rst.left); // null pointer exception
		}
		if ((t1 != null && t1.right != null) || (t2 != null && t2.right != null)) {
			TreeNode right = new TreeNode(0);
			rst.right = right;
			merge(t1.right, t2.right, rst.right);
		}
	}

	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			TreeNode node = null;
			return node;
		}
		TreeNode rst = new TreeNode(0);
		merge(t1, t2, rst);
		return rst;
	}
	*/
	
	//<문제풀이 by MeghaShanbhag>
 
	//똑똑허이..
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Binary Trees.
	//Memory Usage: 40.1 MB, less than 100.00% of Java online submissions for Merge Two Binary Trees.
	
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode node = null;
         if(t1 == null && t2 == null) return node;
         if(t1 != null && t2 != null){
             node = new TreeNode(t1.val+t2.val);
             node.left = mergeTrees(t1.left, t2.left);
             node.right = mergeTrees(t1.right, t2.right);
         }
         else if(t1 == null){             //t1이 null이면
             node = new TreeNode(t2.val);
             node.left = t2.left;         //node의 left child는 t2의 left child
             node.right = t2.right;       //node의 right child는 t2의 right child
         }
         else if(t2 == null){             //t2가 null이면
             node = new TreeNode(t1.val);
             node.left = t1.left;         //node의 left child는 t1의 left child
             node.right = t1.right;       //node의 right child는 t1의 right child
         }
         return node;
    }
}
