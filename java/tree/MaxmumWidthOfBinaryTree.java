package julyChallenge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MaxmumWidthOfBinaryTree {
	
	  public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode() {}
	      TreeNode(int val) { this.val = val; }
	      TreeNode(int val, TreeNode left, TreeNode right) {
	          this.val = val;
	          this.left = left;
	          this.right = right;
	      }
	  }
	 
	//<Trial01>
	
	//문제이해를 잘못했네
	
	//    1
	//   /  
	//  3    
	// / \       
	//5   3  
	
	//얘처럼 오른쪽이 아예 싹 비워져 있으면 5,3 둘만 처리하는데 만약에
	
	//    1
	//   / \ 
	//  3   2 
	// / \   \    
	//5   3   4
	
	//얘처럼 한놈이랑도 살아있으면 다른한놈도 산것처럼 치란 말이지? 5+3+null+4해서 4
	
    public int widthOfBinaryTree(TreeNode root) {
        int rst = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            rst = Math.max(rst, size);
            while(size > 0){
                TreeNode node = q.poll();
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
                size--;
            }
        }
        return rst;
    }
    
    
    //<Trial02>
    
//	    1
//	   / \
//	  3   2
//	 /     \  
//	5       9 
//	/         \
// 6           7
//   Output: 8
    
    //아 또 문제 잘못 이해했네
    
    //아 이경우에 4를 뽑아버리네?
    
    //아 걍 가운데에 null이 몇개가 있던 맨 오른쪽이랑 왼쪽의 거리차만 구하면 되는거구나
    
    public int widthOfBinaryTree(TreeNode root) {
        int rst = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            rst = Math.max(rst, size);
            while(size > 0){
                TreeNode node = q.poll();
                if(node == null){
                    size--;
                    continue;
                }
                boolean flag = !(node.left == null && node.left == node.right);
                
                if(flag){
                    q.add(node.left);
                    q.add(node.right);
                } else {
                    if(node.left != null) q.add(node.left);
                    if(node.right != null) q.add(node.right);       
                }

                size--;
            }
        }
        return rst;
    }
    
    //<Trial03 - time limit exceeded>
    
    public int widthOfBinaryTree(TreeNode root) {
        int rst = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            int size = q.size();
            
            int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE, cnt = 0;
            
            while(size > 0){
                TreeNode node = q.poll();
                if(node == null){
                    q.add(null);
                    q.add(null);
                    size--;
                    continue;
                }
                
                cnt++;
                l = Math.min(l, size);
                r = Math.max(r, size);
                
                q.add(node.left);
                q.add(node.right);
                size--;
            }
            
            if(cnt > 0){
                rst = Math.max(rst, r-l+1);   
            } else {
                break;
            }
        }
        return rst;
    }
    
    
    //<문제풀이1 by shawngao>
    
    //Runtime: 3 ms
    //Memory Usage: 39.8 MB
    
    Map<Integer, int[]> map = new HashMap<>();
    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        findMax(root, 0, 0);
        
        int res = 1;
        for (int[] rec : map.values()) {
            res = Math.max(res, rec[1] - rec[0] + 1);
        }
        
        return res;
    }
    
    private void findMax(TreeNode root, int level, int pos) {
        if (root == null) return;
        
        int[] rec = map.get(level);
        if (rec == null) {
            rec = new int[2];
            rec[0] = Integer.MAX_VALUE;
            rec[1] = Integer.MIN_VALUE;
        }

        rec[0] = Math.min(rec[0], pos);
        rec[1] = Math.max(rec[1], pos);
        map.put(level, rec);
        
        findMax(root.left, level + 1, 2 * pos);   //이부분이 지리네, 이렇게 하면 왼쪽부터 오른쪽으로 순서대로 0,1,2,3,4,5 쭉쭉쭉 가네
        findMax(root.right, level + 1, 2 * pos + 1);
    }
}
