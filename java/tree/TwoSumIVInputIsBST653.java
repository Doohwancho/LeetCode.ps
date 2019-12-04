package Tree;

import java.util.ArrayList;
import java.util.List;

public class TwoSumIVInputIsBST653 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	/*
	//<문제풀이01>
	
	//트리에서 두 수를 더해서 k가 되면 된다.
	
	//그러면 각 노드를 돌 때마다, k-노드의 값을 구하여 set에 저장해 놓으면,
	
	//다음 노드들을 돌 때, 아까 저장해 놓은 값이 set에 있기만 한다면 두 숫자의 합이 k라는 말.
	
	//간결하고 간지나는데 성능은 별로다. ㅜㅜ
	
	//모든 노드를 다 돌아서 그런걸까?

	//Runtime: 5 ms, faster than 30.62% of Java online submissions for Two Sum IV - Input is a BST.
	//Memory Usage: 42.5 MB, less than 67.86% of Java online submissions for Two Sum IV - Input is a BST.

	Set<Integer> set = new HashSet<>();

	public boolean findTarget(TreeNode root, int k) {
		if (root == null) return false;
		if (set.contains(root.val)) return true;
		set.add(k - root.val);
		return findTarget(root.right, k) || findTarget(root.left, k);
	}
	*/
	
	//<문제풀이 by shawngao>
	
	//그냥 먼저 쿨하게 한번 싹 돌면서 트리의 모든 값을 list에 넣고,
	
	//binary search tree를 in order 방식으로 넣었으니, list의 값들이 오름차순으로 정렬되어있음.(bst가 가장 작은숫자가 맨 왼쪽, 오른쪽으로 갈수록 숫자가 점점커짐)
	
	//in order방식이 어떤순서로 흘러가는지는 다음 링크 참조
	
	//https://doohwancho.tistory.com/534?category=1042623
	
	//그리고 k를 구하는데, 양끝단(가장 왼쪽 작은숫자 + 가장 오른쪽 큰숫자)의 합과 k를 비교하여,
	
	//k보다 작으면 가장 왼쪽에 작은숫자를 더 큰숫자로 바꿔줘야 하니까 한칸 오른쪽으로 땡기고,
	
	//k보다 크면 가장 오른쪽에있는 잴큰숫자를 더 작은숫자로 바꿔줘야 하니까 한칸 왼쪽으로 땡김.
	
	//two-pointer방식이라고 보면 됨.
	
	//Runtime: 2 ms, faster than 95.48% of Java online submissions for Two Sum IV - Input is a BST.
	//Memory Usage: 39.1 MB, less than 87.50% of Java online submissions for Two Sum IV - Input is a BST.
	
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        
        int i = 0, j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            if (sum == k) return true;
            if (sum < k) {
                i++;
            }
            else {
                j--;
            }
        }
        
        return false;
    }
    
    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}
