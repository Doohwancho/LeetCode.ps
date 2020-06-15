package juneChallenge;

public class SearchInABinarySearchTree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	// <문제풀이1>
	
	//pre-order traversal

	// Runtime: 0 ms
	// Memory Usage: 39.9 MB

	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null) return null;
		if (root.val == val) return root;
		TreeNode t = searchBST(root.left, val);
		return t != null ? t : searchBST(root.right, val);
	}
	
	
	//<문제풀이2>
	
	//binary search
	
	//아 문제 이름이 binary search tree라고 나와있었네
	
	//그럼 굳이 pre/in/post-order traversal 쓸 필요가 없지
	
	//어느방향으로 갈지 알잖아. root.val이랑 val비교하면
	
	//root.val보다 val이 더 작으면 왼쪽으로 내려가고, root.val보다 val이 더 크면 오른쪽 노드로 가면 되제
	
	//Runtime: 0 ms
	//Memory Usage: 40 MB
	
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        if(root.val == val) return root;
        return root.val > val ? searchBST(root.left, val): searchBST(root.right, val);
    }
}
