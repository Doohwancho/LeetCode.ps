/*
	Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
	
	Assume a BST is defined as follows:
	
	The left subtree of a node contains only nodes with keys less than or equal to the node's key.
	The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
	Both the left and right subtrees must also be binary search trees.
	 
	
	For example:
	Given BST [1,null,2,2],
	
	   1
	    \
	     2
	    /
	   2
	 
	
	return [2].
	
	
	
	<문제>
	
	bst에서, 최빈도수의 값들을 int[]에 담아 반환하라.
 */

package Tree;

import java.util.LinkedList;
import java.util.List;

public class FindModeinBinarySearchTree501 {

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
	
	//map(dictionary)를 만들어 dfs돌면서 각 숫자의 빈도수를 파악, 가장 많은 빈도수의 단어를 뽑아 어레이에 담고 int[]로 형변환 해 반환하는 방법.
	
	//비효율. 구식. 간지가 안남.
	
	//dfs로 모든 노드를 한번 돌고, 돈거에서 빈도수 최대로 뽑으려고 for문 한번 더 돌고, 최빈값 뽑으려고 for문 한번 더 돌고 화룡점정으로 형변환까지 해주기 때문.
	
	//Runtime: 7 ms, faster than 34.36% of Java online submissions for Find Mode in Binary Search Tree.
	//Memory Usage: 38.8 MB, less than 71.43% of Java online submissions for Find Mode in Binary Search Tree.

	Map<Integer, Integer> map = new HashMap<>();

	private void dfs(TreeNode root) {
		if (root == null)
			return;
		map.put(root.val, map.getOrDefault(root.val, 0) + 1);
		dfs(root.left);
		dfs(root.right);
	}

	public int[] findMode(TreeNode root) {
        if(root == null) return new int[0];
        dfs(root);
        
        List<Integer> list = new ArrayList<>();
        int maxFreq = 0;
        for(Integer key : map.keySet() ){
            maxFreq = Math.max(maxFreq, map.get(key)); 
        }
        for(Integer key : map.keySet() ){
            if(map.get(key) == maxFreq) list.add(key);
        }
        int[] ret = new int[list.size()];
        for (int i=0; i < ret.length; i++){
            ret[i] = list.get(i).intValue();
        }
        return ret;
    }
	*/
	
	/*
	//<문제풀이2 by prateek470>
	
	//이건 in-order로 모든 노드를 한번에 돌긴 하지만, 최빈도 얻으려고 for문 돌리거나, 최빈도의 값을 뽑으려고 for문을 또돌리는 불상사는 없음.
	
	//6ms빨라짐.
	
	//Runtime: 1 ms, faster than 95.14% of Java online submissions for Find Mode in Binary Search Tree.
	//Memory Usage: 36.7 MB, less than 100.00% of Java online submissions for Find Mode in Binary Search Tree.
	
	void helper(TreeNode root, int[] var, List<Integer> result){
        if(root == null) return;
        helper(root.left,var,result);
        var[1] = root.val==var[2] ? var[1]+1 : 1;
        if(var[1] >= var[0]){
            if(var[1] > var[0]) result.clear();
            var[0] = var[1];
            if(result.size()==0 || result.get(result.size()-1)!=root.val){
                result.add(root.val);
            }
        }
        var[2] = root.val;
        helper(root.right,var,result);
    }
    //without extra space
    public int[] findMode(TreeNode root) {
        List<Integer> temp = new LinkedList<>();
        int[] var = new int[3]; // var[0] = max, var[1] = curr_max, var[2] = prev
        helper(root,var,temp);
        
        int[] result = new int[temp.size()];
        for(int i=0;i<result.length;i++) result[i] = temp.get(i);
        return result;
    }
    */
	
	//<문제풀이3>
	
	//문제풀이2와 로직은 같은데, if문 안에 비 효율적인 부분이 보여 조금 리팩토링 한 것.
	
	//if(result.size()==0 || result.get(result.size()-1)!=root.val){
    //    result.add(root.val);
    //}
	
	//이 부분을 버리고 else if문 추가. 지저분하자너.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Mode in Binary Search Tree.
	//Memory Usage: 38 MB, less than 92.86% of Java online submissions for Find Mode in Binary Search Tree.
	
	private void dfs(TreeNode root, int[] var, List<Integer> list){
        if(root == null) return;
        dfs(root.left, var, list);
        
        //in-order-traversal
        var[1] = var[2] == root.val ? var[1]+1 : 1; //빈도수 체크. root.val이 기존값(var[2])와 같다면, 기존값의 빈도수(var[1])+1을 해주고, 첨나온거면 빈도수 1 고정.
        var[2] = root.val;
        if(var[1] > var[0]){ //현재 root.val의 빈도수(var[1])이 최빈도수(var[0])보다 더 크면, 여태껏 최빈도수를 담았던 list를 초기화 하고, root.val을 담음.
            var[0] = var[1];
            list.clear();
            list.add(root.val);
        }
        else if(var[1] == var[0]){ //만약 현재 root.val의 빈도수가 최빈값이라면, 문제에서 최빈도수의 값을 모두 반환하라고 했으니까, 리스트에 담아줌.
            list.add(root.val);
        }
        
        dfs(root.right, var, list);
    }

    public int[] findMode(TreeNode root) {
        if(root == null) return new int[0];
        
        List<Integer> list = new LinkedList<>();
        int[] var = new int[3]; //0:maxFreq 1:currFreq 2:prev
        
        dfs(root, var, list);
        //형변환
        int[] ret = new int[list.size()];
        for (int i=0; i < ret.length; i++){
            ret[i] = list.get(i).intValue();
        }
        return ret;
    }
}
