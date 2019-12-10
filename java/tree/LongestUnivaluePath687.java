/*
	Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
	
	The length of path between two nodes is represented by the number of edges between them.
	
	 
	
	Example 1:
	
	Input:
	
	              5
	             / \
	            4   5
	           / \   \
	          1   1   5
	Output: 2
	
	 
	
	Example 2:
	
	Input:
	
	              1
	             / \
	            4   5
	           / \   \
	          4   4   5
	Output: 2
	
	
	
	
	
	<문제>
	
	노드들이 같은값으로 최대 몇번 이어졌는지 반환하라.
	
 */

package Tree;

public class LongestUnivaluePath687 {

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
	
	//Input : [1,null,1,1,1,1,1,1]
	//Output : 6
	//Expected : 4
	
//	  1
//	    1
//	   1  1
//	     1  1
//            1	
	
	//트리가 이따구로 생겼을 경우, 최대로 긴게 4번 이어지는데, 이어진 1의 수(7)-1을 반환해서 에러.

	private void dfs(TreeNode root, int[] var) {
		if (root == null) return;
		dfs(root.left, var);
		var[1] = root.val == var[2] ? var[1] + 1 : 1; //root.val 이 previous value와 같다면, 빈도수+1	
		var[2] = root.val;
		if (var[1] > var[0]) var[0] = var[1]; //root.val의 빈도수가 최빈값 보다 더 크다면, reset
		dfs(root.right, var);
	}

	public int longestUnivaluePath(TreeNode root) {
		if (root == null) return 0;
		int[] var = new int[3]; // maxFreq, currFreq, prevValue
		dfs(root, var);
		return var[0] - 1;
	}
	*/
	
	
	//<문제풀이1>
	
	//Input : [1,null,1,1,1,1,1,1]
	//Expected : 4
	
//		 1
//	   n   1
//		 1   1
//		1 1 1
	
	//만약 트리가 위 같이 생겼으면, node(3)에서 left child 기준 최빈으로 이어진 노드의 값 2와, right child 기준 최빈으로 이어진 노드의 값 2가 합쳐 4를 반환해야함.
	
	//그러기 위해선, 매 노드마다 이어졌으면, left or right child -> parent 갈 때, 여태껏 이어진 횟수 +1을 해야 하는데,
	
	//여태껏 이어진 횟수는 파라미터 compare와 if문에 root.val과의 비교를 통해 확인 할 수 있음.
	
	//여태껏 이어진 횟수 +1을 반환시 주의할 점이, 만약 left child와 right child에 같은값이 주렁주렁 열려서, 이어진 횟수가 left = 32, right = 21이라면,
	
	//더 큰 값인 left을 반환해야 하기 때문에, return문에 3항연산자로 left, right중 더 큰값을 반환하도록 함.
	
	//만약 root.val != compare이라면, 새로운 값이 나와 이전 노드와 이어진게 아니므로, 다시 0에서부터 시작. 
	 
	//Runtime: 3 ms, faster than 99.39% of Java online submissions for Longest Univalue Path.
	//Memory Usage: 41.4 MB, less than 100.00% of Java online submissions for Longest Univalue Path.
	
	int maxLen = 0;
    
    private int dfs(TreeNode root, int compare){
        if(root == null) return 0;
        int left = dfs(root.left, root.val);
        int right = dfs(root.right, root.val);
        maxLen = Math.max(maxLen,left+right);
        if(root.val == compare) return left > right ? left+1 : right+1;
        else return 0;
    }
    
    public int longestUnivaluePath(TreeNode root) {
        if(root == null) return 0;
        dfs(root, 0); 
        return maxLen;
    }
}
