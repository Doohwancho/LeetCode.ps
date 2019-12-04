/*
	Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
	
	Example:
	
	Input: The root of a Binary Search Tree like this:
	              5
	            /   \
	           2     13
	
	Output: The root of a Greater Tree like this:
	             18
	            /   \
	          20     13
	          
	          
	          
	<문제>
	
	트리가 주어지면, 각 노드별로 자기보다 큰 숫자가 있는 다른 노드들의 총 합을 더해라.
	
	예를들어, 트리가 다음과 같이 주어진다고 하자.
	
	              5
	            /   \
	           2     13
	
	그러면 노드(2)보다 큰 숫자는 5,13이므로, 모두 합치면 20가 되고,
	
	노드(5)보다 큰 숫자는 13이므로, 합치면 18이되고,
	
	노드(13)보다 큰 숫자는 없으므로, 그대로 13이니까, 다음과 같으 트리를 반환하면 된다.
	
		          18
	            /   \
	          20     13
 */

package Tree;

public class ConvertBSTtoGreaterTree538 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	
	//<문제풀이1>
	
	//bst이기 때문에, 왼쪽에서 오른쪽으로 갈수록 숫자가 점점 커짐.
	
	//따라서 맨 오른쪽 밑에 노드부터 한칸씩 왼쪽으로 가면서 값을 누적으로 더해주면 됨.
	
	//맨 오른쪽부터 한칸씩 왼쪽으로 가는 방법은, in-order-traversal 방식을 사용하되, 
	
	//원래는 left돌고 right 돌았다면, 순서를 바꿔서
	
	//right먼저 돌고 left도는 식으로 하면 됨. 아래 링크 참조.
	
	//https://doohwancho.tistory.com/534?category=1042623 
	
	//요것도 좀 간지남. 성능도 좋음.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert BST to Greater Tree.
	//Memory Usage: 38.6 MB, less than 90.63% of Java online submissions for Convert BST to Greater Tree.

	int add = 0;

	public TreeNode convertBST(TreeNode root) {
		if (root == null) return null;
			
		convertBST(root.right);
		
		int tmp = root.val;
		root.val += add;
		add += tmp;
		
		convertBST(root.left);
		return root;
	}
}
