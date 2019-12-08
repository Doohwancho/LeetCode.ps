package Tree;

public class PathSumIII437 {

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
	
	//root부터 한번도 안끊기고 더해줘야 해서 에러.

	int rst = 0;
 
	private void dfs(TreeNode root, int sum, int aggregate) {
		if (root == null) return;
		aggregate += root.val;
		if (aggregate == sum) rst++;
		dfs(root.left, sum, aggregate);
		dfs(root.right, sum, aggregate);
	}

	public int pathSum(TreeNode root, int sum) {
		if (root == null) return 0;
		dfs(root, sum, 0);
		return rst;
	}
	*/
	
	//<문제풀이 by jiangsichu>
	
	//brute-force
	
	//pathSum()은 모든 노드를 pre-order-traversal로 돌게끔 하는거고
	
	//pathSumFrom()은 해당 노드를 root삼아서, child node들과의 합이 sum인지 판별해주는 함수
	
	//Runtime: 11 ms, faster than 52.24% of Java online submissions for Path Sum III.
	//Memory Usage: 41.4 MB, less than 52.27% of Java online submissions for Path Sum III.
	
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }
	
}
