package juneChallenge;

public class CountCompleteTreeNodes {

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
	 
	/*
	//<문제풀이1>
	
	//그냥 모든 노드 돌면서 +1씩 해주면 되지. 밑에서 위로.
	
	//아니면 그냥 pre/in/post order traversal중 암거나 쓰면서 매 node들를때마다 +1씩 해주면 되지
	
	//아 근데 문제에서 tree가 complete binary tree라네
	
	//이런 개노잼 방법으로 푸는것보다 더 신박한 방법이 있을텐데
	
	//Runtime: 0 ms
	//Memory Usage: 42 MB
	
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    */ 
    
    
    //<문제풀이2 by StefanPochmann>
    
    //complete binary tree가 맨 왼쪽부터 채워진다는걸 이용해서 height()함수로 총 몇증인지 구함.
    
    //h < 0 ? 0로 root==null이면 0을 반환해주고 그게 아니라면
    
    //height(root.right) == h-1 ? (1 << h) + countNodes(root.right) 을 함
    
    //여기서 height(root.right)을 했을 때, == h-1이라는건, left child랑 right child랑 둘이 같은 높이의 층이라는거임.
    
    //root.right할때 한칸 내려갔으니깐.
    
    //같은층이라면, 오른쪽을 돌 수 있다는 말은 왼쪽이 다 채워졌다는 말이니까, 왼쪽에 있는애들을 다 더해. (1<<h)로. 
	  
	//예를들어, 
	  
//	     1
//	    / \
//	   2   3
//	  / \  /
//	 4  5 6
	  
	
	//1에서 3으로 height(root.right)내려갈 때,
	  
	//height가 2니까, 1<<2를 더해서 4를 더해. 그 4가 왼쪽에 있는 (1,2,4,5)여.
	  
	//그럼 node(3)에서 똑같은은 짓을 반복해. 서로 층이 안맞을때까지. 
	  
//	   3  
//	  / \ 
//	 6   
    
	//에서 왼쪽 오른쪽 층수가 안맞으니까 
	  
	//층이 안맞을 때가 오면, : (1 << h-1) + countNodes(root.left);를 하는겨.
  
	//(1<<h-1)로 일단 1을 더하고(4+1해서 총합5) root.left로 감
	  
	//그럼 height가 0이 나오고, height(root.right) == h-1 이 되니까 (1 << h) + countNodes(root.right)을 해서 또 1을 더해줌
	  
	//그래서 4+1+1해서 6이라는 말인데..
	  
	//어씨 복잡 허다잉
	  
	//모든 노드 다 안돌아서 최적화가 잘되긴 헜네
	  
    //Runtime: 0 ms
    //Memory Usage: 41.6 MB 
    
    
    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
    public int countNodes(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
               height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
                                         : (1 << h-1) + countNodes(root.left);
    }
    
}
