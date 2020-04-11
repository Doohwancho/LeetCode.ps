package thirtyDayChallenge;

public class DiameterOfBinaryTree {
	
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
	
	//root를 지나가면서 longest path를 찾는건 맞는데
	
	//root 안지나가면서 logest path찾는 경우 에러
	
	private int longestPath(TreeNode root, int level) {
		if (root == null)
			return level;
		int curLevel = level;
		if (root.left != null)
			level = Math.max(level, longestPath(root.left, curLevel + 1));
		if (root.right != null)
			level = Math.max(level, longestPath(root.right, curLevel + 1));

		return level;
	}

	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null)
			return 0;
		int rst = 0;
		if (root.left != null)
			rst += longestPath(root.left, 1);
		if (root.right != null)
			rst += longestPath(root.right, 1);
		return rst;
	}
	*/
	
	
	/*
	//<문제풀이1>

	//106 / 106 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 39.3 MB
	
	int rst = 0;
    
    private int longestPath(TreeNode root, int level){
        if(root == null) return level;
        int curLevel = level; //level을 그냥 파라미터로 안넘기고 curLevel쓴 이유는, left후에 right가서 left가 right에 영향을 미치기 때문
        int left = 0;
        int right = 0;
        if(root.left != null) left = longestPath(root.left, curLevel);
        if(root.right != null) right = longestPath(root.right, curLevel);
        rst = Math.max(rst, left+right);               //매 node마다 left+right의 최대치를 .max()로 업데이트
        return Math.max(left,right)+1;                 //이 node의 left층 혹은 right층중 더 큰 층에 이 노드까지+1해서 반환
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
        longestPath(root, 0);
        return rst;
    }
    */
	
	
	//<문제풀이2 by shawngao>
	
	//같은 로직, 더 깔끔한 버전
	
	//처음엔 파라미터에 +1을 쓰는 방향으로 갔는데, 그러는것 보다
	
	//맨 밑에서부터 재귀로 +1씩 해서 올라가면서
	
	//left,right의 max를 업데이트 시켜주는게 더 좋기 때문에
	
	//파라미터에 level을 없애버림
	
	//문제풀이 1에서 return level말고 return 0해도 accepted됨
	
	//파라미터 필요없단 얘기지
	
	int rst = 0;
    
    private int longestPath(TreeNode root){
        if(root == null) return 0;
        int left = longestPath(root.left);
        int right = longestPath(root.right);
        rst = Math.max(rst, left+right);               
        return Math.max(left,right)+1;                 
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
        longestPath(root);
        return rst;
    }
	
    
}
