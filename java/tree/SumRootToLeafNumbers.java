package juneChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class SumRootToLeafNumbers {

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
	
	//dfs(depth-first-search)

	//파라미터로 String계속 넘겨주면서 +root.val 붙인 다음에
	
	//leaf에 끝에 도달하면(== left child, right child가 없을 때) rst에 s+root.val더해주면 되제
	
	//아 근데 뭔가 글로벌 변수 안쓰고 싶긴 한데

	// Runtime: 14 ms
	// Memory Usage: 40 MB

	int rst = 0;

	private void dfs(TreeNode root, String s) {
		if (root == null) return;
		if (root.left == null && root.right == null) {
			rst += Integer.parseInt(s + root.val);
		}
		dfs(root.left, s + root.val);
		dfs(root.right, s + root.val);
	}

	public int sumNumbers(TreeNode root) {
		if (root == null) return 0;
		dfs(root, "");
		return rst;
	}
	
	//<문제풀이2>
	
	//int[]를 파라미터로 넘겨주면, 주솟값을 넘겨주는거라 글로벌 변수를 안써도 되지롱~
	
	//그리고 String써서 붙이느니 그냥 파라미터로 받은거 *10하고 root.val더하면 훨씬 빠르자너~
	
	//Runtime: 0 ms
	//Memory Usage: 37.5 MB
	
    private void helper(TreeNode root, int[] rst, int n){
        if(root == null) return;
        if(root.left == null && root.right == null){
            rst[0] += n*10 + root.val;
        }
        helper(root.left, rst, n*10+root.val);
        helper(root.right, rst, n*10+root.val);
    }
    
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        int[] rst = new int[1];
        helper(root, rst, 0);
        return rst[0];
    }
	
	
	
	//<문제풀이3>
	
	//bfs(breadth-first-search)
	
	//Runtime: 10 ms
	//Memory Usage: 38.4 MB
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        int rst = 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        Queue<String> qString = new LinkedList<>();
        
        q.add(root);
        qString.add(String.valueOf(root.val));
        
        while(!q.isEmpty()){
            TreeNode r = q.poll();
            String s = qString.poll();
            
            if(r.right != null){
                q.add(r.right); 
                qString.add(s+r.right.val);
            } 
            if(r.left != null){
                q.add(r.left);     
                qString.add(s+r.left.val);
            } 
            
            if(r.left == null && r.right == null){
                rst += Integer.parseInt(s);
            }
        }
        return rst; 
    }
    
    
}
