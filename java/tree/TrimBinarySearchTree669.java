package Tree;

public class TrimBinarySearchTree669 {

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
	
	//Your input : [1,0,2] L: 1 R: 2
	//stdout : 1, 2
	//Output : []
	//Expected : [1,null,2]
	
	//값은 잘 들어가는데 null반환함. 원인 못찾음..ㅜ

	private void trim(TreeNode root, TreeNode node, int L, int R) {
		if (root == null)
			return;
		if (root.val >= L && root.val <= R) {
			node = new TreeNode(root.val);
		}
		if (root.left != null && root.left.val >= L)
			trim(root.left, node.left, L, R);
		if (root.right != null && root.right.val <= R)
			trim(root.right, node.right, L, R);
	}

	public TreeNode trimBST(TreeNode root, int L, int R) {
		TreeNode node = null;
		if (root == null)
			return null;
		trim(root, node, L, R);
		return node;
	}
	*/
	
	//<문제풀이 by shawngao>

	//https://www.youtube.com/watch?v=hFwakLj7wFA&feature=youtu.be
	
	//bst트리는 왼쪽에서 오른쪽으로 갈수록 숫자가 커짐. root.left.val < root.val < root.right.val
	
	//그 점을 이용해서 root.val이 L과 R사이 범위에서 벗어난 경우, root.val이 L보다 작으면, 그보다 더 작은 root.left.val은 볼 필요도 없으니, root.right을 붙여주고
	
	//반대로 root.val이 R보다 더 크면, 그보다 더 큰 root.right.val은 볼 필요도 없으니, root.left을 붙여주는 것.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Trim a Binary Search Tree.
	//Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Trim a Binary Search Tree.

	public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;    

        if (root.val < L) return trimBST(root.right, L, R);
        if (root.val > R) return trimBST(root.left, L, R);
        
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        
        return root;
    }
}
