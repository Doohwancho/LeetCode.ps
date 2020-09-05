package september;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AllElementsInTwoBinarySearchTrees {

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

	// <문제풀이1>

	// queue
	
	//bst는 inorder로 돌면 가장 작은 숫자부터 오름차순으로 차례대로 나옴.
	
	//root1을 inorder로 돌면서 queue에 차례대로 오름차순으로 넣음
	
	//그리고 root2를 inorder로 차례대로 돌 때, queue의 가장 작은 값과 비교하면서,
	
	//둘중 더 작은값을 list에 넣어줌.
	
	//맨 마지막에 root2를 다 돌아도 q에 찌꺼기가 남아있을 수 있기 때문에
	
	//(q의 값들이 root2의 가장 큰 값보다 더 큰 경우)
	
	//나머지 애들을 넣어줌
	
	//O(n*m)
	
	//---
	
	//찾아보니 merge solution이라고 있네. 근데 걔네들은 root1, root2둘다 queue에 넣고, merge하는 것.
	
	//코드가 더 간결하고 갈끔하나, 문제풀이1이 n과 m중 하나만큼 iterate안해도 되기 때문에 성능이 눈꼽만큼 더 좋음.
	
	//99% runtime distribution
	
	//Runtime: 6 ms
	//Memory Usage: 42.6 MB

	Queue<Integer> q;
	List<Integer> rst;

	private void dfs(TreeNode root) {
		if (root == null)
			return;
		dfs(root.left);
		while (q.size() > 0 && q.peek() < root.val) {
			rst.add(q.poll());
		}
		rst.add(root.val);
		dfs(root.right);
	}

	private void inOrder(TreeNode root) {
		if (root == null)
			return;
		inOrder(root.left);
		q.add(root.val);
		inOrder(root.right);
	}

	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		q = new LinkedList<>();
		rst = new ArrayList<>();
		inOrder(root1);
		dfs(root2);
		while (q.size() > 0) {
			rst.add(q.poll());
		}
		return rst;
	}
	
	
	
	//<문제풀이2>
	
	//PriorityQueue
	
	//성능이 구려졌지만 코드가 간결해짐
	
	//Runtime: 35 ms  - 20.79% time distribution
	//Memory Usage: 42.2 MB
	
    PriorityQueue<Integer> q;

    private void inOrder(TreeNode root){
        if(root == null) return;
        inOrder(root.left);
        q.offer(root.val);
        inOrder(root.right);
    }
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        q = new PriorityQueue<>((a,b)->a-b);
        inOrder(root1);
        inOrder(root2);
        
        List<Integer> rst = new ArrayList<>();
        while(q.size() > 0) {
			rst.add(q.poll());
		}
        
        return rst;
    }
    
    //<문제풀이3>
    
    //root1->root2(bst 규칙에 맞게)
    
    //그리고 root2를 in-order-traversal로 돌면서,
    
    //순서대로 list에 넣어 return.
    
    //성능은 개나 주었지만 이런 방식도 있음.
    
    //O(n*(m+1)) 
    
    //Runtime: 207 ms
    //Memory Usage: 42.4 MB
    
    List<Integer> rst;
    TreeNode root2_static;
    TreeNode root2_prev;
    
    
    private void insert(TreeNode root1, TreeNode prev, int val){
        if(root1 == null) {
            if(val < prev.val){
                prev.left = new TreeNode(val);
            } else {
                prev.right = new TreeNode(val);
            }
            return;
        }
        if(val < root1.val){
            prev = root1;
            insert(root1.left, prev, val);
        } else {
            prev = root1;
            insert(root1.right, prev, val);
        }
    }
    
    private void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.left);
        insert(root2_static, root2_prev, root.val);
        dfs(root.right);
    }

    private void inOrder(TreeNode root){
        if(root == null) return;
        inOrder(root.left);
        rst.add(root.val);
        inOrder(root.right);
    }
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        if(root2 == null){
            rst = new ArrayList<>();
            inOrder(root1);
            return rst;
        } else {
            root2_static = root2;
            root2_prev = root2;
        }
        
        dfs(root1);
        
        rst = new ArrayList<>();
        inOrder(root2);
        return rst;
    }
}
