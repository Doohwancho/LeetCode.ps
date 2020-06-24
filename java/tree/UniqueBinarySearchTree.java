package juneChallenge;

public class UniqueBinarySearchTree {

	//<문제풀이1 by mo10>
	
	//이건 보고 답도 없어서 풀이봄.
	
	/*
	Then assume we have the number of the first 4 trees: dp[1] = 1 ,dp[2] =2 ,dp[3] = 5, dp[4] =14 , how do we get dp[5] based on these four numbers is the core problem here.
	
	The essential process is: to build a tree, we need to pick a root node, then we need to know how many possible left sub trees and right sub trees can be held under that node, finally multiply them.
	
	To build a tree contains {1,2,3,4,5}. First we pick 1 as root, for the left sub tree, there are none; for the right sub tree, we need count how many possible trees are there constructed from {2,3,4,5}, 
	
	apparently it's the same number as {1,2,3,4}. So the total number of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0] =1). 
	
	Similarly, root 2 has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has dp[0]*dp[4] = 14. 
	
	Finally sum the up and it's done.
	 */
	
	//그니까 이걸 번역하면
	
	//dp[1] = 1 ,dp[2] =2 ,dp[3] = 5, dp[4] =14 이거까진 손으로 얼추 가능한데, 다음을 어떻게 아느냐가 문제인 거잖어?
	
	//dp[5]를 보면, 1이 root일때, 나머지 2,3,4,5들은 binary tree니까 다 오른쪽으로 오겠지?
	
	//그랬을때, 2,3,4,5를 새로운 1,2,3,4라고 생각하고 경우의 수를 보면, dp[4]인 14일꺼 아녀?
	
	//이 오른쪽에 나올 수 있는 경우의 수랑, 왼쪽에 나올 수 있는 경우의 수랑 곱하래.
	
	//그래서 dp[5]일때 왼쪽 1개 * 오른쪽 14개 해서 14개고,
	
	//dp[5]일때 root가 2라면, 왼쪽엔 1이 오겠고, 오른쪽엔 3,4,5가 오니까, dp[1] * dp[3]해서 5가 되겠고,
	
	//dp[5]일때 root가 3이라면, 왼쪽엔 1,2가 오고 오른쪽엔 4,5가 오니까, dp[2] * dp[2]해서 4가 오고
	
	//이런식으로 root가 1,2,3,4,5일때 14+5+4+5+14해서 42 이런식인겨
	
	//거참 신기허네
	
	//Runtime: 0 ms 
	//Memory Usage: 35.8 MB
	
	 public int numTrees(int n) {
		int [] dp = new int[n+1];
	    dp[0]= 1;
	    dp[1] = 1;
	    for(int level = 2; level <=n; level++)
	        for(int root = 1; root<=level; root++)
	            dp[level] += dp[level-root]*dp[root-1];
	    return dp[n];
	}
}
