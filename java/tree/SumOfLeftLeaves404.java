/*
	Find the sum of all left leaves in a given binary tree.
	
	Example:
	
	    3
	   / \
	  9  20
	    /  \
	   15   7
	
	There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
	
	
	
	
	
	
	<문제>
	
	leaf란, 트리의 노드들 중에서 left child, right child 모두 없이 주로 가장 밑에있는 노드를 뜻한다.
	
		3
	   / \
	  9  20
	    /  \
	   15   7
	   
	이 트리에서 leaf는 9,15,7이다.
	
	이중, left leaf들의 총 합을 반환하라.
 */

package Tree;

public class SumOfLeftLeaves404 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	//<문제풀이1>
	
	//1. leaf의 조건은 left child, right child가 없어야(null)한다는 것.
	
	//2. 해당 leaf가 left child여야 한다는 것.
	
	//조건 1은 if(root.left == null && root.right == null)로 해결.
	
	//조건 2는 파라미터에 left면 true, right면 false를 넣어 left child일때만 if문이 작동하게끔 하여 해결.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Sum of Left Leaves.
	//Memory Usage: 36.5 MB, less than 100.00% of Java online submissions for Sum of Left Leaves.

    int leftSum = 0;
    private void dfs(TreeNode root, boolean left){
        if(root == null) return;
        if(left && root.left == null && root.right == null) leftSum+=root.val;
        dfs(root.left, true);
        dfs(root.right, false);
    }
    
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root, false);
        return leftSum;
    }
}
