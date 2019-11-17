package DepthFirstSearch;

public class SameTree100 {

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
	
	//p = [], q = []
	
	//일때 막힘
	
	static boolean flag = true;

	private void dfs(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return;

		if (p.left != null && q.left != null)
			dfs(p.left, q.left);
		else if (p.left == null && q.left != null || p.left != null && q.left == null)
			flag = false;

		if (p.val != q.val)
			flag = false;

		if (p.right != null && q.right != null)
			dfs(p.right, q.right);
		else if (p.right == null && q.right != null || p.right != null && q.right == null)
			flag = false;
	}
	*/
	
	//<문제풀이1>
	
	//dfs방식으로 tree순회를 하되, p와 q 동시에 같은 위치를 순회하면서 값을 비교.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Same Tree.
	//Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Same Tree.
	
    boolean flag = true;
    
    private void dfs(TreeNode p, TreeNode q){        
        if(p == null && q == null) return;
        else if((p != null && q == null) || (p == null && q != null)){ //p, q 한쪽만 자식있으면 false
            flag = false;
            return;
        }
        else{
            if(p.left != null || q.right != null) dfs(p.left, q.left); 
            if(p.val != q.val) flag = false;
            if(p.right != null || q.right != null) dfs(p.right, q.right);
        }
    }
	
    //<문제풀이2 by buoy08>
    
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Same Tree.
    //Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Same Tree.
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null)  
            return p == q;             //한쪽만 null인경우 false반환
        if (p.val != q.val) 
            return false;
       return isSameTree(p.left, q.left) && isSameTree (p.right, q.right);
    }
	
}
