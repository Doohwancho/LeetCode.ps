package thirtyDayChallenge;

public class CheckIfStringIsValidSeqFromRootToLeavesInBinaryTree {

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
	
	// 이거지~~~~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	//recursive
	
	//밑으로 내려갈때마다 arr의 idx를 +1씩 해주면서, return에 가장 앞에있는 root.val == arr[idx]가 true로 바꿔줌.
	
	//근데 root기준 root.left가 맞는건지 root.right가 맞는건지 모르니까, 두개중 하나만 맞아도 true되게 or연산자 써줌.
	
	//그렇게 leaf의 가장 끝단(root.left와 root.right가 모두 null일떄까지) 감.
	
	//그렇게 끝단에 걸리면, 두번쨰 if문이 true가 되면서, 다시 올라감.

	// 63 / 63 test cases passed.
	// Status: Accepted
	// Runtime: 0 ms
	// Memory Usage: 40.4 MB

	private boolean check(TreeNode root, int[] arr, int idx) {
		if (root == null || idx == arr.length) return false;
		if (idx == arr.length - 1 && root.val == arr[idx] && root.left == null && root.right == null) return true;
		return root.val == arr[idx] && (check(root.left, arr, idx + 1) || check(root.right, arr, idx + 1));
	}

	public boolean isValidSequence(TreeNode root, int[] arr) {
		return check(root, arr, 0);
	}
}
