/*
	Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
	
	According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
	
	Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
	
	
	 
	
	Example 1:
	
	Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
	Output: 6
	Explanation: The LCA of nodes 2 and 8 is 6.
	Example 2:
	
	Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
	Output: 2
	Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
	
	
	
	
	<문제>
	
	binary search tree, p,q가 주어졌을 때, p,q를 자식으로하는 부모 중, p,q에 가장 가까운 부모를 구하라.
	
 */
package Tree;

public class LowestCommonAncestorOfBST235 {

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
	
	//될것같은데..
	
	TreeNode rst = null;
    boolean flag = true;
    
    private int dfs(TreeNode root, TreeNode p, TreeNode q, int sum){
        if(root == null) return -1;
        int left = dfs(root.left, p, q, sum);
        int right = dfs(root.right, p, q, sum);

        if(root.val == p.val || root.val == q.val){            
            if(flag && (sum - left == root.val || sum - right == root.val)){
                flag = false;
                rst = root;     
            } 
            return root.val == p.val ? p.val : q.val;    
        } 
        
        if(flag && left+right == sum){
            flag = false;
            rst = root;    
        }    
        if(left >= 0) return left;
        else if(right >= 0) return right;
        else return -1;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return rst;
        dfs(root, p, q, p.val+q.val);
        return rst;
    }
    */
	
	//<문제풀이 by xcv58>
	
	//p, q중에 p가 더 작은데, root.val이 p보다 더 작으면, 왼쪽은 볼 필요가 없으므로, 더 수가 큰 root.right child를 돔.
	
	//(p.val-root.val)*(q.val-root.val)<=0 는 뭐냐면,
	
	//트리가 다음과 같이 있다고 가정.
	
	//  6
	
	//2   8
	
	//p = 2, q = 8, root = 6일 때, binary search tree이므로,
	
	//우리가 찾는 root는 p.val<= root.val <= q.val 일수밖에 없음.
	
	//case1) root == 6 -> ((p.val-root.val)*(q.val-root.val)<=0) 대입하면,
	
	//-4 * 2 == -8
	
	//case2) root == 2 
	
	//0 * 6 == 0
	
	//case3) root == 1 (범위를 벗어날 떄)
	
	//1(양수) * 7(양수) == 7(무조건 양수가 되어 0 혹은 음수가 될 수 없음 고로 X)
	
	//case4) root == 9 (범위를 벗어날 때)
	
	//-7(음수) * -1(음수) == 7(무조건 양수가 되어 0 혹은 음수가 될 수 없음 고로 X)
	
	//크... 이거지
	
	//Runtime: 4 ms, faster than 100.00% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
	//Memory Usage: 37.6 MB, less than 5.10% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
	
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return ((p.val-root.val)*(q.val-root.val)<=0) ? root : lowestCommonAncestor(p.val>root.val?root.right:root.left, p, q);
    }
}
