package array;

public class ConstructBinaryTreeFromInorderAndPostOrderTraversal106 {
	
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	
	//<문제풀이1 by jinwu>
	
	//어떻게 문제보고 이런 생각을 할까

	//이런것도 할수있구나 윽
	
	//Your input
	//[9,3,15,20,7]
	//[9,15,7,20,3]
	
	//node: 3 inStart: 4 idx+1: 2 postStart-1: 3
	//node: 3 idx-1: 0 inEnd: 0 postStart-(inStart-idx)-1: 0
	//-------------
	//node: 20 inStart: 4 idx+1: 4 postStart-1: 2
	//node: 20 idx-1: 2 inEnd: 2 postStart-(inStart-idx)-1: 1
	//-------------
	//node: 7 inStart: 4 idx+1: 5 postStart-1: 1                  --> inEnd > inStart이기 때문에 7의 left child, right child는 모두 null
	//node: 7 idx-1: 3 inEnd: 4 postStart-(inStart-idx)-1: 1
	//-------------
	//node: 15 inStart: 2 idx+1: 3 postStart-1: 0                 --> inEnd > inStart이기 때문에 7의 left child, right child는 모두 null
	//node: 15 idx-1: 1 inEnd: 2 postStart-(inStart-idx)-1: 0     
	//-------------
	//node: 9 inStart: 0 idx+1: 1 postStart-1: -1                 --> inEnd > inStart이기 때문에 7의 left child, right child는 모두 null
	//node: 9 idx-1: -1 inEnd: 0 postStart-(inStart-idx)-1: -1    --> postStart < 0이기 때문에 left child는 null
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
	//Memory Usage: 39.9 MB, less than 21.82% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal.
	
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return buildTree(inorder, inorder.length - 1, 0, postorder, postorder.length - 1);
	}

	private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart) {

		if (postStart < 0 || inEnd > inStart)
			return null;

		TreeNode root = new TreeNode(postorder[postStart]);

		int idx = 0;
		for (int i = inStart; i >= inEnd; i--) {
			if (inorder[i] == postorder[postStart]) {
				idx = i;
				break;
			}
		}

		root.right = buildTree(inorder, inStart, idx + 1, postorder, postStart - 1);
		root.left = buildTree(inorder, idx - 1, inEnd, postorder, postStart - (inStart - idx) - 1);

		return root;
	}
}
