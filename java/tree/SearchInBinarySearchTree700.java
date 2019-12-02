/*
	Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the node's value equals the given value. Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.
	
	For example, 
	
	Given the tree:
	        4
	       / \
	      2   7
	     / \
	    1   3
	
	And the value to search: 2
	You should return this subtree:
	
	      2     
	     / \   
	    1   3
	In the example above, if we want to search the value 5, since there is no node with value 5, we should return NULL.
	
	Note that an empty tree is represented by NULL, therefore you would see the expected output (serialized tree format) as [], not null.
 */

package Tree;

public class SearchInBinarySearchTree700 {

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
	
	//null을 반환하지 않은 경우(root의 값이 문제에서 준 val과 같은 때)에만 해당 노드를 저장했다가 반환하는 법.
	
	//Runtime: 1 ms, faster than 8.33% of Java online submissions for Search in a Binary Search Tree.
	//Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Search in a Binary Search Tree.
    
	public TreeNode searchBST(TreeNode root, int val) {
        TreeNode node = null;
        if(root == null){
            return node;    
        } 
        if(root.val == val) return root;
        
        if(root.left != null){
            TreeNode child = searchBST(root.left, val);
            if(child != null) node = child;
        }
        if(root.right != null){
            TreeNode child = searchBST(root.right, val);
            if(child != null) node = child;
        } 
        return node;
    }
    */
	
	//<문제풀이2>
	
	//bst tree의 특수성(가장 작은 숫자 맨 왼쪽, 오른쪽으로 갈수록 점점커짐)을 이용하여 품.
	
	//root.val이 val보다 크다는 말은, root.left에 내가 찾는 val이 있다는 뜻이므로, root.left만 보면 됨.
	
	//root.val이 val보다 작다는 말은, root.right에 내가 찾는 val이 있다는 뜻이므로, root.right만 보면 됨.

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode node = null;
        if(root == null) return node;
        
        if(root.val == val) return root;
        
        else if(root.val > val){
            TreeNode child = searchBST(root.left, val);
            return child != null ? child : node;
        } 
        else if(root.val < val){
            TreeNode child = searchBST(root.right, val);
            return child != null ? child : node;
        } 
        return node;
    }
}


