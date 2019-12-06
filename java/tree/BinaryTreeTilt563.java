package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeTilt563 {
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
	
	//아닌가?

    int left;
    int right;
    
    public int findTilt(TreeNode root) {
        if(root == null) return 0;
        if(root.left != null){
            left += root.left.val;
            findTilt(root.left);
        }
        if(root.right != null){
            right += root.right.val;
            findTilt(root.right);
        }
        return Math.abs(right-left);
    }
    */
	
	/*
	//<Trial02>
	
	//왜아녀
	
	int left;
	int right;

	public int findTilt(TreeNode root) {
		if (root == null)
			return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				TreeNode currNode = queue.poll();
				if (currNode.left != null) {
					left += currNode.left.val;
					queue.add(currNode.left);
				}
				if (currNode.right != null) {
					right += currNode.right.val;
					queue.add(currNode.right);
				}
			}
		}
		return Math.abs(right - left);
	}
	*/
	
	/*
	//<Trial03>
	
	//왜 아녀??
	
    int rst;
    
    public int findTilt(TreeNode root) {
        if(root == null) return 0;
        int left = findTilt(root.left);
        int right = findTilt(root.right);
        rst += Math.abs(right - left);
        return rst;
    }
    */
	
	/*
    //<Trial04>
    
	//정답에 한발자국 더 다가감.
	
    int rst;
    
    public int findTilt(TreeNode root) {
        if(root == null) return 0;
        int left = findTilt(root.left);
        int right = findTilt(root.right);
        rst += Math.abs(left - right);
        return root.val;
    }
    */
	
	/*
	//<Trial05>
	
	//이건 진짜 아닐수가 없는데 print찍어보면 left,right,rst 다 0으로 찍힘. 왜?
	
    int rst;
    
    private int tilt(TreeNode root){
        if(root == null) return 0;
        int left = findTilt(root.left);
        int right = findTilt(root.right);
        //System.out.println(root.val+"   "+left+"   "+right+"   "+rst);
        rst += Math.abs(left - right);
        return root.val;
    }
    
    public int findTilt(TreeNode root) {
        tilt(root);
        return rst;
    }
    */
	
	/*
	//<Trial06>
	
	//아ㅏㅏㅏㅏㅏㅏㅏㅏㅏ tilt()대신  findTilt() 라고씀. 오타. 근데 그래도 틀림.
	
    int rst;
    
    private int tilt(TreeNode root){
        if(root == null) return 0;
        int left = tilt(root.left);
        int right = tilt(root.right);
        rst += Math.abs(left - right);
        return root.val;
    }
    
    public int findTilt(TreeNode root) {
        tilt(root);
        return rst;
    }
    */
	/*
	//<Trial07>
	
	//[1,2,3,4,null,5]
	
	//4-2-5-3-1
	
//	   1
//	 2   3
//	4   5
	
	//각 노드별로 left,right difference 를 구해서 rst에 더해주는게 밑에 로직인데,
	
	//이 로직은 문제점이, 어떤때는 오른쪽이 더 크고, 어떤때는 왼쪽이 더 큼
	
	//근데 문제에서는 tree의 left child의 총합 - right child의 총합을 빼라고 함.
	
	//그래서 left chlid, right child 따로 더한 후, 맨 마지막에 빼야 할 것 같음.
	
    int rst;
    
    private int tilt(TreeNode root){
        if(root == null) return 0;
        int left = tilt(root.left);
        int right = tilt(root.right);
        rst += Math.abs(left - right);
        System.out.println(root.val+"   "+left+"   "+right+"   "+rst);
        return root.val;
    }
    
    public int findTilt(TreeNode root) {
        tilt(root);
        return rst;
    }
	*/
	
	
	//<문제풀이 by shawngao>
	
	//답은 그리 멀지않은곳에 있었다..
	
	//return left+right+root.val; 의 이유는 문제에서 모든 left subtree와 right subtree의 차이를 반환하라고 했기 때문에, 나중에 쓸 left,right를 넘겨줌.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Tilt.
	//Memory Usage: 39 MB, less than 63.64% of Java online submissions for Binary Tree Tilt.
	
    int rst;
    
    private int tilt(TreeNode root){
        if(root == null) return 0;
        int left = tilt(root.left);
        int right = tilt(root.right);
        rst += Math.abs(left - right);
        return left+right+root.val;
    }
    
    public int findTilt(TreeNode root) {
        tilt(root);
        return rst;
    }
	
}
