package DepthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDepthOfBinaryTree111 {
	
	  public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
	  
	  
	  //<문제풀이1>
	  
	  //Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Depth of Binary Tree.
	  //Memory Usage: 39.3 MB, less than 98.44% of Java online submissions for Minimum Depth of Binary Tree.
	 
	  public int minDepth(TreeNode root) {
	        if(root == null) return 0; //유효성검사
	        
	        Queue<TreeNode> queue = new LinkedList<>(); //stack말고 queue로 선언 이유는, 자식 노드를 뒤에넣고, 부모노드를 앞에서 빼는 FIFO이기 때문
	        queue.add(root);
	        int depth = 1; 
	        
	        while(!queue.isEmpty()){
	            int size = queue.size(); //해당 level(depth)에 들어가있는 노드들만 적용

	            while(size-- > 0){
	                TreeNode currNode = queue.poll(); //자식노드말고 부모노드만 앞에서 하나씩 빼서
	                if(currNode.left != null || currNode.right != null){     //해당 부모노드가 자식노드(left child, right child)있으면 queue에 더해주고
	                    if(currNode.left != null) queue.add(currNode.left);
	                    if(currNode.right != null) queue.add(currNode.right);
	                }
	                else{
	                    return depth; //자식이 없는 노드면, 해당 레벨이 문제에서 요구하는 최소 depth이므로 반환.
	                }
	            }
	            depth++; //만약 해당 level(depth)에 있는 모든 노드들이, 최소 한개 이상의 left or right child 가지고 있었다면, 다음 층으로 내려가니까 depth+1
	        }
	        return depth;
	    }
	  
	  /*
	  //<문제풀이2 by earlme>
	  
	  //recursive. 어렵다.
	  
	  //걍 bfs가 훨 나음. root.left의 children이 500개고 root.right의 children이 1개면, dfs는 왼쪽부터 돌기때문에 다돔.
	  
	  //근데 bfs는 층별로 보기때문에 훨씬 더 효율적.
	  
	  //Logic here is that if you find a node with either of right or left is absent, return the maximum of left/right because one of them is 0 anyway.
	  
	    public int minDepth(TreeNode root) {
	        if(root == null) return 0;
	        if(root.left == null || root.right == null) 
	        	return 1 + Math.max(minDepth(root.left), minDepth(root.right));
	        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
	    }
	*/
}

