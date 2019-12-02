package Tree;

import java.util.ArrayList;
import java.util.List;

public class SumOfRootToLeafBinaryNumbers1022 {

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
	
	//pre-order-traversal로 한층씩 내려가면서 해당 노드의 숫자(node.val)을 string에 붙여 다음 child로 넘겨주는 방법.
	
	//그러다가 leaf면(tree의 가장 밑 끝단이라 left child, right child가 없다면) list에 담아서
	
	//마지막에 parseInt()로 int형변환 후, 값을 모두 더해 반환해주는 방법.
	
	//Runtime: 2 ms, faster than 29.67% of Java online submissions for Sum of Root To Leaf Binary Numbers.
	//Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Sum of Root To Leaf Binary Numbers.

	List<String> list = new ArrayList<>();

	private void dfs(TreeNode root, String s) {
		if (root == null) return;			
		s += root.val + "";
		if (root.left == null && root.right == null) list.add(s);
		dfs(root.left, s);
		dfs(root.right, s);
	}

	public int sumRootToLeaf(TreeNode root) {
		if (root == null) return 0;			
		dfs(root, "");
		int rst = 0;
		for (String str : list) {
			rst += Integer.parseInt(str, 2);
		}
		return rst;
	}
	*/
	
	//<문제풀이2 by lee215>
	
	//parent node -> child node될 때, 2진수니까, parent node가 1이면, child node에서 10이 됨. 
	
	//그럼 단순히 1*2 해주면 됨. 마찬가지로 또다시 child node로 갈 때, 2진수니까 100이됨. 그래서 1*2*2=4 해주면 됨.
	
	//그리고 return문에 root.left==root.right조건을 건 이유는, 
	
	//input이 [1,0,1,0,1,0,1] 이렇게 주어지기 때문에, left child, right child 둘 다있으면 0, 1이되어 서로 다를 수 밖에 없고,
	
	//둘 중 하나만 있고 나머지 하나가 null이라 그래도 다르기 때문에, dfs(root.left, val) + dfs(root.right, val) 이부분이 작동됨.
	
	//left child, right child둘 다 null이여야지 val을 반환함.
	
	//똘똘허이
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Sum of Root To Leaf Binary Numbers.
	//Memory Usage: 36.9 MB, less than 100.00% of Java online submissions for Sum of Root To Leaf Binary Numbers.
	
	public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int val) {
        if (root == null) return 0;
        val = val * 2 + root.val;
        return root.left == root.right ? val : dfs(root.left, val) + dfs(root.right, val);
    }
}
