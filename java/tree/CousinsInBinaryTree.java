package mayChallenge;

import java.util.LinkedList;
import java.util.Queue;

public class CousinsInBinaryTree {

	// <문제풀이1>

	// queue를 이용한 방법. 좀 드릅긴 한데 젤 빠르지 않을까?
	
	// 다 안돌아도 되잖아

	// 103 / 103 test cases passed.
	// Status: Accepted
	// Runtime: 1 ms
	// Memory Usage: 37.1 MB

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public boolean isCousins(TreeNode root, int x, int y) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (q.size() > 0) {
			int size = q.size();
			int x_ = 0;
			int y_ = 0;
			while (size > 0) {
				TreeNode tmp = q.poll();
				if (tmp.left != null) {
					q.add(tmp.left);
					if (tmp.left.val == x) {
						if (tmp.right != null && tmp.right.val == y) {
							return false;
						}
						x_ = x;
					} else if (tmp.left.val == y) {
						if (tmp.right != null && tmp.right.val == x) {
							return false;
						}
						y_ = y;
					}
				}
				if (tmp.right != null) {
					q.add(tmp.right);
					if (tmp.right.val == x) {
						if (tmp.left != null && tmp.right.val == y) {
							return false;
						}
						x_ = x;
					} else if (tmp.right.val == y) {
						if (tmp.left != null && tmp.right.val == x) {
							return false;
						}
						y_ = y;
					}
				}
				if (x_ == x && y_ == y) {
					return true;
				}
				size--;
			}
		}
		return false;
	}
	
	
	//<문제풀이2 by migfulcrum>
	
	//recursive
	
	//오씨 이게 더 빠르네
	
	//xP != yP; 이 부분이 똑똑했음. 그리고 return문에서 or처리한것.
	
	//103 / 103 test cases passed.
	//Status: Accepted
	//Runtime: 0 ms
	//Memory Usage: 37.2 MB
	
    Integer xDepth;
    Integer yDepth;
    int xP = 0;
    int yP = 0;
    
    public boolean dfs(TreeNode root, int x, int y, int depth, int parentVal) {
        if(root==null) return false;
        if(root.val==x){
            xP = parentVal;
            xDepth = depth;
        }
        else if(root.val==y){
            yP=parentVal;    
            yDepth = depth;
        }
        if(xDepth != null && yDepth != null){
            return xDepth == yDepth && xP != yP;
        }
        
        return dfs(root.left, x, y, depth+1, root.val) || dfs(root.right, x, y, depth+1, root.val);
	}
    
    public boolean isCousins(TreeNode root, int x, int y) {
        return dfs(root, x, y, 0, root.val);
    }
}
