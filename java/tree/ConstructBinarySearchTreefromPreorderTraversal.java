package thirtyDayChallenge;

public class ConstructBinarySearchTreefromPreorderTraversal {

	// <문제풀이1 by lee215>
	
	//binary search tree는 left child가 부모보다 작은놈, right child가 부모보다 큰놈으로 구성됨.

	// 110 / 110 test cases passed.
	// Status: Accepted
	// Runtime: 0 ms
	// Memory Usage: 37.4 MB

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	int i = 0;

	public TreeNode bstFromPreorder(int[] A) {
		return bstFromPreorder(A, Integer.MAX_VALUE); //tree.right의 최대치를 요런식으로 설정해놓네
	}

	public TreeNode bstFromPreorder(int[] A, int bound) {
		if (i == A.length || A[i] > bound) return null;
		TreeNode root = new TreeNode(A[i++]);
		root.left = bstFromPreorder(A, root.val); //tree.left의 최대치는 당연히 부모 노드겠고
		root.right = bstFromPreorder(A, bound);
		return root;
	}
}
