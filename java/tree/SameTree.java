package julyChallenge;

public class SameTree {

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

	// 크 이거지. 완샷 완킬

	// Runtime: 0 ms
	// Memory Usage: 38.8 MB

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) return true;
		else if (p == null || q == null) return false; // 둘중 하나만 null이거나 값이 다른 경우만 false, 나머지 경우는 모두 true반환.
		return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right); // 양 child모두 true값이 나와야 인정
	}
	
	//<문제풀이2 by ijaz20>
	
	//같은걸 한줄로 압축한 것.
	
	//p==q || 처리한 이유는 null값이 있을 수 있어서. 
	
	// Runtime: 0 ms
	// Memory Usage: 38.8 MB
	
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return p == q || (p!= null && q!=null && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
	
	
}
