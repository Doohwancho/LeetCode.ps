package juneChallenge;

public class InvertBinaryTree {
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
	
	/*
	//<문제풀이1>
	
	//그냥 pre/post order중 암거나 둘면서
	
	//left child랑 right child 위치만 바꿔주면 됨.
	
	//Runtime: 0 ms
	//Memory Usage: 36.7 MB

	private void flip(TreeNode root) {
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
	}

	private void preorder(TreeNode root) {
		if (root == null) return;
		flip(root);
		preorder(root.left);
		preorder(root.right);
	}

	public TreeNode invertTree(TreeNode root) {
		preorder(root);
		return root;
	}
	*/
	
	//<문제풀이2 by SOY>
	
	//bottom up approach
	
	//Runtime: 0 ms
	//Memory Usage: 37.1 MB
	
    private TreeNode invert(TreeNode root){
        if(root == null) return null;
        TreeNode tmp = root.right;
        root.right = invert(root.left);
        root.left = invert(tmp);
        return root;
    }
    
    public TreeNode invertTree(TreeNode root) {
        return invert(root);
    }
}
