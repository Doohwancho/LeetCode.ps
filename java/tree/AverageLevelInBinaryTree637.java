package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageLevelInBinaryTree637 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	//<문제풀이1>
	
	//bfs방식으로 돌면서 해당층의 값을 모두 summation이라는 변수에 더해서, 해당 층의 원소의 갯수인 denominator로 나눠준 후, 리스트 변수 rst에 더해주는 것.

	// Runtime: 2 ms, faster than 97.66% of Java online submissions for Average of Levels in Binary Tree.
	// Memory Usage: 39.7 MB, less than 100.00% of Java online submissions for Average of Levels in Binary Tree.
	List<Double> rst = new ArrayList<>();

	public List<Double> averageOfLevels(TreeNode root) {
		if (root == null)
			return null;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size(); //해당 층의 원소갯수만큼 돔
			double summation = 0;
			int denominator = size; //n개 숫자 평균내려면 n으로 나눠주어야 하니깐.

			while (size-- > 0) {  //해당 층의 원소갯수만큼 돔
				TreeNode currNode = queue.poll(); //맨 앞엣것만 뺌. 자식들은 위에 queue.add(root)에서 뒤에 더해짐
				summation += currNode.val;
				if (currNode.left != null)
					queue.add(currNode.left);
				if (currNode.right != null)
					queue.add(currNode.right);
			}
			rst.add(summation / denominator);
		}
		return rst;
	}
}
