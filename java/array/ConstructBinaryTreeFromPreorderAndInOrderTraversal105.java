package array;

import java.util.Arrays;

public class ConstructBinaryTreeFromPreorderAndInOrderTraversal105 {
	
	/*
	//<Trial01>
	
	//문제 이해를 못하고 그저 만들라는것만 고대로 만듬..
	
	//핵심은 binary tree만들라는 건데..
	
	//정신차리자 친구야
	
	private void inOrder(TreeNode root, int[] A, int idx){
        if(root != null){
            if(root.left == null && idx < A.length){
                if(idx < A.length){
                    root.left = new TreeNode(A[idx++]);    
                } else {
                    root.left = null;
                }
            }
            if(root.right == null){
                if(idx < A.length){
                    root.right = new TreeNode(A[idx++]);    
                } else {
                    root.right = null;
                }
            } 
            inOrder(root.right, A, idx);
            // inOrder(root.left, A, idx);
        }
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length < 1) return null;
        TreeNode rst = new TreeNode(preorder[0]);
        inOrder(rst, preorder, 1);
        return rst;
    }
    */
	
	/*
	//<Trial02>
	
	//Input
	//[3,9,20,15,7]
	//[9,3,15,20,7]
	//Output
	//[3,9,20,null,null,15,null,7]
	//Expected
	//[3,9,20,null,null,15,7]
	
	//에서 막힘
	
    TreeNode node;
    
    private TreeNode addRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value < current.val || current.left == null) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.val || current.right == null) {
            current.right = addRecursive(current.right, value);  
        } else {
            // value already exists
            return current;
        }

        return current;
    }
    
    public void add(int value) {
        node = addRecursive(node, value);
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0; i < preorder.length; i++){
            add(preorder[i]);
        }
        return node;
    }
    */
	
	//<문제풀이1 by fabrizio3>
	
	//The basic idea is here:
	//Say we have 2 arrays, PRE and IN.
	//Preorder traversing implies that PRE[0] is the root node.
	//Then we can find this PRE[0] in IN, say it's IN[5].
	//Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
	//Recursively doing this on subarrays, we can build a tree out of it :).
	
	//int[] preorder = {3,9,20,15,7};
	//int[] inorder = {9,3,15,20,7};
	
	//    3
	//   / \
	//  9  20
	//    /  \
	//   15   7
	
	//breakindex: 1
	//subleftpre: 9 
	//subleftin: 9 
	//subrightpre: 20 15 7 
	//subrightin: 15 20 7 
	
	//breakindex: 1
	//subleftpre: 15 
	//subleftin: 15 
	//subrightpre: 7 
	//subrightin: 7 
	
	public static class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }

	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		if(preorder==null || inorder==null || inorder.length==0 || preorder.length==0) return null;
		TreeNode root = new TreeNode(preorder[0]);
		if(preorder.length==1) return root;
		int breakindex = -1;
		for(int i=0;i<inorder.length;i++) { if(inorder[i]==preorder[0]) { breakindex=i; break;} }
		int[] subleftpre  = Arrays.copyOfRange(preorder,1,breakindex+1);
		int[] subleftin   = Arrays.copyOfRange(inorder,0,breakindex);
		int[] subrightpre = Arrays.copyOfRange(preorder,breakindex+1,preorder.length);
		int[] subrightin  = Arrays.copyOfRange(inorder,breakindex+1,inorder.length);
		
		root.left  = buildTree(subleftpre,subleftin);
		root.right = buildTree(subrightpre,subrightin);
		return root;
	}
	
	public static void main(String[] args) {
		int[] preorder = {3,9,20,15,7};
		int[] inorder = {9,3,15,20,7};
		System.out.println(buildTree(preorder, inorder));
	}
	
}
