package DepthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

public class BalancedBinaryTree110 {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	/*
	//<Trial01>

	//pre-order이라 왼쪽 tree부터 감지해서, 만약 왼쪽에 주렁주렁인데, 오른쪽에 하나 띡하고 나와있으면, 오른쪽 child가기도 전에
	
	//minDepth이 이미 왼쪽 tree에 child-less한 node의 값에 설정되어 버려서 안됨..흑흑

	private static boolean dfs(TreeNode root) {
		boolean flag = true;
		int minDepth = 1;
		int maxDepth = 1;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int cnt = queue.size();
			while (cnt-- > 0) {
				TreeNode node = queue.poll();
				if (flag && node.left == null && node.right == null) {
					minDepth = maxDepth;
					flag = false;
				}
				if (node.left != null || node.right != null) {
					maxDepth++;
					if (node.left != null)
						queue.add(node.left);
					if (node.right != null)
						queue.add(node.right);
				}
			}
		}
		return maxDepth - minDepth > 1 ? false : true;
	}

	public boolean isBalanced(TreeNode root) {
		return dfs(root);
	}
	*/
	
	/*
	
	//<Level Order Traversal>
	
    private static int height(TreeNode root) { 
        if (root == null) return 0;
        else { 
            // compute  height of each subtree 
            int lheight = height(root.left); 
            int rheight = height(root.right); 

            // use the larger one 
            if(lheight > rheight) return lheight+1; 
            else return rheight+1;  
        } 
     }
    
    // Print nodes at the given level 
    private static void printGivenLevel(TreeNode root ,int level) { 
        if(root == null) return;
             
        if(level == 1) {
        	System.out.print("root.val: " + root.val);
        	System.out.println(); 
        }
        else if(level > 1){ 
            printGivenLevel(root.left, level-1); 
            printGivenLevel(root.right, level-1); 
        } 
    }
    
    public static boolean printLevelOrder(TreeNode root) {
        int h = height(root);
        System.out.println(h);
        
        for(int i=1; i<=h; i++){ 
        	System.out.println("level: "+i);
        	printGivenLevel(root, i);
        	System.out.println();
        }
        return true;
    }
    
    public static void main(String[] args) {
    	TreeNode tree = new TreeNode(3); 
        tree.left= new TreeNode(9); 
        tree.right= new TreeNode(20); 
        tree.right.left= new TreeNode(15); 
        tree.right.right= new TreeNode(7);
        
        System.out.println("Level order traversal of binary tree is "); 
        System.out.println(printLevelOrder(tree)); 
	}
    */
	

	//<문제풀이 by bdwalker>
	
	//매 dfs마다 depth를 파라미터로 넣어서 +1해준다.
	
	//해당 노드의 depth는 Math.max(leftTree, rightTree);로 매번 갱신해준다.
	
	//그러다가 leftTree와 rightTree의 depth차이가 1을 초과하면(밸런스가 깨질 때) -1을 리턴한다.
	
	//main()문에선 밸런스가 깨졌는지 return helper(root, 0) >= 0;을 통해 보기만 하면 된다.
	
	//Runtime: 1 ms, faster than 99.76% of Java online submissions for Balanced Binary Tree.
	//Memory Usage: 39.5 MB, less than 73.15% of Java online submissions for Balanced Binary Tree.	

	private int helper(TreeNode root, int height) {
	    if (root == null) return height;
	    int leftTree = helper(root.left, height + 1);
	    int rightTree = helper(root.right, height + 1);
	    if (leftTree < 0 || rightTree < 0 || Math.abs(leftTree - rightTree) > 1) {
	        return -1;
	    }
	    return Math.max(leftTree, rightTree);
	}

	public boolean isBalanced(TreeNode root) {
	    return helper(root, 0) >= 0;
	}
	

}
