/*
	Share
	Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
	
	The binary search tree is guaranteed to have unique values.
	
	 
	
	Example 1:
	
	Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
	Output: 32
	Example 2:
	
	Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
	Output: 23
	

	
	<문제>
	
	트리가 다음과 같이 주어지고, L, R숫자도 주어진다.
	
	Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
	
	
	    10
	  5   15
	3  7    18
	
	그럼 트리안에 모든 숫자중에서 L보다 같거나 큰데 R보다 같거나 작은 수를 모두 더해 반환하라.	
	
	7+5+10+15 = 37가 아니라,
	
	7+10+15다.
	
	노드7에서 노드15사이의 연결된 노드들의 값들의 총 합을 구하는게 아니라, 단순히 모든 노드의 값들중에서 L과 R사이의 숫자(L,R포함)을 모두 더해 반환해주면 된다.
	

 */

package Tree;

public class RangeSumOfBST938 {
	
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
	
	//recursive
	
	//반환할 변수rst를 전역변수로 빼서, 매 노드를 돌면서(pre/in/post - order traversal) 노드의 값이 L과 R사이 범위에 포함되면 rst에 더해주는 방법.
	
	//Runtime: 1 ms, faster than 52.17% of Java online submissions for Range Sum of BST.
	//Memory Usage: 44.9 MB, less than 98.84% of Java online submissions for Range Sum of BST.

	int rst = 0;

	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null)
			return 0;
		if (root.val >= L && root.val <= R)
			rst += root.val;
		rangeSumBST(root.left, L, R);
		rangeSumBST(root.right, L, R);
		return rst;
	}
	*/
	
	//<문제풀이2>
	
	//bst tree라는 이용한 답.
	
	//bst tree는 잘 보면 가장 작은 숫자가 왼쪽 밑 끝에있고, 오른쪽으로 갈수록 점점커짐.
	
	//	    10
	//	  5   15
	//	3  7    18
	
	//그러니까, L이 6이라고 하고, 트리를 돌다가 node 5에 왔다고 하면, 5보다 더 작은 노드(left child)는 들를 필요도 없기 때문에, right child만 들리고,
	
	//R이 14인데, node 15에 왔다고 하면, 어짜피 15보다 큰 숫자가 들어간 node 15의 right child를 갈 필요가 없기 때문에, node 15보다 작은 node 15의 left child만 가게끔 설정 한 것.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Range Sum of BST.
	//Memory Usage: 45.8 MB, less than 97.67% of Java online submissions for Range Sum of BST.
	
	public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(root.val >= L && root.val <= R){
            return rangeSumBST(root.left, L, R) + root.val + rangeSumBST(root.right, L, R);
        }
        else if(root.val < L){
            return rangeSumBST(root.right, L, R);
        }
        else{
            return rangeSumBST(root.left, L, R);
        }
    }
}
