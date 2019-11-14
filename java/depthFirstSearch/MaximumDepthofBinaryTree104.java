package DepthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthofBinaryTree104 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	/*
	//<Trial01>
	
	//가장 위쪽 노드부터 차례대로 한 노드씩 밑으로 내려오는 pre order traversal방식을 써서
	
	//한칸 내려갈때마다 depth +1을 해주고, Math.max()로 가장 깊게 내려간 depth를 구하려고 했는데 왜인지 모르겠지만 예상값이 안나옴.

	private int dfs(TreeNode root, int depth) {
		if (root == null)
			return 0;
		depth++;
		depth = Math.max(depth, dfs(root.left, depth));
		depth = Math.max(depth, dfs(root.right, depth));
		return depth;
	}

	public int maxDepth(TreeNode root) {
		int depth = 0;
		depth = dfs(root, depth);
		return depth;
	}
	*/
	/*
	 
	//<문제풀이1 by yfcheng>
	  
	//DFS(pre-order)
	 
	//한층 한층 내려갈 때마다 각 노드와 해당 노드의 depth를 stack에 병렬방식으로 넣어놓는 방법.
	  
	//Runtime: 3 ms, faster than 11.33% of Java online submissions for Maximum Depth of Binary Tree.
	//Memory Usage: 39.2 MB, less than 93.55% of Java online submissions for Maximum Depth of Binary Tree.
	
	public int maxDepth(TreeNode root) {
	    if(root == null) {
	        return 0;
	    }
	    
	    Stack<TreeNode> stack = new Stack<>();
	    Stack<Integer> value = new Stack<>();
	    stack.push(root);
	    value.push(1);
	    int max = 0;
	    while(!stack.isEmpty()) {
	        TreeNode node = stack.pop();
	        int temp = value.pop();
	        max = Math.max(temp, max);
	        if(node.right != null) {
	            stack.push(node.right);
	            value.push(temp+1);
	        }
	        if(node.left != null) {
	            stack.push(node.left);
	            value.push(temp+1);
	        }
	    }
	    return max;
	}
	*/
	
	//<문제풀이2 by yfcheng>
	
	//BFS
	
	//Runtime: 1 ms, faster than 11.33% of Java online submissions for Maximum Depth of Binary Tree.
	//Memory Usage: 39.1 MB, less than 94.62% of Java online submissions for Maximum Depth of Binary Tree.
	
	public int maxDepth(TreeNode root) {
	    if(root == null) {
	        return 0;
	    }
	    Queue<TreeNode> queue = new LinkedList<>();
	    queue.offer(root);
	    int count = 0;
	    while(!queue.isEmpty()) {
	        int size = queue.size(); 
	        while(size-- > 0) {              //같은 층에있는 노드들을 모두 뽑기 위해 사용
	            TreeNode node = queue.poll();
	            if(node.left != null) {
	                queue.offer(node.left);
	            }
	            if(node.right != null) {
	                queue.offer(node.right);
	            }
	        }
	        count++;
	    }
	    return count;
	}
	
}
