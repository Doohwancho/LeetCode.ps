/*
	You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
	
	Given n, find the total number of full staircase rows that can be formed.
	
	n is a non-negative integer and fits within the range of a 32-bit signed integer.
	
	Example 1:
	
	n = 5
	
	The coins can form the following rows:
	¤
	¤ ¤
	¤ ¤
	
	Because the 3rd row is incomplete, we return 2.
	Example 2:
	
	n = 8
	
	The coins can form the following rows:
	¤
	¤ ¤
	¤ ¤ ¤
	¤ ¤
	
	Because the 4th row is incomplete, we return 3.
	
	
	
	
	<문제>
	
	숫자 n이 만약 8이라고 주어지면, 8개의 블록으로 층수마다 +1씩 증가되는 탑을 쌓는다.
	
	¤
	¤ ¤
	¤ ¤ ¤
	¤ ¤
	
	요렇게.
	
	근데 잘 보면 3층까지는 잘 쌓였는데, 4층에선 블록 2개가 부족해서 마무리를 못하였다.
	
	여기서 완성된 층수의 갯수만 반환하라.
	
 */

package BinarySearch;



public class ArrangingCoins441 {
	
	//<문제풀이>
	
	//1부터 n까지의 합을 구하는 공식은 (n*(n+1))/2 이다.

	//이걸 binary-search와 섞으면 된다.
	
	//Runtime: 1 ms, faster than 100.00% of Java online submissions for Arranging Coins.
	//Memory Usage: 33.5 MB, less than 5.26% of Java online submissions for Arranging Coins.
	public static int arrangeCoins(int n) {
        if(n == 1 || n == 0) return n;
        long l = 0, r = n, m = 0;
		while(l < r) {
			m = l + (r-l)/2; //n이 Integer.MAX_VALUE일때를 대비한 공식으로, 값은 (l+r)/2와 같다.
			long sum = (m*(m+1))/2; //1부터 n까지의 합 공식. int말고 long타입으로 한 이유는 leetcode가 짓궂은 input을 넣기 때문.
			if(sum > n) r = m;
			else if(sum < n) l = m + 1;
			else return (int)m; //만약 1부터 ?까지의 수의 합이 m과 딱 맞아떨어지면, 해당층 반환.
		}
		return (int)l-1; //딱 맞아떨어지지 않는다면, 해당층은 미완성이라는 말이니까 해당층-1반환.
    }
	
	public static void main(String[] args) {
		int n = 14;
		System.out.println(arrangeCoins(n));
	}
}
