/*
	There are some chips, and the i-th chip is at position chips[i].
	
	You can perform any of the two following types of moves any number of times (possibly zero) on any chip:
	
	Move the i-th chip by 2 units to the left or to the right with a cost of 0.
	Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
	There can be two or more chips at the same position initially.
	
	Return the minimum cost needed to move all the chips to the same position (any position).
	
	 
	
	Example 1:
	
	Input: chips = [1,2,3]
	Output: 1
	Explanation: Second chip will be moved to positon 3 with cost 1. First chip will be moved to position 3 with cost 0. Total cost is 1.
	Example 2:
	
	Input: chips = [2,2,2,3,3]
	Output: 2
	Explanation: Both fourth and fifth chip will be moved to position two with cost 1. Total minimum cost will be 2.
	
	Constraints:
	
	1 <= chips.length <= 100
	1 <= chips[i] <= 10^9
	
	
	
	<문제>
	
	문제 워딩이 그지같아서 다른 사람이 설명해준것을 봐서 겨우 이해할 수 있었다.
	
	chips = [1,2,2,2,2,3,3]
	
	이렇게 주어진다.
	
	여기서 숫자는 포지션을 의미한다. 그림으로 표현하면,
	
	
	position 1 position 2	position 3
	chip 1     chip 2	    chip 6
			   chip 3	    chip 7
			   chip 4
			   chip 5	
	
	이다.
	
	우리는 모든 칩들을 한 포지션에 몰아주기하고 싶다.
	
	chip들의 포지션을 옮길 때, 두칸씩 띄어서 옮기면 cost가 안들지만,(ex. 2->4->6...->12이나, 1->3->5->7 은 cost 안듬)
	
	한칸씩 옮기면 cost가 1든다.
	
	모든 칩들을 한 포지션에 옮길 때 드는 최소cost를 반환하라.
	
 */

package Greedy;

public class PlayWithChips1217 {
	
	//<문제풀이1>
	
	//모든 홀수 position의 있는 수를 cost없이 몰수있고, 같은 방식으로 모든 짝수 position의 chip도 cost없이 모을 수 있다.
	
	//결국, 짝수따로, 홀수따로 모았을 때, 더 적은 부분의 양만 많은쪽으로 몰빵하면 되기 때문에,
	
	//짝수 홀수 빈도수 센것 중, 더 적은것을 반환하면 된다.
	
	//Runtime: 0 ms, faster than 100.00% of Java online submissions for Play with Chips.
	//Memory Usage: 34.7 MB, less than 100.00% of Java online submissions for Play with Chips.

	public static int minCostToMoveChips(int[] chips) {
		int odd = 0;
		int even = 0;
		
		for(int i : chips) {
			if(i%2==0) even++;
			else odd++;
		}
		
		return even > odd ? odd : even;
    }
	
	public static void main(String[] args) {
		int[] chips = {2,2,2,3,3};
		System.out.println(minCostToMoveChips(chips));
	}
}
