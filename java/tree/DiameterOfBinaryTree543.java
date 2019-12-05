
package Tree;

public class DiameterOfBinaryTree543 {

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
	
	//모르겠다 ㅜㅜ
	
    int left;
    int right;
    
    private int dfs(TreeNode root, int path){
        if(root == null) return 0;
        left += dfs(root.left, path);
        right += dfs(root.right, path);
        return left+right+1;
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        dfs(root, 0);
        return 999;
    }
    */
	
	
	//<문제풀이1 by shawngao>
	
	//If at a root node (without using parents), the diameter is the maximum of either:

	//Max depth for Left Subtree + max depth for right subtree
	//Diameter of Left subtree (without using this TreeNode)
	//Diameter of Right subtree (without using this TreeNode)
	//We visit every TreeNode recursively and calculate (1), saving the largest value we've found in max. 
	//Since we calculate this value for every TreeNode, we have indirectly calculated (2) and (3) as well. 
	//Our solution is the final value for max.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Diameter of Binary Tree.
	//Memory Usage: 38.6 MB, less than 24.68% of Java online submissions for Diameter of Binary Tree.
	
    private int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) { // defines 1-TreeNode tree to have depth 1
        if (root == null) {
            return 0;
        }
        int L = maxDepth(root.left);
        int R = maxDepth(root.right);
        max = Math.max(max, L + R); // 'L+R' is diameter: L to root to R
        return 1 + Math.max(L, R);
    }
   
}
