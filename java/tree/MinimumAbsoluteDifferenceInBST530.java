package Tree;

import java.util.ArrayList;
import java.util.List;

public class MinimumAbsoluteDifferenceInBST530 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	//<문제풀이1>
	
	//bst tree를 in-order-traversal로 돌면서 리스트에 값을 담으면, 오름차순으로 자동적으로 이쁘게 담아짐.
	
	//bst는 왼쪽맨 밑 노드가 값이 젤 작고, 오른쪽으로 갈수록 점점 커지는데, in-order-traversal이 왼쪽 맨밑부터 차례대로 오른쪽으로 돌기 때문. 아래 링크 참조.
	
	//https://doohwancho.tistory.com/534?category=1042623
	
	//모든 트리의 값이 오름차순 정렬된 체로 리스트에 담겼으니, 리스트를 for문으로 돌면서 서로간 차이가 최소인 것을 Math.min()으로 뽑으면 됨.
	
	//Runtime: 2 ms, faster than 35.39% of Java online submissions for Minimum Absolute Difference in BST.
	//Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Minimum Absolute Difference in BST.

	List<Integer> list = new ArrayList<>();

	private void dfs(TreeNode root) {
		if (root == null) return;
		dfs(root.left);
		list.add(root.val);
		dfs(root.right);
	}

	public int getMinimumDifference(TreeNode root) {
		if (root == null) return 0;
		dfs(root);
		int rst = Integer.MAX_VALUE;
		for (int i = 1; i < list.size(); i++) {
			rst = Math.min(rst, list.get(i) - list.get(i - 1));
		}
		return rst;
	}
}
