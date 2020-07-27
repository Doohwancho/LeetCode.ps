package julyChallenge;

public class ConstructBinaryTreeFromInorderPostOrderTraversal {

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
	 
	 
	//<문제풀이1 by jinwu>
	
	//어씨 모르겠네;;
	  
	//inorder = [9,3,15,20,7]
	//postorder = [9,15,7,20,3]
	
	//    3
	//   / \
	//  9  20
	//    /  \
	//   15   7
	  
	// post order을 뒤집에서 pre order로 바꿈
	
	//1) root.right방향으로 postorder에있는 숫자를 역순으로 넣음. inEnd > inStart이 될때까지. 
	//   inEnd > inStart은 inStart는 inorder.length-1이고, inEnd는 매 iterate마다 for문에서 inorder[i] == postorder[postStart]할때마다 계속커짐.
	//   근데 왜?
	  
	//2) 다시 root로 올라오면서 left child를 마저 채워줌.
	
	//Runtime: 0 ms
	//Memory Usage: 39.5 MB
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, inorder.length-1, 0, postorder, postorder.length-1);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart){
        
        if(postStart < 0 || inEnd > inStart) return null;
        
        TreeNode root = new TreeNode(postorder[postStart]);
        
        int idx = 0;
        for(int i = inStart; i >= inEnd; i--){
            if(inorder[i] == postorder[postStart]){ //inorder은 root기준으로 왼쪽 오른쪽 갈리니까, root를 idx로 두고
                idx = i;
                break;
            }
        }
        
        root.right = buildTree(inorder, inStart, idx+1, postorder, postStart-1); //오른쪽은 postorder역순으로 넣으면 되네
        root.left = buildTree(inorder, idx-1, inEnd, postorder, postStart-1-(inStart - idx));  //idx-1이 중요. inorder에서 root에 왼쪽애까지라는 뜻.
        return root;
    }
}
