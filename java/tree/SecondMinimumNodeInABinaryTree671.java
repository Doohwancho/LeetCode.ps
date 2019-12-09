/*
	Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.

	Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
	
	If no such second minimum value exists, output -1 instead.
	
	Example 1:
	
	Input: 
	    2
	   / \
	  2   5
	     / \
	    5   7
	
	Output: 5
	Explanation: The smallest value is 2, the second smallest value is 5.
	 
	
	Example 2:
	
	Input: 
	    2
	   / \
	  2   2
	
	Output: -1
	Explanation: The smallest value is 2, but there isn't any second smallest value.
	
	
	
	
	
	
	<문제>
	
	트리가 다음과 같이 주어진다.
	
	    2
	   / \
	  2   5
	     / \
	    5   7
	    
	이 트리의 특징은 두가지 인데, 
	1. binary search tree이다.
	2. root.val = min(root.left.val, root.right.val) 이다.
	3. 모든 노드는 left child, right child 둘 다 가지고 있거나, 둘 다 없다.
	
	이 때, 이 트리에서 두번째로 작은 값을 반환하라.
	
	만약 [2,2,2]같이 두번째로 작은 값이 없다면, -1을 반환하라.
 */
package Tree;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SecondMinimumNodeInABinaryTree671 {
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
	
	//root가 tree에서 가장 작은 값이니까, 단순무식하게 dfs로 다 돌면서 root보다 큰 숫자중 가장 작은 숫자를 Math.min()으로 뽑음.
	
	//rst의 형을 double로 선언한 이유는 문제에서 구질구질하게 다음과 같은 input주었기 때문
	
	//Input: [2,2,2147483647]
	//Output: -1
	//Expected: 2147483647
	
	//성능은 좋은데 bst라는 점을 이용해서 더 성능이 좋게 짤 수도 있을 것 같음.
	 
	//dfs는 모든 노드를 돈다는 점에서 tree의 사이즈가 겁나 커질수록 성능이 떨어짐.
	
	//질문자의 의도를 벗어나 푼 기분. 찝찝..
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Second Minimum Node In a Binary Tree.
	//Memory Usage: 34 MB, less than 100.00% of Java online submissions for Second Minimum Node In a Binary Tree.

	double rst = Double.MAX_VALUE;

	private void dfs(TreeNode root, int minimum) {
		if (root == null) return;
		if (root.val > minimum) rst = Math.min(rst, root.val); 
		dfs(root.left, minimum);
		dfs(root.right, minimum);
	}

	public int findSecondMinimumValue(TreeNode root) {
		if (root == null) return -1;
		int minimum = root.val;
		dfs(root, minimum);
		return rst == Double.MAX_VALUE ? -1 : (int) rst;
	}
	*/
	
	/*
	//<문제풀이2 by anna_boltenko>
	
	//treeset에 값을 넣으면, 가장 작은 값부터 차례차례 쌓인다는 점을 이용한 풀이법. treeset의 원리는 다음 링크 참조.
	
	//https://dlsdn73.tistory.com/399
	
	//Runtime: 1 ms, faster than 19.61% of Java online submissions for Second Minimum Node In a Binary Tree.
	//Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Second Minimum Node In a Binary Tree.
	
	public int findSecondMinimumValue(TreeNode root) {
        if ((root == null) || (root.left == null && root.right == null)) 
            return -1;
        
        Set<Integer> set = new TreeSet<>();
        dfs(root, set);
        Iterator<Integer> it = set.iterator();
        it.next(); 	//.next()함수를 통해 최솟값(root.val)보다 한칸 더 큰값으로 넘김
        return (it.hasNext()) ? it.next() : -1; 
    }
    private void dfs(TreeNode root, Set<Integer> set) {
        if (root == null) return;
        set.add(root.val);
        dfs(root.left, set);
        dfs(root.right, set);
    }
    */
	//<문제풀이3 by diddit>
	
	//출제자의 의도를 잘 파악하여 푼 케이스.
	
	//root.val과 root.children(left or right).val이 다를때만 변수 l,r에 값을 넣고, null이면 -1을 넣는다.
	
	//만약 l, r 둘 중 하나라도 null값인 -1이 포함되어 있고, 다른 한 값이 살아있다면, 그 값을 살려야 하므로 Math.max(l,r)로 살리고
	
	//만약 모든 노드가 children(both left and right)가 있어서 null값인 -1이 없는 상태라면, l,r중 더 작은 애를 골라야 하기 때문에 Math.min()으로 추린다.
	
	//생각해보면 이 방식도 모든 노드를 돈다는 점에서 풀이1과 성능상 큰 차이는 없겠다. 다만 dfs->recursive라 코드량도 줄고 간지날 뿐.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Second Minimum Node In a Binary Tree.
	//Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Second Minimum Node In a Binary Tree.
	
	public int findSecondMinimumValue(TreeNode root) {
        if(root == null) return -1;
        
        int l = (root.left != null && root.left.val != root.val) ? root.left.val : findSecondMinimumValue(root.left); 
        int r = (root.right != null && root.right.val != root.val) ? root.right.val : findSecondMinimumValue(root.right);
        
        if(l == -1 || r == -1) return Math.max(l, r);
        return Math.min(l, r);
    }	
}
