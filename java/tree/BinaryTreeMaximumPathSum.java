package thirtyDayChallenge;

public class BinaryTreeMaximumPathSum {
	
	/*
	//<Trial01>
	
	//음.. 아래에서 위로 가야되나
	
    int rst = Integer.MIN_VALUE;
    
    private int mps(TreeNode root){
        if(root == null) return 0;
        int l = mps(root.left);
        int r = mps(root.right);
        rst = Math.max(rst, Math.max(root.val, root.val+l+r));
        return root.val + l + r;
    }
    
    public int maxPathSum(TreeNode root) {
        mps(root);
        return rst;
    }
    */
	
	//<문제풀이1 by jeantimex>

	//이 로직의 단점:모든 노드의 값이 마이너스면 에러난다.
	
	//93 / 93 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 41.6 MB

	
    
    int rst = Integer.MIN_VALUE;
    
    private int mps(TreeNode root){
        if(root == null) return 0;
        int l = Math.max(0, mps(root.left)); //마이너스 값이 나온다면, 어짜피 더해도 무의미 하니까 0으로 바꿔줌.  ? -> 그럼 parent node가 2이고 parent node.left가 -1이면 어떻게 하나? node.left가 Math.max(0, node.left)로 0이되니, 2+0+0(null)하여 2가됨.
        int r = Math.max(0, mps(root.right)); 
        rst = Math.max(rst, root.val+l+r);   //children에서 나온걸 더한게 제일 크다면, 업데이트 시켜주는 것.
        return root.val+Math.max(l, r); //밑에서 위로 값을 토스할때 left child랑 right child중 더 큰걸 선택해서 전달해줌
    }
    
    public int maxPathSum(TreeNode root) {
        mps(root);
        return rst;
    }
    
}
