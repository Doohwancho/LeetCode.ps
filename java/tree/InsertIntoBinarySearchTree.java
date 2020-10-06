package october;

public class InsertIntoBinarySearchTree {

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
	
	//binary search tree

	// Runtime: 0 ms
	// Memory Usage: 39.6 MB

	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null) {
			return new TreeNode(val);
		}
		TreeNode parent = root;
		if (val < root.val) {
			if (root.left == null) {
				TreeNode tmp = new TreeNode(val);
				root.left = tmp;
			}
			insertIntoBST(root.left, val);
		} else if (val > root.val) {
			if (root.right == null) {
				TreeNode tmp = new TreeNode(val);
				root.right = tmp;
			}
			insertIntoBST(root.right, val);
		}
		return parent;
	}
}
