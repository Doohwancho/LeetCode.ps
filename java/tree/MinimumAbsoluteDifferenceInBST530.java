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
	
	/*
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
	*/
	
	/*
	//<Trial01>
	
	//in-order-traversal로 한칸씩 돌면서 노드간 차이를 담으려고 했으나,
	
	//right child로 갔을 때, 이전 부모노드의 값을 받아오지 못해 실패.

    int rst = Integer.MAX_VALUE;
    
    private int inOrder(TreeNode root) {
        if(root == null) return 0;
        int left = inOrder(root.left);
        if(left > 0) rst = Math.min(rst, root.val - left);
        inOrder(root.right);    
        return root.val;
    }
    public int getMinimumDifference(TreeNode root) {
        if(root == null) return 0;
        inOrder(root);
        return rst;
    }
    */

	
	//<문제풀이2 by compson_scatter>
	
	//Trial01에서 right child로 갔을 때, 이전노드의 값을 받아오지 못하는 문제점을 해결.
	
	//깔-끔
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Absolute Difference in BST.
	//Memory Usage: 38.7 MB, less than 100.00% of Java online submissions for Minimum Absolute Difference in BST.
    
	int rst = Integer.MAX_VALUE;
    TreeNode prev;
    
    private void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        if(prev != null) rst = Math.min(rst, root.val - prev.val);
        prev = root;
        inOrder(root.right);    
    }
    public int getMinimumDifference(TreeNode root) {
        if(root == null) return 0;
        inOrder(root);
        return rst;
    }
}
