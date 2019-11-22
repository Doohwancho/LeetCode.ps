package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class PathSum112 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	/*
	//<문제풀이1>
	
	//pre-order-traversal로 돌면서 leaf면(left child, right child 없으면) 파라미터로 더해오던 값을 리스트에 올리고
	
	//dfs가 끝나면 리스트의 값들이 문제에서 제세한 sum과 같은지 확인하는 방법
	
	//Runtime: 17 ms, faster than 5.37% of Java online submissions for Path Sum.
	//Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for Path Sum.

	static List<Integer> list = new ArrayList<>();

	private static void dfs(TreeNode root, int sum) {
		if (root == null)
			return;
		sum += root.val;
		if (root.left == null && root.right == null)
			list.add(sum);
		dfs(root.left, sum);
		dfs(root.right, sum);
		return;
	}

	public boolean hasPathSum(TreeNode root, int sum) {
		dfs(root, 0);
		for (int i : list) {
			if (i == sum)
				return true;
		}
		return false;
	}
	*/
	
	//<문제풀이2>
	
	//굳이 리스트에 값 다 안넣고 boolean하나로 해결.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Path Sum.
	//Memory Usage: 37.5 MB, less than 94.12% of Java online submissions for Path Sum.
	
    boolean flag = false;
    
    private void dfs(TreeNode root, int sum, int target){
        if(root == null) return;
        sum += root.val;
        if(root.left == null && root.right == null && sum == target){
            flag = true;
            return;    
        } 
        dfs(root.left, sum, target);
        dfs(root.right, sum, target);
        return;
    }
    
    public boolean hasPathSum(TreeNode root, int sum) {
        dfs(root, 0, sum);
        return flag;
    }
}
