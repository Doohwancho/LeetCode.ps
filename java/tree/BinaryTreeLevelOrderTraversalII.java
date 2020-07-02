package julyChallenge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversalII {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	
	//<문제풀이1>
	
	//bfs - level order traversal
	
	//Runtime: 2 ms
	//Memory Usage: 40.7 MB
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //유효성 검사
        if(root == null) return new ArrayList<>();
        
        List<List<Integer>> rst = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            int size = q.size(); //이게 한 층 전체의 숫자임
            while(size > 0){ //해당 층 전체 노드를 돌면서
                TreeNode node = q.poll(); //
                if(node.left != null){ //left child, right child을 같은 층(temp)에 담아주고
                    q.add(node.left);
                    temp.add(node.left.val);
                }

                if(node.right != null){
                    q.add(node.right);
                    temp.add(node.right.val);
                }                
                size--;
            }
            if(temp.size() > 0) rst.add(0, new ArrayList<>(temp)); //해당 층 애들이 다 담겼으면 rst에 역순으로 담아야 하니까, 첫번째에 박아둠.
        }
        
        List<Integer> tmp = new ArrayList<>();
        tmp.add(root.val);
        rst.add(tmp);
        
        return rst;
    }
}
