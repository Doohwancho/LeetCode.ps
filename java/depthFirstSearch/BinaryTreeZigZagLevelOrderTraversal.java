package julyChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigZagLevelOrderTraversal {

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

	// <Trial01>

	// 일단 level order traversal까진 했는데,

	// 이걸 짝수번째 오면 순서를 까뒤집어야 되자너?

	// 근데 queue에 넣을때부터 까뒤집어서 넣으면,
	
	//Runtime: 2 ms
	//Memory Usage: 39.8 MB

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        int flag = 1;
        
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> tmp = new LinkedList<>();
            
            while(size > 0){
                TreeNode node = q.poll();
                
                if(flag == 1) tmp.add(node.val);
                else tmp.add(0, node.val);
                
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
                
                size--;
            }
            list.add(tmp);
            flag ^= 1;
        }
        
        return list;
    }
    
    
    //<문제풀이2 by caikehe>
    
    //dfs로도 가능하구나
    
    //Runtime: 1 ms
    //Memory Usage: 39.6 MB
    
    private void dfs(List<List<Integer>> list, TreeNode node, int i){
        if(node == null) return;
        
        if(i == list.size()){
            list.add(new LinkedList<>());
        }
        if(i % 2 == 1){
            list.get(i).add(0, node.val);    //홀수번째만 뒤집어서 담아
        } else {
            list.get(i).add(node.val);
        }
        
        dfs(list, node.left, i+1);
        dfs(list, node.right, i+1);
    }
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(list, root, 0);
        return list;
    }
}
