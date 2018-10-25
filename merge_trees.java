package LeetCode05;

import javax.swing.tree.TreeNode;

public class merge_trees {

		public static int[] mergeTrees(int[] t1, int[] t2) {
			
			int val;
			TreeNode left;
			TreeNode right;
			TreeNode(int x) { val = x; };
			 
	        if (t1 == null) {
	            return t2;
	        }
	        if (t2 == null) {
	            return t1;
	        }
	        
	        TreeNode root = new TreeNode(t1.val + t2.val);
	        root.left = mergeTrees(t1.left, t2.left);
	        root.right = mergeTrees(t1.right, t2.right);
	        
	        return root;
	    }
	    public static void main(String[] args) {
		int [] t1 = {1,3,2,5};
		int [] t2 = {2,1,3,null,4,null,7};
		System.out.println(mergeTrees(t1,t2));
		}  
	    }
	

