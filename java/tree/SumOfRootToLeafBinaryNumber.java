package september;

import java.util.ArrayList;
import java.util.List;

public class SumOfRootToLeafBinaryNumber {

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
	
	//brute-force
	
	//string으로 +=해주면 너무 오래걸리니까, stringbuilder로 대신해서
	
	//한칸씩 내려갈때마다 root.val붙여줌.
	
	//물론 돌아오면 sb.deleteCharAt(sb.length() - 1); 로 해당 노트 밑에 이미 다녀온 애들은 떼주고.
	
	//if (root.left == null && root.right == null) {
//		list.add(new StringBuilder(sb));
//		return;
//	}
	
	//에서 list.add(sb);를 안하고 list.add(new StringBuilder(sb));를 한 이유는
	
	//새로 stringbuilder를 복사하지 않으면, 포인터가 이전 주솟값을 가리키기 때문에,
	
	//다음 노드 돌면서 sb.append(root.val)할 때, 
	
	//리스트에 이미 들어가 있는 stringbuilder까지 바뀌기 때문.
	
	//binary number -> int할 땐,
	
	//Integer.parseInt(String(binary number), 2);
	
	//함수를 이용함.

	// Runtime: 2 ms
	// Memory Usage: 38.8 MB

	List<StringBuilder> list;

	private void dfs(TreeNode root, StringBuilder sb) {
		if (root == null)
			return;
		sb.append(root.val);

		if (root.left == null && root.right == null) {
			list.add(new StringBuilder(sb));
			return;
		}
		if (root.left != null) {
			dfs(root.left, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
		if (root.right != null) {
			dfs(root.right, sb);
			sb.deleteCharAt(sb.length() - 1);
		}

	}

	public int sumRootToLeaf(TreeNode root) {
		if (root == null)
			return 0;
		list = new ArrayList<>();
		dfs(root, new StringBuilder());
		int rst = 0;
		for (StringBuilder sb : list) {
			rst += Integer.parseInt(sb.toString(), 2);

		}

		return rst;
	}
	
	
	//<문제풀이2 by lee215>
	
	//이인간은 항상 감탄사가 터져나오는 방법을 생각해내는구만
	
	//어짜피 이진수의 자릿수가 왼쪽으로 갈때마다 *2되잖아
	
	//그걸 val = val*2 + root.val로 표현한거고
	
	//만약 내려가다가 leaf(끄트머리)면 여태껏 더해준걸 반환하고, (root.left == null && root.right == null ? val)
	
	//그 반환한 거가 위로 올라오면서 dfs(root.left, val) + dfs(root.right, val) 로 다 더해져 
	
	//총 합이 반환되게 됨.
	
	//O(N)
	
	//Runtime: 0 ms
	//Memory Usage: 38.6 MB
	
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int val) {
        if(root == null) return 0;
        val = val*2 + root.val;
        return root.left == null && root.right == null ? val : dfs(root.left, val) + dfs(root.right, val);
    }
}
