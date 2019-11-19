package DepthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SymmetricTree101 {

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
	
	//root의 leftchild와 rightchild를 따로 dfs(pre order traversal)하는데, <https://doohwancho.tistory.com/category/Problem%20Solving/Data%20Structure%20%26%20Algorithm>
	
	//leftchild dfs시 원래는 left child넣고 right child넣는데, 좌우 대칭이니까 right child먼저 넣고 left child넣음.
	 
	//생각보다 느리네. 
	
	//Runtime: 2 ms, faster than 5.40% of Java online submissions for Symmetric Tree.
	//Memory Usage: 38.2 MB, less than 69.39% of Java online submissions for Symmetric Tree.

	Stack<Integer> st1 = new Stack<>();
	Stack<Integer> st2 = new Stack<>();

	private void dfsLeft(TreeNode root) {
		if (root == null) {
			st1.add(-1);
			return;
		}
		st1.add(root.val);
		dfsLeft(root.right);
		dfsLeft(root.left);
		return;
	}

	private void dfsRight(TreeNode root) { 
		if (root == null) {
			st2.add(-1);
			return;
		}
		st2.add(root.val);
		dfsRight(root.left);
		dfsRight(root.right);
		return;
	}

	public boolean isSymmetric(TreeNode root) {
		if (root == null || (root.left == null && root.right == null))
			return true;
		else if (root.left == null || root.right == null)
			return false;
		dfsLeft(root.left);
		dfsRight(root.right);
		while (!st1.isEmpty()) {
			if (st1.pop() != st2.pop())
				return false;
		}
		return true;
	}
	*/
	
	/*
	//<문제풀이2>
	
	//다시 짰는데 1ms 더 빨라짐. 근데 코드가 안이쁨. ㅜㅜ
	
	//Runtime: 1 ms, faster than 45.77% of Java online submissions for Symmetric Tree.
	//Memory Usage: 38 MB, less than 72.11% of Java online submissions for Symmetric Tree.
	
	public boolean isSymmetric(TreeNode root) {
		//stack대신 queue씀.
		
		//부모root 넣고, 그걸 뺀 후, 그 부모의 left child, right child를 가장 윗쪽에 넣는데, 
		//stack을 쓰면, .pop()시 그 다음부모를 빼와야 하는데 방금넣은 자식을 빼오게 되서 stack대신 queue씀. 
		//아따 설명어렵네
        Queue<TreeNode> queue = new LinkedList<>(); 
        queue.add(root);
        while(!queue.isEmpty()){
            int cnt = queue.size();			   //하나의 queue에 부모+자식 다들어가기 때문에, 부모의 갯수만 뽑아서 카운트 해줘야 함
            int[] compare = new int[cnt*2];    //자식을 담아 비교할 int[]생성. 길이를 queue.size() * 2한 이유는, 자식 노드.val을 넣을 때, 자식노드가 없더라도(null) -1을 넣기 때문에 left,right 둘다필요. 따라서 노드의 갯수 * left,right child 해서 곱하기 2
            int index = 0;
            while(cnt-- > 0){
                TreeNode node = queue.poll();  //하나의 queue에 부모+자식 다들어가기 때문에, 부모의 갯수만큼만 .poll()로 뽑음
                if(node != null){              
                    if(node.left == null){     //자식이 null이면 -1을 넣음.
                        compare[index++] = -1;
                        queue.add(null);
                    } 
                    else if(node.left != null){ //자식이 null이 아니면 해당 자식의 값을 넣음
                        compare[index++] = node.left.val;
                        queue.add(node.left);
                    } 
                    if(node.right == null){
                        compare[index++] = -1;
                        queue.add(null);    
                    } 
                    else if(node.right != null){
                        compare[index++] = node.right.val;
                        queue.add(node.right);    
                    } 
                }
            }
            int length = queue.size();
            for(int i = 0, j = length-1; i < (length/2)+1 && j > (length/2)-1; i++, j--){ //two-pointer 방법으로 값 비교 
                if(compare[i] != compare[j]) return false;
            }
        }
        return true;
    }
    */
	
	//<문제풀이3 by jeantimex> 
	
	//아름다운 코드네 히히
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Symmetric Tree.
	//Memory Usage: 39.1 MB, less than 38.09% of Java online submissions for Symmetric Tree.
	
    public boolean isSymmetric(TreeNode root) {
        if (root == null) 
            return true;
        
        return isSymmetric(root.left, root.right);
    }
    
    boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) 
            return true;

        if (left == null || right == null) 
            return false;

        if (left.val != right.val) 
            return false;

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}




