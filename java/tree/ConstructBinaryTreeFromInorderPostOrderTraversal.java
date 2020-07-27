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
	  
	//inorder = [9,3,15,20,7]
	//postorder = [9,15,7,20,3]
	
	//    3
	//   / \
	//  9  20
	//    /  \
	//   15   7
	
	//1) root.right방향으로 postorder에있는 숫자를 역순으로 넣음. 왜냐면 postorder의 역순이 pre-order이자너.
	  
	//   언제까지 넣냐면 inEnd > inStart이 될때까지. 
	//   왜냐면 inorder이 이 문제에서 쓰이는 목적이, inorder는 돌때 순서가 left-root-right순으로 돌기 때문에, inorder에서 마지막을 inStart가 넘어가 버리면,
	//   더이상 오른쪽에 넣을 애가 없다는 말이니까, 이때부터 왼쪽 child를 채우면 됨.  
	  
	//2) 다시 root로 올라오면서 left child를 마저 채워줌. 채울때 
	//   root.left = buildTree(inorder, idx-1, inEnd, postorder, postStart-1-(inStart - idx)); 을 쓰자너?
	//   여기서 inEnd는 inorder.length()고, inStart가 inorder.length()-1이었는데 얘를 점점 낮추면서 범위를 늘려감.
	//   낮추는건 idx-1로. 왜냐면 for문돌려서   if(inorder[i] == postorder[postStart]) idx = i
	//   하면 idx는 inorder에서 root가 되고, 왼쪽에 더할 child는 inorder에서 idx기준 왼쪽에 있어야 되니까(inorder는 left-root-right순)

	//   그리고 postStart-1-(inStart - idx)이건.... 모르겠네 슈바
	
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
